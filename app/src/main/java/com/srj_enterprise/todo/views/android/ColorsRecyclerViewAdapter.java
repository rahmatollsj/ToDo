package com.srj_enterprise.todo.views.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.data.ColorData;
import com.srj_enterprise.todo.enums.RecyclerViewAdapterType;
import com.srj_enterprise.todo.listeners.ItemOnClickListener;

public class ColorsRecyclerViewAdapter extends RecyclerView.Adapter<ColorsRecyclerViewAdapter.ViewHolder> {

    private ItemOnClickListener listener;
    private ColorData[] colors;

    public void setOnClickListener(ItemOnClickListener listener) { this.listener = listener; }

    public ColorsRecyclerViewAdapter(ColorData[] colors) {
        this.colors = colors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_color_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardViewColorItem.setCardBackgroundColor(colors[position].getColor());

        if(colors[position].isChecked()) {
            holder.imageViewColorItem.setVisibility(View.VISIBLE);
        } else {
            holder.imageViewColorItem.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemOnClickListener listener;
        private final CardView cardViewColorItem;
        private final ImageView imageViewColorItem;

        public ViewHolder(@NonNull View itemView, ItemOnClickListener listener) {
            super(itemView);

            this.listener = listener;

            cardViewColorItem = itemView.findViewById(R.id.card_view_color_item);
            cardViewColorItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(RecyclerViewAdapterType.COLOR, getAdapterPosition());
                    }
                }
            });

            imageViewColorItem = itemView.findViewById(R.id.image_view_color_item);
        }
    }
}