package com.devindi.mapper.demo.data;

import android.os.Build;

import com.devindi.mapper.demo.Mapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by devindi on 06.03.18.
 */
public class PerformanceMapper {

    private final Mapper impl;
    private final PerformanceLogger logger;

    public PerformanceMapper(Mapper impl, PerformanceLogger logger) {
        this.impl = impl;
        this.logger = logger;
    }

    public void toPersonDto(final List<Person> source, final List<PersonDto> target) {
        for (final Person person : source) {
            target.add(measuredRun("simple", 1, new Callable<PersonDto>() {
                @Override
                public PersonDto call() {
                    return impl.toPersonDto(person);
                }
            }));
        }
    }

    public void toUserDto(final List<Person> source, final List<UserDto> target) {
        for (final Person person : source) {
            target.add(measuredRun("rename", 1, new Callable<UserDto>() {
                @Override
                public UserDto call() {
                    return impl.toUserDto(person);
                }
            }));
        }
    }

    public void toOrderDto(final List<Order> source, final List<OrderDto> target) {
        for (final Order order : source) {
            target.add(measuredRun("order", 1, new Callable<OrderDto>() {
                @Override
                public OrderDto call() {
                    return impl.toDto(order);
                }
            }));
        }
    }

    private <T> T measuredRun(String title, int size, Callable<T> runnable) {
        try {
            long start = System.nanoTime();
            T result = runnable.call();
            long duration = TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - start);
            try {
                logger.logDuration(title, size, duration);
            } catch (IOException loggerExc) {
                throw new RuntimeException(loggerExc);
            }
            return result;
        } catch (Exception e) {
            try {
                logger.logException(title, e);
            } catch (IOException ioExc) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    e.addSuppressed(ioExc);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
