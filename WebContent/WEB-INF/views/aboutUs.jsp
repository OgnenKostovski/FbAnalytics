<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>About Us</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<script src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">
	function Facebook() {
		FB.init({
			appId : '1613576805567907',
			cookie : true,
			status : true,
			xfbml : true
		});
	}
	window.onload = Facebook;

	function logoutFacebook() {
		FB.logout(function(response) {
			console.log("Here logout response", response);
			document.getElementById("disconnectionForm").submit();

		});
	}
</script>
</head>
<body>
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1>
					<a href="/fbanalytics">Facebook Analytics</a>
				</h1>
				<div id="menu">
					<ul>
						<li class="active"><a href="/fbanalytics" title="">${facebookProfile.name}
						</a></li>
						<li><a href="#" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div id="page-wrapper">

			<div class="title">
				<h2>About Us</h2>
			</div>
			<h3>Advanced Web Technologies</h3>
			<br><br>
			<div>
			<img align="right" style="padding: 5ex;" src="<c:url value="/img/polimi.png"/>"/>
			<p>The FBA application implements a simple system for acquiring, 
			visualizing and metering the social relationships of people in a given social network (facebook).</p>
			<p>Facebook analytics application is built using JEE, adopting the Spring framework.
			With the help of Facebook analytics we gather information about the users, their social network relationships. 
			The application’s business logic takes care of organizing this information in a way easily accessible, reusable and maintainable.
			Spring MVC helps us with the application presentation, navigation and through the controller it allows us to use the implemented business logic.
			The goal of this application is to design this system for acquiring, visualizing and metering the social relationships of people in a given social network (Facebook).
			</p>
			
			</div>
			
			
			
			
			
			<br><br>
			<br>
			<br>
			 
				



			<form id="disconnectionForm" action="${pageContext.request.contextPath}/signout">
			 <input type="hidden" name="_method" value="delete">
			</form>
	</div>
	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>Advanced Web Technology - Politechnico di Milano<br/>
		Ognen Kostovski<br/>
		814551</p>
	</div>
</body>
</html>
