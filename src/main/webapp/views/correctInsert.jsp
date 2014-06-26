<%@include file="/views/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta name="author" content="Fernando Palacios Landi" />
	<title>correct booking inserted</title>
	<link rel="stylesheet"  href="vendors/css/bootstrap.min.css" />
	<link rel="stylesheet"  href="css/main.css" />
</head>
<body>
	<header class="text-center">
		<h1>Nuevo registro de reserva insertado</h1>
		<c:out value="${model.now}" />
	</header><br/><br/>
	
	<%-- <c:out value="${model.myNewBooking.destination}"/> --%>
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<div class="alert alert-info" role="alert">
				<pre class="text-justify"><c:out value="${model.toStringBooking}"/></pre>
			</div>
		</div>
		
		<div class="col-xs-6 col-xs-offset-3 col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<div class="alert alert-warning text-center" role="alert">
				Beneficio por la venta de esta reserva:   <c:out value="${model.myBenefit}"/> Euros
			</div>
		</div>
	</div>
	
	<ul class="pager">
	  <li><a href="<c:url value="index.html"/>">Volver a Listado de Reservas</a></li>
	  <li><a href="<c:url value="insertBooking.html"/>">Insertar nueva Reservas</a></li>
	</ul>
</body>
</html>