package bionic.project.abo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import bionic.project.abo.entity.Airport;
import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.Flight.FlightStatus;
import bionic.project.abo.service.AirportService;
import bionic.project.abo.service.FService;

@Named
@Scope("session")
public class FlightBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3363137139903630896L;

	@Autowired
	private FService fService;

	@Autowired
	private AdminBean adminBean;

	@Autowired
	private TicketBean ticketBean;
	
	@Inject
	private AirportService airportService;

	// @Autowired
	// private AdminBean adminBean;

	
	
	public AdminBean getAdminBean() {
		return adminBean;
	}

	public AirportService getAirportService() {
		return airportService;
	}

	public void setAirportService(AirportService airportService) {
		this.airportService = airportService;
	}

	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}

	public void setfService(FService fService) {
		this.fService = fService;
	}

	private int id;
	private Date departureTime;
	private Date arrivalTime;
	private Integer freeSeats;
	private Double price;
	private String departurePoint;
	private String destination;
	private String airCompany;
	private String flightnumber;
	private FlightStatus flightStatus;
	private int resultsQuantity;
	private int amount;
	private int[] amounts = { 1, 2, 3, 4, 5, 6 };
	private String[] flightStatuses = { "ACTIVE", "CANCELLED" };
	private static List<Airport> airports = new ArrayList<>();
//	{
//		cities.add("London");
//		cities.add("Boston");
//	}

	@PostConstruct
	public void init(){
		airports = airportService.showAll();
		
	}
	
	
	private List<Flight> flights;
	private Date currentDate = new Date();

	public FlightBean() {

		flights = new ArrayList<>();
	}

	
	
	
	public static List<Airport> getAirports() {
		return airports;
	}

	public static void setAirports(List<Airport> airports) {
		FlightBean.airports = airports;
	}

	

	public String[] getFlightStatuses() {
		return flightStatuses;
	}

	public void setFlightStatuses(String[] flightStatuses) {
		this.flightStatuses = flightStatuses;
	}

	public TicketBean getTicketBean() {
		return ticketBean;
	}

	public void setTicketBean(TicketBean ticketBean) {
		this.ticketBean = ticketBean;
	}

	public int[] getAmounts() {
		return amounts;
	}

	public void setAmounts(int[] amounts) {
		this.amounts = amounts;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getResultsQuantity() {
		return resultsQuantity;
	}

	public void setResultsQuantity(int resultsQuantity) {
		this.resultsQuantity = resultsQuantity;
	}

	public FService getfService() {
		return fService;
	}

	public void setFService(FService fService) {
		this.fService = fService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer freeSeats) {
		this.freeSeats = freeSeats;
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

	public String getDeparturePoint() {
		return departurePoint;
	}

	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public String showAllFlights() {

		List<Flight> foundFlights = fService.showAll();
		this.flights = foundFlights;

		resultsQuantity = foundFlights.size();
		System.out.println(amount);

		return "searchResults";
	}

	public String searchFlight() {
		List<Flight> foundFlights = fService.searchFlight(this.departurePoint,
				this.destination, this.departureTime, this.amount);
		this.flights = foundFlights;
		resultsQuantity = foundFlights.size();
		ticketBean.setAmount(this.amount);
		return "searchResults?faces-redirect=true";
	}

	public String addFlight() {
		Flight flight = new Flight();
		flight.setAirCompany(this.airCompany);
		flight.setFlightnumber(this.flightnumber);
		flight.setDeparturePoint(this.departurePoint);
		flight.setDestination(this.destination);
		flight.setDepartureTime(this.departureTime);
		flight.setArrivalTime(this.arrivalTime);
		flight.setFreeSeats(this.freeSeats);
		flight.setPrice(this.price);
		fService.addFlight(flight);
		// List<Flight> fl = new ArrayList<>();
		// fl.add(flight);
		adminBean.updateFlightList(flight);
		// flights.add(flight);

		return "Admin?faces-redirect=true";
	}

	public String clearFields() {
		this.airCompany = "";
		this.arrivalTime = null;
		this.departurePoint = "";
		this.destination = "";
		this.freeSeats = 0;
		this.price = 0.0;
		this.departureTime = null;
		
		return "AddFlight";
	}

	public String searchFlightByDate() {
		List<Flight> foundFlights = fService
				.searchFlightByDate(this.departureTime);
		this.flights = foundFlights;

		return "searchResults";
	}

	public String bookTicket() {

		return "bookTicket";

	}

	public String editFlight(Flight flight) {
		this.id = flight.getId();
		this.airCompany = flight.getAirCompany();
		this.arrivalTime = flight.getArrivalTime();
		this.departurePoint = flight.getDeparturePoint();
		this.departureTime = flight.getDepartureTime();
		this.destination = flight.getDestination();
		this.flightnumber = flight.getFlightnumber();
		this.freeSeats = flight.getFreeSeats();
		this.flightStatus = flight.getFlightStatus();
		return "editFlight?faces-redirect=true";

	}

	public String submitEdit() {
		Flight flight = new Flight();
		flight.setId(this.id);
		flight.setAirCompany(this.airCompany);
		flight.setFlightnumber(this.flightnumber);
		flight.setDeparturePoint(this.departurePoint);
		flight.setDestination(this.destination);
		flight.setDepartureTime(this.departureTime);
		flight.setArrivalTime(this.arrivalTime);
		flight.setFreeSeats(this.freeSeats);
		flight.setPrice(this.price);
		flight.setFlightStatus(this.flightStatus);

		fService.updateFlight(flight);

		adminBean.updateFlightList(flight);

		return "Admin?faces-redirect=true";

	}

	// Autocompleter method
	public List<String> completeCity(String cityPrefix) {
		List<String> matches = new ArrayList<String>();
		String possibleCity = "";
		for (Airport airport : airports) {
			possibleCity = airport.getCity();
			if (possibleCity.toUpperCase().startsWith(cityPrefix.toUpperCase())) {
				matches.add(possibleCity);
			}
		}
		return matches;

	}
	//Action controller method
//	// Autocompleter method
//		public List<String> completeCity(String cityPrefix) {
//			List<String> matches = new ArrayList<String>();
//			for (String possibleCity : cities) {
//				if (possibleCity.toUpperCase().startsWith(cityPrefix.toUpperCase())) {
//					matches.add(possibleCity);
//				}
//			}
//			return matches;
}
