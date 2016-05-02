package com.springapp.mvc.Service.AncillaryServices;


public class ResponseMessage {

    private boolean result;
    private String message;


    public ResponseMessage(){}

    public ResponseMessage(String message){
        this.message=message;
    }

    public ResponseMessage(boolean result){
        this.result=result;
    }

    public ResponseMessage(boolean result,String message){
        this.result=result;
        this.message=message;
    }

    public void setResponse(boolean result,String message){
        this.result=result;
        this.message=message;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
