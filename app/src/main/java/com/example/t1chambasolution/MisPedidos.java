package com.example.t1chambasolution;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.t1chambasolution.modelo.Pedido;

public class MisPedidos extends AppCompatActivity {

    List<String> numerosOrden;
    List<Pedido> pedidos;
    AdaptadorPersona ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        numerosOrden = new ArrayList<>();
        pedidos = new ArrayList<>();

        pedidos.add(new Pedido("ss1284848nabc", "10 de mayo de 2024", 250.22, "Enviado"));
        pedidos.add(new Pedido("ss1234567abc", "12 de mayo de 2024", 320.50, "Pendiente"));
        pedidos.add(new Pedido("ss1289999xyz", "15 de mayo de 2024", 150.75, "Enviado"));
        pedidos.add(new Pedido("ss1201234def", "18 de mayo de 2024", 410.00, "Entregado"));
        pedidos.add(new Pedido("ss1212345ghi", "20 de mayo de 2024", 199.99, "Cancelado"));
        pedidos.add(new Pedido("ss1223456jkl", "22 de mayo de 2024", 85.50, "Pendiente"));
        pedidos.add(new Pedido("ss1234567mno", "25 de mayo de 2024", 300.00, "Enviado"));
        pedidos.add(new Pedido("ss1245678pqr", "28 de mayo de 2024", 275.30, "Entregado"));
        pedidos.add(new Pedido("ss1256789stu", "30 de mayo de 2024", 420.15, "Pendiente"));
        pedidos.add(new Pedido("ss1267890vwx", "1 de junio de 2024", 120.40, "Enviado"));
        pedidos.add(new Pedido("ss1278901yzb", "3 de junio de 2024", 350.75, "Entregado"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ap = new AdaptadorPersona();

        recyclerView.setAdapter(ap);

    }


    public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.AdaptadorPersonaHolder> {


        @NonNull
        @Override
        public AdaptadorPersona.AdaptadorPersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorPersonaHolder(getLayoutInflater().inflate(R.layout.pedido_individual, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorPersona.AdaptadorPersonaHolder holder, int position) {
            holder.imprimir(position);
        }


        @Override
        public int getItemCount() {
            return pedidos.size();
        }


        class AdaptadorPersonaHolder extends RecyclerView.ViewHolder {

            TextView tv1, tv2, tv3, tv4;

            public AdaptadorPersonaHolder(@NonNull View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.tvNumeroOrden);
                tv2 = itemView.findViewById(R.id.tvFechaPedido);
                tv3 = itemView.findViewById(R.id.tvTotalPagado);
                tv4 = itemView.findViewById(R.id.tvEstadoEnvio);
            }

            public void imprimir(int position) {

                tv1.setText(pedidos.get(position).getNumeroOrden());
                tv2.setText(pedidos.get(position).getFechaPedido());
                tv3.setText(String.valueOf(pedidos.get(position).getTotalPagado()));
                tv4.setText(pedidos.get(position).getEstadoEnvio());


            }

        }

    }

}