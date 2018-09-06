package com.evgen.rest;

import com.evgen.exception.OperationFailedException;
import com.evgen.rest.wrappers.ErrorWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger(RestErrorHandler.class);

    @ExceptionHandler(OperationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorWrapper handleOperationFailedException(OperationFailedException ex) {
        LOGGER.debug("Handling OperationFailedException: " + ex);
        return ErrorWrapper.wrap(ex);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorWrapper handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        LOGGER.debug("Handling EmptyResultDataAccessException: " + ex);
        return ErrorWrapper.wrap(ex);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorWrapper handleDataAccessException(DataAccessException ex) {
        LOGGER.debug("Handling DataAccessException: " + ex);
        return ErrorWrapper.wrap(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorWrapper handleIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.debug("Handling IllegalArgumentException: " + ex);
        return ErrorWrapper.wrap(ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorWrapper handleIllegalStateException(IllegalStateException ex) {
        LOGGER.debug("Handling IllegalStateException: " + ex);
        return ErrorWrapper.wrap(ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorWrapper handleAllExceptions(Exception ex) {
        LOGGER.debug("Handling Exception: " + ex);
        return ErrorWrapper.wrap(ex);
    }
}
