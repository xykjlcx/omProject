package com.xykj.omservice.user.dao;

import com.xykj.ombase.bases.BaseJpaDao;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseJpaDao<TUserPo,Integer> {
}