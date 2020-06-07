package com.example.examenm8recu.MVVM.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
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
        listFirebaseViewModel = new ViewModelProvider(requireActivity()).get(ListFirebaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listafirebase, container, false);

        rv_persona = root.findViewById(R.id.rv_listfb);
        rv_persona.setLayoutManager(new LinearLayoutManager(getActivity()));
        listFirebaseViewModel.getPersonas();
        listFirebaseViewModel.getListaPersonas().observe(getViewLifecycleOwner(), new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                listaPersonas = personas;
                adapterPersona.notifyDataSetChanged();
            }
        });
        adapterPersona = new RecyclerAdapterPersona(listaPersonas);
        rv_persona.setAdapter(adapterPersona);

        return root;
    }

    public class PersonaHolder extends RecyclerView.ViewHolder{

        TextView holder_name;
        TextView holder_curso;

        public PersonaHolder(@NonNull View itemView) {
            super(itemView);

            holder_name = itemView.findViewById(R.id.txt_holderfb_name);
            holder_curso = itemView.findViewById(R.id.txt_holderfb_curso);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("POSICION", getAdapterPosition());
                    Navigation.findNavController(v).navigate(R.id.detalleFragment, bundle);
                }
            });
        }
    }

    public class RecyclerAdapterPersona extends RecyclerView.Adapter<PersonaHolder> {

        private List<Persona> personaList;

        public RecyclerAdapterPersona(List<Persona> personaList) { // Creo un constructor
            this.personaList = personaList;
        }

        @NonNull
        @Override
        public PersonaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonaHolder(getLayoutInflater().inflate(R.layout.personaholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PersonaHolder holder, int position) {

            Persona persona = listaPersonas.get(position);

            holder.holder_name.setText(persona.getNombre());
            holder.holder_curso.setText(persona.getCurso());

        }

        @Override
        public int getItemCount() {
            return listaPersonas.size();
        }
    }
}