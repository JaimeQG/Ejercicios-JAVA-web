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
	private PerroDAOSqlite dao = PerroDAOSqlite.getInstance();

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
		// String se puede poner cualquier nombre, pero ese mismo nombre lo usaremos en
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

		// recibri datos del formulario, fijaros en el input el atributo 'name'
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String raza = request.getParameter("raza");
		float peso = Float.parseFloat(request.getParameter("peso"));
		boolean vacunado = (request.getParameter("vacunado") == null) ? false : true;
		String historia = request.getParameter("historia");

		Perro p = new Perro();
		p.setId(id);
		p.setNombre(nombre);
		p.setRaza(raza);
		p.setPeso(peso);
		p.setVacunado(vacunado);
		p.setHistoria(historia);

		// guardarlo en la bbdd
		try {

			if (id == 0) {
				dao.crear(p);
				mensaje = "Perro insertado con exito";
			} else {
				dao.modificar(p);
				mensaje = "Perro Modificado con exito";
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "Lo sentimos pero " + p.getNombre() + " de perro ya existe";

		} finally {

			// enviarlos a la JSP
			request.setAttribute("perro", p);
			request.setAttribute("mensaje", mensaje);
			// ir a la JSP
			request.getRequestDispatcher("formulario.jsp").forward(request, response);
		}
	}

}
