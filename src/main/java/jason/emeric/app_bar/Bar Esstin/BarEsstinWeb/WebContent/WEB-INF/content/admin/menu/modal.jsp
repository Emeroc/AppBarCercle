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
Êtes-vous sûr de vouloir supprimer ce menu : <c:out value="${menu.name}"></c:out>
<form name="input" action="${pageContext.request.contextPath}/admin/menu/deletemenu?id=<c:out value="${menu.id}"></c:out>" method="POST"></form> 
</body>
</html>