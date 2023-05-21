<%@tag import="beans.RegistroDTO, java.util.ArrayList" language="java" description="Generador de Tabla Registro" pageEncoding="UTF-8"%>
<%@attribute name="idTabla" required="true"%>
<%@attribute name="listaRegistros" required="true" type="ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id='<%=idTabla%>' class='table table-hover'>
	<thead>
		<tr>
			<th scope='col'>CODIGO</th>
			<th scope='col'>DESCRIPCION</th>
			<th scope='col'>IMPACTO</th>
			<th scope='col'>CATEGORIA</th>
			<th scope='col'>FECHA</th>
			<th scope='col' colspan='2'>ACCIONES</th>
		</tr>
	</thead>
		
		
		<tbody id='<%=idTabla%>Body' class='table-group-divider'>
		
		<c:if test="<%=listaRegistros != null%>">
			<%for(RegistroDTO obj : (ArrayList<RegistroDTO>)listaRegistros){%>
			
				<tr>
					<th scope='row'><%=obj.getId()%></th>
					<td><%=obj.getDescripcion()%></td>
					<td><%=obj.getImpacto()%></td>
					<td><%=obj.getCategoria()%></td>
					<td><%=obj.getFecha()%></td>
					<td align='center'>
						<img data-regid='<%=obj.getId()%>' data-regdesc='<%=obj.getDescripcion()%>' data-regimpact='<%=obj.getImpacto()%>' data-regidcat='<%=obj.getId_categoria()%>' role='button' src='img/editar.png' title='Editar' class='btnTableActionEdit btn btn-light' data-bs-toggle='modal' data-bs-target='#tableEditModal'>
					</td>
					<td align='center'>
						<img role='button' src='img/eliminar.png' title='Eliminar' class='btnTableActionDelete btn btn-light' data-regid='<%=obj.getId()%>'>
					</td>
				</tr>
			
			<%}%>
		
		</c:if>
		
		</tbody>
		</table>
		
	