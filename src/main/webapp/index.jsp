<h1>Soy la pagina de inicio</h1>

<%
	// podemos combinar HTML + Java = JSP - Java Server Page
	out.print("<p>Este parrafo esta escrito en Java</p>");
%>

<p>Esto hace una peticion GET al controlador</p>
<a href="perroController">Listado de Perros</a>

<form method="post" action="perroController">
 <p>Esto envia datos por POST al controlador</p>
 <input type="text" name="nombre" placeholder="Nombre del perro" required>
 <br>
 <input type="text" name="raza" placeholder="Raza del perro" required>
 <br>
 <input type="text" name="peso" placeholder="Peso del perro" required>
 <br>
 Marca si esta Vacunado
 <input type="checkbox" name="vacunado">
 <br>
 <textarea name="historia" cols="30" rows="5" placeholder="Cuentame la historia del perro"></textarea> 
 <br>
 <input type="submit" value="Enviar">
</form>

