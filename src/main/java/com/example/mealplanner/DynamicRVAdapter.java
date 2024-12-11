package com.example.mealplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;


public class DynamicRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    LoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<DynamicrvModel> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public DynamicRVAdapter(RecyclerView recyclerView, Activity activity, List<DynamicrvModel> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMore != null) {
                        loadMore.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rv_item_layout, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.loading_layout, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            DynamicrvModel item = items.get(position);
            itemViewHolder.name.setText(item.getMealName());

            // Handle the "View" button click
            itemViewHolder.itemView.findViewById(R.id.viewButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        // Get the current meal
                        DynamicrvModel currentMeal = items.get(adapterPosition);
                        // Open a dialog to view saved meal details
                        openSavedMealsDialog(currentMeal);
                    }
                }
            });

            // Handle the "Insert" button click (this part remains the same)
            itemViewHolder.itemView.findViewById(R.id.insertButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        DynamicrvModel item = items.get(adapterPosition);
                        openMealInputDialog(item.getMealName(), adapterPosition);
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoded() {
        isLoading = false;
    }
    // Assuming you have a list of saved meal details
    private List<String> savedMeals = new ArrayList<>();

    // This method shows the saved meals in a dialog
    private void openSavedMealsDialog(DynamicrvModel meal) {
        // Create a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Saved Meals for " + meal.getMealName());

        // Create a string to display all the saved meals
        StringBuilder savedMealsText = new StringBuilder();
        for (String savedMeal : savedMeals) {
            savedMealsText.append(savedMeal).append("\n");
        }

        // If no meals are saved, show a message indicating no saved meals
        if (savedMeals.isEmpty()) {
            savedMealsText.append("No meals saved yet.");
        }

        // Set the message of the dialog
        builder.setMessage(savedMealsText.toString());

        // Set up the positive button (Close)
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Just close the dialog
            }
        });

        // Show the dialog
        builder.show();
    }

    // Method to handle the meal input (could open a dialog or another activity)
    private void openMealInputDialog(String mealName, int position) {
        // Create a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Yohoo! what have you eaten for " + mealName + "?");

        // Create an EditText for user input
        final EditText input = new EditText(activity);
        input.setHint("Enter meal details");
        builder.setView(input);

        // Set up the positive button (Save)
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mealDetails = input.getText().toString();
                // Save the entered meal details
                savedMeals.add(mealDetails);

                // Display a toast
                Toast.makeText(activity, "Saved meal: " + mealDetails, Toast.LENGTH_SHORT).show();
            }
        });

        // Set up the negative button (Cancel)
        builder.setNegativeButton("Cancel", null);

        // Show the dialog
        builder.show();
    }
}

class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}
