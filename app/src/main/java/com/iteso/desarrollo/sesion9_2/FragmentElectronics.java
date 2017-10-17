package com.iteso.desarrollo.sesion9_2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;

import adapters.AdapterProduct;
import beans.ItemProduct;
import database.DataBaseHandler;
import database.ItemProductControl;

public class FragmentElectronics extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemProduct> myDataSet;

    public FragmentElectronics() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electronics, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_electronics_recycler_view);

        // Es para mejorar el desempeño cuando sabes que los cambios en el contenido
        // no modifican el tamaño del Layout del RecyclerView
        recyclerView.setHasFixedSize(true);

        // Obtengo todos los productos de la categoría Technology
        ItemProductControl itemProductControl = new ItemProductControl();

        myDataSet = itemProductControl.getProductsWhere(
                "CA." + DataBaseHandler.KEY_CATEGORY_NAME + " = 'ELECTRONICS'",
                DataBaseHandler.KEY_PRODUCT_ID + " ASC",
                DataBaseHandler.getInstance(getActivity()));

        // Establece el layout manager para el recycler view. Usa un linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        // Carga los datos al adaptador y se establece en el recycler view
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);

        itemProductControl = null;

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<ItemProduct> iterator = myDataSet.iterator();

        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                myDataSet.set(position, itemProduct);
                break;
            }
            position ++;
        }

        mAdapter.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(ItemProduct itemProduct){
        myDataSet.add(itemProduct);
        mAdapter.notifyDataSetChanged();
    }

}
