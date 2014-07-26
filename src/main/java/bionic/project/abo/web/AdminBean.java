package bionic.project.abo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.taglibs.standard.tag.common.fmt.BundleSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.Flight.FlightStatus;
import bionic.project.abo.entity.User;
import bionic.project.abo.enums.Role;
import bionic.project.abo.service.FService;

@Named
@Scope("session")
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9078358147410446657L;

	@Inject
	private FService fService;
	
	@Inject
	private TicketBean ticketBean;
	
	
	private List<Flight> flights;
	private Flight flight;

	private int resultsQuantity;

	private Flight flightToEdit;
	private List<Flight> filteredFlights;

	Severity severity;
	FacesContext context = FacesContext.getCurrentInstance();
	ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

	

	public AdminBean() {
		flights = new ArrayList<>();
		
		
	}

//	 @PostConstruct
//	 public void init(){
//	 editable = false;
//	 List<Flight> foundFlights = flightService.showAll();
//	 this.flights = foundFlights;
//	
//	 }
	
	
	
	
	public Flight getFlight() {
		return flight;
	}

	

	public TicketBean getTicketBean() {
		return ticketBean;
	}

	public void setTicketBean(TicketBean ticketBean) {
		this.ticketBean = ticketBean;
	}

	public List<Flight> getFilteredFlights() {
		return filteredFlights;
	}

	public void setFilteredFlights(List<Flight> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}

	public Flight getFlightToEdit() {
		return flightToEdit;
	}

	public void setFlightToEdit(Flight flightToEdit) {
		this.flightToEdit = flightToEdit;
	}

	public int getResultsQuantity() {
		return resultsQuantity;
	}

	public void setResultsQuantity(int resultsQuantity) {
		this.resultsQuantity = resultsQuantity;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setfService(FService fService) {
		this.fService = fService;
	}

	public FService getfService() {
		return fService;
	}

	public void setFlightService(FService fService) {
		this.fService = fService;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void updateFlightList(Flight flight) {
		Iterator<Flight> iter = flights.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId() == flight.getId()) {
				iter.remove();
			}
		}
		flights.add(flight);

	}

	public String searchFlight() {
		List<Flight> foundFlights = fService.showAll();
		this.flights = foundFlights;
		resultsQuantity = foundFlights.size();
		return null;
	}

	public void removeFlight(Flight flight) {
		// System.out.println(id);
		fService.removeFlight(flight.getId());
		flights.remove(flight);
		resultsQuantity = flights.size();
		removeDone();

	}

	public void cancelFlight(Flight flight) {
		if (flight.getFlightStatus() == FlightStatus.CANCELLED) {
			cancelFailed();
		} else {
			flight.setFlightStatus(FlightStatus.CANCELLED);
			fService.updateFlight(flight);
			for (Flight f : flights) {
				if (f.getId() == flight.getId())
					f.setFlightStatus(FlightStatus.CANCELLED);
			}
			cancelDone();
		}
	}

	public String editFlight(Flight flight) {
		this.flightToEdit = flight;
		return "editFlight2";
	}

	public void clearTable() {
		this.flights = null;

	}

	/*
	 * Messaging module. Output to grawl
	 */
	public void removeDone() {
		String message1= bundle.getString("remove");
		String message2= bundle.getString("removedet");
		
		addMessage(FacesMessage.SEVERITY_INFO, message1,
				message2);
	}

	public void cancelDone() {
		addMessage(FacesMessage.SEVERITY_INFO, "Cancellation Done",
				"Flight was successfully cancelled");
	}

	public void cancelFailed() {
		addMessage(FacesMessage.SEVERITY_ERROR, "Cancellation Failed",
				"Flight is cancelled already!");
	}

	public void addMessage(Severity severity, String summary,
			String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
//    public void onPreRenderView(ComponentSystemEvent event) {
//        User user = sessionControlBean.getCurrentUser();
//        if (user.getRole().equals(Role.SUPERADMIN){
//        	
//        };
    
	public String showTickets(){
		ticketBean.showAll();
		return "admintickets?faces-redirect=true";
	}
}
