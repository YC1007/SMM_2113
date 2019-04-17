package com.bdqn.ssm.service;

import com.bdqn.ssm.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {
    //分页查询订单信息，模糊查询
    public List<Bill> getBillList(String queryProductName,
                                  int queryProviderId,
                                  int queryIsPayment,
                                  int pageIndex,
                                  int pageSize);


    //统计账单总记录数queryProductName,_queryProviderId,_queryIsPayment
    public int getBillCount(String queryProductName,int queryProviderId,int queryIsPayment);


    //添加订单信息
    public int addBill(Bill bill);

    //修改订单信息
    public int updateBill(Bill bill);

    //删除订单信息
    public int deleteBill(String bid);

    //通过ID查询订单记录
    public Bill getBillById(String bid);
}
