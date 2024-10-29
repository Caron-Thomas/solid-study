package br.study.solid.single_responsibility.solution;

import br.study.solid.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SendEmail {
    private String host = "smtp.provedor.com";
    private String port = "587";
    private String username = "email@dominio.com";
    private String password = "senha";

    public void sendConfirmationEmail(Orders orders) {

        if (orders == null || orders.getUser_id() == null) {
            throw new RuntimeException("Could not send email: invalid order or user data.");
        }

        // Configuração das propriedades SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Criação da sessão SMTP com autenticação

        // Envio do e-mail
        StringBuilder html = new StringBuilder("Caro usuário " );
        html.append(orders.getUser_id().getName());
        html.append(". \nSeu pedido nº ");
        html.append(orders.getOrder_id());
        html.append(" foi confirmado.");

        System.out.println("E-mail enviado com sucesso! \n".concat(html.toString()));
    }
}
