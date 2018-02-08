package com.devindi.mapper.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devindi.mapper.demo.data.Provider;
import com.devindi.mapper.demo.dto.complex.OrderDto;
import com.devindi.mapper.demo.model.complex.Order;
import com.devindi.mapper.demo.model.simple.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = new View(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Provider provider = new Provider();
//                List<Order> orders = new ArrayList<>();
//                List<OrderDto> dtos = new ArrayList<>();
                Mapper mapper = new Mapper();
//                for (int i = 0; i < 10; i++) {
//                    orders.add(provider.getRandomOrder());
//                }
//                Log.d("mapping", "onClick: start mapping");
//                for (int i = 0; i < 500_000; i ++) {
//                    mapper.toPersonDto(new Person("a" + i, null));
//                }
//                Log.d("mapping", "onClick: finish mapping");
            }
        });
        setContentView(view);


    }
}
