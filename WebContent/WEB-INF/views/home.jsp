<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>User Profile</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<script src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">
	function Facebook() {
		FB.init({
			appId : '1613576805567907',
			cookie : true,
			status : true,
			xfbml : true,
			version : 'v2.3'
		});
	}
	window.onload = Facebook;

	function logoutFacebook() {
		FB.logout(function(response) {
			console.log("Facebook logout response", response);
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
						<li><a href="aboutUs" title="">About Us</a></li>
						<li><a href="javascript:logoutFacebook()">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div id="page-wrapper">

		<div class="title">
			<h2>User Profile</h2>
		</div>
		<div class="info">
			<div class="basicinfo">
				<img
					src="https://graph.facebook.com/${facebookProfile.id}/picture?redirect=1&height=200&type=normal&width=200"
					class="imageborder" />
				<h3>
					<span>${facebookProfile.name}</span>
				</h3>
				<span>${facebookProfile.id}</span><br> 
				<span>${facebookProfile.gender}</span><br>
				<span>${facebookProfile.locale}</span><br>
			</div>
			<div class="detailinfo">
				<h2>Welcome ${facebookProfile.name}</h2> <br/><br/>
				<span>Link to your profile !!</span><br> 
				<span>${facebookProfile.link}</span><br>
				<span>Here we can add more information ---------</span><br>  
				<span>When the app permissions have been approved from facebook ---------</span><br/>
			</div>
		</div>


		<form id="disconnectionForm"
			action="${pageContext.request.contextPath}/signout">
			<!-- <a href="friendsList" class="button">See your friends</a> <input
				type="hidden" name="_method" value="delete"> -->
			<!--  <a href="friendsList" class="button">Signout</a> -->
			<input type="hidden" name="scope"
				value="email,publish_actions,user_friends">
		</form>
		<a href="friendsList" class="button">See your friends</a> <input
			type="hidden" name="scope" value="email,publish_actions,user_friends">

	</div>
	<div class="wrapper"></div>
	<div id="copyright" class="container">
		<p>Advanced Web Technology - Politechnico di Milano<br/>
		Ognen Kostovski<br/>
		814551</p>
	</div>
</body>
</html>