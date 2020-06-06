package com.example.examenm8recu.MVVM.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.examenm8recu.R;
import com.example.examenm8recu.MVVM.ViewModels.RegistroViewModel;

public class RegistroFragment extends Fragment {

    private RegistroViewModel registroViewModel;
    private TextView txtNombre, txtCurso, txtEdad;

    public RegistroFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroViewModel =
                ViewModelProviders.of(this).get(RegistroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro, container, false);

        txtNombre = root.findViewById(R.id.txt_name);
        txtCurso = root.findViewById(R.id.txt_curso);
        txtEdad = root.findViewById(R.id.txt_edad);

        Button btn_registro = root.findViewById(R.id.btn_registro);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarTextosVacios()) {
                    Toast.makeText( getActivity(), "Para registrar los campos no deben estar vacios",
                            Toast.LENGTH_SHORT).show();
                } else {
                    accionRegistrar();
                    Toast.makeText(getActivity(), "Registro añadido",Toast.LENGTH_SHORT).show();
                    clearFields();
                }
            }
        });

        return root;
    }

    private boolean comprobarTextosVacios(){
        String nombre = txtNombre.getText().toString();
        String curso = txtCurso.getText().toString();
        String edad = txtEdad.getText().toString();

        return !TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(curso) && TextUtils.isEmpty(edad);
    }

    private void accionRegistrar(){
        String nombre = txtNombre.getText().toString();
        String curso = txtCurso.getText().toString();
        String edad = txtEdad.getText().toString();
        registroViewModel.Registrar(nombre, curso, edad);
    }

    private void clearFields(){
        txtNombre.setText("");
        txtCurso.setText("");
        txtEdad.setText("");
    }
}