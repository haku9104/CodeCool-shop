package com.codecool.shop.util;


import com.codecool.shop.model.User;
import com.codecool.shop.model.order.Order;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);
    public static void sendEmail(Order order) throws IOException {
        String emailContent = orderToString(order);
        Email from = new Email("siposm17@gmail.com");
        String subject = "Your Codecool Shop Order";
        Email to = new Email(order.getEmail());
        Content content = new Content("text/plain", emailContent);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            logger.info(String.format("Email sent to %s about successfull order.", order.getEmail()));
        } catch (IOException ex) {
            logger.error(String.format("Sendng email to %s was unsuccessfull", order.getEmail()));
            throw ex;
        }
    }

    private static String orderToString(Order order) {
        String result = "Dear " + order.getName() + ",\n\n" +
                "We are happy to confirm that your order was successfull. \n" + "\n" +
                "\tYour cart total is: " + "$" + order.cartSumPrice() + "\n" +
                "\tPhone number:" + order.getPhoneNumber() + "\n\n" +
                "We are going to send your package to the following address:" + "\n" +
                order.getBillingAddress().toString() + "\n" + "\n" +
                "Best regards,\n Codecool Shop DevTeam";
        return result;
    }
}