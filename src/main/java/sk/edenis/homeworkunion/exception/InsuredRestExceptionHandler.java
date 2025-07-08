package sk.edenis.homeworkunion.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sk.edenis.homeworkunion.controller.InsuredRestController;
import sk.edenis.homeworkunion.dto.ErrorResponseDTO;
import sk.edenis.homeworkunion.utility.ErrorUtil;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice(assignableTypes = {InsuredRestController.class})
public class InsuredRestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(InsuredRestExceptionHandler.class);

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingFieldException(MissingFieldException e) {
        logger.debug("Exception: " + e);
        return ResponseEntity.badRequest().body(ErrorUtil.formatErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistingException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExistingException(AlreadyExistingException e) {
        logger.debug("Exception: " + e);
        return ResponseEntity.status(409).body(ErrorUtil.formatErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(InvalidFieldFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFieldFormatException(InvalidFieldFormatException e) {
        logger.debug("Exception: " + e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.formatErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.debug("Exception: " + e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorUtil.formatErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidJson(HttpMessageNotReadableException e) {
        logger.debug("Exception: " + e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.formatErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception e) {
        logger.error("Unexpected error: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorUtil.formatErrorMessage("Objavil sa neočakávaný problém. Prosím skúste to neskôr."));
    }
}
