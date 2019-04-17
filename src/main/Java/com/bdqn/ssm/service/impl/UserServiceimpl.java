package com.bdqn.ssm.service.impl;

import com.bdqn.ssm.dao.UserDao;
import com.bdqn.ssm.entity.User;
import com.bdqn.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceimpl implements UserService {
    @Resource
    private UserDao ud;
    public UserDao getUd() {
        return ud;
    }
    public void setUd(UserDao ud) {
        this.ud = ud;
    }

    @Override//userCode查询用户信息，登陆验证
    public User getLoginUser(String userCode, String userPassword) {
        User user=ud.getLoginUser(userCode);
        System.out.println(user.getUserName()+user.getUserName()+user.getUserName()+user.getUserName()+
                user.getUserName()+user.getUserName()+user.getUserName()+user.getUserName()+
                user.getUserName()+user.getUserName()+user.getUserName()+user.getUserName()+
                user.getUserName()+user.getUserName()+user.getUserName()+user.getUserName());
        if (user!=null){
            if (user.getUserPassword().equals(userPassword)){
                return user;
            }
        }
        return null;
    }


    @Override //查询用户数量
    public int getUserCount(String userName, int userRole) {
        return ud.getUserCount(userName,userRole);
    }

    @Override//分页获取账单信息，currentPageNo当前页，pageSize每页显示的记录数getUserList
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
        return ud.getUserList(userName,userRole,currentPageNo,pageSize);
    }

    @Override//添加用户信息
    public int add(User user) {
        return ud.add(user);
    }

    @Override//通过id删除用户
    public int del(int id) {
        return ud.del(id);
    }

    @Override //通过id查询用户信息
    public User getUserById(int id) {
        return ud.getUserById(id);
    }

    @Override //修改用户信息
    public int updateUser(User user) {
        return ud.updateUser(user);
    }

    @Override  //通过userCode 获取 User
    public User getuserCodeLoginUser(String userCode) {
        return ud.getuserCodeLoginUser(userCode);
    }
}
