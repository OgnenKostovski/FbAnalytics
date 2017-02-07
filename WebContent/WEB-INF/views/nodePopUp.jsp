<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Hello Facebook</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>

<meta charset="utf-8">

</head>
<body>

	<br>
	<br>
	<br>
	<img
		src="https://graph.facebook.com/${profile.id}/picture?redirect=1&height=${height}&type=normal&width=${width}"
		class="imageborder" />
	<br> Name: ${profile.name}
	<br>
	<br> Centrality information:
	<br> Degree = ${degree}
	<br> Normal Degree ${norm_degree}
	<br> Betweeness = ${betweenness}
	<br> Closenes = ${closeness}
	<br> Normal Closenes = ${norm_closeness}



</body>
</html>