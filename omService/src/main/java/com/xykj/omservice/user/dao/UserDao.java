package com.xykj.omservice.user.dao;

import com.xykj.omservice.bases.BaseJpaDao;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseJpaDao<TUserPo,Integer> {

    List<TUserPo> findAllById(int userId);

}
