<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client</title>
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
		 $("#ff").validate();
	});
	</script>
	<form id="ff" class="form-horizontal" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/clients/updateclient?id=${client.id}"
		method="POST">
		<div class="control-group">
			<label class="control-label" for="inputLogin">login Univ</label>
			<div class="controls">
				<input type="text" id="inputLogin" name="client.login"
					value="<c:out value="${client.login}"></c:out>" >
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputName">Nom</label>
			<div class="controls">
				<input type="text" id="inputName" name="client.name"
					placeholder="Nom" value="<c:out value="${client.name}"></c:out>" class="required" minlength="2">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputFirstName">Prénom</label>
			<div class="controls">
				<input type="text" id="inputFirstName" name="client.firstname"
					placeholder="Prénom" value="<c:out value="${client.firstname}"></c:out>" class="required" minlength="2">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputMail">Mail</label>
			<div class="controls">
				<input type="text" id="inputMail" name="client.mail"
					placeholder="courriel" value="<c:out value="${client.mail}"></c:out>" class="required email">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputCat">Année</label>
			<div class="controls">
				<select name="client.yearId" id="inputCat">
					<c:forEach items="${l}" var="cat">
						<option value="<c:out value="${cat.id}"></c:out>"
							<c:if test="${cat.id==client.yearId}">selected</c:if>><c:out value="${cat.year}"></c:out></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputContributor">Cotisant</label>
			<div class="controls">
				<input type="checkbox" name="client.contributor" value="true"
					<c:if test="${client.contributor == true}">
     checked="checked" </c:if>
					id="inputContributor" /> <input type="hidden"
					id="__checkbox_inputContributor"
					name="__checkbox_client.contributor" value="true" />
			</div>
		</div>
	</form>
</body>
</html>