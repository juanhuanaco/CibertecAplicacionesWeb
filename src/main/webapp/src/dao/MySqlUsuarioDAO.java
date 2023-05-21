package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.UsuarioDTO;
import interfaces.UsuarioDAO;
import utils.MySqlDBConnection;

public class MySqlUsuarioDAO implements UsuarioDAO{

	@Override
	public UsuarioDTO buscarXEmail(String email) {
		// TODO Auto-generated method stub
		
		Connection cn = null;
		UsuarioDTO obj = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "select * from tb_usuario where email_usu = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, email);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new UsuarioDTO();
				obj.setId(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setEmail(rs.getString(3));
				obj.setPassword(rs.getString(4));
				obj.setPresupuesto(rs.getDouble(5));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int eliminar(int cod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizar(UsuarioDTO usuario) {
		int state = 0;
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_actualizarUsuario (?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, usuario.getId());
			pstm.setString(2, usuario.getNombre());
			pstm.setString(3, usuario.getEmail());
			pstm.setString(4, usuario.getPassword());
			state = pstm.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return state;
	}

	@Override
	public int agregar(UsuarioDTO usuario) {
		int state = 0;
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			
			cn = MySqlDBConnection.getConnection();
			String sql = "insert into tb_usuario values (null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, usuario.getNombre());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getPassword());
			pstm.setDouble(4, usuario.getPresupuesto());
			state = pstm.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return state;
	}


}
