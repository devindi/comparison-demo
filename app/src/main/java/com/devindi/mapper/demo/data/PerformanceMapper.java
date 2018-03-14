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
        measuredRun("simple", source.size(), new Runnable() {
            @Override
            public void run() {
                for (Person person : source) {
                    target.add(impl.toPersonDto(person));
                }
            }
        });
    }

    public void toUserDto(final List<Person> source, final List<UserDto> target) {
        measuredRun("rename", source.size(), new Runnable() {
            @Override
            public void run() {
                for (Person person : source) {
                    target.add(impl.toUserDto(person));
                }
            }
        });
    }

    public void toOrderDto(final List<Order> source, final List<OrderDto> target) {
        measuredRun("order", source.size(), new Runnable() {
            @Override
            public void run() {
                for (Order order : source) {
                    target.add(impl.toDto(order));
                }
            }
        });
    }

    private void measuredRun(String title, int size, Runnable runnable) {
        try {
            long start = System.nanoTime();
            runnable.run();
            long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            try {
                logger.logDuration(title, size, duration);
            } catch (IOException loggerExc) {
                throw new RuntimeException(loggerExc);
            }
        } catch (Exception e) {
            try {
                logger.logException(title, e);
            } catch (IOException ioExc) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    e.addSuppressed(ioExc);
                }
            }
            throw e;
        }
    }
}
