package br.study.solid;

import br.study.solid.entity.Orders;
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
	private final OrdersService ordersService;

    public Application(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ordersService.addNewOrder(2, new Orders(new Users(), "Fan Arno T2000", "O vento de furacão produzido na sua sala de estar"
					,null, 22267L));
		};
	}

}
