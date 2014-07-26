package bionic.project.abo.web;

import java.io.Serializable;
import bionic.project.abo.sql.ReportDirections;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import bionic.project.abo.service.TicketService;
import bionic.project.abo.sql.Report;
import bionic.project.abo.entity.Ticket;


@Named
@Scope("request")
public class ReportBean implements Serializable {
	
	@Inject
	private TicketService ticketService;
	

	public ReportBean(){
		
	}
	
	private Date startDate;
	private Date endDate;
	private int totalTickets;
	private double totalSum;
	private List<Ticket> tickets;
	private boolean reportVisibility = false;
	private List<Report> ticketReport = new ArrayList<>();
	private boolean renderChart = true;
	private List<ReportDirections> directionsReport = new ArrayList<>();
	
	
	
	
	
	
	public boolean isRenderChart() {
		return renderChart;
	}
	public void setRenderChart(boolean renderChart) {
		this.renderChart = renderChart;
	}
	public List<Report> getTicketReport() {
		return ticketReport;
	}
	public void setTicketReport(List<Report> ticketReport) {
		this.ticketReport = ticketReport;
	}
	public boolean isReportVisibility() {
		return reportVisibility;
	}
	public void setReportVisibility(boolean reportVisibility) {
		this.reportVisibility = reportVisibility;
	}
	public TicketService getTicketService() {
		return ticketService;
	}
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
//	public String getReport(){
//		
//		this.tickets = ticketService.getReport(this.startDate,this.endDate);
//		double sum = 0;
//		int ticketsAmount = 0;
//		for (Ticket t:tickets){
//			sum = sum + t.getTotalPrice();
//			ticketsAmount = ticketsAmount + t.getAmount();
//			
//		}
//		this.reportVisibility = true;
//		this.totalSum = sum;
//		this.totalTickets = ticketsAmount;
//		
//		
//		return null;
//	}
	
	public String getReport(){
		this.ticketReport = ticketService.getReport(this.startDate,this.endDate);
		Double totalSum = 0.0;
		int totalTickets = 0;
		for (Report r:ticketReport){
			totalSum+=r.getTicketsSellValue();
			totalTickets+=r.getTicketsQuantity();
		}
		this.totalSum = totalSum;
		this.totalTickets = totalTickets;
		
		this.renderChart = true;
		
		
		return null;
	}
	
//	public String getDirectionsReport(){
//		
//	}
//	

}
