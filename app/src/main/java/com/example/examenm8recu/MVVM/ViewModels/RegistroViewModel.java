package com.example.examenm8recu.MVVM.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.examenm8recu.MVVM.BBDDFirebase;

public class RegistroViewModel extends ViewModel {

    private BBDDFirebase bbdd;

    public RegistroViewModel() {
        bbdd = BBDDFirebase.getBBDDFirebase();
    }

    public void Registrar(String nombre, String curso, String edad){
        bbdd.addRegistro(nombre, curso, edad);
    }
}