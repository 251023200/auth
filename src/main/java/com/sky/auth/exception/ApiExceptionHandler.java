package com.sky.auth.exception;

import java.io.IOException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.access.AccessDeniedException;
import com.sky.auth.dto.Response;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
　　　　　　　　 NoSuchMethodException,IOException,IndexOutOfBoundsException
　　　　　　　　 以及springmvc自定义异常等，如下：
SpringMVC自定义异常对应的status code  
           Exception                       HTTP Status Code  
ConversionNotSupportedException         500 (Internal Server Error)
HttpMessageNotWritableException         500 (Internal Server Error)
HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
NoSuchRequestHandlingMethodException    404 (Not Found) 
TypeMismatchException                   400 (Bad Request)
HttpMessageNotReadableException         400 (Bad Request)
MissingServletRequestParameterException 400 (Bad Request)
 *
 */

@ControllerAdvice
public class ApiExceptionHandler {
	
	//未登录认证
    @ExceptionHandler(AuthenticationException.class)  
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody  
    public Response authenticationExceptionHandler(AuthenticationException e) {  
    	Response response = new Response();
    	response.setCode(e.getCode());
    	response.setMsg(e.getMessage());
    	return response;
    }  
    
    //未授权
    @ExceptionHandler(AuthorizationException.class)  
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody  
    public Response authorizationExceptionHandler(AuthorizationException e) {  
    	Response response = new Response();
    	response.setCode(e.getCode());
    	response.setMsg(e.getMessage());
    	return response;
    }  
    
    //未授权
    @ExceptionHandler(AccessDeniedException.class)  
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody  
    public Response accessDeniedException(AccessDeniedException e) {  
    	Response response = new Response();
    	response.setCode(ExceptionEnum.AuthorizationException.getCode());
    	response.setMsg(e.getMessage());
    	return response;
    }  
    
    //Token不存在
    @ExceptionHandler(TokenNotFountException.class)  
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody  
    public Response tokenNotFountException(TokenNotFountException e) {  
    	Response response = new Response();
    	response.setCode(ExceptionEnum.TokenNotFountException.getCode());
    	response.setMsg(e.getMessage());
    	response.setData(0);
    	return response;
    }  
    
    //未授权
    @ExceptionHandler(TokenExpireException.class)  
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody  
    public Response tokenExpireException(TokenExpireException e) {  
    	Response response = new Response();
    	response.setCode(ExceptionEnum.TokenExpireException.getCode());
    	response.setMsg(e.getMessage());
    	response.setData(0);
    	return response;
    }  
    
    
	//参数错误异常
    @ExceptionHandler(ParameterInvalidException.class)  
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody  
    public Response parameterInvalidExceptionHandler(ParameterInvalidException e) {  
    	Response response = new Response();
    	response.setCode(e.getCode());
    	response.setMsg(e.getMessage());
    	return response;
    }  
	
	//数据找不到异常
    @ExceptionHandler(DataNotFoundException.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody  
    public Response dataNotFoundExceptionHandler(DataNotFoundException e) {  
    	Response response = new Response();
    	response.setCode(e.getCode());
    	response.setMsg(e.getMessage());
    	return response;
    }  
	
	//运行时异常
    @ExceptionHandler(RuntimeException.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody  
    public Response runtimeExceptionHandler(RuntimeException runtimeException) {  
    	System.out.println("runtimeExceptionHandler");
    	Response response = new Response();
    	response.setMsg(runtimeException.getMessage());
    	return response;
        //return ReturnFormat.retParam(1000, null);
    }  

    //空指针异常
    @ExceptionHandler(NullPointerException.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody  
    public Response nullPointerExceptionHandler(NullPointerException ex) {  
    	System.out.println("null");
    	Response response = new Response();
    	response.setMsg("空指针异常");
        ex.printStackTrace();
        return response;
       // return ReturnFormat.retParam(1001, null);
    }   
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)  
    @ResponseBody  
    public Response classCastExceptionHandler(ClassCastException ex) {  
    	Response response = new Response();
    	response.setMsg("类型转换异常");
        ex.printStackTrace();
        return response;
        //return ReturnFormat.retParam(1002, null);  
    }  

    //IO异常
    @ExceptionHandler(IOException.class)  
    @ResponseBody  
    public Response iOExceptionHandler(IOException ex) {  
    	Response response = new Response();
    	response.setMsg("io exception");
        ex.printStackTrace();
        return response;
        //return ReturnFormat.retParam(1003, null); 
    }  
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)  
    @ResponseBody  
    public Response noSuchMethodExceptionHandler(NoSuchMethodException ex) { 
    	Response response = new Response();
    	response.setMsg("noSuchMethodExceptionHandler");
        ex.printStackTrace();
        return response;
    	
        //return ReturnFormat.retParam(1004, null);
    }  

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)  
    @ResponseBody  
    public Response indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) { 
    	Response response = new Response();
    	response.setMsg("indexOutOfBoundsExceptionHandler");
        ex.printStackTrace();
        return response;
        //return ReturnFormat.retParam(1005, null);
    }
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public Response requestNotReadable(HttpMessageNotReadableException ex){
    	Response response = new Response();
    	response.setMsg("400..requestNotReadable");
        ex.printStackTrace();
        return response;
        //return ReturnFormat.retParam(400, null);
    }
    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public String requestTypeMismatch(TypeMismatchException ex){
        System.out.println("400..TypeMismatchException");
        ex.printStackTrace();
        return "type mismatch";
        //return ReturnFormat.retParam(400, null);
    }
    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public String requestMissingServletRequest(MissingServletRequestParameterException ex){
        System.out.println("400..MissingServletRequest");
        ex.printStackTrace();
        return "request miss servlet";
        //return ReturnFormat.retParam(400, null);
    }
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public String request405(){
        System.out.println("405...");
        return "method not supported";
        //return ReturnFormat.retParam(405, null);
    }
    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public String request406(){
        System.out.println("404...");
        return "media type not ";
        //return ReturnFormat.retParam(406, null);
    }
    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    public String server500(RuntimeException runtimeException){
        System.out.println("500...");
        return "convertion not ";
        //return ReturnFormat.retParam(406, null);
    }
    
}
