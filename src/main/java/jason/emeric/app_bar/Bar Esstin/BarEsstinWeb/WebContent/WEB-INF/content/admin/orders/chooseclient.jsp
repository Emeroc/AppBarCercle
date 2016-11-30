<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="orders.new.pagetitle" /> &gt; <s:text name="orders.new.chooseclient.pagetitle" /></title>
	<link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/userslist/dragscrollable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/userslist/azscroller.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#clients-list').dragscrollable();
		$('#clients-selector').azscroller($('#clients-list'), $('#client-id'), $('#client-name'),$('#client-price'));
		
		$(document).keypress(
			    function(event){
			     if (event.which == '13') {
			        event.preventDefault();
			      }


			});
		$("#client-id").change(function() {
			if ($("#client-price").val()<(-10))
				  $('#password').show();
			else
				  $('#password').hide();
		});
	});
	</script>
	<div class="box padded">
		<ul class="breadcrumb">
		    <li class="active">1. <s:text name="orders.new.chooseclient.pagetitle" /> <span class="divider">&gt;</span></li>
		    <li>2. <s:text name="orders.new.chooseproducts.pagetitle" /> <span class="divider">&gt;</span></li>
		    <li>3. <s:text name="orders.new.confirm.pagetitle" /></li>
		</ul>
		<c:if test="${actionErrors.size() > 0}">
		<div class="alert alert-error">
			<p><strong><s:text name="errors" /></strong></p>
			<s:actionerror />
		</div>
		</c:if>
		<div class="row-fluid">
			<div class="span6">
				<form action="${pageContext.request.contextPath}/admin/orders/saveclient" method="POST" enctype="multipart/form-data" class="form-horizontal">
					<fieldset>
						<legend><s:text name="orders.new.chooseclient.client" /></legend>
						<p class="bottom-margin"><s:text name="orders.new.chooseclient.choose" /></p>
						<div class="control-group">
							<label class="control-label"><s:text name="name" /></label>
							<div class="controls">
								<input type="hidden" name="clientId" id="client-id"<c:if test="${client.id != -1}"> value="<c:out value="${client.id}"></c:out>"</c:if>>
								<input type="hidden" id="client-price">
								<input type="text" name="clientName" class="input-large" id="client-name" readonly<c:if test="${client.id != -1}"> value="<c:out value="${client.name}"></c:out>"</c:if>>
							</div>
						</div>
						<div class="control-group" id="password"  <c:choose>
    <c:when test="${client.balance < -10}">
      
    </c:when>
    <c:otherwise>
      style="display: none;"
    </c:otherwise>
</c:choose>>
							<label class="control-label">Mot de passe</label>
							<div class="controls">
								<input AUTOCOMPLETE=OFF type="password"  name="password" >
						</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-primary" type="submit" name="submitType" value="client"><s:text name="submit" /></button>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend><s:text name="orders.new.chooseclient.guest" /></legend>
						<p class="bottom-margin"><s:text name="orders.new.chooseclient.write" /></p>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-primary" type="submit" name="submitType" value="guest"><s:text name="submit" /></button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><s:text name="name" /></label>
							<div class="controls">
								<input type="text" name="guestName" class="input-large"<c:if test="${client.id == -1}"> value="<c:out value="${client.name}"></c:out>"</c:if>>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="span6">
				<div id="clients">
					<div class="pull-right">
						<div id="clients-selector"></div>
					</div>
					<div id="clients-list" class="clearfix">
						<div class="dragger">
							<ul>
								<c:forEach items="${clients}" var="client">
								<li><a <c:choose>
    <c:when test="${client.balance < 0}">
      style="color:#EA2A00;"
    </c:when>
    <c:otherwise>
       style="color:#00A402;"
    </c:otherwise>
</c:choose> data-userid="<c:out value="${client.id}"></c:out>" data-price="<c:out value="${client.balance}"></c:out>" data-username="<c:out value="${client.name}"></c:out>"><c:out value="${client.name} ${client.firstname}"></c:out>  (<fmt:formatNumber value="${client.balance}" type="currency" currencySymbol="&euro;" />)</a> <span class="label label-info"><c:out value="${client.year }"></c:out></span><c:if test="${client.contributor == true }"><span class="badge badge-important">C</span></c:if></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>