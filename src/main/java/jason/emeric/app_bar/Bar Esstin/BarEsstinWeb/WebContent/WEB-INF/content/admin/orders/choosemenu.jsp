<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:forEach items="${products}" var="menupart">
	<div class="category-item">
		<h1><c:out value="${menupart.key.name}"></c:out></h1>
		<div class="products-list">
			<ul>
				<c:forEach items="${menupart.value}" var="product">
				<c:if test="${product.enable == true && product.available==true }">
					<li data-productid="<c:out value="${product.id}"></c:out>" class="menupart-item"><h2><c:out value="${product.name}"></c:out></h2></li>
					</c:if>
				</c:forEach>
			</ul>
			<input type="hidden" name="menupart.id" value="<c:out value="${menupart.key.id}"></c:out>">
			<input type="hidden" name="menupart.product" value="-1">
			<input type="hidden" name="menupart.name">
		</div>
	</div>
</c:forEach>