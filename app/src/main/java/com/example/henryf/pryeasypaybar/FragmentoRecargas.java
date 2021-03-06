package com.example.henryf.pryeasypaybar;

/**
 * Created by HenryF on 23/05/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
       * Fragmento para la pestaña "DIRECCIONES" De la sección "Mi Cuenta"
        */
public class FragmentoRecargas extends Fragment {

    private LinearLayoutManager linearLayout;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public static void setLista_result(ArrayList<Recargas> lista_result) {
        FragmentoRecargas.lista_result = lista_result;
    }

    public static ArrayList<Recargas> lista_result = new ArrayList<Recargas>();

    public FragmentoRecargas() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_recarga, container, false);

        getListRecargas();
        ejem(lista_result);

       /* RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.reciclador);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        AdaptadorRecargas adaptador = new AdaptadorRecargas();
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
        */

        return view;
    }



    public void getListRecargas(){
        final ArrayList<Recargas> lista_recargas = new ArrayList<Recargas>();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        //Consulta todos los proveedores
        mFirebaseDatabase.child("proveedor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot proveedor : dataSnapshot.getChildren()) {
                    try {
                        System.out.println("pruebaProveedor: "+proveedor.child("nombre").getValue().toString());
                        lista_recargas.add(new Recargas(proveedor.child("nombre").getValue().toString().toString(),""));
                    }catch (Exception e){
                        System.out.println("Error: "+e.getMessage());
                    }
                }

                setLista_result(lista_recargas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void ejem(ArrayList<Recargas> recarga){
        System.out.println("Nombre p"+recarga.size());
    }


}