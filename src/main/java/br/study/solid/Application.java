package br.study.solid;

import br.study.solid.entity.Orders;
import br.study.solid.open_closed.solution.OrdersServiceWithLocation;
import br.study.solid.single_responsibility.problem.ProcessOrders;
import br.study.solid.entity.Users;
import br.study.solid.single_responsibility.solution.OrdersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class Application {

	//private final ProcessOrders processOrders;
	//private final OrdersService ordersService;
	private final OrdersServiceWithLocation ordersServiceWithLocation;

	public Application(OrdersServiceWithLocation ordersServiceWithLocation) {
		this.ordersServiceWithLocation = ordersServiceWithLocation;
	}


    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ordersServiceWithLocation.addNewOrderWithLocation(2, new Orders(new Users(), "Fiat Marea 2005"
					, "Provavelmente vai explodir na sua garagem"
					,null, 670000L));
		};
	}

}
