package bionic.project.abo.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.dao.TicketDAO;
import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.Ticket;
import bionic.project.abo.entity.Ticket.TicketStatus;
import bionic.project.abo.sql.Report;
import bionic.project.abo.sql.ReportDirections;

@Named
public class TicketService {
	
	@Inject
	private TicketDAO ticketDao;
	
	@Transactional
	public void addTicket(Ticket ticket){
		ticketDao.create(ticket);
		
	}
	
	public List<Ticket> showBookedTickets(){
		return ticketDao.showBookedTickets();
	}
	
	@Transactional
	public List<Ticket> showAll() {
		return ticketDao.showAll();
	}
	
	public List<Ticket> getFlightTickets(int flightId){
		return ticketDao.getFlightTickets(flightId);
	}
	
	@Transactional
	public void removeTicket(Ticket ticket){
		if (ticket.getTicketStatus() == TicketStatus.BOOKED)
		ticketDao.delete(ticket.getId());
	}
	
	@Transactional
	public void soldTicket(Ticket ticket){
		
		
		if (ticket.getTicketStatus() == TicketStatus.BOOKED){
			ticket.setTicketStatus(TicketStatus.SOLD);
			ticket.setSoldDate(new Date());
			ticketDao.update(ticket);
		}
			
	}
	
	
	public List<Report> getReport(Date startDate, Date endDate){
		return ticketDao.getReport(startDate, endDate);
		
	}
	
//	public List<ReportDirections> getDirectionsReport(Date startDate, Date endDate){
//		return ticketDao.getDirectionsReport(startDate, endDate);
//		
//	}
	
}
