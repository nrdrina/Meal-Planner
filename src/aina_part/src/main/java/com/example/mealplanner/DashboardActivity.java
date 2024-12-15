package com.example.mealplanner;

import static java.util.UUID.randomUUID;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    List<DynamicrvModel> items = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.pizza, "MON"));
        item.add(new StaticRvModel(R.drawable.pizza, "TUE"));
        item.add(new StaticRvModel(R.drawable.pizza, "WED"));
        item.add(new StaticRvModel(R.drawable.pizza, "THU"));
        item.add(new StaticRvModel(R.drawable.pizza, "FRI"));
        item.add(new StaticRvModel(R.drawable.pizza, "SAT"));
        item.add(new StaticRvModel(R.drawable.pizza, "SUN"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter =new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
         recyclerView.setAdapter(staticRvAdapter);

        items.add(new DynamicrvModel("Breakfast", "Rise and shine! Start your day with a delicious boost of energy! "));
        items.add(new DynamicrvModel("Lunch", "Midday munchies? Dive into a tasty treat to keep you going!"));
        items.add(new DynamicrvModel("Snacks", "Snack attack! Grab something light and fun to nibble on! "));
        items.add(new DynamicrvModel("Dinner", "End your day on a delicious note with a hearty meal!"));


        RecyclerView drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this,items);
        drv.setAdapter(dynamicRVAdapter);





//        dynamicRVAdapter.setLoadMore(new LoadMore() {
//            @Override
//            public void onLoadMore() {
//                if (items.size()<=3){
//                    items.add(null);
//                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            items.remove(items.size()-1);
//                            dynamicRVAdapter.notifyItemRemoved(items.size());
//
//                        int index = items.size();
//                        int end = index+3;
//                        for (int i = index; i <end; i++){
//                            String name = UUID.randomUUID().toString();
//                            DynamicrvModel item = new DynamicrvModel(name);
//                            items.add(item);
//                        }
//                        dynamicRVAdapter.notifyDataSetChanged();
//                        dynamicRVAdapter.setLoded();
//                        }
//                    }, 4000);
//
//
//                }
//                else
//                    Toast.makeText(DashboardActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
