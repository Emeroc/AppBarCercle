<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {		
		$(document).keypress(
			    function(event){
			     if (event.which == '13') {
			        event.preventDefault();
			      }


			});
	});
	</script>
	<form id="ff" class="form-horizontal" name="input" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/clients/deleteclient?id=${client.id}"
		method="POST">
		<h3>Confirmez-vous la suppression de <c:out value="${client.name } ${client.firstname }"></c:out> ?</h3>
		<div class="control-group">
			<label class="control-label" for="inputPassword">Mot de passe</label>
			<div class="controls">
				<input type="password" autocomplete = "off" id="inputPassword" name="password"
					placeholder="Le mot de passe" >
			</div>
		</div>
		</form>
</body>
</html>