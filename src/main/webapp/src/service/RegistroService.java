package service;

import java.util.List;

import beans.RegistroDTO;
import dao.DAOFactory;
import interfaces.RegistroDAO;
import utils.Constantes;

public class RegistroService {

	DAOFactory factory = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	RegistroDAO objRegistro = factory.getRegistro();
	
	public RegistroDTO buscaXCodigo (String codigoRegistro, int codigoUsuario) {
		return objRegistro.buscarXCodigo(codigoRegistro, codigoUsuario);
	}
	
	public List<RegistroDTO> obtenRegistrosParaExportar (int codigoUsuario){
		return objRegistro.obtenerRegistrosParaExportar(codigoUsuario);
	}
	
	public int eliminaRegistro (String codigoRegistro, int codigoUsuario) {
		return objRegistro.eliminar(codigoRegistro, codigoUsuario);
	}
	
	public int actualizaRegistro (RegistroDTO registro) {
		return objRegistro.actualizar(registro);
	}
	
	public int agregaRegistro (RegistroDTO registro) {
		return objRegistro.agregar(registro);
	}
	
	public List<RegistroDTO> listaRegistroIngresos (int codigoUsuario){
		return objRegistro.listarIngresos(codigoUsuario);
	}
	
	public List<RegistroDTO> listaRegistroEgresos (int codigoUsuario){
		return objRegistro.listarEgresos(codigoUsuario);
	}
	
	public List<List<RegistroDTO>> obtenImpactosYFecha (int codigoUsuario){
		return objRegistro.obtenerImpactosYFecha(codigoUsuario);
	}
	
	
}
