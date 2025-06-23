package com.dxers.single_server_rest.dto.response;

public interface ResponseMessage {
    String SUCCESS = "Success.";
    String VALIDATION_FAILED = "Validation Failed.";
    String DUPLICATED_EMAIL = "Duplicated Email.";
    String SIGN_IN_FAILED = "Sign in Failed.";
    String AUTHENTICATION_FAILED = "Authentication Failed.";
    String AUTHORIZATION_FAILED = "Authorization Failed.";
    String NOT_FOUND = "Not Found.";
    String TOKEN_CREATION_FAILED = "Token creation Failed.";
    String DATABASE_ERROR = "Database Error.";
}