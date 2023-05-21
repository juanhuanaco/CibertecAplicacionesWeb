package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UsuarioDTO;
import service.UsuarioService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UsuarioService usuarioserv = new UsuarioService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		validar(request, response);
	}

	private void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UsuarioDTO user = usuarioserv.buscaXEmail(email);
		if (user == null) {
			request.setAttribute("errorMsj", "No existe tal Email");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		else if (!user.getPassword().contentEquals(password)) {
			request.setAttribute("errorMsj", "Contraseña incorrecta");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("objUsuario", user);
		response.sendRedirect("dashboard");
	}
	

}
