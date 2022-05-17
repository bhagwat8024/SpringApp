<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="org.hibernate.*,org.hibernate.cfg.*,com.bhagwat.model.Product,java.util.*,javax.servlet.http.*"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="main.css" rel="stylesheet">
<title>Product Management Tool</title>
</head>
<body>
	<%
	// Check if user direct come without login
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	<!-- Navbar -->
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light container-fluid"">
		<!-- Container wrapper -->
		<div class="container">
				<p class="navbar-nav me-auto mb-2 mb-lg-0">Product Management
					Tool</p>

				<div class="d-flex align-items-center">
					<button class="btn btn-primary px-3 me-2">Hello
						${username}</button>
					<form method="get" action="LogoutModule">
						<button type="submit" class="btn btn-primary px-3 me-2">
							Logout</button>
					</form>
			</div>
		</div>
	</nav>
	<div class="main container-fluid">
		<div class="row">
			<div class="col-xs-12 col-md-6">

				<form id="inputForm" class="form" action="SearchModule" method="get">

					<div class="row mb-3">
						<label for="color" class="col-12 col-sm-2 col-form-label">Color</label>
						<div class="col-12 col-sm-10">
							<input type="text" class="form-control" name="color" id="color"
								maxlength="20" required>
						</div>
					</div>
					<div class="row mb-3">
						<label for="size" class="col-12 col-sm-2 col-form-label">Size</label>
						<div class="col-12 col-sm-10">
							<input type="text" class="form-control" name="size" id="size"
								required>
						</div>
					</div>

					<fieldset class="row mb-3">
						<legend class="col-form-label col-12 col-sm-2 pt-0">Gender</legend>
						<div class="col-12 col-sm-10">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender"
									id="genderRadioMale" value="M" checked> <label
									class="form-check-label" for="gende"> Male </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="gender"
									id="genderRadioFemale" value="F"> <label
									class="form-check-label" for="gender"> Female </label>
							</div>
						</div>
					</fieldset>

					<fieldset class="row mb-3">
						<legend class="col-form-label col-12 col-sm-2 pt-0">Output
							Preference</legend>
						<div class="col-12 col-sm-10">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="output"
									id="outputPrice" value="Price" checked> <label
									class="form-check-label" for="output"> Price </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="output"
									id="outputrating" value="Rating"> <label
									class="form-check-label" for="output"> Rating </label>
							</div>
						</div>
					</fieldset>

					<!--Submit Button-->
					<button type="submit" id="submit" class="btn btn-primary">Submit</button>
				</form>
				<!--End of Form-->
			</div>
			<div class="col-xs-0 col-md-6"></div>
		</div>
		
		<c:if test="${productList!=null}">
		<div class="row">
			<blockquote class="blockquote">
				<p class="mb-0">Total Product is :- "${productList.size()}"</p>
			</blockquote>
		</div>
		</c:if>
		<!-- Table -->
		<div class="row">
			<div class="col-12 table_div mr-8">
				<table class="table table-bordered border-cprimary">
					<thead>
						<tr class="table-success text-start text-dark">
							<th scope="col" class="col-1">S.No.</th>
							<th scope="col" class="col-3">Name</th>
							<th scope="col" class="col-2">Color</th>
							<th scope="col" class="col-2">Price</th>
							<th scope="col" class="col-3">Rating</th>
							<th scope="col" class="col-1">Available</th>
						</tr>
					</thead>
					<tbody id="tableBody" class="text-start">
						<c:forEach var="product" items="${productList}"
							varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<td><c:out value="${product.name}" /></td>
								<td><c:out value="${product.color}" /></td>
								<td><c:out value="${product.price}" /></td>
								<td><c:out value="${product.rating}" /></td>
								<td><c:out value="${product.avail}" /></td>
							</tr>
						</c:forEach>
					</tbody>
					</div>
					</div>
					</div>
</body>
</html>