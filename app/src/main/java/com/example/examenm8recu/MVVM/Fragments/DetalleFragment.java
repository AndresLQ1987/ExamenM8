package com.example.examenm8recu.MVVM.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenm8recu.MVVM.Models.Persona;
import com.example.examenm8recu.MVVM.ViewModels.ListFirebaseViewModel;
import com.example.examenm8recu.R;

public class DetalleFragment extends Fragment {

    private ListFirebaseViewModel listFirebaseViewModel;
    private TextView detalle_nombre;
    private TextView detalle_curso;
    private TextView detalle_edad;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listFirebaseViewModel = new ViewModelProvider(requireActivity()).get(ListFirebaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detalle_lista, container, false);

        detalle_nombre = root.findViewById(R.id.lbl_detalle_valorname);
        detalle_curso = root.findViewById(R.id.lbl_detalle_valorcurso);
        detalle_edad = root.findViewById(R.id.lbl_detalle_valoredad);

        cargar_datos(listFirebaseViewModel.getPersonaAtPosition(getArguments().getInt("POSICION")));
        return root;
    }

    public void cargar_datos(Persona persona){
        detalle_nombre.setText(persona.getNombre());
        detalle_curso.setText(persona.getCurso());
        detalle_edad.setText(persona.getEdad());
    }
}