package sk.edenis.homeworkunion.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import sk.edenis.homeworkunion.dto.ErrorResponseDTO;

public class ErrorUtil {

    /**
     * Formats error messages from the BindingResult and creates an ErrorResponseDTO.
     * <p>
     * This method processes all errors from the BindingResult and formats them into a response. If there is only one error,
     * it returns the message as a simple string. In case of multiple errors, the result is a map that associates field names
     * with their respective error messages.
     *
     * @param bindingResult The result of validation containing all the detected errors.
     * @return ErrorResponseDTO An object containing the error message and the details of the errors, depending on the type of error.
     * @see ErrorResponseDTO
     */
    public static ErrorResponseDTO formatErrorMessage(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        if (fieldErrors.size() == 1) {
            return new ErrorResponseDTO(fieldErrors.get(0).getDefaultMessage());
        }

        fieldErrors.stream().filter(
                        fieldError -> fieldError.getDefaultMessage() != null && !fieldError.getDefaultMessage().isEmpty())
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ErrorResponseDTO(errors);
    }

    public static ErrorResponseDTO formatErrorMessage(String errorMessage) {
        return new ErrorResponseDTO(errorMessage);
    }
}
