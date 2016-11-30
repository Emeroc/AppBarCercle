<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion des suppléments</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function() {		
	$(document).keypress(
		    function(event){
		     if (event.which == '13') {
		        event.preventDefault();
		      }


		});
	$('body').on('hidden', '.modal', function () {
		  $(this).removeData('modal');
		});
});

</script>
	<legend>
		<s:text name="menu.extra"/>
	</legend>
	<div class="span8 accordion" id="accordion2" style="height: 400px;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prix (€)</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${l}" var="extra">
					<tr>
						<td><c:out value="${extra.name}"></c:out></td>
						<td><c:out value="${extra.price}"></c:out></td>
						<td><a href="${pageContext.request.contextPath}/admin/menu/extradetails?id=<c:out value="${extra.id}"></c:out>" class="btn btn-warning">Modifier</a></td>
						<td><a href="${pageContext.request.contextPath}/admin/menu/modalextra?id=<c:out value="${extra.id}"></c:out>" class="btn btn-danger" data-target="#myModal" data-toggle="modal">Supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${pageContext.request.contextPath}/admin/menu/extradetails" class="btn btn-success">Ajouter un supplément</a>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h4 id="myModalLabel">Confirmation : suppression du supplément</h4>
		</div>
		<div class="modal-body">
			<p>Attention cette action est irreversible !!!</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Annuler</button>
			<a class="btn btn-primary btn-danger"
				onclick="$('.modal-body > form').submit();">Confirmer</a>
		</div>
	</div>
</body>
</html>