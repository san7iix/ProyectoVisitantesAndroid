package com.example.visitantes.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.visitantes.DefBD;
import com.example.visitantes.Modelos.Usuarios;
import com.example.visitantes.Modelos.Visitante;
import com.example.visitantes.SQLhelper;

public class InvitadosControlador {

    private SQLhelper bd;
    private Context c;

    public InvitadosControlador(Context c) {
        this.c = c;
        this.bd = new SQLhelper(c,1);
    }

    public void AgregarUsuario(Visitante v){
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.visitantes_identificacion, v.getId());
            valores.put(DefBD.visitantes_nombre, v.getNombre());
            valores.put(DefBD.visitantes_apartamento, v.getApartamento());
            valores.put(DefBD.visitantes_tipo_visitante, v.getTipo_visitante());

            long id = sql.insert(DefBD.tablaVisitantes, null, valores);
            Toast.makeText(c, "Visitante registrado", Toast.LENGTH_LONG).show();
            bd.close();
        }catch (Exception e){
            Toast.makeText(c, "Error registrar visitante, " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public Cursor allVisitantes() {
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            Cursor c = sql.rawQuery("select identificacion as _id , nombre, apartamento, fecha from visitantes", null);
            return c;
        } catch (Exception ex) {
            Toast.makeText(c, "Error consulta visitantes " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor ConsultarPorApartamento(String apartamento){
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = new String[] {apartamento};
            Cursor c = sql.rawQuery("select identificacion as _id , nombre, apartamento, fecha from visitantes WHERE apartamento = ?", args);
            return c;
        } catch (Exception ex) {
            Toast.makeText(c, "Error consulta visitantes " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public Cursor ConsultarVisitante(String identificacion) {
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = new String[] {identificacion};
            Cursor c = sql.rawQuery("SELECT * FROM "+ DefBD.tablaVisitantes+" WHERE identificaion = ?", args );
            return c;
        } catch (Exception ex) {
            Toast.makeText(c, "Error consulta visitantes " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void Eliminar(String identificacion){
        try {
            SQLiteDatabase sql = bd.getReadableDatabase();
            String[] args = new String[]{identificacion};
            sql.delete(DefBD.tablaVisitantes, "identificacion=?", args);
            Toast.makeText(c, "Visitante eliminado", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(c, "Error eliminación del visitante " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Actualizar(Visitante v, String identificacion_antigua) {
        try {
            SQLiteDatabase sql = bd.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.visitantes_identificacion, v.getId());
            valores.put(DefBD.visitantes_nombre, v.getNombre());
            valores.put(DefBD.visitantes_apartamento, v.getApartamento());
            valores.put(DefBD.visitantes_tipo_visitante, v.getTipo_visitante());
            String[] args = new String[]{identificacion_antigua};
            sql.update(DefBD.tablaVisitantes, valores, "identificacion=?", args);
            Toast.makeText(c, "Visitante Actualizado", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(c, "Error al actualizar visitante " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
