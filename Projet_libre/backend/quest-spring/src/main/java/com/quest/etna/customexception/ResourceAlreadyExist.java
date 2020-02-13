package com.quest.etna.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "resource already exist")
public class ResourceAlreadyExist extends RuntimeException  {
}
