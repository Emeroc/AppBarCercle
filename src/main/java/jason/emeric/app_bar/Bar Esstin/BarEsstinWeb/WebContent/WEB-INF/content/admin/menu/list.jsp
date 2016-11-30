<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion de la carte</title>
<link href="${pageContext.request.contextPath}/css/orders.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript">
	</script>
	<legend>
		<s:text name="products" />
		<a href="${pageContext.request.contextPath}/admin/menu/product" class="pull-right"><img src="${pageContext.request.contextPath}/img/plus.png" class="img-circle"></a>
	</legend>
	<div class="span8 accordion" id="accordion2" style="height:400px;">
	  
		<c:forEach items="${products}" var="category">
		<div class="accordion-group">
		<div class="accordion-heading">
		<a class="accordion-toggle"  data-toggle="collapse" data-parent="#accordion2" href="#collapse<c:out value="${category.key.id}" />">
<c:out value="${category.key.name}" />
</a>
</div>
			
				<div id="collapse<c:out value="${category.key.id}" />" class="accordion-body collapse" style="overflow:auto; max-height:240px;">
<div class="accordion-inner">
					<div class="category-item">
				
				<div class="products-list">
					<ul>
						<c:forEach items="${category.value}" var="product">
							<li data-productid="<c:out value="${product.id}" />" onclick="location.href='${pageContext.request.contextPath}/admin/menu/product?id=<c:out value="${product.id}" />';">
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
								<h2>${product.name}</h2>
								<p>
									<fmt:formatNumber value="${product.price}" type="currency"
										currencySymbol="&euro;" />
								</p></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</div>
			</div>
				</div>
		</c:forEach>

	</div>
</body>
</html>