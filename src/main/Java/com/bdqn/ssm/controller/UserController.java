package com.bdqn.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bdqn.ssm.entity.Role;
import com.bdqn.ssm.entity.User;
import com.bdqn.ssm.service.RoleService;
import com.bdqn.ssm.service.UserService;
import com.bdqn.ssm.util.Constants;
import com.bdqn.ssm.util.PageSupport;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Resource
    private UserService us;
    @Resource
    private RoleService rs;

    private Logger logger=Logger.getLogger(UserController.class);

//进入登陆页面
    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login(){
        logger.debug("进入登陆页面");
        return "login";
    }


      //通过用户名与密码实现登陆
    @RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword
            , HttpServletRequest request, HttpSession session){
        User user=us.getLoginUser(userCode,userPassword);
        if (user==null){
            request.setAttribute("error","用户名或者密码错误");
            return "login";
        }else {
            session.setAttribute(Constants.USER_SESSION,user);
            return "redirect:/user/main.html";
        }
    }

      //进入系统主页面
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        if (session.getAttribute(Constants.USER_SESSION)==null){
            return "redirect:/user/login.html";
        }else {
            return "frame";
        }
    }



    //分页查询
    @RequestMapping(value = "/userlist.html")
    public String getUserList(Model model,
                              @RequestParam(value = "queryname",required = false)String queryUserName,
                              @RequestParam(value = "queryUserRole",required = false)String queryUserRole,
                              @RequestParam(value = "pageIndex",required = false)String pageIndex){
        logger.info("getUserList--------:queryUserName:"+queryUserName);
        logger.info("getUserList--------:queryUserRole:"+queryUserRole);
        logger.info("getUserList--------:pageIndex:"+pageIndex);
        int _queryUserRole=0;
        List<User> userList=null;
        //设置页面容量
        int pageSize=Constants.pageSize;
        //当前页码
        int currentPageNo=1;
        if (queryUserName==null){
            queryUserName="";
        }
        if (queryUserRole!=null && !queryUserRole.equals("")){
            _queryUserRole=Integer.parseInt(queryUserRole);
        }
        if (pageIndex!=null){

            try {
                currentPageNo=Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "redirect:/user/syserror.html";
//                e.printStackTrace();
            }

        }

        //总数量（表）
        int totalCount=us.getUserCount(queryUserName,_queryUserRole);
        System.out.println("\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount+
                "\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount+
                "\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount+"\t中记录是"+totalCount);
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount=pages.getTotalPageCount();
        //控制首页和末页
        if (currentPageNo<1){
            currentPageNo=1;
        }else if (currentPageNo>totalPageCount){
            currentPageNo=totalPageCount;
        }
        userList=us.getUserList(queryUserName,_queryUserRole,(currentPageNo-1)*pageSize,pageSize);
        model.addAttribute("userList",userList);
        List<Role> roleList=null;
        roleList=rs.getRoleList();
        model.addAttribute("roleList",roleList);
        model.addAttribute("queryUserName",queryUserName);
        model.addAttribute("queryUserRole",queryUserRole);
        model.addAttribute("totalPageCount",totalPageCount);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("currentPageNo",currentPageNo);
        return "userlist";

    }

    //跳转到添加页面
    @RequestMapping(value = "/useradd.html",method = RequestMethod.GET)
    public String add(){
        return "useradd";
    }

    //添加用户信息
    @RequestMapping(value = "/useradd.html",method = RequestMethod.POST)
    public String adduuser(User user,HttpSession session){
        System.out.println("\t名字："+user.getUserName()+"\t名字："+user.getUserRoleName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+
                "\t名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+
                "\n名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+
                "\t名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+
                "\n名字："+user.getUserName()+"\t名字："+user.getUserName()+"\t名字："+user.getUserName()+
                user.getCreationDate()+user.getCreationDate());
        try {
            user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
            user.setCreationDate(new Date());
            if (user!=null || user.equals(' ')){
                if (us.add(user)>0){
                    return ("redirect:/user/userlist.html");
                }
            }else {
                System.out.println("\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\nUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\nUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\nUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\nUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+"\tUser："+user.getUserName()+
                        "\tUser："+user.getUserName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "useradd";
    }

    //多文件上传
    @RequestMapping(value="/useraddsave.html",method=RequestMethod.POST)
    public String addUserSave(User user,HttpSession session,HttpServletRequest request,
                              @RequestParam(value ="attachs", required = false) MultipartFile[] attachs){
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag = true;
        String path = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfiles");
        logger.info("uploadFile path ============== > "+path);
        for(int i = 0;i < attachs.length ;i++){
            MultipartFile attach = attachs[i];
            if(!attach.isEmpty()){
                if(i == 0){
                    errorInfo = "uploadFileError";
                }else if(i == 1){
                    errorInfo = "uploadWpError";
                }
                String oldFileName = attach.getOriginalFilename();//原文件名
                logger.info("uploadFile oldFileName ============== > "+oldFileName);
                String prefix= FilenameUtils.getExtension(oldFileName);//原文件后缀
                logger.debug("uploadFile prefix============> " + prefix);
                int filesize = 500000;
                logger.debug("uploadFile size============> " + attach.getSize());
                if(attach.getSize() >  filesize){//上传大小不得超过 500k
                    request.setAttribute("e", " * 上传大小不得超过 500k");
                    flag = false;
                }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                        || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
                    String fileName = System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+"_Personal.jpg";
                    logger.debug("new fileName======== " + attach.getName());
                    File targetFile = new File(path, fileName);
                    if(!targetFile.exists()){
                        targetFile.mkdirs();
                    }
                    //保存
                    try {
                        attach.transferTo(targetFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute(errorInfo, " * 上传失败！");
                        flag = false;
                    }
                    if(i == 0){
                        idPicPath = path+File.separator+fileName;
                    }else if(i == 1){
                        workPicPath = path+File.separator+fileName;
                    }
                    logger.debug("idPicPath: " + idPicPath);
                    logger.debug("workPicPath: " + workPicPath);

                }else{
                    request.setAttribute(errorInfo, " * 上传图片格式不正确");
                    flag = false;
                }
            }
        }
        if(flag){
            user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
            user.setCreationDate(new Date());
            user.setIdPicPath(idPicPath);
            user.setWorkPicPath(workPicPath);
            try {
                if(us.add(user)>0){
                    return "redirect:/user/userlist.html";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "useradd";
    }


    //    通过id查询用户信息
    @RequestMapping(value = "usermodift.html",method = RequestMethod.GET)
    public String getUserById(@RequestParam int userid,Model model){
        User user=us.getUserById(userid);
        model.addAttribute(user);
        return "usermodify";
    }
    //使用REST风格通过id查询用户信息
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model){
        User user=us.getUserById(id);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM--dd");
        String date=sdf.format(user.getBirthday());
        model.addAttribute("birthday",date);
        model.addAttribute(user);
        return "userview";
    }

    //    修改用户信息
    @RequestMapping(value = "usermodifysave.html",method = RequestMethod.POST)
    public String getUsetxiugai(User user,HttpSession session){
        user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        user.setCreationDate(new Date());
        if (us.updateUser(user)>0){
            return ("redirect:/user/userlist.html");
        }
        return "user/usermodify";
    }

//    通过id删除
    @RequestMapping(value = "/deluser/{id}",method = RequestMethod.GET)
    public String deluser(@PathVariable int id,Model model){
        int count=us.del(id);
        if (count>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        return ("redirect:/user/userlist.html");
    }
    @RequestMapping(value = "/iddel")
    //以下注解表示将方法的返回结果直接放入response响应数据区中
    @ResponseBody//查询userCode是否可用
    public Object userdel(@RequestParam int userid) {
        HashMap<String, String> resultMap=new HashMap<String, String>();
        if (userid==0){
            resultMap.put("delResult","notexist");
        }else {
            int count=us.del(userid);
            if (count>0){
                resultMap.put("delResult","true");
            }else {
                resultMap.put("delResult","false");
            }
        }
        return JSONArray.toJSONString(resultMap);

    }


    /**
     * 使用json返回数据格式查询用户信息详情，produces={"application/json;charset=UTF-8"}
     * 该配置处理返回json对象，页面中文乱码问题
     */
    @RequestMapping(value = "/view",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object view(@RequestParam int id) {
        logger.debug("view id========" + id);
        String cjson = "";
        if (0 == id || "".equals(id)) {
            return "nodate";
        } else {
            try {
                User user = us.getUserById(id);
                cjson = JSON.toJSONString(user);
                logger.debug("cjson==========" + cjson);
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
            return cjson;
        }
    }

    @RequestMapping(value = "/ucexist.html")
    //以下注解表示将方法的返回结果直接放入response响应数据区中
    @ResponseBody//查询userCode是否可用
    public Object userCodeIsExit(@RequestParam String userCode){
        logger.debug("userCodeIsExit  userCode=========="+userCode);
        HashMap<String, String> resultMap=new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(userCode)){
            resultMap.put("userCode","exist");
        }else {
            User user=us.getuserCodeLoginUser(userCode);
            if (null!=user){
                resultMap.put("userCode","exist");
            }else {
                resultMap.put("userCode","noexist");
            }
        }
        return JSONArray.toJSONString(resultMap);

    }














}
