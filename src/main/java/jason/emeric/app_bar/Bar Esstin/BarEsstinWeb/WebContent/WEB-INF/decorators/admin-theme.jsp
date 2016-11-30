<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" media="screen">
	<title><decorator:title default="BarEsstin" /></title>
	<decorator:head/>
</head>
<body>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
     <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<!--<header class="jumbotron subhead">
		<div class="container">
			<h1><s:text name="application.name" /></h1>
			<p class="lead"><s:text name="application.description" /></p>
		</div>
	</header>-->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand"  href="${pageContext.request.contextPath}/"><i style="vertical-align: inherit;" class="icon-home"></i></a>
				<ul class="nav">
					<li class=""><a href="${pageContext.request.contextPath}/admin/orders/listorders"><s:text name="menu.orders.listorders" /></a></li>
					<li class=""><a href="${pageContext.request.contextPath}/admin/orders/listproducts"><s:text name="menu.orders.listproducts" /></a></li>
					<li class=""><a href="${pageContext.request.contextPath}/admin/orders/chooseclient"><s:text name="menu.orders.new" /></a></li>
					<li class=""><a href="${pageContext.request.contextPath}/admin/orders/history"><s:text name="menu.orders.history" /></a></li>
					<li class="divider-vertical"></li>
					<li class="dropdown">
								<a class="dropdown-toggle"
								data-toggle="dropdown"
								href="#">
								<s:text name="menu.carte" />
								<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/admin/menu/list"><s:text name="products" /></a></li>
									<li><a href="${pageContext.request.contextPath}/admin/menu/menu"><s:text name="menu.menu" /></a></li>
									<li><a href="${pageContext.request.contextPath}/admin/menu/extra"><s:text name="menu.extra" /></a></li>
								</ul>
							</li>
					<li class="divider-vertical"></li>
					<li class=""><a href="${pageContext.request.contextPath}/admin/clients/list"><s:text name="menu.clients" /></a></li>
					<li class="divider-vertical"></li>
					<li class=""><a href="${pageContext.request.contextPath}/logout/logout"><i class="icon-off"></i> <c:out value="${pageContext.request.remoteUser}"></c:out></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span12">
    			<decorator:body />
    		</div>
		</div>
	</div>
</body>
</html>