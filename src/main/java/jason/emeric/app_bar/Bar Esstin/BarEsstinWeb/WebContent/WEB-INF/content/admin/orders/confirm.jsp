<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/custom.css"
	rel="stylesheet" media="screen">

<title><s:text name="orders.new.pagetitle" /> &gt; <s:text
		name="orders.new.confirm.pagetitle" /></title>
<link href="${pageContext.request.contextPath}/css/orders.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#formOrder').submit(
							function() {
								$('#formOrder .btn-group').each(
										function(i) {
											$(this).parent().find('input').val(
													$(this).find(
															'button.active')
															.val());
										});
							});
					$(document).keypress(function(event) {
						if (event.which == '13') {
							event.preventDefault();
						}

					});
				});
		function openextra(id)
		{
			$(":checkbox").attr("checked", false);
			$('#name').val(id);
			$('#myModal').modal('show');
		}
		function confirmextra()
		{
			//remise par défaut du nom et du prix
			var count = $('#name').val();
			//nom
			$('#itemnameshow'+count).html($('#itemnameo'+count).val());
			$('#itemname'+count).val("");
			//prix
			$('#priceshow'+count).html($('#priceo'+count).val());
			$('#priceitem'+count).val($('#priceo'+count).val());
			var first = true;
			$( "input:checked" ).each(function(){
				if (first == true)
					{
					$('#itemnameshow'+count).html($('#itemnameshow'+count).html()+" Suppl");
					$('#itemname'+count).val($('#itemname'+count).val()+"Suppl");
					first = false;
					}
				
				//get id
				var id = $(this).val();
				//get name extra
				$('#itemnameshow'+count).html($('#itemnameshow'+count).html()+" "+$('#name'+id).val());
				$('#itemname'+count).val($('#itemname'+count).val()+" "+$('#name'+id).val());
				//get price extra
				$('#priceshow'+count).html((parseFloat($('#priceshow'+count).html())+parseFloat($('#price'+id).val())).toFixed(2));
				$('#priceitem'+count).val((parseFloat($('#priceitem'+count).val())+parseFloat($('#price'+id).val())).toFixed(2));
				
			});
			$('#priceshow'+count).html($('#priceshow'+count).html()+" €");
			//mise à jour de la somme totale
			$('#totalprice').html(0);
			$('#total').val(0);
			$( ".pricecount" ).each(function(){
				$('#totalprice').html((parseFloat($('#totalprice').html())+parseFloat($(this).val())).toFixed(2));
				$('#total').val((parseFloat($('#total').val())+parseFloat($(this).val())).toFixed(2));
			});
			$('#totalprice').html($('#totalprice').html()+" €");
			
			$('#myModal').modal('hide');
		}
		
	</script>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="box padded">
					<ul class="breadcrumb">
						<li><a
							href="${pageContext.request.contextPath}/admin/orders/chooseclient">1.
								<s:text name="orders.new.chooseclient.pagetitle" /> (<c:out
									value="${client.name}" />)
						</a> <span class="divider">&gt;</span></li>
						<li><a
							href="${pageContext.request.contextPath}/admin/orders/chooseproducts">2.
								<s:text name="orders.new.chooseproducts.pagetitle" />
						</a> <span class="divider">&gt;</span></li>
						<li class="active">3. <s:text
								name="orders.new.confirm.pagetitle" /></li>
					</ul>
					<c:if test="${client.id == -1 }">
						<div class="alert alert-error">
							<strong>Attention!</strong> N'oubliez pas de demander la monnaie
							car ce client n'a pas de compte !
						</div>
					</c:if>
					<form action="${pageContext.request.contextPath}/admin/orders/save"
						enctype="multipart/form-data" method="POST"
						class="form-horizontal" id="formOrder">
						<fieldset>
							<legend>
								<s:text name="products" />
							</legend>
							<a
								href="${pageContext.request.contextPath}/admin/orders/chooseproducts"
								class="btn btn-large btn-warning" id="btnModify"><s:text
									name="orders.new.confirm.modify" /></a>

							<button type="submit"
								class="btn btn-large btn-success pull-right">
								<s:text name="orders.new.confirm.submit" />
							</button>
							<table class="table table-condensed orders-list">
								<thead>
									<tr>
										<th><s:text name="product" /></th>
										<th><s:text name="deliver" /></th>
										<th><s:text name="price" /></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="totalPrice" value="0" />
									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${order}" var="item">
										<tr>
											<td><span id="itemnameshow${count}"><c:out
														value="${item.name}"></c:out></span> <input type="hidden"
												id="itemnameo${count}"
												value="<c:out value="${item.name}"></c:out>"> <input
												type="hidden" name="orderitems[${count}].product.id"
												value="<c:out value="${item.id}"></c:out>"> <input
												type="hidden" name="orderitems[${count}].product.name"
												id="itemname${count}"> <input type="text"
												placeholder="Commentaire"
												name="orderitems[${count}].comment"
												id="orderitems${count}comment"> <input type="hidden"
												name="orderitems[${count}].menu.id"
												value="<c:out value="${item.menuId}"></c:out>">
												<button type="button" class="btn" value="true"
													onclick="openextra(${count});">+</button></td>
											<td><c:choose>
													<c:when test="${item.id != -1}">
														<div class="btn-group" data-toggle="buttons-radio">
															<button type="button"
																class="btn<c:if test="${item.delivered == true}"> active</c:if>"
																value="true">
																<s:text name="yes" />
															</button>
															<button type="button"
																class="btn<c:if test="${item.delivered == false}"> active</c:if>"
																value="false">
																<s:text name="no" />
															</button>
														</div>
													</c:when>
													<c:otherwise>
														<s:text name="Menu" />
													</c:otherwise>
												</c:choose> <input type="hidden" name="orderitems[${count}].delivered"
												value="${item.delivered}"></td>
											<td><span id="priceshow${count}"><fmt:formatNumber
														value="${item.price}" type="currency"
														currencySymbol="&euro;" /> </span> <input class="pricecount"
												type="hidden" id="priceitem${count}"
												name="orderitems[${count}].product.price"
												value="${item.price}"> <input type="hidden"
												id="priceo${count}" value="${item.price}"></td>
										</tr>
										<c:set var="totalPrice" value="${totalPrice + item.price}" />
										<c:set var="count" value="${count + 1}" scope="page" />
									</c:forEach>
								</tbody>
							</table>
							<div class="row-fluid order-info">
								<div class="span8">
									<small><s:text name="orders.for" /></small>
									<c:out value="${client.name}"></c:out>
								</div>
								<div class="span4">
									<small><s:text name="orders.total" /></small>&nbsp; <span
										id="totalprice"><fmt:formatNumber value="${totalPrice}"
											type="currency" currencySymbol="&euro;" /></span> <input
										type="hidden" name="total" id="total" value="${totalPrice}">
								</div>
							</div>
							<a
								href="${pageContext.request.contextPath}/admin/orders/chooseproducts"
								class="btn btn-large btn-warning" id="btnModify"><s:text
									name="orders.new.confirm.modify" /></a>
							<button type="submit"
								class="btn btn-large btn-success pull-right">
								<s:text name="orders.new.confirm.submit" />
							</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Choix des suppléments</h3>
		</div>
		<div class="modal-body">
			<input id="name" type="hidden">
			<c:forEach items="${extras}" var="extra">
				<label class="checkbox"> <input type="checkbox"
					id="inlineCheckbox1" value="${extra.id}"> <c:out
						value="${extra.name } ${extra.price } €"></c:out> <input
					type="hidden" id="name${extra.id}"
					value="<c:out
							value="${extra.name }"></c:out>"> <input
					type="hidden" id="price${extra.id}"
					value="<c:out
							value="${extra.price }"></c:out>">
				</label>
			</c:forEach>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Fermer</button>
			<button class="btn btn-primary" onclick="confirmextra();">Confirmer</button>
		</div>
	</div>

</body>
</html>