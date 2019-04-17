package com.bdqn.ssm.service;

import com.bdqn.ssm.entity.User;

import java.util.List;

public interface UserService {
    //userCode查询用户信息，登陆验证
    public User getLoginUser(String userCode,String userPassword);

    //查询用户数量
    public int getUserCount(String userName, int userRole);

    //分页获取账单信息，currentPageNo当前页，pageSize每页显示的记录数getUserList
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize);

    //添加用户add
    public int add(User user);

    //通过id删除用户信息del
    public int del(int id);

    //通过id查询用户信息getUserById
    public User getUserById(int id);

    //修改用户信息updateUser
    public int updateUser(User user);

    //通过userCode获取User getuserCodeLoginUser
    public User getuserCodeLoginUser(String userCode);

}
