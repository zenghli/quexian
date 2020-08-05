package com.javaclimb.mapper;

import com.javaclimb.entity.Address;

import java.util.List;

/**
 * 微信用户地址管理
 */
public interface AddressMapper {
    List<Address> getList(Address address);
    int add(Address address);
    int delete(Address address);
    int update(Address address);
    int updateState(Address address);
    Address getById(Integer addressId);
}
