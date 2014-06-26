package com.nando.springhotel.service;

import java.io.Serializable;
import java.util.List;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.domain.Hotel;

public interface BookingManager extends Serializable {
	
	public List<Booking> getBooking(String initBooking, String lastBooking);
	public List<String> getHotelName(Integer idHotel);
	public List<Hotel> getAllHotel();
	public void insertBooking(Booking newBooking);
	
}