package com.example.examenm8recu.MVVM.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.examenm8recu.MVVM.BBDDFirebase;
import com.example.examenm8recu.MVVM.Models.Persona;

import java.util.ArrayList;
import java.util.List;

public class ListFirebaseViewModel extends ViewModel {

    private BBDDFirebase bbddFirebase;
    private MutableLiveData<List<Persona>> lista_personas;

    public ListFirebaseViewModel() {
        bbddFirebase = BBDDFirebase.getBBDDFirebase();
        lista_personas = new MutableLiveData<>();
    }

    public void getPersonas() {
        bbddFirebase.getListPersonas();
        bbddFirebase.getListaPersonasLiveData().observeForever(new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                lista_personas.postValue(personas);
            }
        });
    }

    public LiveData<List<Persona>> getListaPersonas(){
        return lista_personas;
    }

}