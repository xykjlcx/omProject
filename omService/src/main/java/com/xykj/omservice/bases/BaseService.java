package com.xykj.omservice.bases;

import java.io.Serializable;
import java.util.List;

/**
 * simple基础service接口
 */
public interface BaseService<T,ID extends Serializable> {

    /**
     * 保存
     * @param data
     * @return
     */
    void save(T data);

    /**
     * 使用id删除
     * @param id
     * @return
     */
    void deleteById(ID id);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 使用id查询
     * @return
     */
    T findById();


}
