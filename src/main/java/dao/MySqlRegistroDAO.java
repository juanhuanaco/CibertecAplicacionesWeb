package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.RegistroDTO;
import interfaces.RegistroDAO;
import utils.MySqlDBConnection;

public class MySqlRegistroDAO implements RegistroDAO{

	@Override
	public RegistroDTO buscarXCodigo(String codigo_registro, int codigo_usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<RegistroDTO> obtenerRegistrosParaExportar(int codigo_usuario) {
		
		List<RegistroDTO> data = new ArrayList<RegistroDTO>();
		RegistroDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_obtenerRegistrosParaExportar(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo_usuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setId(rs.getString(1));
				obj.setDescripcion(rs.getString(2));
				obj.setImpacto(rs.getDouble(3));
				obj.setCategoria(rs.getString(4));
				obj.setFecha(rs.getDate(5));
				
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

	@Override
	public int eliminar(String codigoRegistro, int codigoUsuario) {
		
		int estado = 0;
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_eliminarRegistro (?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, codigoRegistro);
			pstm.setInt(2, codigoUsuario);
			estado = pstm.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int actualizar(RegistroDTO reg) {
		
		int estado = 0;
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_actualizarRegistro (?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, reg.getId());
			pstm.setString(2, reg.getDescripcion());
			pstm.setDouble(3, reg.getImpacto());
			pstm.setInt(4, reg.getId_categoria());
			pstm.setInt(5, reg.getId_usuario());
			estado = pstm.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int agregar(RegistroDTO reg) {
		
		int estado = 0;
		Connection cn = null;
		PreparedStatement pstm = null;

		try {
			
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_agregarRegistro (?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, reg.getDescripcion());
			pstm.setDouble(2, reg.getImpacto());
			pstm.setInt(3, reg.getId_categoria());
			pstm.setInt(4, reg.getId_usuario());
			estado = pstm.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public List<RegistroDTO> listarIngresos(int codigoUsuario) {
		
		List<RegistroDTO> data = new ArrayList<RegistroDTO>();
		RegistroDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_listarRegistrosIngresos(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigoUsuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setId(rs.getString(1));
				obj.setDescripcion(rs.getString(2));
				obj.setImpacto(rs.getDouble(3));
				obj.setId_categoria(rs.getInt(4));
				obj.setCategoria(rs.getString(5));
				obj.setFecha(rs.getDate(6));
				obj.setId_usuario(codigoUsuario);
				
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

	@Override
	public List<RegistroDTO> listarEgresos(int codigoUsuario) {
		
		List<RegistroDTO> data = new ArrayList<RegistroDTO>();
		RegistroDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_listarRegistrosEgresos(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigoUsuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setId(rs.getString(1));
				obj.setDescripcion(rs.getString(2));
				obj.setImpacto(rs.getDouble(3));
				obj.setId_categoria(rs.getInt(4));
				obj.setCategoria(rs.getString(5));
				obj.setFecha(rs.getDate(6));
				obj.setId_usuario(codigoUsuario);
				
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

	@Override
	public List<List<RegistroDTO>> obtenerImpactosYFecha(int codigoUsuario) {
		
		List<List<RegistroDTO>> result = new ArrayList<List<RegistroDTO>>();
		
		List<RegistroDTO> data = new ArrayList<RegistroDTO>();
		RegistroDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_obtenerSumatoriaGeneralImpactosYFecha(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigoUsuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setImpacto(rs.getDouble(1));
				obj.setFecha(rs.getDate(2));
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		result.add(data);
		
		
		data = new ArrayList<RegistroDTO>();
		obj = null;
		cn = null;
		pstm = null;
		rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_obtenerSumatoriaIngresoImpactosYFecha(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigoUsuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setImpacto(rs.getDouble(1));
				obj.setFecha(rs.getDate(2));
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		result.add(data);
		
		
		
		data = new ArrayList<RegistroDTO>();
		obj = null;
		cn = null;
		pstm = null;
		rs  = null;
		
		try {
			cn = MySqlDBConnection.getConnection();
			String sql = "call usp_obtenerSumatoriaEgresoImpactosYFecha(?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigoUsuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				obj = new RegistroDTO();
				obj.setImpacto(rs.getDouble(1));
				obj.setFecha(rs.getDate(2));
				data.add(obj);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		result.add(data);
		
		return result;
		
		
	}

}
