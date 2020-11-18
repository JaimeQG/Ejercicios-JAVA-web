<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="includes/cabecera.jsp" />


<h1>${perro.nombre}</h1>


*TODO maquetar el resto de campos del perro
<hr>
${perro}

<hr>
${perro.historia}

<hr>
<div class="contenedor">
	<a class="boton" href="perro-eliminar?id=${perro.id}">Eliminar Perro</a>
</div>

<jsp:include page="includes/pie.jsp" />




