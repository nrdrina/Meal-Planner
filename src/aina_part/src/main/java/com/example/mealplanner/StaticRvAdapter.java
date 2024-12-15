package com.example.mealplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{


    private ArrayList<StaticRvModel> items;
    int row_index = 1 ;

    public StaticRvAdapter(ArrayList<StaticRvModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent, false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
//        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use holder.getAdapterPosition() to get the current position
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    row_index = currentPosition;
                    notifyDataSetChanged(); // Consider using notifyItemChanged(previousIndex) and notifyItemChanged(currentPosition) for better performance
                }
            }
        });

        // Set background based on selection
        if (holder.getAdapterPosition() == row_index) {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

     TextView textView;
//     ImageView imageView;
     LinearLayout linearLayout;

//     TextDate textDate;

     public StaticRVViewHolder(@NonNull View itemView){
         super(itemView);
//         imageView = itemView.findViewById(R.id.image);
         textView = itemView.findViewById(R.id.textView);
//         textDate = itemView.findViewById(R.id.textDate);
         linearLayout = itemView.findViewById(R.id.LinearLayout);
     }
 }

}
