package study.study.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import study.study.common.dto.BaseResponse
import study.study.common.status.ResultCode

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    private fun handleValidationExceptions(ex: MethodArgumentNotValidException):
            ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }
        return ResponseEntity(
            BaseResponse(
            ResultCode.ERROR.name,
            errors,
            ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidInputException::class)
    private fun invalidInputException(ex: InvalidInputException):
            ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(
            BaseResponse(
            ResultCode.ERROR.name,
            errors,
            ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    private fun badCredentialsException(ex:BadCredentialsException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("로그인 실패" to "아이디 혹은 비밀번호를 다시 확인하세요.")
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    private fun defaultException(ex: Exception):
            ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(
            BaseResponse(
            ResultCode.ERROR.name,
            errors,
            ResultCode.ERROR.msg
        ), HttpStatus.BAD_REQUEST)
    }
}