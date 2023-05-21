package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.RegistroDTO;
import beans.UsuarioDTO;
import service.RegistroService;
import service.UsuarioService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private RegistroService regService = new RegistroService();
	private UsuarioService usuService = new UsuarioService();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("objUsuario") == null) {
			response.sendRedirect("login");
			return;
		}
		
		String accion = request.getParameter("accion");
		if (accion == null) {
			redirigir(request, response);
		}
		else if(accion.equals("agregarRegistro")) {
			agregarRegistro(request, response);
		}
		else if(accion.equals("eliminarRegistro")) {
			eliminarRegistro(request, response);
		}
		else if(accion.equals("actualizarRegistro")) {
			actualizarRegistro(request, response);
		}
		else if(accion.equals("queryRegEgresos")) {
			queryRegEgresos(request, response);
		}
		else if(accion.equals("queryRegIngresos")) {
			queryRegIngresos(request, response);
		}
		else if(accion.equals("actualizarUsuario")) {
			actualizarUsuario(request, response);
		}
		else if(accion.equals("queryImpactoYFecha")) {
			queryImpactoYFecha(request, response);
		}
		else if(accion.equals("queryPresupuesto")) {
			queryPresupuesto(request, response);
		}
		else if(accion.equals("queryRegsParaExportar")){
			queryRegsParaExportar(request, response);
		}
		else if(accion.equals("logout")) {
			logout(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		request.getSession().invalidate();
		response.sendRedirect("login");
		
	}

	private void queryRegsParaExportar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<RegistroDTO> registros = regService.obtenRegistrosParaExportar(((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId());
		
		String data = gson.toJson(registros);
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		
	}

	private void queryPresupuesto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write(""+((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getPresupuesto());
	}

	private void queryImpactoYFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<List<RegistroDTO>> impactos = regService.obtenImpactosYFecha(((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId());
		String data = gson.toJson(impactos);
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		
	}

	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		UsuarioDTO usu = new UsuarioDTO();
		
		usu.setId(((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId());
		usu.setNombre(request.getParameter("usu_nombre"));
		usu.setEmail(request.getParameter("usu_email"));
		usu.setPassword(request.getParameter("usu_pass"));
		
		int state = usuService.actualizaUsuario(usu);
		response.setCharacterEncoding("utf-8");
		if (state == 0) {
			response.getWriter().write("Error al actualizar");
			return;
		};
		response.getWriter().write("Actualización exitosa");
		request.getSession(false).setAttribute("objUsuario", usuService.buscaXEmail(usu.getEmail()));
		
	}

	private void queryRegIngresos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("objUsuario");
		List<RegistroDTO> lista = regService.listaRegistroIngresos(usuario.getId());
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
		
	}

	private void queryRegEgresos(HttpServletRequest request, HttpServletResponse response) throws IOException {

		UsuarioDTO usuario = (UsuarioDTO)request.getSession(false).getAttribute("objUsuario");
		List<RegistroDTO> lista = regService.listaRegistroEgresos(usuario.getId());
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}

	private void actualizarRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		RegistroDTO reg = new RegistroDTO();
		reg.setId(request.getParameter("reg_id"));
		reg.setDescripcion(request.getParameter("reg_descripcion"));
		reg.setId_categoria(Integer.parseInt(request.getParameter("reg_cod_categoria")));
		reg.setImpacto(Double.parseDouble(request.getParameter("reg_impacto")));
		if(request.getParameter("reg_tipo").equals("egreso")) {
			reg.setImpacto(Math.negateExact((long)reg.getImpacto()));
		}
		reg.setId_usuario(((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId());
		int estado = regService.actualizaRegistro(reg);
		
		response.getWriter().append(""+estado);
		
	}

	private void eliminarRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String codigoReg = request.getParameter("reg_id");
		int codigoUsuario = ((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId();
		int estado = regService.eliminaRegistro(codigoReg,codigoUsuario);
		response.getWriter().append(""+estado);
	}

	private void agregarRegistro(HttpServletRequest request, HttpServletResponse response) throws IOException {

		RegistroDTO reg = new RegistroDTO();
		reg.setDescripcion(request.getParameter("reg_descripcion"));
		reg.setId_categoria(Integer.parseInt(request.getParameter("reg_cod_categoria")));
		reg.setImpacto(Double.parseDouble(request.getParameter("reg_impacto")));
		if(request.getParameter("reg_tipo").equals("egreso")) {
			reg.setImpacto(Math.negateExact((long)reg.getImpacto()));
		}
		reg.setId_usuario(((UsuarioDTO)request.getSession(false).getAttribute("objUsuario")).getId());
		int estado = regService.agregaRegistro(reg);
		
		response.getWriter().append(""+estado);
		
	}

	private void redirigir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("objUsuario");
		
		request.setAttribute("lista_registro_ingresos", regService.listaRegistroIngresos(usuario.getId()));
		request.setAttribute("lista_registro_egresos", regService.listaRegistroEgresos(usuario.getId()));
		
		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
