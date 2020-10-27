package com.example.visitantes.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.visitantes.R;

import org.w3c.dom.Text;

public class VisitanteAdaptador extends CursorAdapter {

    public VisitanteAdaptador(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.visitanterow,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombre = view.findViewById(R.id.row_nombre);
        TextView apartamento = view.findViewById(R.id.row_apartamento);
        TextView fecha = view.findViewById(R.id.row_fecha);
        String nombre_text = cursor.getString(1);
        String apartamento_text = cursor.getString(2);
        String fecha_text = cursor.getString(3);

        nombre.setText(nombre_text);
        apartamento.setText("Apartamento: "+apartamento_text);
        fecha.setText(fecha_text);
    }
}
