<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Friends List</title>
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
						<li class="active"><a href="/fbanalytics" title="">${facebookProfile.name}</a></li>
						<li><a href="aboutUs" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div id="page-wrapper">
		<div class="nav">
			<ul>
				<li><a href="/fbanalytics">User Profile</a></li>
				<li>Friends</li>
			</ul>
		</div>
		<div class="title">
			<h2>Select Friends for analysis</h2>
		</div>


		<form action="commonFriends" method="post" class="form1">
			<input type="submit" value="See common friends" class="button" /> <input
				type="reset" value="Clear" class="button" /> <br> <br>
			<ul class="checkbox-grid" style="display: table-row">
				<c:forEach var="i" items="${nameList}" varStatus="status">
					<li style="vertical-align: middle"><input type="checkbox" name="id[]"
						value="${idList[status.index]}" />${i}</li>
				</c:forEach>
			</ul>

		</form>

		<form id="disconnectionForm"
			action="${pageContext.request.contextPath}/signout"></form>
	</div>

	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>Advanced Web Technology - Politechnico di Milano<br/>
		Ognen Kostovski<br/>
		814551</p>
	</div>
</body>
</html>
