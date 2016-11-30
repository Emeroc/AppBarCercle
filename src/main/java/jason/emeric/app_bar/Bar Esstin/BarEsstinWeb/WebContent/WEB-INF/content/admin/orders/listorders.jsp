<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="orders.list.pagetitle" /></title>
<link href="${pageContext.request.contextPath}/css/orders.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('.list-viewer li')
									.click(
											function() {
												$(document)
														.scrollTop(
																$(
																		'#order'
																				+ $(
																						this)
																						.attr(
																								'data-orderid'))
																		.offset().top-40);
											});

							$('.orders-list')
									.each(
											function(i) {
												var orderid = $(this).attr(
														'data-orderid');

												$(this)
														.find('button')
														.each(
																function(i) {
																	$(this)
																			.click(
																					function() {
																						$
																								.post(
																										'${pageContext.request.contextPath}/admin/orders/deliver',
																										{
																											orderId : orderid,
																											itemId : $(
																													this)
																													.attr(
																															'data-itemid')
																										});

																						var cell = $(
																								this)
																								.parent();
																						cell
																								.html('<i class="icon-ok"></i>');
																					});
																});
											});
						});
	</script>
	<div class="box">
		<div class="title">
			<s:text name="orders.list.pagetitle" />
			<c:if test="${orders.size() > 0}"> (<c:out
					value="${orders.size()}" />)</c:if>
		</div>
		<c:choose>
			<c:when test="${orders.size() == 0}">
				<div class="empty-warning">
					<s:text name="orders.list.empty" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="list-viewer">
					<ul>
						<c:forEach items="${orders}" var="order">
							<li data-orderid="<c:out value="${order.id}" />"
								class="order-<c:out value="${order.status}" />">
								<h2>
									#
									<c:out value="${order.id}" />
								</h2>
								<h3>
									<c:out value="${order.clientName}" />
								</h3>
								<p>
									<c:choose>
										<c:when test="${order.status == 'pending'}">
											<s:text name="orders.status.pending" />
										</c:when>
										<c:otherwise>
											<s:text name="orders.status.partial" />
										</c:otherwise>
									</c:choose>
								</p>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<c:forEach items="${orders}" var="order">
		<form action="${pageContext.request.contextPath}/admin/orders/deliver"
			method="POST">
			<div class="box padded" id="order<c:out value="${order.id}" />">
				<div class="row-fluid order-info">
					<div class="span4">
						#
						<c:out value="${order.id}" />
					</div>
					<div class="span8">
						<small><s:text name="orders.for" /></small>
						<c:out value="${order.clientName}" />
					</div>
				</div>
				<table class="table table-condensed orders-list"
					data-orderid="<c:out value="${order.id}" />">
					<tr>
						<th><s:text name="product" /></th>
						<th><s:text name="category" /></th>
						<th><s:text name="orders.list.deliverproduct" /></th>
					</tr>
					<c:forEach items="${order.orderItems}" var="item">
						<tr>
							<c:choose>
								<c:when test="${not empty item.product}">
									<td><c:out value="${item.product.name}" /> <c:if
											test="${not empty item.comment}">
											<span class="text-warning">(<c:out
													value="${item.comment}" />)
											</span>
										</c:if></td>
								</c:when>
								<c:otherwise>
									<td>Menu <c:out value="${item.menu.name}" /> <c:if
											test="${not empty item.comment}">
											<span class="text-warning">(<c:out
													value="${item.comment}" />)
											</span>
										</c:if></td>
								</c:otherwise>
							</c:choose>
							<td><c:out value="${item.product.category.name}" /></td>
							<td><c:if test="${not empty item.product}">
									<c:choose>
										<c:when test="${item.delivered}">
											<i class="icon-ok"></i>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-mini btn-success"
												data-itemid="<c:out
													value="${item.id}" />">
												<s:text name="deliver" />
											</button>
										</c:otherwise>
									</c:choose>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="row-fluid">
					<div class="span4 text-center">
						<s:text name="date" />
						<fmt:formatDate value="${order.datePlaced}"
							pattern="dd/MM/yyyy - HH:mm:ss" />
					</div>
					<div class="span8 text-right">
						<input type="hidden" name="orderId"
							value="<c:out value="${order.id}" />" />
						<button type="submit" class="btn btn-info">
							<s:text name="orders.list.deliverall" />
						</button>
					</div>
				</div>
			</div>
		</form>
	</c:forEach>
</body>
</html>