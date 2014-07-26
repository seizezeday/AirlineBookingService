package bionic.project.abo.entity;



import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

/**
 * @author Vasya
 * 
 */
@Entity
public class Flight implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 35, unique = true)
	private String flightnumber;
	
	@Column(nullable = false)
	private String destination;
	
	@Column(nullable = false)
	private String departurePoint;
	// @Temporal(TemporalType.TIMESTAMP)
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date departureTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalTime;
	// private java.sql.Timestamp deptime;
	
	@Column(nullable = false)
	private Integer freeSeats;
	
	@Column(nullable = true)
	private Double price;
	
	@Column(nullable = false)
	private FlightStatus flightStatus = FlightStatus.ACTIVE;
	
	@Column(nullable = false)
	private String airCompany;

	public Flight() {

	}

	public String getFrom() {
		return departurePoint;
	}

	public void setFrom(String from) {
		this.departurePoint = from;
	}

	public enum FlightStatus {
		ACTIVE, CANCELLED
	}

	@Transient
	final static String PERSISTENCE_UNIT_NAME = "ABO";

	
	
	public String getDeparturePoint() {
		return departurePoint;
	}

	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date depTime) {
		this.departureTime = depTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getId() {
		return id;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer avSeats) {
		this.freeSeats = avSeats;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAirCompany() {
		return airCompany;
	}

	public void setAirCompany(String airCompany) {
		this.airCompany = airCompany;
	}

//	public static void showFlightHeader() {
//		System.out
//				.println("\n=========================================================="
//						+ "=======================================");
//		System.out.format("%-15s%-17s%-20s%-20s%-10s%s", "FLIGHTNUMBER",
//				"DESTINATION", "DEPARTURE TIME", "AVAILABLE SEATS", "PRICE",
//				"STATUS");
//		System.out
//				.println("\n----------------------------------------------------------"
//						+ "----------------------------------");
//	}

	@Override
	public String toString() {

		String s = String.format(Locale.US,
				"%1$-14s %2$-16s %3$-1tF %3$-8tR       %4$-13d %5$-9f %6$-1s",
				this.flightnumber, this.destination, this.departureTime,
				this.freeSeats, this.price, this.flightStatus);
		return s;
	}

//	static void showFlights() {
//
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//
//		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f",
//				Flight.class);
//		List<Flight> f = null;
//
//		f = query.getResultList();
//		showFlightHeader();
//		for (Flight f1 : f) {
//			System.out.println(f1);
//		}
//		System.out.println("");
//		em.close();
//		factory.close();
//	}

//	public static void addFlight(Flight flight) {
//
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//		em.getTransaction().begin();
//
//		em.persist(flight);
//
//		em.getTransaction().commit();
//
//		em.close();
//		factory.close();
//
//	}

//	static void deleteFlight(String flightnumber) {
//
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//		Flight flight = em.find(Flight.class, flightnumber);
//		try {
//			if (flight != null) {
//				em.getTransaction().begin();
//				flight.setFlightStatus(FlightStatus.CANCELLED);
//				em.persist(flight);
//				em.getTransaction().commit();
//			}
//		} finally {
//			em.close();
//		}
//
//	}
//
//	static void editAmountOfTickets(int tickets, String flightnumber) {
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//		Flight flight = em.find(Flight.class, flightnumber);
//		try {
//			if (flight != null) {
//				em.getTransaction().begin();
//				flight.setFreeSeats(tickets);
//				;
//				em.persist(flight);
//				em.getTransaction().commit();
//			}
//		} finally {
//			em.close();
//		}
//	}
//
//	static void showRegisteredFlights() {
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//		Calendar gc = GregorianCalendar.getInstance();
//		gc.add(Calendar.HOUR, -1);
//		TypedQuery<Flight> query = em
//				.createQuery("SELECT f FROM Flight f WHERE f.deptime > :limit",
//						Flight.class);
//		query.setParameter("limit", gc); // (gc.getTime()).getTime()
//		List<Flight> f = null;
//
//		f = query.getResultList();
//		showFlightHeader();
//		for (Flight f1 : f) {
//			System.out.println(f1);
//		}
//		System.out.println("");
//		em.close();
//		factory.close();
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (freeSeats != other.freeSeats)
			return false;
		if (departureTime != other.departureTime)
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (flightnumber == null) {
			if (other.flightnumber != null)
				return false;
		} else if (!flightnumber.equals(other.flightnumber))
			return false;
		return true;
	}

}
