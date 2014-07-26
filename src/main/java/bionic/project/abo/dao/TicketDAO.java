package bionic.project.abo.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.Ticket;
import bionic.project.abo.entity.Ticket.TicketStatus;
import bionic.project.abo.sql.Report;
import bionic.project.abo.sql.ReportDirections;

@Repository
public class TicketDAO extends GenericDAO<Ticket> implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<Ticket> showAll() {
		return em.createQuery("from Ticket").getResultList();
	}

	public List<Ticket> showBookedTickets() {
		TypedQuery<Ticket> query = em.createQuery(
				"SELECT t from Ticket t WHERE t.ticketStatus = :ticketStatus" ,
				Ticket.class);
		query.setParameter("ticketStatus", TicketStatus.BOOKED);

		List<Ticket> tickets = query.getResultList();
		return tickets;

	}

	public List<Ticket> getFlightTickets(int flightId) {

		TypedQuery<Ticket> query = em.createQuery(
				"SELECT t from Ticket t WHERE t.flightId = :flightId",
				Ticket.class);
		query.setParameter("flightId", flightId);

		List<Ticket> tickets = query.getResultList();
		return tickets;

	}

	public List<Report> getReport(Date startDate, Date endDate) {
		TypedQuery<Report> query = em
				.createQuery(
						"SELECT NEW bionic.project.abo.sql.Report(DATE(t.soldDate), SUM(t.amount), SUM(t.totalPrice)) "
								+ "FROM Ticket t  WHERE DATE(t.soldDate) >= DATE(:startDate) and DATE(t.soldDate) <= DATE(:endDate) GROUP BY DATE(t.soldDate)",
						Report.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		List<Report> report = query.getResultList();

		for (Report r : report) {

			System.out.println(r.getDay());
			System.out.println(r.getTicketsQuantity());
			System.out.println(r.getTicketsSellValue());

		}

		return report;

	}

	
//	public List<ReportDirections> getDirectionsReport(Date startDate, Date endDate) {
//		TypedQuery<Report> query = em
//				.createQuery(
//						"SELECT NEW bionic.project.abo.sql.ReportDirections(DATE(t.soldDate), SUM(t.amount), SUM(t.totalPrice)) "
//								+ "FROM Ticket t  WHERE DATE(t.soldDate) >= DATE(:startDate) and DATE(t.soldDate) <= DATE(:endDate) GROUP BY DATE(t.soldDate)",
//						Report.class);
//		query.setParameter("startDate", startDate);
//		query.setParameter("endDate", endDate);
//
//		List<Report> report = query.getResultList();
//
//		for (Report r : report) {
//
//			System.out.println(r.getDay());
//			System.out.println(r.getTicketsQuantity());
//			System.out.println(r.getTicketsSellValue());
//
//		}
//
//		return report;
//
//	}
	
	
	
	
}
