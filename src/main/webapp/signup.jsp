<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="login.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title>SignUp Page</title>
</head>
<body>
	<main class="container-fluid">
		<div class="login_container container">
			<form class="border login_form" action="SignUpModule" method="get">
				<div class="row mb-3">
					<label for="username" class="col-12 col-sm-2 col-form-label">UserName</label>
					<div class="col-12 col-sm-10">
						<input type="text" class="form-control" name="username"
							id="username" maxlength="20" required>
					</div>
				</div>

				<div class="row mb-3">
					<label for="password" class="col-12 col-sm-2 col-form-label">Password</label>
					<div class="col-12 col-sm-10">
						<input type="password" class="form-control" name="password"
							id="password" maxlength="30" required>
					</div>
				</div>

				<button type="submit" class="btn btn-primary btn-block mb-4">Sign
					Up</button>
			</form>
		</div>
	</main>
</body>
</html>