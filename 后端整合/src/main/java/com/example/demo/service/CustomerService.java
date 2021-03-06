package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerSimple;
import com.example.demo.model.Vo.Search;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ArticleDao articleDao;

    //获得简介信息
    @Transactional
    public PageInfo<CustomerSimple> getAll(Integer pageNum, Integer pageSize){
        // 1 设置分页页码和显示条数
        PageHelper.startPage(pageNum, pageSize,true);
        // 依然调用查询所有的方法
        List<CustomerSimple> list = customerDao.getAll();
        // 2 集合封装到PageInfo类中
        PageInfo<CustomerSimple> pages = new PageInfo<>(list);
        return pages;
    }

    @Transactional
    public PageInfo<CustomerSimple> getAll(Search search){
        System.out.println(search);
        // 1 设置分页页码和显示条数
        PageHelper.startPage(search.getCurrent(), search.getSize(),true);
        // 依然调用查询所有的方法
        List<CustomerSimple> list;
        System.out.println(search.getSearch());
        if(search.getSearch()==null||search.getSearch().equals(""))
            list= customerDao.getAll();
        else  list= customerDao.searchAll(search.getSearch());;
        // 2 集合封装到PageInfo类中
        PageInfo<CustomerSimple> pages = new PageInfo<>(list);
        return pages;
    }

    //获得某一个人的全部信息
    public Customer getById(Integer id){
        Customer customer= customerDao.getCustomerById(id);
        return customer;
    }
    //修改用户信息
    public Customer updateByCustomer(Customer customer){
        customerDao.updateCustomer(customer);
        Customer customer1=customerDao.getCustomerById(customer.getId());
        return customer1;
    }
    //注销用户
    public Customer deleteCustomer(Integer id){
        Customer customer= customerDao.getCustomerById(id);
        customer.setState((byte)2);
        customerDao.updateCustomer(customer);
        return customer;
    }
    //封禁用户
    public Customer banCustomer(Integer id){
        Customer customer= customerDao.getCustomerById(id);
        customer.setState((byte)1);
        customerDao.updateCustomer(customer);
        return customer;
    }
    //登录
    public Customer login(String name,String pass){
        Customer customer=new Customer();
        customer.setName(name);
        customer.setPassword(pass);
        List<Customer> list= customerDao.selectCustomer(customer);
        if(list.size()>0)
            return list.get(0);
        return null;
    }
    //注册
    public Customer register(Customer customer){
        Customer customer1=new Customer();
        customer1.setName(customer.getName());
        if(customerDao.selectCustomer(customer1).size()>0)
            return null;
        System.out.println(customer1);
        customerDao.addCustomer(customer);
        return customer;
    }
    //查找用户
    public List<Customer> selectCustomer(Customer customer){
        return customerDao.selectCustomer(customer);
    }

    public void unBanCustomer(Integer id) {
        customerDao.changeCustomerState(0,id);
    }


    public PageInfo<Customer> adminGetCustomer(Search search) {
        // 1 设置分页页码和显示条数
        PageHelper.startPage(search.getCurrent(), search.getSize(),true);
        // 依然调用查询所有的方法
        List<Customer> list;
        System.out.println(search.getSearch());
//        if(search.getSearch()==null||search.getSearch().equals(""))
        list= customerDao.adminGetAll();
        // 2 集合封装到PageInfo类中
        PageInfo<Customer> pages = new PageInfo<>(list);
        return pages;
    }

}
