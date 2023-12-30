package com.example.aaaa;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.aaaa.models.Insignia;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<Insignia> insignias;

    public void setInsignias(List<Insignia> value) {
        this.insignias = value;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter2.ViewHolder holder, int position) {
        Insignia insignia = insignias.get(position);
        holder.bind(insignia);
    }

    @Override
    public int getItemCount() {
        return insignias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView uri;
        //private ImageView featuredImage;
        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.nombreUsuario2);
            uri = view.findViewById(R.id.imagenUsuario2);
        }
        public void bind(Insignia insignia) {
            username.setText(insignia.getNombre());
            uri.setText(insignia.getUri());
        }
    }

}
