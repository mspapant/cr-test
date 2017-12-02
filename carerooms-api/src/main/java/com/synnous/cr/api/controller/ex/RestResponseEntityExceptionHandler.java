package com.synnous.cr.api.controller.ex;

import com.synnous.cr.core.domain.exception.AuthenticationException;
import com.synnous.cr.core.domain.exception.AuthorizationException;
import com.synnous.cr.core.domain.exception.IllegalDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * The rest response entity exception handler.
 *
 * @author : Manos Papantonakos on 10/3/2015.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler implements WebResponseExceptionTranslator {

    /** The ERROR_AUTHENTICATION. */
    private static final String ERROR_AUTHENTICATION = "Authentication error. Reason {0}";

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    /** The context. */
    @Autowired
    private ApplicationContext context;

    /**
     * Exception handing in case or user authorization error.
     *
     * @param ex
     *         the exception
     * @param request
     *         the request
     * @return the entity
     */
    @ExceptionHandler(value = {AuthenticationException.class})
    protected ResponseEntity<Object> onException(AuthenticationException ex, HttpServletRequest request) {
        LOGGER.error(MessageFormat.format(ERROR_AUTHENTICATION, ex.getReason()));

        final String message = context.getMessage(ex.getReason().messageId, null, request == null ? null : request.getLocale());
        return createErrorResponse(message, HttpStatus.UNAUTHORIZED, request);
    }

    /**
     * Exception handing in case or user authorization error.
     *
     * @param ex
     *         the exception
     * @param request
     *         the request
     * @return the entity
     */
    @ExceptionHandler(value = {AuthorizationException.class})
    protected ResponseEntity<Object> onException(AuthorizationException ex, HttpServletRequest request) {
        return createErrorResponse("", HttpStatus.FORBIDDEN, request);
    }

    /**
     * Exception handing in case illegal data exception.
     *
     * @param ex
     *         the exception
     * @param request
     *         the request
     * @return the entity
     */
    @ExceptionHandler(value = {IllegalDataException.class})
    protected ResponseEntity<Object> onException(IllegalDataException ex, HttpServletRequest request) {
        final String message = context.getMessage(ex.getReason().messageId, null, request == null ? null : request.getLocale());
        return createErrorResponse(message, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Creates an error response.
     *
     * @param message
     *         the message
     * @param httpStatus
     *         the status
     * @param request
     *         the request
     * @return the entity
     */
    private ResponseEntity<Object> createErrorResponse(final String message, final HttpStatus httpStatus, final HttpServletRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("timestamp", String.valueOf(new Date().getTime()));
        responseBody.put("status", String.valueOf(httpStatus.value()));
        responseBody.put("error", httpStatus.name());
        responseBody.put("message", message);
        responseBody.put("path", request == null ? null : request.getRequestURL().toString());
        return new ResponseEntity<>(responseBody, httpStatus);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> onException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Error error = processFieldErrors(fieldErrors);
        return createErrorResponse(MessageFormat.format("{0}. {1} {2}", error.getMessage(), error.getFieldErrors().get(0).getObjectName(), error.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST, null);
    }

    private Error processFieldErrors(List<FieldError> fieldErrors) {
        Error error = new Error(BAD_REQUEST.value(), "validation error");
        for (FieldError fieldError : fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String path, String message) {
            FieldError error = new FieldError(path, message, message);
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<OAuth2Exception> translate(final Exception e) throws Exception {
        if (e instanceof AuthenticationException) {
            ResponseEntity<Map<String, String>> response = (ResponseEntity) onException((AuthenticationException) e, null);
            final OAuth2Exception exception = new OAuth2Exception(response.getBody().get("message"));
            for (Map.Entry<String, String> entry : response.getBody().entrySet()) {
                exception.addAdditionalInformation(entry.getKey(), entry.getValue());
            }
            return new ResponseEntity<OAuth2Exception>(exception, HttpStatus.UNAUTHORIZED);
        }
        throw new RuntimeException("No Case");
    }
}