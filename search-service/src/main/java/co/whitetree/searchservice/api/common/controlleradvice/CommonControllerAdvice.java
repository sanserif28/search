package co.whitetree.searchservice.api.common.controlleradvice;

import co.whitetree.searchservice.api.common.dto.ErrorResponse;
import co.whitetree.searchservice.api.common.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        List<ErrorResponse.ErrorField> errorFields = e.getConstraintViolations()
                .stream()
                .map(violation -> ErrorResponse.ErrorField.builder()
                        .field(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .build())
                .collect(toList());

        return ErrorType.INVALID_FIELDS.toErrorResponse(errorFields);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorResponse.ErrorField> errorFields = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ErrorResponse.ErrorField.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(toList());

        return ErrorType.INVALID_FIELDS.toErrorResponse(errorFields);
    }
}
