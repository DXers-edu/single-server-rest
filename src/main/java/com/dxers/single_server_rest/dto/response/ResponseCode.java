package com.dxers.single_server_rest.dto.response;

public interface ResponseCode {
    String SUCCESS = "SU";
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_EMAIL = "DE";
    String SIGN_IN_FAILED = "SF";
    String AUTHENTICATION_FAILED = "AF";
    String AUTHORIZATION_FAILED = "AF";
    String NOT_FOUND = "NF";
    String TOKEN_CREATION_FAILED = "TF";
    String DATABASE_ERROR = "DBE";
}
