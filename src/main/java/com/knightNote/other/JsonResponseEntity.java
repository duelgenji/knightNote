package com.knightNote.other;

public class JsonResponseEntity<T> {

    private int code = 0; // zero is default successful code

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public JsonResponseEntity<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResponseEntity<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResponseEntity<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> JsonResponseEntity<T> success(T data) {
        return new JsonResponseEntity<T>()
                .setData(data);
    }

    public static <T> JsonResponseEntity<T> success(T data, String msg) {
        return new JsonResponseEntity<T>()
                .setData(data)
                .setMsg(msg);
    }

    public static <T> JsonResponseEntity<T> failure(int code, String msg) {
        return new JsonResponseEntity<T>()
                .setCode(code)
                .setMsg(msg);
    }

}
