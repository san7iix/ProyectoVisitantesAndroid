package com.example.visitantes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.visitantes.Controladores.InvitadosControlador;
import com.example.visitantes.Modelos.Visitante;

public class EditarVisitante extends AppCompatActivity {
    EditText identificacion_nueva, nombre, apartamento;
    Button btn_editar;
    InvitadosControlador controlador_visitante;
    RadioGroup radio_group;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_visitante);
        final String identificacion = getIntent().getStringExtra("identificacion");
        identificacion_nueva = findViewById(R.id.identificaion_editar);
        nombre = findViewById(R.id.nombre_editar);
        radio_group = findViewById(R.id.tipo_visita_editar);
        apartamento = findViewById(R.id.apartamento_editar);
        btn_editar = findViewById(R.id.btn_editar);
        controlador_visitante = new InvitadosControlador(getApplicationContext());


        identificacion_nueva.setText(identificacion);

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ValidarCampos(v)){
                    Visitante visitante = new Visitante();
                    visitante.setId(identificacion_nueva.getText().toString());
                    visitante.setNombre(nombre.getText().toString());
                    visitante.setApartamento(apartamento.getText().toString());
                    visitante.setTipo_visitante(checkRadioButton(v));
                    controlador_visitante.Actualizar(visitante,identificacion);
                    finish();
                }
            }
        });
    }

    public boolean ValidarCampos(View v){
        if(TextUtils.isEmpty(identificacion_nueva.getText().toString()) || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(apartamento.getText().toString()) || TextUtils.isEmpty(checkRadioButton(v))){
            Toast.makeText(getApplicationContext(),"Los campos no pueden ser vacíos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String checkRadioButton(View view) {
        int radioId = radio_group.getCheckedRadioButtonId();
        if(radioId != R.id.editar_tipo_amigo && radioId != R.id.editar_tipo_familia && radioId != R.id.editar_tipo_domiciliario){
            return null;
        }
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }



}