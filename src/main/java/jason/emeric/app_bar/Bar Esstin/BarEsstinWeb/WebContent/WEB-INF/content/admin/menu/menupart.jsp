<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-hover" id="table<c:out value="${count}"></c:out>">
	<caption>
		<c:out value="${name}"></c:out><input type="hidden" name="menuparts[<c:out value="${count}"></c:out>].name"
			id="menuPartName" value="<c:out value="${name}"></c:out>"> <input
			type="hidden" name="menuparts[<c:out value="${count}"></c:out>].id" id="menuPartId"
			value="">
			<a onclick="removeRow($(this))"><i class="icon-remove"></i></a>
	</caption>
	<thead>
		<tr>
			<th>Nom</th>
			<th>Prix (€)</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input id="completion<c:out value="${count}"></c:out>" type="text"
				data-provide="typeahead" placeholder="Ajouter un produit"
				class="completion" autocomplete=off> <input type="hidden"
				id="inputIdAuto<c:out value="${count}"></c:out>" class="inputAutoH"></td>
			<td></td>
			<td><button class="btn btn-primary" type="button"
					onclick="addRow($('#table<c:out value="${count}"></c:out> tr:last'),$('#inputIdAuto<c:out value="${count}"></c:out>'),<c:out value="${count}"></c:out>)">Ajouter</button>
			</td>

		</tr>
	</tbody>
</table>