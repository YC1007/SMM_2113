package com.bdqn.ssm.controller;


import com.alibaba.fastjson.JSONArray;
import com.bdqn.ssm.entity.Bill;
import com.bdqn.ssm.entity.Provider;
import com.bdqn.ssm.entity.User;
import com.bdqn.ssm.service.BillService;
import com.bdqn.ssm.service.ProviderService;
import com.bdqn.ssm.util.Constants;
import com.bdqn.ssm.util.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService bs;
    @Resource
    private ProviderService ps;
    private Logger logger=Logger.getLogger(UserController.class);

    //分页查询
    @RequestMapping(value = "/billlist.html")
    public String getBillList(Model model,
                              @RequestParam(value = "queryProductName",required = false)String queryProductName,
                              @RequestParam(value = "queryProviderId",required = false)String queryProviderId,
                              @RequestParam(value = "queryIsPayment",required = false)String queryIsPayment,
                              @RequestParam(value = "pageIndex",required = false)String pageIndex){
        logger.info("getUserList--------:queryProductName:"+queryProductName);
        logger.info("getUserList--------:queryProviderId:"+queryProviderId);
        logger.info("getUserList--------:pageIndex:"+pageIndex);
        int _queryProviderId=0;
        int _queryIsPayment=0;
        List<Bill> billList=null;
        //设置页面容量
        int pageSize= Constants.pageSize;
        //当前页码
        int currentPageNo=1;
        if (queryProductName==null){
            queryProductName="";
        }
        if (queryProviderId!=null && !queryProviderId.equals("")){
            _queryProviderId=Integer.parseInt(queryProviderId);
        }
        if (queryIsPayment!=null && !queryIsPayment.equals("")){
            _queryIsPayment=Integer.parseInt(queryIsPayment);
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
        int totalCount=bs.getBillCount(queryProductName,_queryProviderId,_queryIsPayment);
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
        billList=bs.getBillList(queryProductName,_queryProviderId,_queryIsPayment,(currentPageNo-1)*pageSize,pageSize);
        model.addAttribute("billList",billList);
        List<Provider> providerList=null;
        providerList=ps.getProviderList();
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProductName",queryProductName);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        model.addAttribute("totalPageCount",totalPageCount);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("currentPageNo",currentPageNo);
        return "billlist";

    }

    //跳转到添加页面
    @RequestMapping(value = "/addbill.html",method = RequestMethod.GET)
    public String getBilladd(){
        return "billadd";
    }
    //添加订单信息
    @RequestMapping(value = "/addbill.html",method = RequestMethod.POST)
    public String getBilladd(Bill bill, HttpSession session){
        bill.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        System.out.println("\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\n"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\n"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\n"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+
                "\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy()+"\t"+bill.getCreatedBy());
        System.out.println("\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                "\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                "\n"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                "\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                "\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                        "\n"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                        "\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                        "\n"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+
                        "\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()+"\t"+bill.getCreationDate()
                );
        if (bs.addBill(bill)>0){
            return "redirect:/bill/billlist.html";
        }else {
            return "billadd";
        }
    }

    //通过ID查询订单记录getBillById
    @RequestMapping(value = "/selectid/{id}",method = RequestMethod.GET)
    public String getBillBuId(@PathVariable String id,Model model){
           Bill bill=bs.getBillById(id);
            model.addAttribute(bill);
            return "billview";
    }
@RequestMapping(value = "/providerlist",method = RequestMethod.GET,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object getproviderlist(){
        List<Provider>providerList=ps.getProviderList();
        return JSONArray.toJSONString(providerList);
    }





}
