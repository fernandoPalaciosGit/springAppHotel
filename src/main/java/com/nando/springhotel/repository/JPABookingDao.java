package com.nando.springhotel.repository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nando.springhotel.domain.Booking;
import com.nando.springhotel.domain.Hotel;

//@Repository permite acceso directo a base de datos a traves de beans
@Repository(value = "bookingDao")
public class JPABookingDao implements BookingDao{
	
	//clase de conexion a bbdd
	private EntityManager em = null;

	/*@Persistence es utilizada en la claseJPAProductDao
	para inyectar automáticamente el EntityManagerr*/
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	/*@Transactional: los metodos se ejecutan de manera transaccional,
	si hay algun error de commit en las sentencias del metodo, se ejecutara un rollback*/
	
	//recupero todos los productos en stock
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Booking> getBookingList(String initBooking, String lastBooking, String filter_type){
		//FROM class Product  -> p.id [son las propiedades de la clese]
		/*NOTA HQL: si hacemos select de atributos de registros, 
		no nos devolvera el objeto inicializado, sino una lista de objetos con propiedades, dentro de un array*/
		
		String query = new String();
		if ( "fecha".equals(filter_type) ){ //FILTRO POR FECHA
			query = 	  "SELECT b FROM Booking b "	//cada registro corresponde con un <Booking>
					+ "WHERE b.booking_date >= '"+initBooking+"' "
					+ "AND b.booking_date < '"+lastBooking+"' "
					+ "ORDER BY b.booking_date ASC";
			
		}else if ( "hotel".equals(filter_type) ){	//FILTRO POR HOTEL DE DESTINO
			query = 	  "SELECT b FROM Booking b "	//cada registro corresponde con un <Booking>
					+ "WHERE b.booking_date >= '"+initBooking+"' "
					+ "AND b.booking_date < '"+lastBooking+"' "
					+ "ORDER BY b.hotel_id ASC";
		}
		
		System.out.println(query);
		Query q = em.createQuery( query );
		List<Booking> empList = q.getResultList();	//Ljava.lang.Object
		
		return empList;
	}
	
	//recuperar el hotel de destino
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String> getHotelName(Integer idHotel){
		String query = 	  "SELECT h.name FROM Hotel h, Booking b "
						+ "WHERE b.hotel_id = "+idHotel+" "
						+ "AND h.id = b.hotel_id";
		Query q = em.createQuery( query );
		List<String> hotelName = q.getResultList();
		return hotelName;
	}

	
	public void saveProduct(Booking booking) { 
		em.merge(booking);
	}

	//recuperar los hotel de la BBDD
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelList() {
		String query = "SELECT h FROM Hotel h";
		Query q = em.createQuery( query );
		List<Hotel> hotels = q.getResultList();
		return hotels;
	}

	//almacenamos el producto pasado por parametro en la base de datos
	@Transactional(readOnly = false)
	public void  setNewBooking(Booking newBooking) {
		em.merge(newBooking);
	}
}
