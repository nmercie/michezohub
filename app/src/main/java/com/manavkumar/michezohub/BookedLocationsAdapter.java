package com.manavkumar.michezohub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookedLocationsAdapter extends RecyclerView.Adapter<BookedLocationViewHolder> {
    private List<Map<String, String>> bookedLocations = new ArrayList<>();
    private String name = "";

    @NonNull
    @Override
    public BookedLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_location_item,
                parent, false);

        name = ((AppController) parent.getContext().getApplicationContext()).getPreferences()
                .getString("name", "");
        return new BookedLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedLocationViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.tv_name);
        name.setText(this.name);

        TextView locationName = holder.itemView.findViewById(R.id.tv_location_name);
        Map<String, String> map = bookedLocations.get(position);
        locationName.setText(map.get("name"));
    }

    public void setBookedLocations(Map<String,Map<String, String>> bookedLocations) {
        this.bookedLocations = new ArrayList<>(bookedLocations.values());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bookedLocations.size();
    }
}
