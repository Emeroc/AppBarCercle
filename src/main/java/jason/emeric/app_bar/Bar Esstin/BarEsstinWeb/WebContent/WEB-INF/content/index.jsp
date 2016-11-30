<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<title>Application Bar ESSTIN</title>
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<div class="container">
		<div style="text-align: center;">
			<h1>Application Bar ESSTIN</h1>
		</div>

		<div style="text-align: center; margin-top: 50px;">
			<p><c:if test="${admin ==true}">
				<a class="btn btn-large btn-primary" type="button"
					href="${pageContext.request.contextPath}/admin/orders/chooseclient"><i
					class="icon-lock icon-white"></i> Administration</a> </c:if><a
					class="btn btn-large" type="button"
					href="${pageContext.request.contextPath}/client/list"><i
					class="icon-user"></i> Ma consommation</a>
			</p>
		</div>
	</div>
	<br>
	<center>~~~~</center>
	<br>
	<!-- Button to trigger modal -->
	<div>
	<center><a href="#myModal" role="button"  data-toggle="modal">crédits</a></center>	
	</div>
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Crédits</h3>
		</div>
		<div class="modal-body">
			<p>Application Web réalisée en 2013 par 4 étudiants de 5A de l'option ISYS-SIR tutorés par M. David Ledanseur.</p>
			<p>Merci à Géraldine, à l'équipe bar et à Nicolas Rogier d'avoir rendu possible cette application</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Fermer</button>
		</div>
	</div>
</body>
</html>
