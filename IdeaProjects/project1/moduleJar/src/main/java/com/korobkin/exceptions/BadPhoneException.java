package com.korobkin.exceptions;

/**
 * Throws when client enter not valid phone number.
 */
public class BadPhoneException extends Exception {
    public BadPhoneException(Throwable cause) {
        super(cause);
    }
}
