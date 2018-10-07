package com.xykj.omservice.user.services.impl;

import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.user.dao.IdeaBackDao;
import com.xykj.omservice.user.dao.UserDao;
import com.xykj.omservice.user.po.TIdeaBackPo;
import com.xykj.omservice.user.po.TUserPo;
import com.xykj.omservice.user.services.IIdeaBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ocean
 * @Title: IdeaBackServiceImpl
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/7上午11:05
 */
@Service
public class IdeaBackServiceImpl implements IIdeaBackService {

    @Autowired
    IdeaBackDao ideaBackDao;
    @Autowired
    UserDao userDao;

    @Override
    public TUserPo addNewIdeaBack(TIdeaBackPo ideaBackPo) throws RuntimeException {
        List<TUserPo> checkUserList = userDao.findAllById(ideaBackPo.getUserId());
        if (OceanOperationUtil.isNullOrEmptyForCollection(checkUserList)){
            throw new RuntimeException("提交失败,用户不存在");
        }
        ideaBackDao.save(ideaBackPo);
        return checkUserList.get(0);
    }
}
