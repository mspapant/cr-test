package com.synnous.cr.api.controller.ex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import java.util.List;
import java.util.Set;

/**
 * The exception entity handler.
 *
 * @author : Manos Papantonakos on 3/11/2015.
 */
public abstract class ResponseEntityExceptionHandler {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Log category to use when no mapped handler is found for a request.
     * @see #pageNotFoundLogger
     */
    public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

    /**
     * Additional logger to use when no mapped handler is found for a request.
     * @see #PAGE_NOT_FOUND_LOG_CATEGORY
     */
    protected static final Log pageNotFoundLogger = LogFactory.getLog(PAGE_NOT_FOUND_LOG_CATEGORY);


    /**
     * Provides handling for standard Spring MVC exceptions.
     * @param ex the target exception
     * @param request the current request
     */
    @ExceptionHandler(value={
            NoSuchRequestHandlingMethodException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class
    })
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();

        logger.error("Exception found: ", ex);

        if (ex instanceof NoSuchRequestHandlingMethodException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleNoSuchRequestHandlingMethod((NoSuchRequestHandlingMethodException) ex, headers, status, request);
        }
        else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
            return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, headers, status, request);
        }
        else if (ex instanceof HttpMediaTypeNotSupportedException) {
            HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, headers, status, request);
        }
        else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException) ex, headers, status, request);
        }
        else if (ex instanceof MissingServletRequestParameterException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleMissingServletRequestParameter((MissingServletRequestParameterException) ex, headers, status, request);
        }
        else if (ex instanceof ServletRequestBindingException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleServletRequestBindingException((ServletRequestBindingException) ex, headers, status, request);
        }
        else if (ex instanceof ConversionNotSupportedException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleConversionNotSupported((ConversionNotSupportedException) ex, headers, status, request);
        }
        else if (ex instanceof TypeMismatchException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleTypeMismatch((TypeMismatchException) ex, headers, status, request);
        }
        else if (ex instanceof HttpMessageNotReadableException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleHttpMessageNotReadable((HttpMessageNotReadableException) ex, headers, status, request);
        }
        else if (ex instanceof HttpMessageNotWritableException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleHttpMessageNotWritable((HttpMessageNotWritableException) ex, headers, status, request);
        }
        else if (ex instanceof MethodArgumentNotValidException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleMethodArgumentNotValid((MethodArgumentNotValidException) ex, headers, status, request);
        }
        else if (ex instanceof MissingServletRequestPartException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleMissingServletRequestPart((MissingServletRequestPartException) ex, headers, status, request);
        }
        else if (ex instanceof BindException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleBindException((BindException) ex, headers, status, request);
        }
        else if (ex instanceof NoHandlerFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleNoHandlerFoundException((NoHandlerFoundException) ex, headers, status, request);
        }
        else {
            logger.warn("Unknown exception type: " + ex.getClass().getName());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * A single place to customize the response body of all Exception types.
     * This method returns {@code null} by default.
     * @param ex the exception
     * @param body the body to use for the response
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<Object>(body, headers, status);
    }

    /**
     * Customize the response for NoSuchRequestHandlingMethodException.
     * This method logs a warning and delegates to
     * {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
                                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for HttpRequestMethodNotSupportedException.
     * This method logs a warning, sets the "Allow" header, and delegates to
     * {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!supportedMethods.isEmpty()) {
            headers.setAllow(supportedMethods);
        }

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for HttpMediaTypeNotSupportedException.
     * This method sets the "Accept" header and delegates to
     * {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for HttpMediaTypeNotAcceptableException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for MissingServletRequestParameterException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for ServletRequestBindingException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for ConversionNotSupportedException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for TypeMismatchException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for HttpMessageNotReadableException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for HttpMessageNotWritableException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for MethodArgumentNotValidException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for MissingServletRequestPartException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for BindException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Customize the response for NoHandlerFoundException.
     * This method delegates to {@link #handleExceptionInternal(Exception, Object, HttpHeaders, HttpStatus, WebRequest)}.
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     * @since 4.0
     */
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, null, headers, status, request);
    }
}
