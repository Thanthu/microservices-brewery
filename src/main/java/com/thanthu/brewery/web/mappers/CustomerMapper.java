package com.thanthu.brewery.web.mappers;

import org.mapstruct.Mapper;

import com.thanthu.brewery.domain.Customer;
import com.thanthu.brewery.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
