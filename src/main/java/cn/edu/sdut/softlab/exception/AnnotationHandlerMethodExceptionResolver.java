///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cn.edu.sdut.softlab.exception;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.ServletException;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.HttpOutputMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.http.server.ServletServerHttpResponse;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.method.HandlerMethod;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
//
///**
// *
// * @author huanlu
// */
//public class AnnotationHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {
//
//    private String defaultErrorView;
//
//    public String getDefaultErrorView() {
//        return defaultErrorView;
//    }
//
//    public void setDefaultErrorView(String defaultErrorView) {
//        this.defaultErrorView = defaultErrorView;
//    }
//
//    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
//
//        if (handlerMethod == null) {
//            return null;
//        }
//
//        Method method = handlerMethod.getMethod();
//
//        if (method == null) {
//            return null;
//        }
//        //如果定义了ExceptionHandler则返回相应的Map中的数据  
//        ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
//        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
//        if (responseBodyAnn != null) {
//            try {
//                ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method, ResponseStatus.class);
//                if (responseStatusAnn != null) {
//                    HttpStatus responseStatus = responseStatusAnn.value();
//                    String reason = responseStatusAnn.reason();
//                    if (!StringUtils.hasText(reason)) {
//                        response.setStatus(responseStatus.value());
//                    } else {
//                        response.sendError(responseStatus.value(), reason);
//                    }
//                }
//                // 如果没有ExceptionHandler注解那么returnValue就为空  
//                if (returnValue == null) {
//                    ServiceResponse res = new ServiceResponse();
//                    res.setResultCode(BusiStatusCons.STATUS_FAILURE);
//                    res.setResultDesc("系统异常：" + exception.getLocalizedMessage());
//                    res.setTimestamp(DateHelper.getCurrentTimeStamp(null));
//                    handleResponseError(res, request, response);
//                    return new ModelAndView();
//                }
//                return handleResponseBody(returnValue, request, response);
//            } catch (Exception e) {
//                return null;
//            }
//        }
//
//        if (null == returnValue) {
//            returnValue = new ModelAndView();
//            if (null == returnValue.getViewName()) {
//                returnValue.setViewName(defaultErrorView);
//            }
//        }
//        return returnValue;
//    }
//
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Map value = returnValue.getModelMap();
//        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
//        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
//        if (acceptedMediaTypes.isEmpty()) {
//            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
//        }
//        MediaType.sortByQualityValue(acceptedMediaTypes);
//        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
//        Class<?> returnValueType = value.getClass();
//        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
//        if (messageConverters != null) {
//            for (MediaType acceptedMediaType : acceptedMediaTypes) {
//                for (HttpMessageConverter messageConverter : messageConverters) {
//                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
//                        messageConverter.write(value, acceptedMediaType, outputMessage);
//                        return new ModelAndView();
//                    }
//                }
//            }
//        }
//        if (logger.isWarnEnabled()) {
//            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);
//        }
//        return null;
//    }
//
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    private ModelAndView handleResponseError(ServiceResponse returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
//        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
//        if (acceptedMediaTypes.isEmpty()) {
//            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
//        }
//        MediaType.sortByQualityValue(acceptedMediaTypes);
//        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
//        Class<?> returnValueType = returnValue.getClass();
//        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
//        if (messageConverters != null) {
//            for (MediaType acceptedMediaType : acceptedMediaTypes) {
//                for (HttpMessageConverter messageConverter : messageConverters) {
//                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
//                        messageConverter.write(returnValue, acceptedMediaType, outputMessage);
//                        return new ModelAndView();
//                    }
//                }
//            }
//        }
//        if (logger.isWarnEnabled()) {
//            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);
//        }
//        return null;
//    }
//
//}
