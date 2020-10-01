package com.example.fourthhomework;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Car> carList;
    private onTextChangeListener textListener;
    private ArrayList<TextWatcher> listeners;

    public void addTextChangeListener(TextWatcher watcher){
        if(listeners == null){
            listeners = new ArrayList<TextWatcher>();
        }
        listeners.add(watcher);
    }

    public void setOntextChangeListener(onTextChangeListener textListener){
        this.textListener = textListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View carView;
        ImageView carImage;
        EditText carName;

        public ViewHolder(View view){
            super(view);
            carView = view;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.carView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Car car = carList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + car.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        holder.carImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Car car = carList.get(position);
                Toast.makeText(v.getContext(), "you clicked image "+car.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Car car = carList.get(position);
        holder.carImage.setImageResource(car.getImageId());
        holder.carName.setText(car.getName());
        final TextWatcher textWatcher = new TextWatcher() {
            Car car = carList.get(position);
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                textListener.onTextChanged(position, holder.carName.getText().toString());
                car.setName(holder.carName.getText().toString());
            }
        };

        holder.carName.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    holder.carName.addTextChangedListener(textWatcher);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
