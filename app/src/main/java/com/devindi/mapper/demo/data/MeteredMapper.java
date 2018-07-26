package com.devindi.mapper.demo.data;

import android.os.Build;

import com.devindi.mapper.demo.IMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MeteredMapper implements IMapper {

    private final IMapper impl;
    private final PerformanceLogger logger;

    public MeteredMapper(IMapper impl, PerformanceLogger logger) {
        this.impl = impl;
        this.logger = logger;
    }

    @Override
    public OrderDto toDto(Order order) {
        return null;
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        return null;
    }

    @Override
    public UserDto toUserDto(Person person) {
        return null;
    }

    private <T> T measuredRun(String title, Callable<T> runnable) {
        try {
            long start = System.nanoTime();
            T result = runnable.call();
            long duration = TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - start);
            try {
                logger.logDuration(title, duration);
            } catch (IOException loggerExc) {
                throw new RuntimeException(loggerExc);
            }
            return result;
        } catch (Exception e) {
            try {
                logger.logException(title);
            } catch (IOException ioExc) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    e.addSuppressed(ioExc);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
