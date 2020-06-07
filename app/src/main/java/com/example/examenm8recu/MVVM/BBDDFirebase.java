package com.example.examenm8recu.MVVM;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.examenm8recu.MVVM.Models.Persona;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BBDDFirebase {

     private static BBDDFirebase bbddFirebase;

     private DatabaseReference fbBBDD;
     MutableLiveData<List<Persona>> lista_personas;

     private BBDDFirebase(){
         fbBBDD = FirebaseDatabase.getInstance().getReference("ListaFirebase");
         lista_personas = new MutableLiveData<>();
     }

     public static BBDDFirebase getBBDDFirebase(){
         if(bbddFirebase == null){
            bbddFirebase = new BBDDFirebase();
         }
         return bbddFirebase;
     }

     public void addRegistro(String nombre, String curso, String edad){
         String id = fbBBDD.push().getKey();
         Persona persona = new Persona(id, nombre, curso, edad);
         fbBBDD.child("Personas").child(id).setValue(persona);
     }

     public void getListPersonas(){
        HiloLeerListaPersonasFB thread = new HiloLeerListaPersonasFB();
        thread.execute("https://examenm8recu.firebaseio.com/ListaFirebase/Personas.json");
     }

     public LiveData<List<Persona>> getListaPersonasLiveData(){
         return lista_personas;
     }

     public class HiloLeerListaPersonasFB extends AsyncTask<String, Void, String> {

         @Override
         protected String doInBackground(String... strings) {
             HttpURLConnection connection;
             URL url;
             connection = null;
             String result;
             result = "";

             try {
                 url = new URL(strings[0]);
                 connection = (HttpURLConnection) url.openConnection();
                 InputStream inputStream = connection.getInputStream();

                 int data = inputStream.read();

                 while (data != -1) {
                     result += (char) data;
                     data = inputStream.read();
                 }

             } catch (Exception e) {
                 e.printStackTrace();
             }

             return result;
         }

         @Override
         protected void onPostExecute(String data) {
             super.onPostExecute(data);
             List<Persona> listaPersonas = new ArrayList<>();

             try {
                 JSONObject jsonObject = new JSONObject(data);
                 JSONArray jsonArrayKeys = jsonObject.names();
                 for(int i = 0; i < jsonObject.length(); i++){
                     String key = (String) jsonArrayKeys.get(i);
                     JSONObject jsonObject_item = jsonObject.getJSONObject(key);
                     Persona persona = new Persona(jsonObject_item);
                     listaPersonas.add(persona);
                 }
             } catch (JSONException jsonException) {
                jsonException.printStackTrace();
             }

             lista_personas.postValue(listaPersonas);
         }
     }
}
