package com.example.visitantes.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.visitantes.DefBD;
import com.example.visitantes.Modelos.Usuarios;
import com.example.visitantes.SQLhelper;

public class UsuarioControlador {
    private SQLhelper bd;
    private Context c;


    public UsuarioControlador(Context c) {
        this.bd = new SQLhelper(c,2);
        this.c = c;
    }


    public void AgregarUsuario(Usuarios u){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.usuario_usuario, u.getUsuario());
            valores.put(DefBD.usuario_password, u.getPassword());
            long id = sql.insert(DefBD.tablaUsuarios, null, valores);
            Toast.makeText(c, "Usuario registrado", Toast.LENGTH_LONG).show();
            bd.close();
        }catch (Exception e){
            Toast.makeText(c, "Error registrar usuario, " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean Login(Usuarios u){
        try{
            String args[] = new String[]{u.getUsuario(),u.getPassword()};
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.query(DefBD.tablaUsuarios, null, DefBD.usuario_usuario+" = ? AND "+DefBD.usuario_password+ " = ?", args, null, null, null);
            if (c.getCount() > 0) {
                bd.close();
                return true;
            } else {
                bd.close();
                return false;
            }
        }catch (Exception e){
            Toast.makeText(c, "Error al loguear usuario" , Toast.LENGTH_LONG).show();
            Log.e("Error",e.getMessage());
            return false;
        }
    }

    public boolean buscarUsuario(Usuarios u) {
        String args[] = new String[]{u.getUsuario()};
        String col[] = new String[]{DefBD.usuario_usuario};
        SQLiteDatabase sql = bd.getReadableDatabase();
        Cursor c = sql.query(DefBD.tablaUsuarios, null, "usuario=?", args, null, null, null);
        if (c.getCount() > 0) {
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

}
