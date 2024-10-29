package br.study.solid.single_responsibility.solution;

import br.study.solid.entity.Orders;
import br.study.solid.entity.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class ValidateOrder {

    public Orders validateNewOrder(Users user, Orders order) {
        order.setUser_id(user);
        if (order.getOrdered_date() == null) {
            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            order.setOrdered_date(date);
        }

        return order;
    }
}
