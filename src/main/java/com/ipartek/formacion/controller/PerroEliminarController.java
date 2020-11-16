package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.PerroDAOSqlite;

/**
 * Servlet implementation class PerroEliminarController
 */
@WebServlet("/perro-eliminar")
public class PerroEliminarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PerroDAOSqlite dao = PerroDAOSqlite.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PerroEliminarController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroId = request.getParameter("id");
		try {
			int id = Integer.parseInt(parametroId);

			if (dao.eliminar(id)) {
				request.setAttribute("mensaje", "Perro Eliminado");
			} else {
				request.setAttribute("mensaje", "Los sentimos pero no se ha podido Eliminar el Perro");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Despues de eliminar vamos a la tabla, pero primero obtenemos el
			// ArrayList<Perro> para poder pintarlos

			// ArrayList<Perro> lista = new ArrayList<Perro>();
			// lista = dao.listar();

			// request.setAttribute("perros", lista);
			// request.setAttribute("total", lista.size());

			request.setAttribute("perros", dao.listar());
			request.setAttribute("total", dao.countDBRows());

			request.getRequestDispatcher("tabla-perros.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
