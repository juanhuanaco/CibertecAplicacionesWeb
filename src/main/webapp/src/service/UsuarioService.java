package service;

import beans.UsuarioDTO;
import dao.DAOFactory;
import interfaces.UsuarioDAO;
import utils.Constantes;

public class UsuarioService {

	DAOFactory factory = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	UsuarioDAO objUsuario = factory.getUsuario();
	
	public UsuarioDTO buscaXEmail(String email) {
		return objUsuario.buscarXEmail(email);
	}
	
	public int eliminaUsuario(int cod) {
		return objUsuario.eliminar(cod);
	}
	
	public int actualizaUsuario (UsuarioDTO usuario) {
		return objUsuario.actualizar(usuario);
	}
	
	public int agregaUsuario (UsuarioDTO usuario) {
		return objUsuario.agregar(usuario);
	}
	
}
