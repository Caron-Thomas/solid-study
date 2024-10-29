package br.study.solid.single_responsibility.problem;

import br.study.solid.entity.Orders;
import br.study.solid.entity.Users;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@Validated
public class ProcessOrders {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public ProcessOrders(UserRepository userRepository, OrdersRepository ordersRepository) {
        this.userRepository = userRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void newOrder(long user, @Valid Orders order) throws Exception {
        Users currentUser = userRepository.findById(user).orElse(null);
        if(currentUser == null )
            throw new Exception("User not found");

        Orders savedOrder = saveOrder(currentUser, order);
        boolean sentEmail = sendEmail(savedOrder);

        if(!sentEmail)
            throw new Exception("Fail to send email");

    }

    private Orders saveOrder(Users user,Orders order) {

        order.setUser_id(user);
        if (order.getOrdered_date() == null) {
            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            order.setOrdered_date(date);
        }
        Orders saved;
        try {
            saved = ordersRepository.save(order);
        } catch (Exception e) {
            //log
            throw new RuntimeException("could not save", e);
        }
        return saved;
    }

    private boolean sendEmail(Orders orders) {

        if(orders == null)
            return false;

        EmailSend smtp = new EmailSend("email@dominio.com", "Senha");
        StringBuilder html = new StringBuilder("Caro usuário " );
        html.append(orders.getUser_id().getName());
        html.append(". \nSeu pedido nº ");
        html.append(orders.getOrder_id());
        html.append(" foi confirmado.");

        smtp.sendEmail(html.toString(), "Pedido Confirmado");

        return true;
    }
}
