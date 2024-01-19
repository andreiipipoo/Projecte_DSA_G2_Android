package com.example.aaaa;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aaaa.models.Item;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Item> items;
    private OnItemClickListener onItemClickListener;


    public void setItems(List<Item> value) {
        this.items = value;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView descripcion;
        private ImageButton botonImagen;
        //private ImageView featuredImage;
        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.firstLine);
            descripcion = view.findViewById(R.id.secondLine);
            botonImagen = view.findViewById(R.id.botonImagen);
            botonImagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(items.get(position));
                    }
                }
            });
        }
        public void bind(Item item) {
            nombre.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());

        }
    }
}


