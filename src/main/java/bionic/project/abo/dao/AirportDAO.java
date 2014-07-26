package bionic.project.abo.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import bionic.project.abo.entity.Airport;
import bionic.project.abo.entity.Flight;


@Repository
public class AirportDAO extends GenericDAO<Airport> implements Serializable{
	@PersistenceContext
	private EntityManager em;
	
	public List<Airport> showAll() {
		return em.createQuery("from Airport").getResultList();
	}
	
	

}
