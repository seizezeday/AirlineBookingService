package bionic.project.abo.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import bionic.project.abo.entity.Flight;

@Repository
public class FlightDAO extends GenericDAO<Flight> implements Serializable {
	@PersistenceContext
	private EntityManager em;

	public List<Flight> showAll() {
		return em.createQuery("from Flight").getResultList();
	}

	public List<Flight> searchFlight(String departurePoint, String destination,
			Date departureTime, int amount) {
		System.out.println(amount);
		TypedQuery<Flight> query = em
				.createQuery(
						"SELECT f FROM Flight f WHERE DATE(f.departureTime) = DATE(:date) and f.departurePoint = :departurePoint and f.destination = :destination and f.freeSeats >= :amount",
						Flight.class);
		query.setParameter("date", departureTime);
		query.setParameter("departurePoint", departurePoint);
		query.setParameter("destination", destination);
		query.setParameter("amount", amount);
		List<Flight> f = null;
		f = query.getResultList();

		return f;

	}

	public List<Flight> searchFlightByDate(Date date) {
		// Calendar gc = GregorianCalendar.getInstance();
		// gc.setTime(date);
		TypedQuery<Flight> query = em.createQuery(
				"SELECT f FROM Flight f WHERE DATE(f.departureTime) = :date",
				Flight.class);
		query.setParameter("date", date);
		List<Flight> f = null;
		f = query.getResultList();

		return f;
	}

}