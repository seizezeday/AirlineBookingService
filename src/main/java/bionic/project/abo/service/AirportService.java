package bionic.project.abo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;



import bionic.project.abo.dao.AirportDAO;
import bionic.project.abo.entity.Airport;


@Named
public class AirportService implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AirportDAO airportDao;
	
	public List<Airport> showAll() {
		return airportDao.showAll();
	}

}
