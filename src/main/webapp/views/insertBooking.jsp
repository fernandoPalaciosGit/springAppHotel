<%@include file="/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta name="author" content="Fernando Palacios Landi" />
	<link rel="stylesheet"  href="vendors/css/bootstrap.min.css" />
	<link rel="stylesheet"  href="css/main.css" />
	<title><fmt:message key="title"/></title>
</head>
<body>
	<header class="page-header text-center">
		<h1><fmt:message key="insertbooking.heading"/></h1>
		<a href="<c:url value="index.html"/>">Regresar a Listado de Reservas</a>
	</header>
	
	<%-- <form:input path="atributo_BookingInsert"/> --%>
	<%-- <form:errors path="atributo_BookingInsert" cssClass="error"/> --%>
	<!-- con los controles instanciamos la clase BookingInsert y enviamos los datos por post -->
	<form:form method="POST" modelAttribute="myModelBooking" class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">hoteles de reservas</label>
			<div class="col-sm-3">
				<!-- List<Hotel> -->
				<form:select class="form-control" multiple="false" path="hotels">
					<!-- con <options> iteras sobre un ArrayList -->
					<form:options items="${mapHotels}" itemValue="id" itemLabel="name"/>
				</form:select>
			</div>
	   	</div>
	   	
	   	<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">destino turistico</label>
			<div class="col-sm-3">
				<!-- varchar(10) -->
				<form:input class="form-control" path="destination"/>
			</div>
	   	</div>
	   	
	   	<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">identificador de reserva</label>
			<div class="col-sm-3">
				<!-- varchar(10) -->
			    <form:input class="form-control" path="booking_ref"/>
			</div>
	   	</div>
	  
	  	<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">precio de coste</label>
			<div class="col-sm-3">
			    <!-- decimal(6,2) --> 
				<form:input class="form-control" path="supplier_price"/>
			</div>
	   	</div> 
	   		
		<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">precio de venta</label>
			<div class="col-sm-3">
			    <form:input class="form-control" path="customer_price"/>
				<!-- decimal(6,2) -->
			</div>
	   	</div>
		
		
		<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">fecha de reserva</label>
			<div class="col-sm-3">
			    <form:input type="date" class="form-control" path="booking_date"/>
				<!-- date -->
			</div>
	   	</div>
			
		<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">fecha de checkin</label>
			<div class="col-sm-3">
			    <form:input type="date" class="form-control" path="checkin_date"/>
				<!-- date, > booking_date-->
			</div>
	   	</div>
					
		<div class="form-group">
			<label class="col-sm-3 col-sm-offset-1 control-label">plazas reservadas</label>
			<div class="col-sm-3">
			    <form:input type="number" class="form-control" min="0" path="reserved_seats"/>
				<!-- Int(3), >0  -->
			</div>
	   	</div>
					
		<div class="form-group">
	      <div class="col-sm-offset-4 col-sm-3">
	         <input type="submit" value="Insertar" class="btn btn-info input-block-level"/>
	      </div>
	   </div>
	</form:form>
</body>
</html>