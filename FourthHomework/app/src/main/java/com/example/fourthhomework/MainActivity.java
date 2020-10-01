package com.example.fourthhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Car> carList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCars();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CarAdapter adapter = new CarAdapter(carList);
        recyclerView.setAdapter(adapter);
    }

    private void initCars(){
        for(int i = 0; i < 3; i++){
            Car bike = new Car(getRandomLengthName("自行车"), R.drawable.bike);
            carList.add(bike);
            Car car = new Car(getRandomLengthName("轿车"),R.drawable.car);
            carList.add(car);
            Car digger = new Car(getRandomLengthName("挖掘机"),R.drawable.digger);
            carList.add(digger);
            Car escort_car = new Car(getRandomLengthName("救护车"), R.drawable.escort_car);
            carList.add(escort_car);
            Car fire_truck = new Car(getRandomLengthName("消防车"), R.drawable.fire_truck);
            carList.add(fire_truck);
            Car suv = new Car(getRandomLengthName("越野车"), R.drawable.suv);
            carList.add(suv);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
    }
}