package team.kin.forest.global.error.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.kin.forest.global.error.exception.ForestException
import team.kin.forest.global.error.response.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ForestException::class)
    fun handler(e: ForestException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.errorCode.message, e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )

}