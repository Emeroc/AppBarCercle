<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div>
	<div class="row-fluid order-info">
		<div class="span4"># <c:out value="${order.id}" /></div>
		<div class="span8"><small><s:text name="orders.for" /></small> <c:out value="${order.clientName}" /></div>
	</div>
	<table class="table table-condensed orders-list" data-orderid="<c:out value="${order.id}"></c:out>">
		<tr>
			<th><s:text name="product" /></th>
			<th><s:text name="category" /></th>
			<th><s:text name="price" /></th>
		</tr>
		<c:forEach items="${order.orderItems}" var="item">
			<tr <c:if test="${true == item.cancelled}">class="error"</c:if> > 
					<c:choose>
								<c:when test="${not empty item.product}">
									<td><c:out value="${item.product.name}" /> <c:if
											test="${not empty item.comment}">
											<span class="text-warning">(<c:out value="${item.comment}" />)
											</span>
										</c:if></td>
								</c:when>
								<c:otherwise>
									<td>Menu <c:out value="${item.menu.name}" /> <c:if
											test="${not empty item.comment}">
											<span class="text-warning">(<c:out value="${item.comment}" />)
											</span>
										</c:if></td>
								</c:otherwise>
							</c:choose>
				<td><c:out value="${item.product.category.name}" /></td>
				<td><fmt:formatNumber value="${item.orderItemPrice}" type="currency" currencySymbol="&euro;" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2"></td>
			<td><b><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="&euro;" /></b></td>
		</tr>
	</table>
</div>