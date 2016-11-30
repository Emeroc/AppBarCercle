<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="orders.history.pagetitle" /></title>
	<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function() {		
		$('#page').click(function() {
			$(this).select();
		}).keypress(function(e) {
			if (e.which === 13) {
				$('#ok').trigger('click');
				return false;
			}
		});
		
		$('#previous').click(function() { document.location = '${pageContext.request.contextPath}/admin/orders/history?page=${page == 1 ? 1 : page - 1}'; });
		$('#ok').click(function() { document.location = '${pageContext.request.contextPath}/admin/orders/history?page=' + parseInt($('#page').val()); });
		$('#next').click(function() { document.location = '${pageContext.request.contextPath}/admin/orders/history?page=${page + 1}'; });
		
		$(".orders-list > tbody tr")
			.css('cursor', 'pointer')
			.click(function(ev) {
				var target = '${pageContext.request.contextPath}/admin/orders/historydetails?id=' + $(this).attr('data-id');
			    $("#myModal .modal-body").load(target, function() { 
			         $("#myModal").modal("show"); 
			    });
			});
	});
	</script>
	<table class="table orders-list">
		<thead>
			<tr>
				<th><i class="icon-barcode icon-white"></i> <s:text name="orders.number" /></th>
				<th><i class="icon-calendar icon-white"></i> <s:text name="orders.date" /></th>
				<th><i class="icon-user icon-white"></i> <s:text name="orders.client" /></th>
				<th><i class="icon-cog icon-white"></i> <s:text name="orders.status" /></th>
				<th><i class="icon-shopping-cart icon-white"></i> <s:text name="orders.totalprice" /></th>
				<th><i class="icon-lock icon-white"></i> <s:text name="admin" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${orders.size() == 0}">
				<tr>
					<td colspan="5">
						<div class="empty-warning"><s:text name="orders.history.empty" /></div>
					</td>
				</tr>
			</c:if>
			<c:forEach items="${orders}" var="order">
				<tr data-id="<c:out value="${order.id}"></c:out>">
					<td><c:out value="${order.id}"></c:out></td>
					<td><fmt:formatDate value="${order.datePlaced}" pattern="dd/MM/yyyy - HH:mm:ss" /></td>
					<td><c:out value="${order.clientName}"></c:out><c:if test="${order.clientId != -1}"> <i class="icon-star"></i></c:if></td>
					<td>
						<c:choose>
							<c:when test="${order.status == 'pending'}">
								<s:text name="orders.status.pending" />
							</c:when>
							<c:when test="${order.status == 'partial'}">
								<s:text name="orders.status.partial" />
							</c:when>
							<c:otherwise>
								<s:text name="orders.status.completed" />
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="&euro;" /></td>
				<td><c:out value="${order.admin}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="text-center">
		<form class="form-inline">
			<button type="button" class="btn" id="previous" ${page == 1 ? 'disabled' : ''}>&larr; <s:text name="previous" /></button>
			<div class="input-append">
				<input type="text" class="input-mini text-center" id="page" value="${page}">
				<button type="button" class="btn" id="ok"><s:text name="ok" /></button>
			</div>
			<button type="button" class="btn" id="next" <%--  ${orders.size() < 20 ? 'disabled' : ''}--%>><s:text name="next" /> &rarr;</button>
		</form>
	</div>
	
	<div id="myModal" class="modal hide fade in">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3><s:text name="orders.history.details" /></h3>
		</div>
		<div class="modal-body"></div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" data-dismiss="modal"><s:text name="close" /></button>
		</div>
	</div>
</body>
</html>