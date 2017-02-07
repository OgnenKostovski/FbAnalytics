<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Facebook Sign-in</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<script type="text/javascript">
	function submit() {
		document.getElementById("connectForm").submit();
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
				<br> <br> <br>

			</div>
		</div>
	</div>

	<div id="page-wrapper">

		<form id="connectForm"
			action="${pageContext.request.contextPath}/signin/facebook"
			method="POST">
			<div id="status"></div>
			<input type="submit" value="Sign in with Facebook" class="button">
			<input type="hidden" name="scope"
				value="email,publish_actions,read_stream,user_friends,manage_pages" />
			<div class="fb-like" data-share="true" data-width="450"
				data-show-faces="true"></div>
		</form>

		<p>Connect to facebook to start using Facebook Analytics</p>

	</div>

	<div id="copyright" class="container">
		<p>Advanced Web Technology - Politechnico di Milano<br/>
		Ognen Kostovski<br/>
		814551</p>
	</div>
</body>
</html>