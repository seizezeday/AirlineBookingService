package bionic.project.abo.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import bionic.project.abo.entity.Flight.FlightStatus;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int flightId;
	@Column(nullable = false)
	private String ownerId;
	@Column(nullable = false)
	private String ownerName;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date soldDate = null;
	@Column(nullable = false)
	private int amount;
	@Column(nullable = false)
	private TicketStatus ticketStatus = TicketStatus.BOOKED;
	@Column(nullable = false)
	private String email;
	private double totalPrice;
	
	@ManyToOne(targetEntity=Flight.class)
	@JoinColumn(name="flightId", insertable = false, updatable = false)
	private Flight flight;

	
	public Ticket() {

	}

	@Transient
	final static String PERSISTENCE_UNIT_NAME = Flight.PERSISTENCE_UNIT_NAME;

	public enum TicketStatus {
		BOOKED, SOLD;
	}

	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	

	/**
	 * @return the bookDate
	 */
	public Date getBookDate() {
		return bookDate;
	}

	/**
	 * @param bookDate the bookDate to set
	 */
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	/**
	 * @return the soldDate
	 */
	public Date getSoldDate() {
		return soldDate;
	}

	/**
	 * @param soldDate the soldDate to set
	 */
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	
	public int getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

//	static void bookTicket(Ticket ticket) {
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//
//		Flight flight = em.find(Flight.class, ticket.getFlightNumber());
//		try {
//
//			if ((flight != null) & (flight.getFreeSeats() >= ticket.amount)) {
//				em.getTransaction().begin();
//				Flight.editAmountOfTickets(flight.getFreeSeats() - ticket.amount,
//						ticket.getFlightNumber());
//				BigDecimal price = flight.getPrice();
//				BigDecimal amount = new BigDecimal(ticket.amount);
//				ticket.setTicketPrice(price.multiply(amount));
//				em.persist(flight);
//				em.persist(ticket);
//				em.getTransaction().commit();
//			} else
//				System.out
//						.println("Amount of tickets is smaller then requested");
//		} finally {
//			em.close();
//			factory.close();
//		}
//
//	}

//	@Override
//	public String toString() {
//		String s = String
//				.format(Locale.US,
//						"%1$-5d %2$-14s %3$-16s %4$-1tF %4$-8tR  %5$-13d %6$-9.2f %7$-1s",
//						id, flightNumber, ownerId, bookDate, amount, ticketPrice,
//						ticketStatus);
//		return s;
//	}

	static void showTHeader() {
		System.out
				.println("\n========================================================="
						+ "=======================================");
		System.out.format("%s    %-15s%-17s%-20s%-15s%-10s%s", "ID",
				"FLIGHTNUMBER", "OWNERID", "BOOK TIME", "AMOUNT", "TPRICE",
				"STATUS");
		System.out
				.println("\n------------------------------------------------------------"
						+ "----------------------------------");
	}

	static void showTicket() {

		EntityManagerFactory factory;
		EntityManager em;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t",
				Ticket.class);
		List<Ticket> t = null;

		t = query.getResultList();
		showTHeader();
		for (Ticket t1 : t) {
			System.out.println(t1);
		}
		System.out.println("");
		em.close();
		factory.close();
	}
		
	/*
		 * Find all tickets that was booked more then 3 days;
		 * Remove them form Ticket table;
		 * Returning removed amount into corresponding flights "avSeats" amount
		 * 
		 * 
		 */
//	static void convertTicketsFree() {
//		EntityManagerFactory factory;
//		EntityManager em;
//		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = factory.createEntityManager();
//		Calendar gc = GregorianCalendar.getInstance();
//		gc.add(Calendar.DAY_OF_YEAR, -3);
//		TypedQuery<Ticket> query = em.createQuery(
//				"SELECT t FROM Ticket t WHERE t.bookTime < :limit",
//				Ticket.class);
//		query.setParameter("limit", gc); // (gc.getTime()).getTime()
//		List<Ticket> tickets = null;
//		tickets = query.getResultList();
//		for (Ticket ticket : tickets) {
//			Flight flight = em.find(Flight.class, ticket.getFlightNumber());
//				if(flight != null){
//					em.getTransaction().begin();
//					flight.setFreeSeats(flight.getFreeSeats() + ticket.amount);
//					em.persist(flight);
//					em.remove(ticket);
//					em.getTransaction().commit();
//				}
//
//		}
//
//	}
		
	static void showBookedTickets() {

		EntityManagerFactory factory;
		EntityManager em;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.tStatus = :status",
				Ticket.class);
		List<Ticket> t = null;
		query.setParameter("status", TicketStatus.BOOKED);
		t = query.getResultList();
		System.out.println("Booked tickets");
		showTHeader();
		for (Ticket t1 : t) {
			System.out.println(t1);
		}
		System.out.println("");
		em.close();
		factory.close();
	}
	
	
	/*
	 * 
	 * This method set status of current ticket(s) to sold
	 * 
	 */
	
	static void setTicketToSoldStatus(int id) {
		
		EntityManagerFactory factory;
		EntityManager em;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		Ticket ticket = em.find(Ticket.class,id);
		try{	
		if(ticket != null){
				em.getTransaction().begin();
				ticket.setTicketStatus(TicketStatus.SOLD);
				ticket.setSoldDate(new Date());
				//em.persist(ticket);
				em.getTransaction().commit();
		}
		}
		finally {
			em.close();
			factory.close();
			
		}
			}
		
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result
				+ ((bookDate == null) ? 0 : bookDate.hashCode());
		result = prime * result + flightId;
		result = prime * result + id;
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result
				+ ((soldDate == null) ? 0 : soldDate.hashCode());
		result = prime * result
				+ ((ticketStatus == null) ? 0 : ticketStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (amount != other.amount)
			return false;
		if (bookDate == null) {
			if (other.bookDate != null)
				return false;
		} else if (!bookDate.equals(other.bookDate))
			return false;
		if (flightId != other.flightId)
			return false;
		if (id != other.id)
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (soldDate == null) {
			if (other.soldDate != null)
				return false;
		} else if (!soldDate.equals(other.soldDate))
			return false;
		if (ticketStatus != other.ticketStatus)
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double
				.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}
		

}
