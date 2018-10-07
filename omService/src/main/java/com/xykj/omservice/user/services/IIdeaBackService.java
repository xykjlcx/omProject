package com.xykj.omservice.user.services;

import com.xykj.omservice.user.po.TIdeaBackPo;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.stereotype.Service;

/**
 * @author ocean
 * @Title: IIdeaBackService
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/7上午11:04
 */
@Service
public interface IIdeaBackService {

    /**
     * 添加新的意见反馈
     * @param ideaBackPo
     * @throws RuntimeException
     */
    TUserPo addNewIdeaBack(TIdeaBackPo ideaBackPo) throws RuntimeException;

}
