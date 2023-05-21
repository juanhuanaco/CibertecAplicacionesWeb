package dao;

import interfaces.RegistroDAO;
import interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory{

	@Override
	public UsuarioDAO getUsuario() {
		return new MySqlUsuarioDAO();
	}

	@Override
	public RegistroDAO getRegistro() {
		return new MySqlRegistroDAO();
	}

}
