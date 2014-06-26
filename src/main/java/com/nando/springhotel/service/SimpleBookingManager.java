package com.nando.springhotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.domain.Hotel;
import com.nando.springhotel.repository.BookingDao;

@Component //permitira a las clases que agreguen BookingManager, la inicializacion de metodos abstractos
public class SimpleBookingManager implements BookingManager {

	private static final long serialVersionUID = 1L;

	//TESTEO: implementamos los productos
	//private List<Product> products;
	
	//implementamos l capa de persistencia de datos
	@Autowired
	private BookingDao bookingDao;
	
	// pasamos el array de productos [List<Product>] a modificar
	public void setProductDao(BookingDao bookingDao) {
		//throw new UnsupportedOperationException();
		this.bookingDao = bookingDao;
	}
	
	//recuperara la lista de productos
	public List<Booking> getBooking(String initBooking, String lastBooking) {
		return bookingDao.getBookingList(initBooking, lastBooking);		
	}

	public List<String> getHotelName(Integer idHotel) {
		return bookingDao.getHotelName(idHotel);
	}

	public List<Hotel> getAllHotel() {
		return bookingDao.getHotelList();
	}

	public void insertBooking(Booking newBooking) {
		bookingDao.setNewBooking(newBooking);
	}
}
