package interfaces;

import java.util.List;
import beans.RegistroDTO;

public interface RegistroDAO {

	//Busca un único registro de un usuario en particular
	public RegistroDTO buscarXCodigo (String codigoRegistro, int codigoUsuario);
	//Lista todos los registros de un usuario en particular
	public List<RegistroDTO> obtenerRegistrosParaExportar (int codigoUsuario);
	//Lista los registros que reflejan ingresos de un usuarios en particular
	public List<RegistroDTO> listarIngresos (int codigoUsuario);
	//Lista los registros que reflejan egresos de un usuarios en particular
	public List<RegistroDTO> listarEgresos (int codigoUsuario);
	
	//Obtiene los impactos (ingresos y egresos) y sus fechas de un Usuario
	public List<List<RegistroDTO>> obtenerImpactosYFecha (int codigoUsuario);
	
	//Elimina un registro en particular
	public int eliminar (String codigoRegistro, int codigoUsuario);
	//Actualizar un registro en particular
	public int actualizar (RegistroDTO registro);
	//Agregar un registro en particular
	public int agregar (RegistroDTO registro);
	
	
}
