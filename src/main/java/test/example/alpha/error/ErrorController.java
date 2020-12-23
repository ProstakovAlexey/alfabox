package test.example.alpha.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequest.class})
    public ResponseEntity<CustomErrorResponse> badRequestHandler(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setErrorDetail(ex.getMessage());
        errors.setErrorCode(400);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
