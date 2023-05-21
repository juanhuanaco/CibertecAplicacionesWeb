<%@ page
	import="beans.UsuarioDTO, beans.RegistroDTO, java.util.ArrayList"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session='false'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Inicio - Expenses Tracker</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>


<style>
.et-left-sidebar {
	min-width: 280px;
}

body {
	height: 100vh;
	display: flex;
	flex-direction: row;
	overflow: hidden;
}

main {
	height: 100%;
	overflow: auto;
}

i.bx {
	font-size: 1.5em;
	padding-right: 10px;
}
</style>
</head>
<body>
	<%
	
	UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("objUsuario");
	%>

	<div class="d-flex flex-column p-3 bg-light et-left-sidebar">
		<p class="h4 fw-bold" style="text-align: center">EXPENSES TRACKER</p>

		<ul class="nav nav-pills flex-column mb-auto">
			<li><span id="defaultSelectedNav"
				class="nav-link active d-flex align-items-center"
				data-view="view_listar"><i class='bx bx-list-ul'></i> Listado</span></li>
			<li><span class="nav-link link-dark d-flex align-items-center"
				data-view="view_agregar"><i class='bx bx-plus-circle'></i>
					Agregar registro</span></li>
			<li><span class="nav-link link-dark d-flex align-items-center"
				data-view="view_estadisticas"><i class='bx bx-stats'></i>
					Estadísticas</span></li>
			<li><span class="nav-link link-dark d-flex align-items-center"
				data-view="view_ajustes"><i class='bx bxs-cog'></i> Ajustes</span></li>
		</ul>
		<hr>
		<span class="fw-bold d-flex align-items-center">
			<i class='bx bxs-user-circle'></i> <%=usuario.getNombre()%>
			<a class="link-danger ps-3" style="height: 24px" href="dashboard?accion=logout"> <i class='bx bx-log-out'></i> </a> 	
			
		</span>
		
	</div>
	<main class="flex-fill p-3">
		<div id="defaultShowedView" data-view="view_listar">
			<h2>
				Bienvenido:
				<%=usuario.getNombre()%></h2>
			<hr>
			<div class="d-flex align-items-center mb-2">
				<span class="h3 mb-0 me-2">Ingresos del mes</span> <img
					src="img/profit.png" />
			</div>

			<%
			ArrayList<RegistroDTO> listaIngresos = (ArrayList<RegistroDTO>) request.getAttribute("lista_registro_ingresos");
			%>
			<t:tablaRegistro idTabla="tablaIngresos" listaRegistros="<%=listaIngresos%>"></t:tablaRegistro>
			
			<div class="d-flex align-items-center mb-2">
				<span class="h3 mb-0 me-2">Gastos del mes</span> <img
					src="img/loss.png" />
			</div>
			<%
			ArrayList<RegistroDTO> listaEgresos = (ArrayList<RegistroDTO>) request.getAttribute("lista_registro_egresos");
			%>
			
			<t:tablaRegistro idTabla="tablaEgresos" listaRegistros="<%=listaEgresos%>"></t:tablaRegistro>
			
		</div>
		<div data-view="view_agregar" hidden="true">
			<h2>Agregar registro</h2>
			<hr>
			<form id="addRegForm" name="addRegForm">

				<div class="mb-3">
					<input id="reg_tipo_ingreso" name="reg_tipo" type="radio"
						value="ingreso" hidden="true" checked="checked"> <label
						for="reg_tipo_ingreso" id="addRegForm_btnRegTypeIngreso"
						class="btn btn-success">Ingreso</label> <input
						id="reg_tipo_egreso" name="reg_tipo" type="radio" value="egreso"
						hidden="true"> <label for="reg_tipo_egreso"
						id="addRegForm_btnRegTypeEgreso" class="btn btn-light border-dark">Egreso</label>
				</div>

				<div class="form-floating mb-3">
					<input type="text" name="reg_descripcion" id="input_desc"
						class="form-control" placeholder="desc" required> <label
						for="input_desc">Descripción</label> <span hidden="true"
						class="dangerMsgAgregarFrm text-danger">La descripcion no
						puede estar vacía</span>
				</div>

				<div class="form-floating mb-3">
					<input type="number" name="reg_impacto" id="input_impac"
						class="form-control" placeholder="50" required> <label
						for="input_impac">Impacto Económico</label> <span hidden="true"
						class="dangerMsgAgregarFrm text-danger">El formato del
						monto debe ser [999][999.9] ó [999.99] y no puede ser 0.00</span>
				</div>
				<div class="mb-3">
					<span>Categoría:</span> <select id="selectCategoriasEgresos"
						class="form-select" name="reg_cod_categoria" hidden="true"
						disabled="disabled">
						<option value="1">Alimentación</option>
						<option value="2">Cuentas y Pagos</option>
						<option value="3">Casa</option>
						<option value="4">Transporte</option>
						<option value="5">Ropa</option>
						<option value="6">Salud e Higiene</option>
						<option value="7">Diversión</option>
						<option value="8">Otros gastos</option>
					</select> <select id="selectCategoriasIngresos" class="form-select"
						name="reg_cod_categoria">
						<option value="9">Salario o Nómina</option>
						<option value="10">Honorarios</option>
						<option value="11">Negocio Propio o Comerciante</option>
						<option value="12">Dividendos o Participaciones</option>
						<option value="13">Pensión</option>
						<option value="14">Rentas de Capital</option>
						<option value="15">Ocasional</option>
					</select>
				</div>

				<div class="form-floating mb-3"></div>
				<button type="button" id="btnAgregarForm" class="btn btn-primary">Agregar
					registro</button>

			</form>

		</div>
		<div data-view="view_estadisticas" hidden="true">
			<h2>Estadísticas</h2>
			<hr>
			<div class="d-flex flex-column ps-5 pe-5">
				<div>
					<div class="d-flex justify-content-center mb-3">
						<span class="fw-bold p-4 rounded bg-light me-3 border border-dark">Presupuesto Base: <%=usuario.getPresupuesto()%></span>
						<span class="fw-bold p-4 rounded bg-light ms-3 border border-dark" id="chartPresupuesto_preActual">Presupuesto Actual:</span>
					</div>
					<div style="height: 250px">
						<canvas id="chartPresupuesto"></canvas>
					</div>
				</div>
				<hr>
				<div class="d-flex justify-content-evenly">
					<div style="height: 180px; width: 45%" >
						<canvas id="chartIngresos"></canvas>
					</div>
	
					<div style="height: 180px; width: 45%" >
						<canvas id="chartEgresos"></canvas>
					</div>
				</div>
				
			</div>
		</div>
		<div data-view="view_ajustes" hidden="true">
			<h2>Ajustes</h2>
			<hr>

			<form name="editSettingsForm">

				<div class="mb-3">
					<label for="input_nombre">Nombre de usuario</label> <input
						type="text" name="usu_nombre" id="input_nombre"
						class="form-control" value='<%=usuario.getNombre()%>'>
				</div>

				<div class="mb-3">
					<label for="input_email">Correo electrónico</label> <input
						type="email" name="usu_email" id="input_email"
						class="form-control" value='<%=usuario.getEmail()%>'>
				</div>
				<div class="mb-3">
					<label for="input_pass">Contraseña</label> <input type="password"
						name="usu_pass" id="input_pass" class="form-control"
						value='<%=usuario.getPassword()%>'>
				</div>

				<button type="button" id="editSettingsForm_btnSend"
					class="btn btn-primary">Guardar cambios</button>


			</form>
			<br>
			<button id="exportRegBtn" role="button" class="btn btn-success">Exportar Registros</button>
		</div>
	</main>

	<!-- Modal -->
	<div class="modal fade" id="tableEditModal" tabindex="-1"
		aria-labelledby="tableEditModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="tableEditModalLabel">Editar
						registro</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div id="tableEditModalBody" class="modal-body">

					<form id="tableEditModalForm" name="tableEditModalForm">

						<input id="tableEditModalForm_inputRegType" type="hidden"
							name="reg_tipo"> <input
							id="tableEditModalForm_inputRegId" name="reg_id"
							class="form-control-plaintext mb-3" type="text"
							readonly="readonly">

						<div class="form-floating mb-3">
							<input type="text" name="reg_descripcion"
								id="tableEditModalForm_inputRegDesc" class="form-control"
								placeholder="desc"> <label
								for="tableEditModalForm_inputRegDesc">Descripción</label> <span
								hidden="true"
								class="tableEditModalForm_dangerMsgEditFrm text-danger">La
								descripcion no puede estar vacía</span>
						</div>

						<div class="form-floating mb-3">
							<input type="number" name="reg_impacto"
								id="tableEditModalForm_inputRegImpact" class="form-control"
								placeholder="50" required> <label
								for="tableEditModalForm_inputRegImpact">Impacto
								Económico</label> <span hidden="true"
								class="tableEditModalForm_dangerMsgEditFrm text-danger">El
								formato del monto debe ser [999][999.9] ó [999.99] y no puede
								ser 0.00</span>
						</div>
						<div class="mb-3">
							<span>Categoría:</span> <select
								id="tableEditModalForm_selectCatEgreso" class="form-select"
								name="reg_cod_categoria" hidden="true" disabled="disabled">
								<option value="1">Alimentación</option>
								<option value="2">Cuentas y Pagos</option>
								<option value="3">Casa</option>
								<option value="4">Transporte</option>
								<option value="5">Ropa</option>
								<option value="6">Salud e Higiene</option>
								<option value="7">Diversión</option>
								<option value="8">Otros gastos</option>
							</select> <select id="tableEditModalForm_selectCatIngreso"
								class="form-select" name="reg_cod_categoria">
								<option value="9">Salario o Nómina</option>
								<option value="10">Honorarios</option>
								<option value="11">Negocio Propio o Comerciante</option>
								<option value="12">Dividendos o Participaciones</option>
								<option value="13">Pensión</option>
								<option value="14">Rentas de Capital</option>
								<option value="15">Ocasional</option>
							</select>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cerrar</button>
					<button id="tableEditModal_btnSave" type="button"
						class="btn btn-primary">Guardar cambios</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<script type="module" src="./js/dashboard.js"></script>
	<script type="module" src="./js/dashboard_agregar.js"></script>
	<script type="text/javascript" src="./js/dashboard_estadisticas.js"></script>
	<script type="text/javascript" src="./js/dashboard_ajustes.js"></script>
</body>
</html>