package com.korobkin.validation;

import com.korobkin.properties.Message;

/**
 * Util class to check data for creating new admin
 */
public class CreateAdminValidation {
    /**
     * Wrapper for information about validation of creating admin
     */
    public static class Result {
        public boolean noData = false;
        public boolean valid = true;
        public String error="";
    }

    /**
     *
     * @param login
     * @param password1
     * @param password2
     * @param firstname
     * @param lastname
     * @return
     */
    public static Result isValid(String login, String password1, String password2, String firstname, String lastname) {
        Result result;
        result = new Result();
        if (login == null && password1 == null && password2 == null && firstname == null && lastname == null) {
            result.noData = true;
            return result;
        }

        if (login == null || password1 == null || password2 == null || firstname == null || lastname == null) {
            result.valid = false;
            result.error += Message.get(Message.NOT_ALL_PARAMETERS) + "\r\n";
        }

        if (!password1.equals(password2)) {
            result.valid = false;
            result.error += Message.get(Message.PASSWORDS_ARE_NOT_EQUALS) + "\r\n";
        }

//        if (DAOFactory.adminDAO().isAdminExist(login)) {
//            result.valid = false;
//            result.error += Message.getInstance().getProperty(Message.ADMIN_IS_EXIST);
//
//        }

        return result;
    }
}
