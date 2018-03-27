package leifu.mvpdemo.model.http;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/27 15:33
 * 描述:
 */

public class MyHttpResponse<T> {
    private int state;
    private String msg;
    private T data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
