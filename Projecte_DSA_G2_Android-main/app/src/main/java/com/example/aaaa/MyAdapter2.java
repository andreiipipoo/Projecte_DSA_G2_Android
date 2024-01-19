package com.example.aaaa;

import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
        return insignias != null ? insignias.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private ImageView uri;
        //private ImageView featuredImage;
        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.nombreUsuario2);
            uri = view.findViewById(R.id.imagenUsuario2);
        }
        public void bind(Insignia insignia) {
            Log.d("HOLA", "ESTOY EN EL BIND");
            username.setText(insignia.getNombre());
            Log.d("PICASSO", "AHORA ENTRO PICASSO");
            Log.d("URI", String.valueOf(uri));
            Picasso.get().load(insignia.getUri()).into(uri, new Callback() {
                @Override
                public void onSuccess() {
                    // La imagen se cargó correctamente
                    Log.d("Imagen cargada?", "SI");
                }

                @Override
                public void onError(Exception e) {
                    // Se produjo un error al cargar la imagen
                    e.printStackTrace();
                    Log.d("Imagen cargada?", "NO");

                }
            });
            Log.d("HE ENTRADO CALLBACK", "HE SALIDO CALLBACK");
        }
    }

}
