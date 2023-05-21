package dao;

import interfaces.RegistroDAO;
import interfaces.UsuarioDAO;

public abstract class DAOFactory {

	public static final int MYSQL = 1;

	public abstract UsuarioDAO getUsuario();
	public abstract RegistroDAO getRegistro();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL:
			return new MySqlDAOFactory();
		}
		return null;
	}
}
