package com.srj_enterprise.todo.views.android;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.data.IconData;
import com.srj_enterprise.todo.enums.RecyclerViewAdapterType;
import com.srj_enterprise.todo.listeners.ItemOnClickListener;

public class IconsRecyclerViewAdapter extends RecyclerView.Adapter<IconsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ItemOnClickListener listener;
    private IconData[] icons;

    public void setOnClickListener(ItemOnClickListener listener) { this.listener = listener; }

    public IconsRecyclerViewAdapter(Context context, IconData[] icons) {
        this.context = context;
        this.icons = icons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_icon_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageViewIconItem.setImageResource(icons[position].getImageId());

        TypedValue cardColor = new TypedValue();

        if(icons[position].isSelected()) {
            context.getTheme().resolveAttribute(R.attr.colorSecondary, cardColor, true);
            holder.cardViewIconItem.setCardBackgroundColor(cardColor.data);
            holder.imageViewIconItem.setColorFilter(context.getColor(R.color.white));
        } else {
            context.getTheme().resolveAttribute(R.attr.colorPrimary, cardColor, true);
            holder.cardViewIconItem.setCardBackgroundColor(cardColor.data);
            holder.imageViewIconItem.setColorFilter(null);
        }
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemOnClickListener listener;
        private final CardView cardViewIconItem;
        private final ImageView imageViewIconItem;

        public ViewHolder(@NonNull View itemView, ItemOnClickListener listener) {
            super(itemView);

            this.listener = listener;

            cardViewIconItem = itemView.findViewById(R.id.card_view_icon_item);
            cardViewIconItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(RecyclerViewAdapterType.ICON, getAdapterPosition());
                    }
                }
            });

            imageViewIconItem = itemView.findViewById(R.id.image_view_icon_item);
        }
    }
}