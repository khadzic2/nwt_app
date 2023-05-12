package ba.unsa.etf.nwt.order_service.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        List<String> messages = new ArrayList<>();
        String message = ex.getMessage();
        messages.add(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Not found",messages));
    }

    @ExceptionHandler(NotAllowedRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNotAllowedRequest(NotAllowedRequest ex) {
        List<String> messages = new ArrayList<>();
        String message = ex.getMessage();
        messages.add(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Not allowed request",messages));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
        final BindingResult bindingResult = exception.getBindingResult();
        final List<String> messages = bindingResult.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Validation");
        errorResponse.setMessage(messages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
