<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail du menu</title>
</head>
<body>
	<form id="ff" class="form-horizontal" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/admin/menu/<c:choose><c:when test="${newextra==false}">updateextra</c:when><c:otherwise>addextra</c:otherwise></c:choose>"
		method="POST"
		onsubmit="return validate(this);">
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-success btn-large">Sauver</button>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="control-group">
					<label class="control-label" for="inputMenu">Nom du Supplément</label>
					<div class="controls">
						<input type="text" name="extra.name" id="inputMenu"
							placeholder="Nom du Supplément" class="required" minlength="2"
							<c:if test="${newextra==false}">disabled</c:if>
							value="<c:out value="${extra.name}"></c:out>"> <input type="hidden" name="extra.id"
							id="inputId" value="<c:out value="${extra.id}"></c:out>">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPrice">Prix</label>
					<div class="controls">
						<div class="input-append">
							<input type="text" id="inputPrice" name="extra.price"
								placeholder="Le prix du supplément ici" value="<c:out value="${extra.price}"></c:out>">
							<span class="add-on">€</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	var count = ${count};
	
    $(document).ready(function() {
   
    		  $(window).keydown(function(event){
    		    if(event.keyCode == 13) {
    		      event.preventDefault();
    		      return false;
    		    }
    		  });
    	
    	 $("#ff").validate();
    });


	
	function validate() {
		if (validatePrice($('#inputPrice')))
			return true;
		else
			return false;
	}
	function validatePrice(price) {
		if (price.val().trim() === "") {
			alert("Depuis quand on offre des trucs au bar !!!");
			price.focus();
			return false;
		}
		if (price.val() !== "") {
			if (!(/^\d*(?:\.\d{0,2})?$/.test(price.val()))) {
				alert("Merci d'insérer un prix valide");
				price.focus();
				return false;
			}
		}
		return true;
	}
	
	</script>
</body>
</html>