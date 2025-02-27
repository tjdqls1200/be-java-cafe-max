package kr.codesqaud.cafe.common.exception;

import kr.codesqaud.cafe.common.exception.reply.ReplyDeleteFailException;
import kr.codesqaud.cafe.common.exception.reply.ReplyUpdateFailException;
import kr.codesqaud.cafe.controller.dto.ApiResponse;
import kr.codesqaud.cafe.controller.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ReplyUpdateFailException.class, ReplyDeleteFailException.class})
    public ApiResponse<ErrorDto> replyForbiddenExceptionHandler(RuntimeException ex) {
        return ApiResponse.fail(new ErrorDto(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }
}
