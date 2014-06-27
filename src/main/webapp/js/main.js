var	AJAX = {
		xhr: null,
		countdown: null,
		timeoutAjax : 3500, //limite de tiempo de espera de petición
		shim: function(){
			if( window.XMLHttpRequest )
				this.xhr = new XMLHttpRequest();
			else if( window.ActiveXObject )
				this.xhr = new ActiveXObject("Microsoft.XMLHTTP");
			else
				this.xhr = null;
		},
		status: {
			READY_STATE_UNINITIALIZED : 0,
			READY_STATE_LOADING : 1,
			READY_STATE_LOADED : 2,
			READY_STATE_INTERACTIVE : 3,
			READY_STATE_COMPLETE : 4 //si ha terminado de recibir los datos
		},
		_progress : document.getElementById('_progress'),
		stateAjax : document.querySelector(".stateAjax")
};

var init = function(){
	AJAX.shim();
	//seleccionar tipo de filtro: .btnBookingRequest
	$(".btnBookingRequest").on("click", function(e_click){
		e_click.preventDefault();
		var type_filter = $(this).data("filter");
		
		if( AJAX.xhr ){
			bookingRequest(type_filter);
		} else
			AJAX.stateAjax.innerText = "Este navegador no soporta peticiones asíncronas";
		
	});
};

var bookingRequest = function(filter){
	if( document.forms[0].checkValidity() ){
		console.log(filter);
		
		//configuramos parametros de peticion
		var formData = $('form').serializeArray();
		formData.push( {name: "filter_type", value: filter} );
		var filter_param = "";
		for ( var param in formData) {
			var key = formData[param].name;
			var value = formData[param].value;
			filter_param += key+"="+value+"&";
		}
		filter_param = filter_param.slice(0, -1);
		console.log(filter_param);
		$(".table").hide();
		AJAX.xhr = $.ajax({
			//tipo de dato devuelto por el server
			dataType: 'json',
			//tipo de documeto del cliente
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			url : $("form")[0].action,
			//modificar encabezado de peticion, tipo de documento del servidor
			headers: { Accept: 'application/json;charset=UTF-8' },
			
			data : filter_param,
			type : $("form")[0].method,
			timeout: AJAX.timeoutAjax,
			beforeSend: function(xhr, settings){
				console.dir(settings); //configuracion de encabezados
				$(".filter").removeClass("order");
				AJAX.stateAjax.innerText = "Buscando Reservas...";
				AJAX._progress.style.width = "0%";
				// vaciamos el layer de las peticiones
				document.querySelector(".table tbody").innerHTML = "";
			},
			success : ajaxCallback,
			error: function(xhr, status, error) {
				console.log("ERROR Ajax: "+
						xhr.status+"(AJAX.xhr.status) : "+
						xhr.readyState+"(AJAX.xhr.readyState) : "+
						error+"(AJAX.xhr.statusText)");
				
				if( 		AJAX.xhr.readyState === AJAX.status.READY_STATE_UNINITIALIZED )
					error = "conexion fallida, intentarlo mas tarde";
				else if( 	AJAX.xhr.readyState === AJAX.status.READY_STATE_COMPLETE 
							&& AJAX.xhr.status >= 400 )
					error = "Error de peticion Ajax al Servidor";
				else if( 	AJAX.xhr.readyState === AJAX.status.READY_STATE_COMPLETE 
							&& AJAX.xhr.status >= 500 )
					error = "Error Interno del Servidor";
				
				AJAX.stateAjax.classList.add("upload");
				AJAX.stateAjax.innerText = "["+xhr.status+"] "+error;
				AJAX.xhr.abort(); 	
				AJAX.xhr = null;	//reseteamos el objeto ajax
		    },
		    complete :function(){
		    	//simpre se ejecuta,haya o no error
		    	//controlar el tipo de filtro
		    	var filterTag = $("."+filter);
		    	filterTag.addClass("order");
//		    	.fecha
//		    	.hotel
//		    	<span class="order">fecha de reserva</span><br/>
//				<span class="caret"></span>
		    
		    },
		    xhr: function(){
		    	// get the native XmlHttpRequest object
		        var xhr = $.ajaxSettings.xhr();
		        xhr.upload.onprogress = function(e){
		    		if(e.lengthComputable){
		    			var load = Math.round((e.loaded / e.total) * 100) + '%';
		    			AJAX._progress.style.width = load;
		    		}
		    	};
		    	return xhr ;
		    }
		});
		
	} else
		AJAX.stateAjax.innerText = "Los valores de fecha no son correctos";
	
	return false;
};

//recupera los datos de la petición AJAX
var ajaxCallback = function(dataResponse){
	AJAX.stateAjax.classList.remove("upload");
	AJAX.stateAjax.innerText = "Peticion finalizada...";
	
	for(var objBooking in dataResponse){
		var tr = document.createElement("tr");
		var registroBooking = dataResponse[objBooking.toString()];
		
		//cada registro de reserva
		for(var dataBooking in registroBooking){
			var td = document.createElement("td");
			var dato = registroBooking[dataBooking.toString()];
			//marcar registros vacios
			if( dato === "" ){
				dato = "sin especificar";
				td.classList.add('noData');
			}
			//marcar los meses del año con clases para css
			if( dataBooking.toString() === "fecha_reserva" ){
				var bookingDate = new Date(dato);	//objeto fecha
				var month = toStringMonth(bookingDate.getMonth()); //mes
				//marco la celda de la fecha
				td.dataset.month = month;
			}
			//añadimos los datos al registro
			td.innerText = dato;
			tr.appendChild(td);
		}
		
		tr.classList.add(month); //clases css al registro tr
		document.querySelector(".table tbody").appendChild(tr);
	}
	
	//console.dir(dataResponse);
	$(".table").fadeIn('slow');
};

var toStringMonth = function(month){
	switch (month) {
	case 0:
		month = "January"; break;
	case 1:
		month = "February"; break;
	case 2:
		month = "March"; break;
	case 3:
		month = "April"; break;
	case 4:
		month = "May"; break;
	case 5:
		month = "June"; break;
	case 6:
		month = "July"; break;
	case 7:
		month = "August"; break;
	case 8:
		month = "September"; break;
	case 9:
		month = "October"; break;
	case 10:
		month = "November"; break;
	case 11:
		month = "December"; break;
	}
	return month;
}

window.addEventListener("load", init, false);