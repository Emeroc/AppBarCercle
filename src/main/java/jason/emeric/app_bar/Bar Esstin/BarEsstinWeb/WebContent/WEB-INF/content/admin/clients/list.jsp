<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="clients.new.pagetitle" /> &gt; <s:text
		name="clients.new.chooseclient.pagetitle" /></title>
<link href="${pageContext.request.contextPath}/css/orders.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/userslist/dragscrollable.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/userslist/azscroller.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#clients-list').dragscrollable();
					$('#clients-selector').azscroller($('#clients-list'),
							$('#client-id'), $('#client-name'),"");
					/*	$('#client-id')
								.change(
										function() {
											$('#myModal')
													.modal(
															{
																remote : "${pageContext.request.contextPath}/admin/clients/modal?id="
																		+ $(
																				'#client-id')
																				.val()
															});
										});*/
				});
		$('body').on('hidden', '.modal', function() {
			$(this).removeData('modal');
		});

		function clientedit() {
			if ($('#client-id').val() !="")
				$('#myModal')
						.modal(
								{
									remote : "${pageContext.request.contextPath}/admin/clients/modalclientedit?id="
											+ $('#client-id').val()
								});
		}

		function clienthistory() {
			if ($('#client-id').val() !="")
			document.location.href = "${pageContext.request.contextPath}/admin/clients/clienthistory?id="
					+ $('#client-id').val();
		}

		function clientmoney() {
			if ($('#client-id').val() !="")
			$('#myModal')
					.modal(
							{
								remote : "${pageContext.request.contextPath}/admin/clients/modalmoney?id="
										+ $('#client-id').val()
							});

		}
		
		function clientdelete() {
			if ($('#client-id').val() !="")
			$('#myModal')
					.modal(
							{
								remote : "${pageContext.request.contextPath}/admin/clients/modaldelete?id="
										+ $('#client-id').val()
							});

		}
		
		function mail() {
			$('#myModal')
					.modal(
							{
								remote : "${pageContext.request.contextPath}/admin/clients/modalmail"
							});

		}
		function charts() {
			<c:set var="now" value="<%=new java.util.Date()%>" />
			var d = new Date();
			document.location.href = "${pageContext.request.contextPath}/admin/clients/chart?year="
					+ d.getFullYear()+"&date="+"<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />";
		}
	</script>
	<div>
		<div class="control-group"></div>

	</div>
	<div class="box padded">
		<div class="control-group form-horizontal">
			<label class="control-label"><s:text name="name" /></label>
			<div class="controls ">
				<input type="hidden" name="clientId" id="client-id"
					<c:if test="${client.id != -1}"> value="${client.id}"</c:if>>
				<input type="text" name="clientName" class="input-large"
					id="client-name" readonly
					<c:if test="${client.id != -1}"> value="${client.name}"</c:if>>

				<a class="btn" onclick="clientedit()"><i class="icon-user"></i></a>
				<a class="btn" onclick="clienthistory()"><i class="icon-time"></i></a>
				<a class="btn" onclick="clientmoney()"><i class="icon-"><strong>€</strong></i></a>
				<a class="btn" onclick="clientdelete()"><i class="icon-remove"></i></a>
				|
				<a class="btn" onclick="mail()"><i class="icon-envelope"></i></a>
				<a class="btn" onclick="charts()"><i class="icon-signal"></i></a>
				<a class="btn" href="${pageContext.request.contextPath}/admin/admin/list"><i class="icon-lock"></i></a> <a
					href="${pageContext.request.contextPath}/admin/clients/add"
					class="pull-right"><img
					src="${pageContext.request.contextPath}/img/plus.png"
					class="img-circle"></a>
			</div>
		</div>

		<c:if test="${actionErrors.size() > 0}">
			<div class="alert alert-error">
				<p>
					<strong><s:text name="errors" /></strong>
				</p>
				<s:actionerror />
			</div>
		</c:if>
		<div class="row-fluid">
			<input type="hidden" name="clientId" id="client-id">
			<div class="span12">
				<div id="clients">
					<div class="pull-right">
						<div id="clients-selector"></div>
					</div>
					<div id="clients-list">
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
</c:choose> data-userid="<c:out value="${client.id}"></c:out>"
										data-username="<c:out value="${client.name}"></c:out>"><c:out value="${client.name}"></c:out>
											<c:out value="${client.firstname}"></c:out> (<fmt:formatNumber
												value="${client.balance}" type="currency"
												currencySymbol="&euro;" />)
									</a> <span class="label label-info"><c:out value="${client.year }"></c:out></span>
									<c:if test="${client.contributor == true }"><span class="badge badge-important">C</span></c:if></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 id="myModalLabel">Gestion Utilisateur</h4>
		</div>
		<div class="modal-body"></div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Annuler</button>
			<a class="btn btn-primary btn-danger" onclick="$('#ff').submit();">Confirmer</a>
		</div>
	</div>
</body>
</html>