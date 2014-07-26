package bionic.project.abo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Date;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.dao.FlightDAO;
import bionic.project.abo.entity.Flight;

@Service
public class FlightService implements Serializable {

	@Autowired
	private FlightDAO flightDao;
	
	
	
	@Transactional
	public void addFlight(Flight flight) {

		flightDao.create(flight);
	}

	@Transactional
	public List<Flight> showAll() {
		return flightDao.showAll();
	}

	public List<Flight> searchFlightByDate(Date date) {
		return flightDao.searchFlightByDate(date);
	}

	@Transactional
	public void updateFlight(Flight flight) {
		flightDao.update(flight);
	}

//	@Transactional
//	public void removeFlight(Object id) {
//		flightDao.delete(id);
//	}
}