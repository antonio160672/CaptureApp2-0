package com.example.captureapp2_0.Vistas.Servidores_frag;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captureapp2_0.R;
import com.example.captureapp2_0.objetos.Obje_servi;

import java.util.ArrayList;

public class Adaptador_recycle_Servido
        extends RecyclerView.Adapter<Adaptador_recycle_Servido.ViewHoldersServidores> {
    private servidor_view_Fragment servidor_view_fragment_1;
    private ArrayList<Obje_servi> Lista_servi;
    public Adaptador_recycle_Servido(ArrayList<Obje_servi> lista_servi,servidor_view_Fragment servidor_view_fragment) {
        Lista_servi = lista_servi;
        servidor_view_fragment_1=servidor_view_fragment;
    }

    @NonNull
    @Override
    public ViewHoldersServidores onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.elemento_adaptador_servidores,null,false);
        return new ViewHoldersServidores(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersServidores holder, int position) {
        holder.asignardatos(Lista_servi.get(position));
    }

    @Override
    public int getItemCount() {
        return Lista_servi.size();
    }



    public class ViewHoldersServidores extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView DNSS,IP,Puerto_ORCB,Puerto_CTDB;
        ImageView servidor_pre,eliminar,editar;
        public ViewHoldersServidores(@NonNull View itemView) {
            super(itemView);
             DNSS=itemView.findViewById(R.id.DNSS);
             IP=itemView.findViewById(R.id.IP);
             Puerto_ORCB=itemView.findViewById(R.id.Puerto_ORCB);
             Puerto_CTDB=itemView.findViewById(R.id.Puerto_CTDB);
             servidor_pre=itemView.findViewById(R.id.Servidor_pordefecto);
             eliminar=itemView.findViewById(R.id.Eliminar);
             editar=itemView.findViewById(R.id.Editar);
             eliminar.setOnClickListener(this);
             editar.setOnClickListener(this);
            servidor_pre.setOnClickListener(this);

        }

        public void asignardatos(Obje_servi obje_servi) {
            String[] DNNS = obje_servi.getDNS_ser().split("\\.");
            DNSS.setText("DDNS:\n"+DNNS[0]);
            IP.setText("IP:"+obje_servi.getIp_servidor());
            Puerto_ORCB.setText("ORCB:"+obje_servi.getPuerto_orion());
            Puerto_CTDB.setText("CDB:"+obje_servi.getPuerto_crateDB());
            if(obje_servi.getServidor_predeter()==0){
                servidor_pre.setImageResource(R.drawable.tache);
            }
        }

       @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
       @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Eliminar:
                    //eliminar.setVisibility(view.GONE);
                    //Log.e();
                    if (servidor_view_fragment_1!=null){
                        servidor_view_fragment_1.eliminar_servi(Lista_servi.get(getAdapterPosition()).getId_servi(),Lista_servi.get(getAdapterPosition()));
                    }
                break;
                case R.id.Editar:
                    if (servidor_view_fragment_1!=null){
                        if (Lista_servi.get(getAdapterPosition()).getDNS_ser().equals("Sin DNNS")){
                            Lista_servi.get(getAdapterPosition()).setDNS_ser("");
                        }else{
                            Lista_servi.get(getAdapterPosition()).setIp_servidor("");
                        }
                        servidor_view_fragment_1.Editar_servi(Lista_servi.get(getAdapterPosition()).getId_servi(),Lista_servi.get(getAdapterPosition()));
                    }
                    break;
                case R.id.Servidor_pordefecto:
                    if (servidor_view_fragment_1!=null){
                        servidor_view_fragment_1.mensaje_sin_servido("item de servidor"+String.valueOf(getAdapterPosition()));
                    }
                    break;
            }
        }
    }
}
