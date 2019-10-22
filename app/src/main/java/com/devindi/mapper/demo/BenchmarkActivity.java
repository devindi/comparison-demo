package com.devindi.mapper.demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.devindi.mapper.demo.data.PerformanceLogger;
import com.devindi.mapper.demo.data.PerformanceMapper;
import com.devindi.mapper.demo.data.Provider;
import com.devindi.mapper.demo.data.ValidationMapper;
import com.devindi.mapper.demo.data.Validator;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.dto.rename.UserDto;
import com.devindi.mapper.demo.dto.simple.PersonDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class BenchmarkActivity extends Activity {

    private PerformanceMapper mapper;
    private ValidationMapper validationMapper;

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

        Mapper impl = new Mapper();
        mapper = new PerformanceMapper(impl, new PerformanceLogger(BuildConfig.FLAVOR, new File(Environment.getExternalStorageDirectory(), "perfRes").getAbsolutePath()));
        validationMapper = new ValidationMapper(new Validator(), impl);
        Provider provider = new Provider();

        persons = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            persons.add(provider.getPerson(50));
        }
        orders = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            orders.add(provider.getOrder(50));
        }
    }

    private View.OnClickListener buttonsListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            boolean result = false;
            switch (v.getId()) {
                case R.id.btn_simple:
                    result = validationMapper.toPersonDto(persons.get(0)) != null;
                    mapper.toPersonDto(persons, new LinkedList<PersonDto>());

                    break;
                case R.id.btn_rename:
                    result = validationMapper.toUserDto(persons.get(0)) != null;
                    mapper.toUserDto(persons, new LinkedList<UserDto>());
                    break;
                case R.id.btn_order:
                    result = validationMapper.toDto(orders.get(0)) != null;
                    mapper.toOrderDto(orders, new LinkedList<OrderDto>());
                    break;
            }
            Toast.makeText(BenchmarkActivity.this, result ? "OK" : "fail", Toast.LENGTH_SHORT).show();
        }
    };

    private void requestPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }
}
