package com.xykj.ombase.utils.error;

/**
 * @author ocean
 * @Title: OceanException
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/9/24下午1:11
 */
public class OceanException extends Exception {

    private String msg;
    private Object data;

    public OceanException(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
