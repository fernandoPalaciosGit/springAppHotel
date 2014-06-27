<%@include file="/views/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta name="author" content="Fernando Palacios Landi" />
	<title><fmt:message key="title" /></title>
	<link rel="stylesheet"  href="vendors/css/bootstrap.min.css" />
	<link rel="stylesheet"  href="css/main.css" />
</head>
<body>
		
	<div id="contenedor-web" class="container">
		<header class="page-header text-center">
			<h1 class="text-primary"><fmt:message key="heading"/></h1><br/>
			<div id="greet">
				<fmt:message key="greeting"/>
				<c:out value="${model.now}" />
			</div>	
			<span class="stateAjax"></span><br/>
			<div class='progress_outer'>
	            <div id='_progress' class='progress'></div>
	        </div>
		</header>

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<!-- http://localhost:8080/springAppHotel/loadBooking.html -->
				<form 	onsubmit="event.preventDefault();"
						action="<c:out value="${pageContext.request.contextPath}"/>/loadBooking.html" 
						method="POST" role="form" class="form-horizontal">
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">	
						<div class="input-group btn-block col-xs-12 col-sm-12 col-md-12 col-lg-12">
						  <span class="input-group-addon">Fecha inicial</span>
						  <input 	type="date" name="initBooking" 
						  			value='<c:out value="${model.firstOfMonth}"/>' 
						  			class="form-control" title="fecha Inicial" required="required">
						</div>
						<div class="input-group btn-block col-xs-12 col-sm-12 col-md-12 col-lg-12">
						  <span class="input-group-addon">Fecha Final</span>
						  <input 	type="date" name="lastBooking" 
						  			value='<c:out value="${model.lastOfMonth}"/>' 
						  			class="form-control" title="fecha Final" required="required">
						</div>
					</div>
					
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input 	type="submit" value="filtro por fecha" data-filter="fecha"
										class="btnBookingRequest btn btn-success btn-block">
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<input 	type="submit" value="filtro por hoteles" data-filter="hotel"
										class="btnBookingRequest btn btn-success btn-block">
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<a 	href='<c:url value="/insertBooking.html"/>' 
								class="btn btn-warning btn-block">Añadir una nueva reserva</a>
						</div>
					</div>
				</form>
			</div> <!-- FIN FORMULARIO -->
			
			<div class="table-responsive col-sm-12 col-md-12 col-lg-12">
				<table class="table" >
					<thead>
						<tr>
							<th>reserva</th>
							<th class="fecha filter">fecha de reserva</th>
							<th>destino turistico</th>
							<th class="hotel filter">hotel</th>
							<th>precio de coste</th>
							<th>precio de venta</th>
							<th>beneficio</th>
							<th>plazas reservadas</th>
							<th>fecha de checkin</th>
						</tr>
					</thead>
					<tbody>
						<!-- programar registros de reservas a traves de ajax -->
					</tbody>
				</table>
			</div> <!-- FIN TABLA -->
			
		</div>
	</div>
	
	<!-- carga jQuery desde el CDN de google -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<!-- si el CDN falla, se carga desde el servidor cliente -->
	<script>window.jQuery || document.write('<script src="vendors/js/jquery.min.js"><\/script>')</script>
	<script src="vendors/js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>