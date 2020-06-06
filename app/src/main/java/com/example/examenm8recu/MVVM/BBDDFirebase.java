package com.example.examenm8recu.MVVM;

import com.example.examenm8recu.MVVM.Models.Persona;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BBDDFirebase {

     private static BBDDFirebase bbddFirebase;

     private DatabaseReference fbBBDD;

     private BBDDFirebase(){
         fbBBDD = FirebaseDatabase.getInstance().getReference("ListaFirebase");
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
}
