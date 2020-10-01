package com.example.fourthhomework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Car> carList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView carImage;
        EditText carName;

        public ViewHolder(View view){
            super(view);
            carImage = (ImageView) view.findViewById(R.id.car_item);
            carName = (EditText) view.findViewById(R.id.car_name);
        }
    }

    public CarAdapter(List<Car> carList){
        this.carList = carList;
    }

    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carImage.setImageResource(car.getImageId());
        holder.carName.setText(car.getName());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
