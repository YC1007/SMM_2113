package com.bdqn.ssm.dao;

import com.bdqn.ssm.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao {
    //分页查询订单信息，模糊查询getBillList
    public List<Bill> getBillList(@Param("queryProductName")String queryProductName,
                                  @Param("queryProviderId")Integer queryProviderId,
                                  @Param("queryIsPayment")Integer queryIsPayment,
                                  @Param("pageIndex")Integer pageIndex,
                                  @Param("pageSize")Integer pageSize);


    //统计账单总记录数getBillCount
    public int getBillCount(@Param("queryProductName") String queryProductName,
                            @Param("queryProviderId")Integer queryProviderId,
                            @Param("queryIsPayment")Integer queryIsPayment);


    //添加订单信息addBill
    public int addBill(Bill bill);

    //修改订单信息updateBill
    public int updateBill(Bill bill);

    //删除订单信息deleteBill
    public int deleteBill(String bid);

    //通过ID查询订单记录getBillById
    public Bill getBillById(String bid);

}
