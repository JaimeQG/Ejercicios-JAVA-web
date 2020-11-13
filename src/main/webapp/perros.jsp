<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de perros</title>
<style>
		table,td {
			border: 1px solid black;
	}
	</style>
</head>
<body>

	<h1>Perrera</h1>
		
	<table>
		 <tr>
    		<th scope="col">Id</th>
    		<th scope="col">Nombre Perro</th>
    		<th scope="col">Raza Perro</th>
  		</tr>

	<c:forEach items="${perros}" var="perro">
	<tr>
		<td>${perro.id}</td>
		<td>${perro.nombre}</td>
		<td>${perro.raza}</td>
	</tr>
	</c:forEach>
	</table>
	<!--  	
	 <ul>
		 <c:forEach items="${perros}" var="perro">
		 	<li>${perro.id} ${perro.nombre} ${perro.raza}</li>	
		 </c:forEach>
	 </ul>
	 -->
	


</body>
</html>