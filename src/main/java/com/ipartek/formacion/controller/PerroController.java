package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.PerroDAOSqlite;
import com.ipartek.formacion.pojo.Perro;

/**
 * @WebServlet("/perro-controller") es la URL donde escucha este controlador
 */
@WebServlet("/perro-controller")
public class PerroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Perro> lista = new ArrayList<Perro>();

		// conseguir perros llamado al modelo
		try {
			PerroDAOSqlite dao = PerroDAOSqlite.getInstance();
			// conseguir datos llamando al modelo ( bbdd )
			lista = dao.listar();

		} catch (Exception e) {

			e.printStackTrace();

		} /*
			 * finally { lista.add(new Perro("buba", "boxer", 3)); lista.add(new
			 * Perro("Snoppy", "cruce", 3)); lista.add(new Perro("pulgas", "otra", 20)); }
			 */

		// Los DATOS (atributos) a enviar a la vista
		// request.setAttribute( String, Objeto)
		// String se puede poner cualquier nombre, ppero ese mismo nombre lo usaremos en
		// la JSP para acceder al Objeto
		// Objeto se puede enviar lo que querais: String, boolean, Perro,
		// ArrayList<Perro>, ...
		request.setAttribute("perros", lista);
		request.setAttribute("mensaje", "Recuperados " + lista.size() + " perros");
		request.setAttribute("total", lista.size());

		// Comando para ir a la VISTA, hacemos un "forward" y escribimos el nombre de la
		// JSP
		request.getRequestDispatcher("tabla-perros.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mensaje = "";

		// recibir datos del formulario, fijaros en el input el atributo 'name'
		String nombre = request.getParameter("nombre");
		String raza = request.getParameter("raza");
		float peso = Float.parseFloat(request.getParameter("peso"));
		String vacunado = request.getParameter("vacunado");
		String historia = request.getParameter("historia");

		Perro p = new Perro();
		p.setNombre(nombre);
		p.setRaza(raza);
		p.setPeso(peso);
		p.setVacunado((vacunado == null) ? false : true);
		p.setHistoria(historia);

		// guardarlo en la BBDD
		try {
			PerroDAOSqlite dao = PerroDAOSqlite.getInstance();
			dao.crear(p);
			mensaje = "Perro insertado con exito";

		} catch (Exception e) {
			e.printStackTrace();
			// request.setAttribute("mensaje", e.getMessage() );
			mensaje = "*** ERROR: Ya existe el perro " + nombre + " en la BBDD";
		} finally {

			// enviarlos a la JSP
			request.setAttribute("perro", p);
			request.setAttribute("mensaje", mensaje);

			// ir a la JSP
			request.getRequestDispatcher("perro.jsp").forward(request, response);
		}
	}

}
