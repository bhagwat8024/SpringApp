package com.bhagwat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.ServerResponse.Context;

import com.bhagwat.ScheduleConfig;
import com.bhagwat.model.Product;
import com.bhagwat.model.User;
import com.bhagwat.service.ProductService;
import com.bhagwat.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	// Time Delay Constant for delay in CSV Loading Thread work
	public static final int TIME_DELAY = 2000;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private ScheduleConfig scheduler;

	@GetMapping("/LoginModule")
	public void loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isUserExist = userService.isUserExist(username, password);
		if (isUserExist) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("username", username);

			scheduler.scheduleCsvLoader();

			response.sendRedirect("main");
		} else {
			response.sendRedirect("login");
		}

	}

	@GetMapping("/SignUpModule")
	public void signupUser(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		userService.saveUser(new User(username, password));
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("username", username);
		response.sendRedirect("main");
	}

	@GetMapping("/SearchModule")
	public void searchProduct(@RequestParam("color") String color, @RequestParam("size") String size,
			@RequestParam("gender") String gender, @RequestParam("output") String output, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Product> list;
		System.out.println(color + size + gender + output);
		if (output.equals("Price")) {
			list = productService.getProductsByPrice(color, size, gender);
		} else {
			list = productService.getProductsByRating(color, size, gender);
		}

		request.setAttribute("productList", list);
		request.getRequestDispatcher("main").forward(request, response);
		return;
	}

	@GetMapping("/LogoutModule")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		System.out.println(listSchedules());
		postProcessor.postProcessBeforeDestruction(scheduler, "ScheduleConfig");
		System.out.println(listSchedules());
		session.invalidate();
		response.sendRedirect("login");
	}
	
	public String listSchedules(){
	    Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
	    if(!setTasks.isEmpty()){
	        return "YES";
	    }else{
	        return "No running tasks !";
	    }
	 }

	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
