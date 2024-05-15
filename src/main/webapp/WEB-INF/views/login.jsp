<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz-app</title>
<style><%@include file="home.css"%></style>
</head>
<body>
	<div class="container">
	
		<div class="header"><p>Quizzinga</p></div>
	
		<div class="form-container login-form">
		<form action="login" method="post"> 
				<div class="input-box">
				<label>Username : </label>
				<input type="text" name="username"/><br/> 
				</div>
				<div class="input-box">
				<label>Password : </label>
				<input type="password" name="password" /><br/>
				</div> 
				<div class="submit-regis">
					<input type="submit" value="Login" class="submit"/> 
					<span class="new">
					 New User: 
					<a href="/register">Register</a>
					</span>
				</div> 
			</form>
		</div>
	</div>
</body>
</html>