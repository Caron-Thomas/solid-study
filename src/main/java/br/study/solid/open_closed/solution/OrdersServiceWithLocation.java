package br.study.solid.open_closed.solution;

import br.study.solid.entity.Location;
import br.study.solid.entity.Orders;
import br.study.solid.single_responsibility.problem.OrdersRepository;
import br.study.solid.single_responsibility.problem.UserRepository;
import br.study.solid.single_responsibility.solution.OrdersService;
import br.study.solid.single_responsibility.solution.SendEmail;
import br.study.solid.single_responsibility.solution.ValidateOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceWithLocation extends OrdersService {

    @Autowired
    public OrdersServiceWithLocation(UserRepository userRepository, OrdersRepository ordersRepository, ValidateOrder validateOrder, SendEmail sendEmail) {
        super(userRepository, ordersRepository, validateOrder, sendEmail);
    }

    public void addNewOrderWithLocation(long userId, @Valid Orders order) throws Exception {
        //classe origial foi extendida para add uma nova validação, funcionalidade, ou seja fechada para alteração, aberta
        // para extensão
        Location local = new Location();
        if(local.getLocal() == null)
            throw new Exception("Location not found");

        super.addNewOrder(userId, order);
    }


}
