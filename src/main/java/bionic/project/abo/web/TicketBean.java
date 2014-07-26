package bionic.project.abo.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.Ticket;
import bionic.project.abo.entity.Flight.FlightStatus;
import bionic.project.abo.entity.Ticket.TicketStatus;
import bionic.project.abo.service.FService;
import bionic.project.abo.service.FlightService;
import bionic.project.abo.service.TicketService;

@Named
@Scope("session")
public class TicketBean implements Serializable {

	/**
	 * Bean for buying ticket view
	 * 
	 */
	@Inject
	private TicketService ticketService;

	@Inject
	private FService fService;

	private static final long serialVersionUID = 1L;

	private int flightId;
	private int ticketId;
	private Date bookDate = new Date();
	private Date soldDate;
	private String ownerName;
	private String ownerId;
	private int amount;
	private double totalPrice;
	private TicketStatus ticketStatus;
	private String email;
	private String departurePoint;

	private double ticketPrice;
	private String flightnumber;
	private String destination;
	private Date departureTime;

	private Flight flight;

	private List<Ticket> tickets = new ArrayList();

	private int resultsQuantity;

	public TicketBean() {

	}

	
	
	public String getDeparturePoint() {
		return departurePoint;
	}



	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getResultsQuantity() {
		return resultsQuantity;
	}

	public void setResultsQuantity(int resultsQuantity) {
		this.resultsQuantity = resultsQuantity;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public FService getfService() {
		return fService;
	}

	public void setfService(FService fService) {
		this.fService = fService;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
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

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String bookTicket(Flight flight) {
		this.flight = flight;
		this.flightId = flight.getId();
		this.flightnumber = flight.getFlightnumber();
		this.ticketPrice = flight.getPrice();
		this.destination = flight.getDestination();
		this.departurePoint = flight.getDeparturePoint();
		this.departureTime = flight.getDepartureTime();
		this.totalPrice = amount * ticketPrice;
		//

		// this.flightnumber = flightnumber;
		// this.price = price;
		return "bookTicket?faces-redirect=true";
	}

	public String submitBooking() {
		Ticket ticket = new Ticket();
		ticket.setFlightId(flightId);
		ticket.setOwnerId(ownerId);
		ticket.setOwnerName(ownerName);
		ticket.setAmount(amount);
		ticket.setEmail(email);
		ticket.setTotalPrice(ticketPrice * amount);
		ticketService.addTicket(ticket);
		flight.setFreeSeats(flight.getFreeSeats() - amount);
		fService.updateFlight(flight);
		return "bookresult?faces-redirect=true";

	}

	public String showAll() {

		List<Ticket> allTickets = ticketService.showAll();
		this.tickets = allTickets;

		resultsQuantity = allTickets.size();

		return null;
	}

	public void clearTable() {
		this.tickets = null;
		resultsQuantity = 0;
	}

	public void setFree(Ticket ticket) {
		if ((ticket.getTicketStatus().equals(TicketStatus.BOOKED))
				&& (isOverdue(ticket.getBookDate()))) {
			ticketService.removeTicket(ticket);
			tickets.remove(ticket);
			resultsQuantity = tickets.size();
		}
	}

	public void setSold(Ticket ticket) {

		if (ticket.getTicketStatus().equals(TicketStatus.SOLD)) {
			soldFailed();
			ticketService.soldTicket(ticket);
		}
		if (ticket.getFlight().getFlightStatus().equals(FlightStatus.CANCELLED)) {
			soldFailedCancelled();
		} else {
			ticketService.soldTicket(ticket);
		}

	}

	public String getFlightTickets(int flighId) {
		this.tickets = ticketService.getFlightTickets(flighId);
		this.resultsQuantity = tickets.size();
		return "admintickets?faces-redirect=true";
	}

	public boolean isOverdue(Date date) {
		int overdueDays = 3;
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -overdueDays);
		Date compareDate = cal.getTime();
		if (date.before(compareDate)) {
			return true;
		} else {
			return false;
		}
	}

	public void showOverdue() {

		List<Ticket> newtickets = new ArrayList<>();
		for (Ticket t : tickets) {
			if (isOverdue(t.getBookDate())) {
				newtickets.add(t);
			}
			tickets = newtickets;
			resultsQuantity = tickets.size();
		}

	}

	public void showBookedTickets() {
		this.tickets = ticketService.showBookedTickets();
		resultsQuantity = tickets.size();
	}

	public void soldFailed() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Selling is Failed",
				"Tickets are sold already!");
	}

	public void soldFailedCancelled() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Selling is Failed",
				"Air Flight is cancelled!");
	}

	public void addMessage(FacesMessage.Severity severity, String summary,
			String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
