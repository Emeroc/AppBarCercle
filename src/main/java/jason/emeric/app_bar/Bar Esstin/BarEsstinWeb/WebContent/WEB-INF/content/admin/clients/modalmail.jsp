<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client </title>
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function() {		
		 $("#ff").validate();
	});
	function onChange() {
	    var key = window.event.keyCode;

	    // If the user has pressed enter
	    if (key == 13) {
	        document.getElementById("comments").value =document.getElementById("comments").value + "\n*";
	        return false;
	    }
	    else {
	        return true;
	    }
	}
	</script>
	<form id="ff" class="form-horizontal" name="input" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/clients/sendmail"
		method="POST">
		<h4>Ce mail sera envoyé à tous les clients en négatif.</h4>
		<div class="control-group">
			<label class="control-label" for="inputObject">Objet</label>
			<div class="controls">
				<input type="text" id="inputObject" name="subject" class="required" minlength="2">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="comments">Corps du mail</label>
			<div class="controls">
				<textarea id="comments" rows="8" cols="30" name="text" onkeypress="onChange();" class="required" minlength="2"></textarea>
			</div>
		</div>
	</form>
</body>
</html>