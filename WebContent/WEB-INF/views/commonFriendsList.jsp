<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hierarchical List</title>
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
						<li class="active"><a href="/fbanalytics" title="">${name}</a></li>
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
				<li><a href="friendsList">Friends</a></li>
				<li>Selected Friends</li>
			</ul>
		</div>
		<div class="title">
			<h2>Selected Friends</h2>
			<h3>Filtered selected friends</h3>
			<h4>Unfiltered database for mutual friends</h4>
		</div>


		<form action="openGraph" method="POST" class="form1">
			<input type="submit" value="Open Graph" class="button">
		</form>
		<br> <br>
		<c:forEach var="i" items="${friends}" varStatus="statusF">
			<ul style="display: inline-block; vertical-align: top; padding: 10px">
				<li>
					<h3>
						<c:out value="${i.name}"></c:out>
					</h3>
					<ul>
						<jsp:useBean id="cList" class="java.util.LinkedHashSet"
							scope="request" />

						<c:forEach var="k" items="${i.commonFriends}" varStatus="statusK">
							<div hidden><c:out value="${cList.add(k.name)}"></c:out></div>
						</c:forEach>
						<c:forEach var="x" items="${cList}" varStatus="loop">
							<li><c:out value="${x}" /></li>
						</c:forEach>
						<div hidden><c:out value="${cList.clear()}"></c:out></div>
					</ul>
				</li>
			</ul>
		</c:forEach>


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
