package io.github.q843705423.entity;


public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        R<T> tr = new R<>();
        tr.setCode(0);
        tr.setData(data);
        tr.setMsg("success");
        return tr;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
