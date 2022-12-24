package com.karma.prj.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    // duplicated
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "User is duplicated..."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "Nickname is duplicated..."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email is duplicated..."),
    // not found
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Username is not found..."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post is not found..."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment is not found..."),
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Notification is not found..."),
    NOT_LIKED(HttpStatus.NOT_FOUND, "Not liked..."),
    // auth failure
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Password is wrong..."),
    NOT_GRANTED_ACCESS(HttpStatus.UNAUTHORIZED, "Access denied due to grant..."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid..."),
    // conflict
    ALREADY_LIKED(HttpStatus.CONFLICT, "User Already like post"),
    CHATTING_SERVER_ERROR(HttpStatus.CONFLICT, "Error occurs on chatting"),
    // internal server error
    ERROR_ON_CREATE_NOTIFICATION(HttpStatus.INTERNAL_SERVER_ERROR, "Error ocuurs on creating notification"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error...")
    ;
    private final HttpStatus status;
    private final String message;
}
