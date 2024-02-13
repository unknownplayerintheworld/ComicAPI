package com.hung.comicapi.model;

public class DataJSON {
    private Boolean status;
    private String message;
    private Object obj;

    public DataJSON(Boolean status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
    }
    public DataJSON(){}

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
