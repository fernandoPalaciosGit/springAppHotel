package com.nando.springhotel.repository;
import java.util.List;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.domain.Hotel;

//interfaz que implementaran las clases conectoras a bbdd
public interface BookingDao {	
	public List<Booking> getBookingList(String initBooking, String lastBooking);
	public List<String> getHotelName(Integer idHotel);
	public void saveProduct(Booking booking);
	public List<Hotel> getHotelList();
	public void setNewBooking(Booking newBooking);
}
