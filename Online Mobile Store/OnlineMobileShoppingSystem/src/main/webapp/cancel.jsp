<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Cancel</title>
<%@ include file ="components/bootstrap_css_js.jsp" %>

</head>
<body>

<%@ include file="components/navbar.jsp"%>
     <div class="text-center">
		<h1>Payment Error</h1>
		<br />
		<h3>${errorMessage}</h3>
		<br />
	</div>

	<div class="text-center">
		<a href="index.jsp"><button class="btn custom-bg text-white">Go to Home Page</button></a>
	</div>
	
</body>
</html>




 