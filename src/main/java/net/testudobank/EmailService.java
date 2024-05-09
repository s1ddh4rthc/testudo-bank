package net.testudobank;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import java.io.IOException;

public class EmailService {

    public void sendEmail(String toEmail, String type, Boolean success, Double amount) {
        String apiKey = "1u7n57K00LOepKTCrzqnrteQYyAPbHBJ";

        String condition;
        /*
         * This is a fake email created for testudo bank
         */
        Email from = new Email("customerSupport@TestudoBank.com");

        /*
         * Since we currently do not have any customers with eamil accounts this should
         * be replaced with a burner email or another get nada email account
         * just to test functionality. I am currently using the customers username as
         * the toEmail in "toEmail"@testudoBank.com
         */
        Email to = new Email(toEmail + "@gmail.com");

        if (success) {

            condition = "SUCCESSFUL";
        } else {
            condition = "a FAILURE";
        }
        String body = String.format("Your attempt to %s $%f was %s.", type, amount, condition);

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, "Testudo Bank transaction update Email", to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
    }
}
