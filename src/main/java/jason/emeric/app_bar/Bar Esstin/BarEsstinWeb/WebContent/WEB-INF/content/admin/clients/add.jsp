<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
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
		 $("#form").validate();
	});
	</script>
<legend>
		<s:text name="addClient" />
		</legend>
		<c:if test="${actionErrors.size() > 0}">
		<div class="alert alert-error">
			<p><strong><s:text name="errors" /></strong></p>
			<s:actionerror />
		</div>
		</c:if>
<form class="form-horizontal" id="form" name="input" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/clients/addclient"
		method="POST">
		<div class="control-group">
			<label class="control-label" for="inputLogin">login Univ (Laissez vide si extérieur)</label>
			<div class="controls">
				<input type="text" id="inputLogin" name="client.login" placeholder="login">
					<button
							class="btn"
							type="button" onclick="test()">GetData</button>
							
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputName">Nom</label>
			<div class="controls">
				<input type="text" id="inputName" name="client.name"
					placeholder="Nom" class="required" minlength="2">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputFirstName">Prénom</label>
			<div class="controls">
				<input type="text" id="inputFirstName" name="client.firstname"
					placeholder="Prénom" class="required" minlength="2">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputMail">Mail</label>
			<div class="controls">
				<input type="text" id="inputMail" name="client.mail"
					placeholder="courriel" class="required email">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputCat">Année</label>
			<div class="controls">
				<select name="client.yearId" id="inputCat">
					<c:forEach items="${l}" var="cat">
						<option value="<c:out value="${cat.id}"></c:out>"><c:out value="${cat.year}"></c:out></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputContributor">Cotisant</label>
			<div class="controls">
				<input type="checkbox" name="client.contributor" value="true"
					id="inputContributor" /> <input type="hidden"
					id="__checkbox_inputContributor"
					name="__checkbox_client.contributor" value="true" />
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-success btn-large">Ajouter</button>
			</div>
		</div>
	</form>
<script type="text/javascript">
function test() {
	$.post( '${pageContext.request.contextPath}/admin/clients/ajaxldap?login='+$('#inputLogin').val(),
			function( data ) {
		console.log(data);
		$('#inputLogin').val(data.client.login);
		$('#inputName').val(data.client.name);
		$('#inputFirstName').val(data.client.firstname);
		$('#inputMail').val(data.client.mail);
	}
			);
}
</script>
</body>
</html>