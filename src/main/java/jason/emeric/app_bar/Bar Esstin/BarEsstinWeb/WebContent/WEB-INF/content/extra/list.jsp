<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="15" >
	<title><s:text name="orders.listproducts.pagetitle" /></title>
	<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
</head>
<body>
	<div class="box" style="cursor:url('/BarEsstinWeb/img/gg.ico'), pointer;font-size:200%;">
		<div class="padded">
			<table class="table table-condensed orders-list" style="width:100%;">
				<thead>
					<tr>
					<%--	<th><i class="icon-calendar icon-white"></i> <s:text name="orders.date" /></th>  --%>
					<th style="width:10%;"> <s:text name="orders.number" /></th>
						<th style="width:45%;"> <s:text name="orders.client" /></th>
						<%--<th><i class="icon-tag icon-white"></i> <s:text name="category" /></th>--%>
						<th style="width:45%;"> <s:text name="product" /></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${products}" var="item">
					<tr>
						<%--<td><fmt:formatDate value="${item.value.datePlaced}" pattern="dd/MM/yyyy - HH:mm:ss" /></td> --%>
						<td><c:out value="${item.key.id}" /></td>
						<td><c:out value="${item.value.clientName}" /></td>
						<%--<td><c:out value="${item.key.product.category.name}" /></td> --%>
						<td><c:out value="${item.key.product.name}" /><c:if test="${not empty item.key.comment}"> <span class="text-warning">(<c:out value="${item.key.comment}" />) </span></c:if></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>