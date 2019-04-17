package com.bdqn.ssm.dao;

import com.bdqn.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    //userCode查询用户信息，登陆验证
    public User getLoginUser(@Param("userCode") String userCode);

    //查询用户数量
    public int getUserCount(@Param("userName") String userName,
                            @Param("userRole") Integer userRole);

    //分页获取账单信息，currentPageNo当前页，pageSize每页显示的记录数getUserList
    public List<User> getUserList(@Param("userName") String userName,
                                  @Param("userRole") Integer userRole,
                                  @Param("pageNo") Integer currentPageNo,
                                  @Param("pageSize") Integer pageSize);

    //添加用户add
    public int add(User user);

   //通过id删除用户信息
    public int del(int id);

    //通过id查询用户信息
    public User getUserById(int id);

    //修改用户信息
    public int updateUser(User user);

   //通过userCode获取User
    public User getuserCodeLoginUser(String userCode);


}
