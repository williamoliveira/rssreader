package shared.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {

    private static Logger logger = LoggerFactory.getLogger(MailSender.class);

    public static void send(String to, String subject, String text) {
        // Sender's email ID needs to be mentioned
        String from = "web@mail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";
        String port = "2525";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);

            logger.info("Sent email successfully");
        } catch (MessagingException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
