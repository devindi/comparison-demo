package com.devindi.mapper.demo.data;

import com.devindi.mapper.demo.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import hugo.weaving.DebugLog;

public class HugoPerformanceMapper implements IMapper {

    private final IMapper impl;

    public HugoPerformanceMapper(IMapper impl) {
        this.impl = impl;
    }

    @Override
    @DebugLog
    public OrderDto toDto(Order order) {
        return impl.toDto(order);
    }

    @Override
    @DebugLog
    public PersonDto toPersonDto(Person person) {
        return impl.toPersonDto(person);
    }

    @Override
    @DebugLog
    public UserDto toUserDto(Person person) {
        return impl.toUserDto(person);
    }
}
