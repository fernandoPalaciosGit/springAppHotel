package com.nando.springhotel.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.service.BookingManager;

//controlador de pagina de Listado de reservas (/views/index.html)
@Controller
public class CRSController{

	@Autowired
	private BookingManager bookingManager;
	
	//peticion al index, la primera vez, sin listado de reservas
	@RequestMapping(value = "index")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LocalDate localDate = LocalDate.now();
		
		String nowToString = (new Date()).toString();
		String firstDayOfMontth = localDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
		String lastDayOfMontth = localDate.with(TemporalAdjusters.lastDayOfMonth()).toString(); 
		
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", nowToString);
		myModel.put("firstOfMonth", firstDayOfMontth);
		myModel.put("lastOfMonth", lastDayOfMontth);
		
		return new ModelAndView("index", "model", myModel);
	}

	//mapea la peticion al index cuando hay peticion ajax, mostrar listado de reservas
	@RequestMapping(value = "loadBooking", method = RequestMethod.POST)
	public @ResponseBody
	List<Object> loadBooking
	(	@RequestParam ("initBooking") String initBooking, 
		@RequestParam ("lastBooking") String lastBooking,
		@RequestParam ("filter_type") String filter_type
		) {
		
		List<Booking> myBookings = this.bookingManager.getBooking( initBooking, lastBooking, filter_type);
		
		System.out.println(myBookings); //print toString @override
		System.out.println(myBookings.getClass()); //class java.util.ArrayList
		System.out.println("PARAM_REQUEST: "+initBooking+"&"+lastBooking+"&"+filter_type);
		
		List<Object> printBooking = new ArrayList<Object>();
		//devolvemos los datos, en orden (LinkedHashMap), que imprimiremos en la tabla recuperada por ajax
		for (int i = 0, len = myBookings.size(); i < len; i++) {
			Map<String, Object> book = new LinkedHashMap<String, Object>();
			
			//obtener el nombre del hotel (de otra tabla)
			List<String> hotelName = this.bookingManager.getHotelName(myBookings.get(i).getHotel_id());
			
			book.put("reserva", myBookings.get(i).getReference());
			book.put("fecha_reserva", myBookings.get(i).getBookingDate());
			book.put("destino", myBookings.get(i).getDestination());
			book.put("hotel", hotelName.get(0));
			book.put("precio_coste", myBookings.get(i).getSupplierPrice());
			book.put("precio_venta", myBookings.get(i).getCustomerPrice());
			book.put("beneficio", myBookings.get(i).getBenefit());
			book.put("asientos", myBookings.get(i).getNumSeats());
			book.put("checkin", myBookings.get(i).getCheckInDate());
			printBooking.add(i, book);
		}
		
		System.out.println(printBooking);
		return printBooking;
	}
}