package com.example.examenm8recu.MVVM.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.examenm8recu.MVVM.BBDDFirebase;
import com.example.examenm8recu.MVVM.Models.Persona;

import java.util.List;

public class ListFirebaseViewModel extends ViewModel {

    private BBDDFirebase bbddFirebase;
    private List<Persona> listaPersonas;
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
                listaPersonas = personas;
                lista_personas.postValue(listaPersonas);
            }
        });
    }

    public LiveData<List<Persona>> getListaPersonas(){
        return lista_personas;
    }

    public Persona getPersonaAtPosition(int position){
        return listaPersonas.get(position);
    }
}