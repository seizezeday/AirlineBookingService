package bionic.project.abo.sql;

import java.util.Date;

public class Report {

	private Date day;
	private Long ticketsQuantity;
	private Double ticketsSellValue;

	public Report(Date day, Long ticketsQuantity, Double ticketsSellValue) {
		super();
		this.day = day;
		this.ticketsQuantity = ticketsQuantity;
		this.ticketsSellValue = ticketsSellValue;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
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
