package br.study.solid.open_closed.problem;

import br.study.solid.entity.Location;
import br.study.solid.entity.Orders;
import br.study.solid.entity.Users;
import br.study.solid.single_responsibility.problem.OrdersRepository;
import br.study.solid.single_responsibility.problem.UserRepository;
import br.study.solid.single_responsibility.solution.SendEmail;
import br.study.solid.single_responsibility.solution.ValidateOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrdersServiceOCP {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final ValidateOrder validateOrder;
    private final SendEmail sendEmail;

    @Autowired
    public OrdersServiceOCP(UserRepository userRepository, OrdersRepository ordersRepository, ValidateOrder validateOrder
            ,SendEmail sendEmail) {
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.validateOrder = validateOrder;
        this.sendEmail = sendEmail;
    }

    public void addNewOrderWithLocation(long userId, @Valid Orders order) throws Exception {
        Users currentUser = userRepository.findById(userId).orElse(null);
        if(currentUser == null )
            throw new Exception("User not found");

        //classe foi alterada para add uma nova validação, funcionalidade. Pelo OCP isto não deve acontecer, mas sim a
        // extensão da classe
        Location local = new Location();
        if(local.getLocal() == null)
            throw new Exception("Location not found");

        Orders validatedOrder = validateOrder.validateNewOrder(currentUser, order);
        Orders saved = ordersRepository.save(validatedOrder);
        sendEmail.sendConfirmationEmail(saved);
    }
}
