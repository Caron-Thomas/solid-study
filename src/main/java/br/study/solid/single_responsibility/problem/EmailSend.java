package br.study.solid.single_responsibility.problem;

import java.util.Properties;

public class EmailSend {

    private String host = "smtp.provedor.com";
    private String port = "587";
    private String username;
    private String password;

    public EmailSend(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String html, String email) {
        // Configuração das propriedades SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Criação da sessão SMTP com autenticação

        // Envio do e-mail
         System.out.println("E-mail enviado com sucesso! \n".concat(html));
    }

    public EmailSend() {
    }

    @Override
    public String toString() {
        return "EmailSend{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
