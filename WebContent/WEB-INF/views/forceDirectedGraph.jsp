<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Force Directed Graph</title>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
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

<meta charset="utf-8">
<style>
.node {
	stroke: #fff;
	stroke-width: 1.5px;
}

.link {
	stroke: #999;
	stroke-opacity: .6;
}
</style>
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
		<a href="/fbanalytics">User Profile</a> > <a href="friendsList">Your
			Friends</a> > Selected Friends > Graph
		<div class="title">
			<h2>Force Directed Graph</h2>
			<h3>Filtered selected friends</h3>
			<h4>Filtered common friends</h4>
		</div>
		You have selected:
		<div>
			<c:forEach var="i" items="${friends}" varStatus="status">
		
			-<c:out value="${i.name}"></c:out>

			</c:forEach>
		</div>

		<div id="result"></div>
		<div id="graph"></div>


		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
		<script>
			var data = eval('(' + '${graph}' + ')');

			var width = 1000, height = 650;

			var color = d3.scale.category20();

			var svg = d3.select("#graph").append("svg").attr("width", width)
					.attr("height", height);

			var force = d3.layout.force().gravity(.05).linkDistance(150)
					.linkStrength(0.08).distance(250).charge(-60).size(
							[ width, height ]);

			force.nodes(data.nodes).links(data.links).start();

			var link = svg.selectAll(".link").data(data.links).enter().append(
					"line").attr("class", "link").style("stroke-width",
					function(d) {
						return Math.sqrt(d.value);
					});

			var node = svg.selectAll(".node").data(data.nodes).enter().append(
					"g").attr("class", "node").attr("r", 5).style("fill",
					function(d) {
						return color(d.group);
					}).call(force.drag);

			node.on("click", function(d) {
				if (d3.event.defaultPrevented)
					return;

				$.ajax({
					url : 'graphNode?id=' + d.id + '&height=140&width=140',
					success : function(data) {
						$('#result').html(data);
					}
				})
			});

			node
			.append("image")
			.attr(
					"xlink:href",
					function(d) {
						return "https://graph.facebook.com/"
								+ d.id
								+ "/picture?redirect=1&height=56&type=normal&width=56"
					}).attr("x", -40).attr("y", -18).attr("width", 56)
			.attr("height", 56);

			force.on("tick", function() {
				link.attr("x1", function(d) {
					return d.source.x;
				}).attr("y1", function(d) {
					return d.source.y;
				}).attr("x2", function(d) {
					return d.target.x;
				}).attr("y2", function(d) {
					return d.target.y;
				});

				node.attr("transform", function(d) {
					return "translate(" + d.x + "," + d.y + ")";
				});
			});
		</script>
		<form id="disconnectionForm"
			action="${pageContext.request.contextPath}/signout"></form>

	</div>
	<div class="wrapper"></div>

	<div id="copyright" class="container">
		<p>
			Advanced Web Technology - Politechnico di Milano<br /> Ognen
			Kostovski<br /> 814551
		</p>
	</div>
</body>
</html>
