package com.example.visitantes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.visitantes.Controladores.InvitadosControlador;
import com.example.visitantes.Modelos.Visitante;

public class ActivityOpciones extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radio_group;
    RadioButton radioButton;
    EditText identificacion, nombre, apartamento;
    Button btn_agregar, btn_listar;
    InvitadosControlador controlador_visitante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        radio_group = findViewById(R.id.tipo_visita_agregar);
        identificacion = findViewById(R.id.identificacion_agregar);
        nombre = findViewById(R.id.nombre_agregar);
        apartamento = findViewById(R.id.apartamento_agregar);
        btn_agregar = findViewById(R.id.btn_agregar);
        btn_listar = findViewById(R.id.btn_listar);

        //listener botones
        btn_agregar.setOnClickListener(this);
        btn_listar.setOnClickListener(this);
        controlador_visitante = new InvitadosControlador(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerrar_sesion:
                EliminarPreferencias();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public String checkRadioButton(View view) {
        int radioId = radio_group.getCheckedRadioButtonId();
        if(radioId != R.id.tipo_visitante_amigo && radioId != R.id.tipo_visitante_familia && radioId != R.id.tipo_visitante_domiciliario){
            return null;
        }
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_agregar:
                if(ValidarCampos(v)){
                    if(AgregarVisitante(v)){
                        BorrarTexto();
                    }
                }
                break;
            case R.id.btn_listar:
                Intent intent = new Intent(getApplicationContext(),ListaVisitantes.class);
                startActivity(intent);
                break;
        }
    }

    public boolean ValidarCampos(View v){
        if(TextUtils.isEmpty(identificacion.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(apartamento.getText().toString()) || TextUtils.isEmpty(checkRadioButton(v))){
            Toast.makeText(getApplicationContext(),"Los campos no pueden ser vacíos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean AgregarVisitante(View v){
        Visitante visitante = new Visitante();
        visitante.setId(identificacion.getText().toString());
        visitante.setNombre(nombre.getText().toString());
        visitante.setApartamento(apartamento.getText().toString());
        visitante.setTipo_visitante(checkRadioButton(v));
        return controlador_visitante.Agregar(visitante);
    }

    public void EliminarPreferencias(){
        SharedPreferences preferencias = getSharedPreferences("loguedIn", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove("logueado");
        editor.commit();
    }

    public void BorrarTexto(){
        identificacion.setText("");
        nombre.setText("");
        apartamento.setText("");
    }
}