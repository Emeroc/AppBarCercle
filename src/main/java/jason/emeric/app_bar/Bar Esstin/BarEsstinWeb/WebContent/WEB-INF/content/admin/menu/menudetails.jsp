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
		action="${pageContext.request.contextPath}/admin/menu/<c:choose><c:when test="${newmenu==false}">updatemenu</c:when><c:otherwise>addmenu</c:otherwise></c:choose>"
		method="POST"
		onsubmit="$('#test').val($('#filterProductStatus > button.active').val());return validate(this);">
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-success btn-large">Sauver</button>
			</div>
		</div>
		<div class="row">
			<div class="span6">
				<div class="control-group">
					<label class="control-label" for="inputMenu">Nom du menu</label>
					<div class="controls">
						<input type="text" name="menu.name" id="inputMenu"
							placeholder="Nom du menu" class="required" minlength="2"
							<c:if test="${newmenu==false}">disabled</c:if>
							value="<c:out value="${menu.name}"></c:out>"> <input type="hidden" name="menu.id"
							id="inputId" value="<c:out value="${menu.id}"></c:out>">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPrice">Prix</label>
					<div class="controls">
						<div class="input-append">
							<input type="text" id="inputPrice" name="menu.price"
								placeholder="Le prix du Menu ici" value="<c:out value="${menu.price}"></c:out>">
							<span class="add-on">€</span>
						</div>
					</div>
				</div>
			</div>
			<div class="span6">
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${menuparts}" var="menupart">
					<table class="table table-hover" id="table${count}">
						<caption>
							<c:out value="${menupart.name}"></c:out><input type="hidden"
								name="menuparts[${count}].name" id="menuPartName"
								value="<c:out value="${menupart.name}"></c:out>"> <input type="hidden"
								name="menuparts[${count}].id" id="menuPartId"
								value="<c:out value="${menupart.id}"></c:out>"> <a onclick="removeRow($(this))"><i
								class="icon-remove"></i></a>
						</caption>
						<thead>
							<tr>
								<th>Nom</th>
								<th>Prix (€)</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${menupart.products}" var="product">
								<tr
									class="<c:choose><c:when test="${product.enable==false}">error</c:when><c:when test="${product.available==false}">warning</c:when><c:otherwise>success</c:otherwise></c:choose>">
									<td><c:out value="${product.name}"></c:out><input type="hidden"
										name="menuparts[${count}].products.id" id="productId"
										value="<c:out value="${product.id}"></c:out>"></td>
									<td><c:out value="${product.price}"></c:out></td>
									<td><a onclick="removeRow($(this))"><i
											class="icon-remove"></i></a></td>
								</tr>
							</c:forEach>
							<tr>
								<td><input id="completion${count}" type="text"
									data-provide="typeahead" placeholder="Ajouter un produit"
									class="completion" autocomplete=off> <input
									type="hidden" id="inputIdAuto${count}" class="inputAutoH"></td>
								<td></td>
								<td><button class="btn btn-primary" type="button"
										onclick="addRow($('#table${count} tr:last'),$('#inputIdAuto${count}'),${count})">Ajouter</button>
								</td>

							</tr>
						</tbody>
					</table>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:forEach>
				<input type="text" id="menuPart"
					placeholder="Ajouter une nouvelle partie" minlength="2"></input>
				<button class="btn btn-primary" type="button"
					onclick="addMenuPart()">Ajouter</button>
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
    	
    	declareTypeAhead();
    	 $("#ff").validate();
    });


	
	function declareTypeAhead(){
	$('.completion').typeahead({
		source: function(query, process) {
	        objects = [];
	        map = {};
	        var data = [<c:forEach items="${products}" var="product">{"id":${product.id},"label":"${product.name} (${product.price}€)"},</c:forEach>{"id":0,"label":""}] // Or get your JSON dynamically and load it into this variable
	        $.each(data, function(i, object) {
	            map[object.label] = object;
	            objects.push(object.label);
	        });
	        process(objects);
	    },
	    updater: function(item) {
	    	//next() ne marche pas car $(this) ne fait pas référence à l'autocompplete
	    	 $(".inputAutoH").val(map[item].id);
	        return item;
	    }
		
	    });
	}
		
	function addRow(table,id,count) {
		$.post( '${pageContext.request.contextPath}/admin/menu/ajaxproduct?id='+id.val(),
				function( data ) {
			var color;
			if(data.product.available==false)
				color="warning";
			else
				color="success";
				table.before('<tr class="'+color+'"><td>'+data.product.name+'<input type="hidden" name="menuparts['+count+'].products.id" id="productId" value="'+data.product.id+'"></td><td>'+data.product.price+'</td><td><a onclick="removeRow($(this))"><i class="icon-remove"></i></a></td></tr>');
				}
				);
	}
	
	function removeRow(row) {
	row.parent().parent().remove();
	}
	
	function addMenuPart() {
		$.post( '${pageContext.request.contextPath}/admin/menu/menupart?count='+count+'&name='+$('#menuPart').val()+'',
				function( data ) {
				$('#menuPart').before(data);
				declareTypeAhead();
				count++;	
		}
				);
		}
	
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