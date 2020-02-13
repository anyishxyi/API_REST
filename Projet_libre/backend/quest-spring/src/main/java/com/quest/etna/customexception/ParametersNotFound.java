package com.quest.etna.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "paramters not present")
public class ParametersNotFound extends RuntimeException {
}
