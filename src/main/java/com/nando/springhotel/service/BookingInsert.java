package com.nando.springhotel.service;

import java.util.Date;
import java.util.List;

import com.nando.springhotel.domain.Hotel;

//clase para los inputs del formulario de nuevas reserva
public class BookingInsert {
	
	private List<Hotel> hotels; //Enum Hotel: hotel_id + name
	
	//FALTA CONTROLAR ERRORES
	private String destination; //varchar(100)
	private String booking_ref; //varchar(10)
	private Double supplier_price; //decimal(6,2)
	private Double customer_price; //decimal(6,2)
	private Date booking_date; //date
	private Date checkin_date; //date
	private int reserved_seats; //Int(3)
	
	public BookingInsert() {}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBooking_ref() {
		return booking_ref;
	}

	public void setBooking_ref(String booking_ref) {
		this.booking_ref = booking_ref;
	}

	public Double getSupplier_price() {
		return supplier_price;
	}

	public void setSupplier_price(Double supplier_price) {
		this.supplier_price = supplier_price;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	public Double getCustomer_price() {
		return customer_price;
	}

	public void setCustomer_price(Double customer_price) {
		this.customer_price = customer_price;
	}

	public Date getCheckin_date() {
		return checkin_date;
	}

	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}

	public int getReserved_seats() {
		return reserved_seats;
	}

	public void setReserved_seats(int reserved_seats) {
		this.reserved_seats = reserved_seats;
	}
}
