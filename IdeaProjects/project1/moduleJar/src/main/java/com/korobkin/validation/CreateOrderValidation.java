package com.korobkin.validation;

import com.korobkin.properties.Message;
import com.korobkin.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.util.Calendar;

/**
 * Created by Java Developer on 06.12.2015.
 */
public class CreateOrderValidation {
    private static final Logger logger = Logger.getLogger(CreateOrderValidation.class);

    public static class Result {
        public boolean NO_DATA = false;
        public boolean VALID = true;
        public String error = "";
    }

    public static Result isValid(String carId, String dateFrom, String dateTo, String phone, String email) {
        Result result = new Result();
        if (dateFrom == null && dateTo == null && phone == null && email == null) {
            result.NO_DATA = true;
            return result;
        }

        if (carId == null || dateFrom == null || dateTo == null || phone == null) {
            result.VALID = false;
            result.error = Message.get(Message.NOT_ALL_PARAMETERS);
            return result;
        }

        if (!PhoneValidation.isPhone(phone)) {
            result.VALID = false;
            result.error = Message.get(Message.BAD_PHONE_NUMBER);
        }

        if (!EmailValidation.isEmail(email)) {
            result.error += "<br>" + Message.get(Message.BAD_EMAIL);
        }

        try {
            Calendar from = CalendarUtil.getCalendar(dateFrom);
            Calendar to = CalendarUtil.getCalendar(dateTo);

            if (!from.before(to)) {
                result.VALID = false;
                result.error += "<br>" + Message.get(Message.FROM_NOT_AFTER_TO_DATE);
            }

            Calendar now = Calendar.getInstance();
            now.add(Calendar.DATE, -1);
            if (!from.after(now)) {
                result.VALID = false;
                result.error += "<br>" + Message.get(Message.FROM_NOT_AFTER_NOW_DATE);
            }

        } catch (NumberFormatException e) {
            logger.debug(e);
            result.VALID = false;
            result.error += "<br>" + Message.get(Message.BAD_DATE);
        }

        return result;
    }
}
