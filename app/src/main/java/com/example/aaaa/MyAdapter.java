package com.example.aaaa;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.aaaa.models.Message;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Message> messages;

    public void setItems(List<Message> value) {
        this.messages = value;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return (messages != null) ? messages.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mensaje1;
        public ViewHolder(View view) {
            super(view);
            mensaje1 = view.findViewById(R.id.line);

        }
        public void bind(Message message) {
            mensaje1.setText(message.getCuerpoMensaje());


        }
    }
}


