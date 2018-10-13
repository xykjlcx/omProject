package com.xykj.omapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: TestVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/12上午10:48
 */

public class TestVo {

    private int page;
    private int size;
    private int classify;

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public TestVo() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
