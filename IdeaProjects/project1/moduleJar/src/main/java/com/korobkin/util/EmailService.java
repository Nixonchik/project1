package com.korobkin.util;

import com.korobkin.model.Order;
import com.korobkin.properties.Config;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;

/**
 * Util class to send confirm e-mail message to client
 */
public class EmailService {
    private static final Logger logger = Logger.getLogger(EmailService.class);
    private static final String DOMEN = Config.getProperty(Config.DOMEN);

    /**
     * Generate code to send to client for confirm order.
     * @param code generated code to add to link for confirm or cancel
     */
    public static void sendEmail(final Order order, String code) {
        // Recipient's email ID needs to be mentioned.
        String to = order.getClient().getEmail();
        if (to == null) return;

        String theme = "Car RENT - confirm your order!";
        StringBuilder body = new StringBuilder();
        body.append("<h1>To confirm your order go to the next link:</h1>")
                .append("<h1><a href='")
                .append(DOMEN)
                .append("/page/confirm_order_from_email?order_id=")
                .append(order.getId())
                .append("&code=")
                .append(code)
                .append("&confirm=true'>Confirm</a></h1>");
        body.append("<br> If you don't understand what it's about, go to next link:<br>")
                .append("<a href='")
                .append(DOMEN)
                .append("/page/confirm_order_from_email?order_id=")
                .append(order.getId())
                .append("&code=")
                .append(code)
                .append("&confirm=false'>Cancel</a>");
        String message = body.toString();

        try {
            new Mailer().sendEmail(to, theme, message);
        } catch (MessagingException e) {
            logger.warn(e);
        }
    }
}
