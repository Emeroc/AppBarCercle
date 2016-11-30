<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail Produit <c:out value="${product.name}"></c:out> </title>
<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
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
	<form id="ff" class="form-horizontal"
		action="${pageContext.request.contextPath}/admin/menu/<c:choose><c:when test="${newproduct==false}">updateproduct</c:when><c:otherwise>addproduct</c:otherwise></c:choose>"
		method="POST" enctype="multipart/form-data"
		onsubmit="$('#availableid').val($('#filterProductStatus > button.active').val());$('#deliveredid').val($('#filterDeliveredStatus > button.active').val());return validate(this);">
		<div class="control-group">
			<label class="control-label" for="inputProduct">Nom du
				produit</label>
			<div class="controls">
				<input type="text" name="product.name" id="inputProduct"
					placeholder="Nom du produit" class="required" minlength="2"
					<c:if test="${newproduct==false}">disabled</c:if>
					value="<c:out value="${product.name}"></c:out>"> <c:if test="${newproduct==false}"><input type="hidden"
					name="product.id" id="inputId" value="<c:out value="${product.id}"></c:out>"></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPrice">Prix</label>
			<div class="controls">
				<div class="input-append">
					<input type="text" id="inputPrice" name="product.price"
						placeholder="Le prix du produit ici" value="<c:out value="${product.price}"></c:out>">
					<span class="add-on">€</span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputAva">Disponibilité</label>
			<div class="controls">
				<div class="btn-toolbar" id='inputAva' style="margin-top: 0px;">
					<div class="btn-group" id='filterProductStatus'
						data-toggle="buttons-radio">
						<button
							class="btn <c:if test="${product.available==true}">active</c:if>"
							type="button" value="true">Disponible</button>
						<input type="hidden" id="availableid" name="product.available" />
						<button
							class="btn <c:if test="${product.available==false}">active</c:if>"
							type="button" value="false">Indisponible</button>
					</div>

				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputAva">Livré par défaut</label>
			<div class="controls">
				<div class="btn-toolbar" id='inputAva' style="margin-top: 0px;">
					<div class="btn-group" id='filterDeliveredStatus'
						data-toggle="buttons-radio">
						<button
							class="btn <c:if test="${product.delivered==true}">active</c:if>"
							type="button" value="true">Oui</button>
						<input type="hidden" id="deliveredid" name="product.delivered" />
						<button
							class="btn <c:if test="${product.delivered==false}">active</c:if>"
							type="button" value="false">Non</button>
					</div>

				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputCat">Catégorie</label>
			<div class="controls">
				<select name="product.category.id" id="inputCat">
					<c:forEach items="${l}" var="cat">
						<option value="<c:out value="${cat.id}"></c:out>"
							<c:if test="${cat.id==product.category.id}">selected</c:if>><c:out value="${cat.name}"></c:out></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight<c:out value="${product.id}"></c:out>">Priorité</label>
			<div class="controls">
				<div class="input-prepend input-append">
					<button onclick="downWeight()" type="button" class="btn">-</button>
					<input class="input-micro input-height-mini"
						id='weight<c:out value="${product.id}"></c:out>' type="text" name="product.weight"
						value="<c:choose><c:when test="${not empty product.weight}"><c:out value="${product.weight}"></c:out></c:when><c:otherwise>0</c:otherwise></c:choose>"
						style="width: 40px; text-align: center">
					<button type="button" class="btn" onclick="upWeight()">+</button>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="upload">Image (<2Mo, jpg)</label>
			<div class="controls">
				<input type="file" name="upload" value="" id="upload" />
			</div>
		</div>
		
			<img src=
<%--<c:choose>
	<c:when test="${fn:length(product.picture)>0}"><%--"data:image/jpg;base64,<c:out value='${product.getPictureS()}'/>"--%>
		"${pageContext.request.contextPath}/image/img?id=<c:out value="${product.id}" />"
<%--	</c:when>
	<c:otherwise>
		"${pageContext.request.contextPath}/img/carte/<c:out value="${product.category.imagePath}" />"
	</c:otherwise>
</c:choose> --%>

							
								class="img-polaroid cat" style="background-image:url('${pageContext.request.contextPath}/img/carte/<c:out value="${product.category.imagePath}" />');<c:if test="${product.available==false}">filter:url(${pageContext.request.contextPath}/img/filters.svg#grayscale);filter: gray;-webkit-filter: grayscale(1);</c:if>">
							

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-success btn-large">Sauver</button>
				<a href="#myModal" role="button" class="btn btn-danger btn-large"
					data-toggle="modal">Supprimer</a>
			</div>
		</div>
	</form>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 id="myModalLabel">Confirmation : suppression du produit
				"${product.name}"</h4>
		</div>
		<div class="modal-body">
			<p>Attention cette action est irreversible !!!</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Annuler</button>
			<a class="btn btn-primary btn-danger"
				href="${pageContext.request.contextPath}/admin/menu/deleteproduct?id=${product.id}">Confirmer</a>
		</div>
	</div>
	<script type="text/javascript">
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

		function downWeight() {
			if ($('#weight${product.id}').val() >= 0) {
				$('#weight${product.id}').val(
						$('#weight${product.id}').val() - 1);
			}
		}

		function upWeight() {
			if ($('#weight${product.id}').val() >= 0)
				$('#weight${product.id}').val(
						parseInt($('#weight${product.id}').val()) + 1);
		}
	</script>
</body>
</html>