<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Êtes-vous sûr de vouloir réactiver cet admin :
	<c:out value="${admin.login}"></c:out>
	<form name="input"
		action="${pageContext.request.contextPath}/admin/admin/reactivateadmin?id=<c:out value="${admin.id}"></c:out>"
		method="POST">
		<div class="control-group">
			<label class="control-label" for="inputPassword">Mot de passe</label>
			<div class="controls">
				<input type="password" autocomplete="off" id="inputPassword"
					name="password" placeholder="Le mot de passe">
			</div>
		</div>
	</form>
</body>
</html>