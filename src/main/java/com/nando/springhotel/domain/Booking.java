package com.nando.springhotel.domain;

import java.io.Serializable;

//mapeo de la base de datos
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
	// lo usaremos para la persistencia de los datos en nuestra sesion
	private static final long serialVersionUID = 1L;

	//las propiedades de este objeto tienen que coincidir su nombre con las de los rgistro de la tabla a la hora de hacer la query, para que se inicializen cono objetos
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer hotel_id;
	private String booking_ref;
	private String booking_date;
	private String destination;
	private Double supplier_price;
	private Double customer_price;
	private Integer reserved_seats;
	private String checkin_date;
	
	public Double getBenefit() {
		Double benefit = this.getCustomerPrice() - this.getSupplierPrice();
		return benefit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getReference() {
		return booking_ref;
	}

	public void setReference(String reference) {
		this.booking_ref = reference;
	}

	public Double getSupplierPrice() {
		return supplier_price;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplier_price = supplierPrice;
	}

	public Double getCustomerPrice() {
		return customer_price;
	}

	public void setCustomerPrice(Double customerPrice) {
		this.customer_price = customerPrice;
	}

	public Integer getNumSeats() {
		return reserved_seats;
	}

	public void setNumSeats(Integer numSeats) {
		this.reserved_seats = numSeats;
	}

	public String getBookingDate() {
		return booking_date;
	}

	public void setBookingDate(String bookingDate) {
		this.booking_date = bookingDate;
	}

	public String getCheckInDate() {
		return checkin_date;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkin_date = checkInDate;
	}
	
	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Destino turistico: \""+this.destination+"\";\n");
		buffer.append("Precio de coste: "+this.supplier_price+" Euros;\n");
		buffer.append("Precio de venta: "+this.customer_price+" Euros;\n");
		buffer.append("Numero de asientos resevados: "+this.reserved_seats+";\n");
		buffer.append("Fecha de reserva: ["+this.booking_ref+"] "+this.booking_date+";\n");
		buffer.append("Fecha de checkin: "+this.checkin_date);
		return buffer.toString();
	}
}
