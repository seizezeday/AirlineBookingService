package bionic.project.abo.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.dao.FlightDAO;
import bionic.project.abo.entity.Flight;



@Service
public class FService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4043576180973149156L;
	@Autowired
	private FlightDAO flightDao;
	
	@Transactional
	public List<Flight> showAll() {
		return flightDao.showAll();
	}
	
	@Transactional
	public void addFlight(Flight flight) {

		flightDao.create(flight);
	}
	
	public List<Flight> searchFlightByDate(Date date) {
		return flightDao.searchFlightByDate(date);
	}

	@Transactional
	public void updateFlight(Flight flight) {
		flightDao.update(flight);
	}

	@Transactional
	public void removeFlight(Object id) {
		flightDao.delete(id);
	}
	
	@Transactional
	public List<Flight> searchFlight(String departurePoint, String destination, Date departureTime, int amount){
		return flightDao.searchFlight(departurePoint, destination, departureTime, amount);
	}

	public FlightDAO getFlightDao() {
		return flightDao;
	}

	public void setFlightDao(FlightDAO flightDao) {
		this.flightDao = flightDao;
	}
	
	
	

}
