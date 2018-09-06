package com.evgen.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String handleDataAccessException(DataAccessException ex) {
        LOGGER.debug("Handling DataAccessException: " + ex);
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.debug("Handling IllegalArgumentException: " + ex);
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleIllegalStateException(IllegalStateException ex) {
        LOGGER.debug("Handling IllegalStateException: " + ex);
        return ex.getLocalizedMessage();
    }
}
