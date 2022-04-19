package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {

    private static final Logger logger = Logger.getLogger(GlobalExceptions.class);

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> todosLosErrores(Exception e, WebRequest req){
//        logger.error(e.getMessage());
//        return new ResponseEntity("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorNotFound(BadRequestException bex){
        logger.error(bex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bex.getMessage());
    }
}
