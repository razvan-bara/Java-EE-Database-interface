<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css?ver=<%= System.currentTimeMillis() %>" />
<script
	src="${pageContext.request.contextPath}/js/script.js?ver=<%= System.currentTimeMillis() %>"
	defer></script>
</head>
<body>
    <div class="notification error">
    	<c:out value="${sessionScope.error}" />
		<c:remove var="error" scope="session" />
    </div>
    <div class="notification error">
    	${ error }
    </div>
    <div class="notification success">
    	<c:out value="${sessionScope.success}" />
		<c:remove var="success" scope="session" />
    </div>
	<div class="auth-wrapper center inv-color">
		<div class="auth-container center flex-column shadow-depth normal-colors basic-radius">
			<div class="auth-header">
				<h1>Inregistreaza-te</h1>
				<h5>Deja ai cont? Fa-ti login-ul <a class="expanding-link" href="/login">aici</a></h5>
			</div>
			<form class="auth-form" action="/register" method="POST" >
				<fieldset class="field-group" >
					<input class="inv-color" type="text" name="full_name" placeholder="Nume complet" required />
				</fieldset>
				<fieldset class="field-group" >
					<input class="inv-color" type="email" name="email" placeholder="Email" required />
				</fieldset>
				<fieldset class="field-group inv-color">
					<input class="inv-color" type="password" name="password" placeholder="Parola" required />
				</fieldset>
				<fieldset class="field-group inv-color">
					<input class="inv-color" type="password" name="confirm_password" placeholder="Confirmare parola" required />
				</fieldset>
				<fieldset class="field-group ">
					<button class="btn submit-btn submit-btn-new" type="submit" >Submit</button>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>