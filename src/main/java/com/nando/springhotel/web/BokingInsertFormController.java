package com.nando.springhotel.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.domain.Hotel;
import com.nando.springhotel.service.BookingInsert;
import com.nando.springhotel.service.BookingManager;

@Controller
@RequestMapping(value = "insertBooking")
public class BokingInsertFormController {

	public BokingInsertFormController() { }

	@Autowired
	private BookingManager bookingManager;
		
	
	@RequestMapping(method = RequestMethod.GET)
	public String formBookingInsert(
			//el 'path' del <form modelAttribute>, vendra de los atributos de esta clase
			@ModelAttribute("myModelBooking")
			BookingInsert bookingInsert,
			Model model) {
		
		//recupero los hoteles
		BookingInsert myBooking = new BookingInsert();
		List<Hotel> myHotels = this.bookingManager.getAllHotel();
		myBooking.setHotels(myHotels);
		
		//preparo el modelo para el formulario
		List<Hotel> mapHotels = new ArrayList<Hotel>();
		for (Hotel hotel : myBooking.getHotels()){
			mapHotels.add(hotel);
		}
		//creo un atributo(${hotels}) del modelo(myModelBooking), con un array de objetos de tipo new Hotel
		model.addAttribute("mapHotels", mapHotels);
		
//		for (Hotel hotel : myBooking.getHotels()) {
//			System.out.println(hotel.getName()+" : "+hotel.getId());
//		}
		return "insertBooking";	//devuelvo la vista con el modelAtribute mapeado
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView validateBookingInsert(HttpServletRequest request)
			throws ServletException, IOException {
		String now = (new Date()).toString();
		Booking newBooking = new Booking();
		newBooking.setHotel_id( Integer.parseInt(request.getParameter("hotels")));
		newBooking.setDestination(request.getParameter("destination"));
		newBooking.setReference(request.getParameter("booking_ref"));
		newBooking.setSupplierPrice(Double.parseDouble(request.getParameter("supplier_price")));
		newBooking.setCustomerPrice(Double.parseDouble(request.getParameter("customer_price")));
		newBooking.setBookingDate(request.getParameter("booking_date"));
		newBooking.setCheckInDate(request.getParameter("checkin_date"));
		newBooking.setNumSeats(Integer.parseInt(request.getParameter("reserved_seats")));
		
		this.bookingManager.insertBooking(newBooking); //insertar registro
		
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);	//fecha actual
		myModel.put("toStringBooking", newBooking.toString()); //mostrar el nuevo registro
		myModel.put("myBenefit", newBooking.getBenefit()); //beneficio de reserva
		
		//ERROR: pasar el objeto al modelo
		//myModel.put("myNewBooking", newBooking); //nuevo registro
		
		//DEVOLVER UN MODEL ATRIBUTE
		return new ModelAndView("correctInsert", "model", myModel);
	}
}
