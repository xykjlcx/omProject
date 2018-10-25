package com.xykj.omservice.user.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseJpaDao<TUserPo,Integer> {

    List<TUserPo> findAllById(int userId);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<TUserPo> findAllByUserName(String username);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    List<TUserPo> findAllByEmail(String email);

    /**
     * 根据状态信息查询所有(分页)
     * @param status
     * @return
     */
    Page<TUserPo> findAllByStatus(Integer status, Pageable pageable);

}
