package com.nikita.amphibian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class FragmentShow extends Fragment {
    DatabaseHelper myDB;
    private get_key get_key;
    private int id;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof get_key)) {
            throw new ClassCastException("Activity must implement fragment's ICallbacks.");
        }
        get_key = (get_key) context;
        id = get_key.getId_for_db();
    }
    @SuppressLint("Range")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_show, container, false);
        TextView text = v.findViewById(R.id.info_placeholder);
        myDB = DatabaseHelper.instanceOfDatabase(getContext());
        switch (id){
            case Color.RED:
                text.setText(myDB.getFinalInfo(id).getInfo());
            case Color.BLUE:
                text.setText(myDB.getFinalInfo(id).getInfo());
            case Color.YELLOW:
                text.setText(myDB.getFinalInfo(id).getInfo());
            case Color.GREEN:
                text.setText(myDB.getFinalInfo(id).getInfo());
    }
        return v ;
    }

}