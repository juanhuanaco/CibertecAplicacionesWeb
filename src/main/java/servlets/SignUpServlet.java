package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioDTO;
import service.UsuarioService;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioserv = new UsuarioService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		registrar(request, response);
	}
	
	private boolean validar(String email) {
		
		UsuarioDTO user = usuarioserv.buscaXEmail(email);
		if (user != null) return false;
		return true;
		
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		if (!validar(email)) {
			request.setAttribute("errorMsj", "¡El correo ya se encuentra registrado!");
			request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
			return;
		}
		
		String nombre = request.getParameter("nombre");
		
		String password = request.getParameter("password");
		double presupuesto = Double.parseDouble(request.getParameter("presupuesto"));
		
		UsuarioDTO user = new UsuarioDTO();
		user.setNombre(nombre);
		user.setEmail(email);
		user.setPassword(password);
		user.setPresupuesto(presupuesto);
		
		int state = usuarioserv.agregaUsuario(user);
		
		if (state == 0) {
			
			request.setAttribute("errorMsj", "No se pudo crear el usuario :c");
			request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
			return;
		};
		response.sendRedirect("login?src=signup");
		
	}
	
	

}
