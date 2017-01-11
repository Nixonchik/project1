package com.korobkin.exceptions;

/**
 * Throws when client enter not valid e-mail.
 */
public class BadEmailException extends Exception {
    public BadEmailException(Throwable cause) {
        super(cause);
    }
}
