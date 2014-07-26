package bionic.project.abo;

import java.util.List;

import bionic.project.abo.entity.Flight;
import bionic.project.abo.service.FlightService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        FlightService flightService = context.getBean(FlightService.class);
        List<Flight> flights = flightService.showAll();
        for(Flight f:flights){
        	System.out.println(f.getId() + "    ");
        	System.out.println(f.getDestination());
        }
    }
}
