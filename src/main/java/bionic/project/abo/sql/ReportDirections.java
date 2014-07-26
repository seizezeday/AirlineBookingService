package bionic.project.abo.sql;

public class ReportDirections {
	
	private String departurePoint;
	private String destination;
	private Long ticketsQuantity;
	private Double ticketsSellValue;
	
	
	
	
	public ReportDirections(String departurePoint, String destination,
			Long ticketsQuantity, Double ticketsSellValue) {
		super();
		this.departurePoint = departurePoint;
		this.destination = destination;
		this.ticketsQuantity = ticketsQuantity;
		this.ticketsSellValue = ticketsSellValue;
	}
	public String getDeparturePoint() {
		return departurePoint;
	}
	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getTicketsQuantity() {
		return ticketsQuantity;
	}
	public void setTicketsQuantity(Long ticketsQuantity) {
		this.ticketsQuantity = ticketsQuantity;
	}
	public Double getTicketsSellValue() {
		return ticketsSellValue;
	}
	public void setTicketsSellValue(Double ticketsSellValue) {
		this.ticketsSellValue = ticketsSellValue;
	}
	
	
}
