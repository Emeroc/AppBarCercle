<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client <c:out value="${client.name} ${client.firstname}"></c:out></title>
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
<c:if test="${client.contributor == true}">
		<label class="control-label"><c:out value="${client.name} ${client.firstname}"></c:out> est cotisant (le montant sera majoré de 10%).</label></c:if>
	<form id="ff" class="form-horizontal" name="input" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/clients/addbalance?id=<c:out value="${client.id}"></c:out>"
		method="POST">
				<div class="control-group">
			<label class="control-label" for="inputPrice">Montant (€)</label>
			<div class="controls">
				<input type="text" id="inputPrice" name="balance.price"
					 placeholder="Le montant 0.00" class="required number"> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputStatus">Nom</label>
			<div class="controls">
				<select name="balance.status" id="inputStatus">
    <option value="3" selected="selected">Liquide</option>
    <option value="4" >Chèque</option>
</select>
			</div>
		</div>
	</form>
</body>
</html>