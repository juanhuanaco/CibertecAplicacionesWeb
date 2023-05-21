package interfaces;

import beans.UsuarioDTO;

public interface UsuarioDAO {

	//Busqueda de un usuario especifico mediante email
	public UsuarioDTO buscarXEmail (String email);
	//Eliminacion de un usuario especifico mediante codigo
	public int eliminar (int cod);
	//Actualizacion de un usuario especifico mediante nuevo objeto de tipo UsuarioDTO
	public int actualizar (UsuarioDTO usuario);
	//Creacion de un nuevo usuario mediante nuevo objeto de tipo UsuarioDTO
	public int agregar (UsuarioDTO usuario);
}

