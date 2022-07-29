package com.example.a3_marcn_mnicolas_oliva.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a3_marcn_mnicolas_oliva.databinding.CustomRowLayoutBinding;
import com.example.a3_marcn_mnicolas_oliva.models.Zelda;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<Zelda> itemArrayList;
    CustomRowLayoutBinding binding;

    public ItemAdapter(Context context, ArrayList<Zelda> items){
        this.itemArrayList = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Zelda currentItem = itemArrayList.get(position);
        holder.bind(context, currentItem);
    }

    @Override
    public int getItemCount() {
        Log.d("ItemAdapter", "getItemCount: Number of items " +this.itemArrayList.size() );
        return this.itemArrayList.size();
    }
    // Generate list of views
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CustomRowLayoutBinding itemBinding;

        public ItemViewHolder(CustomRowLayoutBinding binding){
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Zelda obj){
            String name = obj.getName();
            // First letter of name to uppercase
            String newName = name.substring(0, 1).toUpperCase() + name.substring(1);

            itemBinding.tvName.setText(newName);
            itemBinding.tvStats.setText("Attack: " + String.valueOf(obj.getAttack()) +
                                        " - Defense: " + String.valueOf(obj.getDefense()));
            itemBinding.tvDescription.setText(obj.getDescription());

            Glide.with(context).load(obj.getImageURL()).into(itemBinding.ivItemPhoto);
        }
    }
}