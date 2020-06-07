package com.example.examenm8recu.MVVM.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenm8recu.MVVM.Models.Persona;
import com.example.examenm8recu.R;
import com.example.examenm8recu.MVVM.ViewModels.ListFirebaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFirebaseFragment extends Fragment {

    private ListFirebaseViewModel listFirebaseViewModel;
    private RecyclerView rv_persona;
    private RecyclerAdapterPersona adapterPersona;
    private List<Persona> listaPersonas = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listFirebaseViewModel =
                ViewModelProviders.of(this).get(ListFirebaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listafirebase, container, false);

        rv_persona = root.findViewById(R.id.rv_listfb);

        rv_persona.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterPersona = new RecyclerAdapterPersona();
        rv_persona.setAdapter(adapterPersona);
        listFirebaseViewModel.getPersonas();
        listFirebaseViewModel.getListaPersonas().observe(getViewLifecycleOwner(), new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                listaPersonas = personas;
                adapterPersona.notifyDataSetChanged();
            }
        });

        return root;
    }

    public class RecyclerAdapterPersona extends RecyclerView.Adapter<RecyclerAdapterPersona.PersonaHolder> {

        @NonNull
        @Override
        public RecyclerAdapterPersona.PersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new PersonaHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapterPersona.PersonaHolder holder, int position) {

            Persona persona = listaPersonas.get(position);

            holder.holder_name.setText(persona.getNombre());
            holder.holder_curso.setText(persona.getCurso());
        }

        @Override
        public int getItemCount() {
            return listaPersonas.size();
        }

        /**
         * Inner Class of Recycler View to define Holder work logs
         */
        public class PersonaHolder extends RecyclerView.ViewHolder {

            TextView holder_name;
            TextView holder_curso;

            public PersonaHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.personaholder, parent,false));

                holder_name = itemView.findViewById(R.id.txt_holderfb_name);
                holder_curso = itemView.findViewById(R.id.txt_holderfb_curso);
            }
        }
    }
}