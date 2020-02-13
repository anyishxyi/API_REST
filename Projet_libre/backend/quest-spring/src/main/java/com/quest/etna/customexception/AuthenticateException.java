package com.quest.etna.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "not connected")
public class AuthenticateException extends RuntimeException {

}
