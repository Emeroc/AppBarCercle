<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
<title>Votre consommation</title>
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
		
		$('#previous').click(function() { document.location = '${pageContext.request.contextPath}/client/list?page=${page == 1 ? 1 : page - 1}'; });
		$('#ok').click(function() { document.location = '${pageContext.request.contextPath}/client/list?page=' + parseInt($('#page').val()); });
		$('#next').click(function() { document.location = '${pageContext.request.contextPath}/client/list?page=${page + 1}'; });
		
		$(".orders-list > tbody tr")
			.css('cursor', 'pointer')
			.click(function(ev) {
				var target = '${pageContext.request.contextPath}/client/historydetails?id=' + $(this).attr('data-id');
			    $("#myModal .modal-body").load(target, function() { 
			         $("#myModal").modal("show"); 
			    });
			});
	});
	</script>
	<ul class="inline">
            <li>Bonjour, <c:if test="${empty client}"><c:out value="${name}"></c:out> vous n'avez pas de compte bar actif</c:if>${client.name} ${client.firstname} !!!</li>
             <li>Votre solde est de :</li>
            <li class="label 
<c:choose>
							<c:when test="${client.balance < 0}">
								label-important
							</c:when>
							<c:when test="${client.balance < 5}">
								label-warning
							</c:when>
							<c:otherwise>
								label-success
							</c:otherwise>
						</c:choose>"><fmt:formatNumber
												value="${client.balance}" type="currency"
												currencySymbol="&euro;" /></li>
          </ul>
       
	<table class="table orders-list">
		<thead>
			<tr>
				<th><i class="icon-barcode icon-white"></i> <s:text name="client.number" /></th>
				<th><i class="icon-calendar icon-white"></i> <s:text name="client.date" /></th>
				<th><i class="icon-cog icon-white"></i> <s:text name="client.operation" /></th>
				<th><i class="icon-shopping-cart icon-white"></i> <s:text name="client.price" /></th>
				<th><i class="icon-lock icon-white"></i> <s:text name="admin" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${lbalanceHistory.size() == 0}">
				<tr>
					<td colspan="5">
						<div class="empty-warning"><s:text name="orders.history.empty" /></div>
					</td>
				</tr>
			</c:if>
			<c:forEach items="${lbalanceHistory}" var="balanceHistory">
				<tr data-id="${balanceHistory.operationId}">
					<td>${balanceHistory.id}</td>
					<td><fmt:formatDate value="${balanceHistory.date}" pattern="dd/MM/yyyy - HH:mm:ss" /></td>
					<td>
						<c:choose>
							<c:when test="${balanceHistory.status == 1}">
								<s:text name="balance.status.orders" />
							</c:when>
							<c:when test="${balanceHistory.status == 2}">
								<s:text name="balance.status.contributor" />
							</c:when>
							<c:when test="${balanceHistory.status == 3}">
								<s:text name="balance.status.cash" />
							</c:when>
							<c:when test="${balanceHistory.status == 4}">
								<s:text name="balance.status.check" />
							</c:when>
								<c:when test="${balanceHistory.status == 5}">
								<s:text name="balance.status.cancel" />
							</c:when>
							<c:otherwise>
								<s:text name="balance.status.error" />
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatNumber value="${balanceHistory.price}" type="currency" currencySymbol="&euro;" /></td>
				<td><c:out value="${balanceHistory.admin}"></c:out></td>
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
			<button type="button" class="btn" id="next" ${orders.size() < 20 ? 'disabled' : ''}><s:text name="next" /> &rarr;</button>
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