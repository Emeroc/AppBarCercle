<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="orders.listproducts.pagetitle" /></title>
	<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript">	
	$(document).ready(function() {
		// Actions on category buttons
		$('.list-viewer li').click(function() {
			// Get the associated id
			var categoryid = $(this).attr('data-categoryid');

			// Sets the active category
			$('.list-viewer li').each(function() {
				if ($(this).attr('data-categoryid') == categoryid) {
					$(this).addClass('list-viewer-active');
				} else {
					$(this).removeClass('list-viewer-active');					
				}
			});
			
			// Show lines from active category
			$('.orders-list > tbody > tr').each(function(i) {
				if ($(this).attr('data-categoryid') != categoryid && categoryid != -1) {
					$(this).hide();
				} else {
					$(this).show();
				}
			});
		});
		
		// Action for each product 
		
			// Remove product
			
			$('.modal-toggle').click(function() {
				console.log($(this));
				var self = $(this).parent().parent();
				// Show modal box after a 2 second click
			
					var confirmModal = 
							$('<div class="modal hide fade">' +    
					        	'<div class="modal-header">' +
									'<a class="close" data-dismiss="modal" >&times;</a>' +
									'<h3><s:text name="orders.listproducts.remove" /></h3>' +
					        	'</div>' +
					 			'<div class="modal-body">' +
					            	'<p><s:text name="orders.listproducts.removewarning" /></p>' +
					            	'<p>' + self.find('td:nth-child(3)').html() + ' (<s:text name="orders.for" /> ' + self.find('td:nth-child(2)').html() + ')</p>' +
					          	'</div>' +
					          	'<div class="modal-footer">' +
					            	'<a href="#" class="btn" data-dismiss="modal"><s:text name="no" /></a>' +
					            	'<a href="#" id="okButton" class="btn btn-danger"><s:text name="yes" /></a>' +
					          	'</div>' +
					          '</div>');
					
					confirmModal.find('.modal-body').append();

					confirmModal.find('#okButton').click(function(event) {
						$.post('${pageContext.request.contextPath}/admin/orders/removeitem', { orderId: self.find('button').attr('data-orderid'), itemId: self.find('button').attr('data-itemid') });
						self.addClass('grayedout');
						self.find('button').parent().html('');
					    confirmModal.modal('hide');
					});

					confirmModal.modal('show');
				
			
			});
		
			$('.orders-list > tbody > tr').each(function(i) {
			// Add deliver link
			var orderid = $(this).find('button').attr('data-orderid');
			var itemid = $(this).find('button').attr('data-itemid');
			var cell = $(this).find('button').parent();
			makeLink(cell, orderid, itemid);	
			});
	
	});
	
	// Create deliver & cancel link
	function makeLink(cell, orderid, itemid) {
		cell.find('button').click(function() {			
			$.post('${pageContext.request.contextPath}/admin/orders/deliver', { orderId: orderid, itemId: itemid });
			
			cell.parent().addClass('grayedout');			
					var oldContent = cell.html();
			//var timer = window.setTimeout(function() { cell.html('<i class="icon-ok"></i>'); }, 3000); 
		
			var cancel = $(document.createElement('a'))
				.text('<s:text name="cancel" />')
				.css('cursor', 'pointer')
				.click(function() {
					$.post('${pageContext.request.contextPath}/admin/orders/canceldelivery', { orderId: orderid, itemId: itemid });					
					cell.parent().removeClass('grayedout');
					//dow.clearTimeout(timer);
					cell.html(oldContent);
					makeLink(cell, orderid, itemid);					
				});
			cell.html('<i class="icon-ok"></i> ').append(cancel); 
		});
	}
	</script>
	<div class="box">
		<div id="nav-wrapper">
			<div id="nav" class="list-viewer">
				<ul>
					<li data-categoryid="-1" class="list-viewer-item list-viewer-active"><s:text name="orders.listproducts.allcategories" /></li>
					<c:forEach items="${categories}" var="category">
						<li data-categoryid="<c:out value="${category.id}" />" class="list-viewer-item"><c:out value="${category.name}" /></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:if test="${id!=0}">
		  <div class="alert alert-success">
    La commande du client <c:out value="${name}"></c:out> a un total de <strong><fmt:formatNumber value="${total}" type="currency"
							currencySymbol="&euro;" /></strong> <c:if test="${id == -1 }"> et a dû être payée en  <strong>liquide</strong></c:if> !
    </div>
    </c:if>
		<div class="padded">
			<table class="table table-condensed orders-list">
				<thead>
					<tr>
					<%--	<th><i class="icon-calendar icon-white"></i> <s:text name="orders.date" /></th>  --%>
						<th><i class="icon-remove icon-white"></i> <s:text name="orders.cancel" /></th>
						<th><i class="icon-user icon-white"></i> <s:text name="orders.client" /></th>
						<%--<th><i class="icon-tag icon-white"></i> <s:text name="category" /></th>--%>
						<th><i class="icon-shopping-cart icon-white"></i> <s:text name="product" /></th>
						<th width="20%"><i class="icon-arrow-right icon-white"></i> <s:text name="deliver" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${products}" var="item">
				<c:if test="${item.key.cancelled == false}">
					<tr data-categoryid="${item.key.product.category.id}">
						<%--<td><fmt:formatDate value="${item.value.datePlaced}" pattern="dd/MM/yyyy - HH:mm:ss" /></td> --%>
						<td><span class="modal-toggle"><i class="icon-remove"></i></span></td>
						<td><c:out value="${item.value.clientName}" /></td>
						<%--<td><c:out value="${item.key.product.category.name}" /></td> --%>
						<td><c:out value="${item.key.product.name}" /><c:if test="${not empty item.key.comment}"> <span class="text-warning">(<c:out value="${item.key.comment}" />) </span></c:if></td>
						<td><button type="button" class="btn btn-mini btn-success" data-orderid="${item.value.id}" data-itemid="${item.key.id}"><s:text name="deliver" /></button></td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>