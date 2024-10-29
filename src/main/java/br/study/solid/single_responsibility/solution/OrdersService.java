package br.study.solid.single_responsibility.solution;

import br.study.solid.entity.Orders;
import br.study.solid.entity.Users;
import br.study.solid.single_responsibility.problem.OrdersRepository;
import br.study.solid.single_responsibility.problem.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrdersService {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final ValidateOrder validateOrder;
    private final SendEmail sendEmail;

    @Autowired
    public OrdersService(UserRepository userRepository,OrdersRepository ordersRepository, ValidateOrder validateOrder, SendEmail sendEmail) {
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
        this.validateOrder = validateOrder;
        this.sendEmail = sendEmail;
    }

    public void addNewOrder(long userId, @Valid Orders order) throws Exception {
        Users currentUser = userRepository.findById(userId).orElse(null);
        if(currentUser == null )
            throw new Exception("User not found");

        Orders validatedOrder = validateOrder.validateNewOrder(currentUser, order);
        //Pode add qualquer regra de negócios aqui
        Orders saved = ordersRepository.save(validatedOrder);
        sendEmail.sendConfirmationEmail(saved);
    }
}
