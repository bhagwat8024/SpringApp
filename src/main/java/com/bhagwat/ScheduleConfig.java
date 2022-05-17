package com.bhagwat;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.bhagwat.model.Product;
import com.bhagwat.service.ProductService;
import com.opencsv.CSVReader;

@Configuration
@EnableScheduling
public class ScheduleConfig {

	@Autowired
	private ProductService productService;
	// FOLDER PATH where all csv file

	private static final String FOLDER_PATH = "D:\\Java Assignments\\HibernateProject\\src\\main\\resources";
	private static final File folder = new File(FOLDER_PATH);

	// Get the list of name of files in a folder
	public List<String> filesFromFolder(final File folder) {

		List<String> filesName = new ArrayList<String>();

		for (File file : folder.listFiles()) {
			if (file.getName().contains(".csv")) {
				filesName.add(file.getName());
			}
		}
		return filesName;
	}

	@Transactional
	@Scheduled(fixedDelay = 600000)
	public void scheduleCsvLoader() {
		//Delete all existing Product list
		productService.deleteAllProducts();
		
		List<String> filesName = filesFromFolder(folder);

		// FileReader for read file
		FileReader fileReader;

		// CSVReader is class of OpenCSV framework ,which help to read csv files
		CSVReader csvReader;

		// if there is no files in folder
		if (filesName.size() == 0) {
			System.out.println("No Files Found");
			return;
		}
		List<Product> list = new ArrayList<>();
		// Read csv file and store in a list of TShirt
		for (String file : filesName) {
			String filePath = FOLDER_PATH + "\\" + file;

			try {
				fileReader = new FileReader(filePath);

				csvReader = new CSVReader(fileReader);

				String[] nextRecord = csvReader.readNext();

				while ((nextRecord = csvReader.readNext()) != null) {

					String[] data = nextRecord[0].split("\\|");
					Product product = new Product(data[0], data[1], data[2], data[3], data[4], data[7],
							Double.parseDouble(data[5]), Double.parseDouble(data[6]));
					list.add(product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productService.insertProduct(list);
	}

}
