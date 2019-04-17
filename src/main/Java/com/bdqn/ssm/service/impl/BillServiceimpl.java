package com.bdqn.ssm.service.impl;

import com.bdqn.ssm.dao.BillDao;
import com.bdqn.ssm.entity.Bill;
import com.bdqn.ssm.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("billService")
public class BillServiceimpl implements BillService {
    @Resource
    private BillDao billDao;
    public BillDao getBillDao() {
        return billDao;
    }
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public List<Bill> getBillList(String queryProductName, int queryProviderId,int queryIsPayment, int pageIndex, int pageSize) {
        return billDao.getBillList(queryProductName,queryProviderId,queryIsPayment,pageIndex,pageSize );
    }

    @Override
    public int getBillCount(String queryProductName, int queryProviderId, int queryIsPayment) {
        return billDao.getBillCount(queryProductName,queryProviderId,queryIsPayment);
    }

    @Override
    public int addBill(Bill bill) {
       return billDao.addBill(bill);
    }

    @Override
    public int updateBill(Bill bill) {
        return billDao.updateBill(bill);
    }

    @Override
    public int deleteBill(String bid) {
        return billDao.deleteBill(bid);
    }

    @Override
    public Bill getBillById(String bid) {
        return billDao.getBillById(bid);
    }
}
