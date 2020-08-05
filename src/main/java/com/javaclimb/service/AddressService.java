package com.javaclimb.service;

import com.javaclimb.entity.Address;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;

/**
 * 收货地址service接口
 */
public interface AddressService {
    ReturnDataForLayui getList(Address address);
    ReturnData add(Address address);
    ReturnData delete(Address address);
    ReturnData update(Address address);
    ReturnData updateState(Address address);
}
