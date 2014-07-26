package bionic.project.abo.web;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.List;

import bionic.project.abo.sql.Report;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class ChartView implements Serializable {

	private LineChartModel areaModel;

	@Inject
	private ReportBean reportBean;

	private List<Report> report;

	public ChartView() {

	}

	@PostConstruct
	public void init() {

		createAreaModel();
	}

	public ReportBean getReportBean() {
		return reportBean;
	}

	public void setReportBean(ReportBean reportBean) {
		this.reportBean = reportBean;
	}

	public List<Report> getReport() {
		return report;
	}

	public void setReport(List<Report> report) {
		this.report = report;
	}

	public LineChartModel getAreaModel() {

		return areaModel;
	}

	private void createAreaModel() {
		// System.out.println("chartview");
		areaModel = new LineChartModel();
		
		LineChartSeries stats = new LineChartSeries();
		stats.setFill(true);
		report = reportBean.getTicketReport();
		System.out.println("report is " + report);

		stats.setLabel("Sells");
		int i = 2004;
		for (Report r : report) {
			System.out.println(r.getDay() + "  " + r.getTicketsQuantity());

			stats.set(String.valueOf(i), r.getTicketsQuantity());
			i++;
		}

		areaModel.addSeries(stats);
		
		
		
		
		System.out.println("stats is " + stats);
//		 areaModel = new LineChartModel();
//		 LineChartSeries girls = new LineChartSeries();
//		 girls.setFill(true);
//		 girls.setLabel("Girls");
//		 girls.set("2004", 52);
//		 girls.set("2005", 60);
//		 girls.set("2006", 110);
//		 girls.set("2007", 90);
//		 girls.set("2008", 120);
//		 areaModel.addSeries(girls);

		areaModel.setTitle("Area Chart");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Dates");
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Sells");
		yAxis.setMin(0);
		yAxis.setMax(110);
	}

}
