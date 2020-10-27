package com.example.visitantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitantes.Adaptadores.VisitanteAdaptador;
import com.example.visitantes.Controladores.InvitadosControlador;

public class ListaVisitantes extends AppCompatActivity implements View.OnClickListener {

    ListView listado;
    InvitadosControlador invitados_controlador;
    EditText apartamento_filtro;
    Button btn_filtrar, btn_limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visitantes);
        invitados_controlador = new InvitadosControlador(getApplicationContext());
        listado = findViewById(R.id.lista_visitantes);
        apartamento_filtro = findViewById(R.id.apartamento_filtrar);
        btn_filtrar = findViewById(R.id.btn_filtrar);
        btn_limpiar = findViewById(R.id.btn_borrar_filtro);
        Cursor c = invitados_controlador.allVisitantes();
        VisitanteAdaptador adaptador = new VisitanteAdaptador(getApplicationContext(),c,false);
        listado.setAdapter(adaptador);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EditarVisitante.class);
                intent.putExtra("identificacion", String.valueOf(id));
                startActivity(intent);
            }
        });

        listado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                invitados_controlador.Eliminar(String.valueOf(id));
                ActualizarLista();
                return false;
            }
        });

        btn_filtrar.setOnClickListener(this);
        btn_limpiar.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActualizarLista();
    }

    public void ActualizarLista(){
        Cursor c = invitados_controlador.allVisitantes();
        VisitanteAdaptador adaptador = new VisitanteAdaptador(getApplicationContext(),c,false);
        listado.setAdapter(adaptador);
    }

    public void Filtrar (){
        if(!TextUtils.isEmpty(apartamento_filtro.getText().toString())){
            Cursor c = invitados_controlador.ConsultarPorApartamento(apartamento_filtro.getText().toString().trim());
            VisitanteAdaptador adaptador = new VisitanteAdaptador(getApplicationContext(),c,false);
            listado.setAdapter(adaptador);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_filtrar:
                Filtrar();
                break;
            case R.id.btn_borrar_filtro:
                apartamento_filtro.setText("");
                ActualizarLista();
                break;
        }
    }
}