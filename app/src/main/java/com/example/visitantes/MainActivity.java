package com.example.visitantes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.service.controls.actions.ControlAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.visitantes.Controladores.UsuarioControlador;
import com.example.visitantes.Modelos.Usuarios;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_ingreso, btn_registro;
    EditText et_usuario, et_password;
    Usuarios usuario;
    UsuarioControlador usuario_controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ingreso = findViewById(R.id.btn_ingresar);
        btn_registro = findViewById(R.id.btn_registrar);
        et_usuario = findViewById(R.id.usuario_login);
        et_password = findViewById(R.id.password_login);

        //Instanciación del controlador
        usuario_controlador = new UsuarioControlador(this);

        //Listeners de los botones
        btn_ingreso.setOnClickListener(this);
        btn_registro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(!ValidarCampos())return;
        switch (v.getId()){
            case R.id.btn_ingresar:
                Ingresar();
                break;
            case R.id.btn_registrar:
                Registrar();
                break;
        }
    }


    public void Ingresar(){
        usuario = new Usuarios(et_usuario.getText().toString(), et_password.getText().toString());
        usuario_controlador.Login(usuario);
    }

    public void Registrar(){
        usuario = new Usuarios(et_usuario.getText().toString(), et_password.getText().toString());
        usuario_controlador.AgregarUsuario(usuario);
    }

    public boolean ValidarCampos(){
        if(TextUtils.isEmpty(et_usuario.getText().toString()) || TextUtils.isEmpty(et_password.getText().toString())){
            Toast.makeText(this,"Los datos no pueden ser vacíos", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}