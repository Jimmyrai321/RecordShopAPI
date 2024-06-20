package jimmyrai321.recordShopAPI.exceptions;

import jimmyrai321.recordShopAPI.service.DTO.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> generateNotFoundException(NotFoundException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(String.valueOf(ex.getStatus()));
        errorDto.setTime(new Date().toString());

        return new ResponseEntity<ErrorDto>(errorDto, ex.getStatus());
    }
}
