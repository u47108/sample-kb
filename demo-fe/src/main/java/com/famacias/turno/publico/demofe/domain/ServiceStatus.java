package com.famacias.turno.publico.demofe.domain;

public class ServiceStatus {

    protected int code;
    protected String message;
    protected String nativeMessage;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getNativeMessage() {
        return nativeMessage;
    }
    public void setNativeMessage(String nativeMessage) {
        this.nativeMessage = nativeMessage;
    }
   
    
}
