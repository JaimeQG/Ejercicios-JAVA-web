<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="includes/cabecera.jsp" />

<h1>${mensaje}</h1>

<p>* Si queremos ver la historia del perro, tendremos que acceder a
	sus detalles</p>

<table>

	<caption>Perros dados de alta en la perrera</caption>

	<thead>
		<tr id="cabecera">
			<th scope="col">Id</th>
			<th scope="col">Nombre Perro</th>
			<th scope="col">Raza Perro</th>
			<th scope="col">Peso</th>
			<th scope="col">Vacunado</th>
			<th scope="col">Acciones</th>
			<!-- <th scope="col">Historia</th> -->
		</tr>
	</thead>

	<tbody>

		<c:forEach items="${perros}" var="p">
			<tr>
				<td>${p.id}</td>
				<!-- <td><a href="perro-detalle?id=${p.id}">${p.nombre}</a></td> -->
				<td>${p.nombre}</td>
				<td>${p.raza}</td>
				<td>${p.peso}</td>
				<td>${ (p.vacunado) ? 'Si' : 'No' }</td>
				<!-- <td>${perro.historia}</td> -->
				<td>
					<!--  VER y Eliminar --> 
					<a href="perro-detalle?id=${p.id}"><i class="fa fa-eye"></i></a>
					<a href="perro-eliminar?id=${p.id}"><i class="fa fa-trash-o"></i></a>
				</td>
			</tr>
		</c:forEach>

	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">Total Perros:</td>
			<td id="total">${total}</td>
	</tfoot>
</table>
<!--  	
	 <ul>
		 <c:forEach items="${perros}" var="perro">
		 	<li>${perro.id} ${perro.nombre} ${perro.raza} ${perro.peso} ${perro.vacunado}</li>	
		 </c:forEach>
	 </ul>
	 -->

<jsp:include page="includes/pie.jsp" />