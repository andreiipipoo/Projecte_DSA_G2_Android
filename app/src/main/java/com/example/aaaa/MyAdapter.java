package com.example.aaaa;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aaaa.models.Item;
import com.example.aaaa.models.Message;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Message> messages;
    private OnItemClickListener onItemClickListener;


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
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mensaje1;

        //private ImageView featuredImage;
        public ViewHolder(View view) {
            super(view);
            mensaje1 = view.findViewById(R.id.firstLine);

        }
        public void bind(Message message) {
            mensaje1.setText(message.getCuerpoMensaje());

        }
    }
}


