package com.devindi.mapper.demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.devindi.mapper.demo.data.HugoPerformanceMapper;
import com.devindi.mapper.demo.data.MeteredMapper;
import com.devindi.mapper.demo.data.PerformanceLogger;
import com.devindi.mapper.demo.data.Provider;
import com.devindi.mapper.demo.data.TracedMapper;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkActivity extends Activity {

    private IMapper mapper;

    private List<Person> persons;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.benchmark_activity);

        requestPermissionIfNeeded();

        findViewById(R.id.btn_simple).setOnClickListener(buttonsListener);
        findViewById(R.id.btn_rename).setOnClickListener(buttonsListener);
        findViewById(R.id.btn_order).setOnClickListener(buttonsListener);

        setTitle(getString(R.string.app_name) + " " + BuildConfig.FLAVOR);

        mapper = createMapper(2);
        Provider provider = new Provider();

        persons = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            persons.add(provider.getPerson(50));
        }
        orders = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            orders.add(provider.getOrder(50));
        }
    }

    private View.OnClickListener buttonsListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_simple:
                    List<PersonDto> target = new LinkedList<>();
                    for (Person person : persons) {
                        PersonDto dto = mapper.toPersonDto(person);
                        target.add(dto);
                    }
                    Log.v("result", target.toString());
                    break;
                case R.id.btn_rename:
                    List<UserDto> userDtos = new LinkedList<>();
                    for (Person person : persons) {
                        UserDto dto = mapper.toUserDto(person);
                        userDtos.add(dto);
                    }
                    Log.v("result", userDtos.toString());
                    break;
                case R.id.btn_order:
                    List<OrderDto> orderDtos = new LinkedList<>();
                    for (Order order : orders) {
                        OrderDto dto = mapper.toDto(order);
                        orderDtos.add(dto);
                    }
                    Log.v("result", orderDtos.toString());
                    break;
            }
        }
    };

    private void requestPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    private IMapper createMapper(int kind) {
        switch (kind) {
            //Metered
            case 0:
                try {
                    return new MeteredMapper(new Mapper(), new PerformanceLogger(BuildConfig.FLAVOR, new File(Environment.getExternalStorageDirectory(), "perfRes").getAbsolutePath(), Build.DEVICE));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            //Hugo
            case 1:
                return new HugoPerformanceMapper(new Mapper());
            //Traced
            case 2:
                return new TracedMapper(new Mapper());
            default:
                throw new IllegalArgumentException();
        }
    }
}
