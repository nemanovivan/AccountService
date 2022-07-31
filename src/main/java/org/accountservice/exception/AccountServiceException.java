package org.accountservice.exception;

/**
 * Custom exception
 */
public class AccountServiceException extends RuntimeException {

    public AccountServiceException (String message) {
        super(message);
    }

    public AccountServiceException (String message, Throwable cause) {
        super(message, cause);
    }
}
