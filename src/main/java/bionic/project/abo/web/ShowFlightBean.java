package bionic.project.abo.web;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.service.FlightService;


@Named
@Scope("session")
public class ShowFlightBean {
	@Inject
	private FlightService flightService;
	
	public List<Flight> getFlights(){
	return flightService.showAll();	
	}
}
