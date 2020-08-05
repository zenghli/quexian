package com.javaclimb.controller;

import com.javaclimb.entity.Address;
import com.javaclimb.service.AddressService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 收货地址管理控制层
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "address/list";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(Address address, Integer userId){
        address.setUid(userId);
        return addressService.getList(address);
    }

    @RequestMapping("/addPage")
    public String addPage(){
        return "address/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnData add(Address address, Integer userId){
        address.setUid(userId);
        return addressService.add(address);
    }

    @RequestMapping("/editPage")
    public String editPage(){
        return "address/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(Address address, Integer userId){
        address.setUid(userId);
        return addressService.update(address);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public ReturnData updateState(Address address){
        return addressService.updateState(address);
    }

}
