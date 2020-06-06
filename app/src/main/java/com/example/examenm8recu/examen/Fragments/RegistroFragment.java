package com.example.examenm8recu.examen.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.examenm8recu.R;
import com.example.examenm8recu.examen.ViewModels.RegistroViewModel;

public class RegistroFragment extends Fragment {

    private RegistroViewModel registroViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroViewModel =
                ViewModelProviders.of(this).get(RegistroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro, container, false);

        return root;
    }
}