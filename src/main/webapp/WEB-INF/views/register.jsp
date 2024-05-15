<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New User</title>
<style><%@include file="home.css"%></style>
</head>
<body>
<div class="container">

	<div class="header"><p>Quizzinga</p></div>
	
	<div class="form-container register-form">
	
		<form action="register-process" method="post"> 
			<div class="input-box">
			<label>Username : </label>
			<input type="text" name="name"/><br/>
			</div> 
			<div class="input-box">
			<label>Password : </label>
			<input type="password" name="password" /><br/> 
			</div>
			<input type="submit" value="Register" class="submit"/>  
		</form> 
		
	</div>
</div>
</body>
</html>