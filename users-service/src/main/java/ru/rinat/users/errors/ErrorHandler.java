package ru.rinat.users.errors;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rinat.users.common.CommonResponseDto;

import javax.lang.model.type.NullType;
import java.io.PrintWriter;
import java.io.StringWriter;

import static ru.rinat.users.errors.Constants.COMMON_EXCEPTION;
import static ru.rinat.users.errors.Constants.EXCEPTION_WRONG_PARAMETER;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({UsersServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponseDto<?> handleUsersServiceException(UsersServiceException e) {
        log.error(e.getCode(), e);
        return CommonResponseDto.<NullType>builder()
                .success(false)
                .code(e.getCode())
                .message(e.getMessage())
                .stackTrace(getStringWriter(e))
                .build();
    }

    private static String getStringWriter(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponseDto<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        return CommonResponseDto.<NullType>builder()
                .success(false)
                .code(EXCEPTION_WRONG_PARAMETER)
                .message(e.getMessage())
                .stackTrace(getStringWriter(e))
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponseDto<?> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
       return CommonResponseDto.<NullType>builder()
                .success(false)
                .code(EXCEPTION_WRONG_PARAMETER)
                .message(e.getMessage())
                .stackTrace(getStringWriter(e))
                .build();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponseDto<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);

        return CommonResponseDto.<NullType>builder()
                .success(false)
                .code(EXCEPTION_WRONG_PARAMETER)
                .message(e.getMessage())
                .stackTrace(getStringWriter(e))
                .build();
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public CommonResponseDto<?> handleCommonException(Exception e) {
        log.error(e.getMessage(), e);

        return CommonResponseDto.<NullType>builder()
                .success(false)
                .code(COMMON_EXCEPTION)
                .message(e.getMessage())
                .stackTrace(getStringWriter(e))
                .build();
    }

}
