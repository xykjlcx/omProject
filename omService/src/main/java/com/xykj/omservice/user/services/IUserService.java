package com.xykj.omservice.user.services;

import com.xykj.omservice.bases.BaseService;
import com.xykj.omservice.user.po.TUserPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService extends BaseService<TUserPo,Integer> {

    /**
     * 登录
     * @param username
     * @param pwd
     * @return
     */
    TUserPo login(String username,String pwd) throws RuntimeException;

    /**
     * 注册
     * @param registerUser
     * @return
     */
    void register(TUserPo registerUser);


    /**
     * 修改用户信息
     * @param userPo
     * @return
     * @throws RuntimeException
     */
    TUserPo updateInfo(TUserPo userPo) throws RuntimeException;

    /**
     * 修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @throws RuntimeException
     */
    void modifyPassWord(int userId,String oldPwd,String newPwd) throws RuntimeException;

    /**
     * 根据状态查询所有用户(分页)
     * status:
     * -1 全部
     * 0 不可用
     * 1 可用
     * @param status
     * @return
     * @throws RuntimeException
     */
    Page<TUserPo> getAllUserByStatus(Integer status, Pageable pageable) throws RuntimeException;

    /**
     * 删除用户
     * @param userId
     * @throws RuntimeException
     */
    void deleteUserById(int userId) throws RuntimeException;

    /**
     *
     * @param userId
     * @param isBan 是否禁用 true为禁用账号
     * @throws RuntimeException
     */
    void operateUserStatus(int userId,boolean isBan) throws RuntimeException;

}
