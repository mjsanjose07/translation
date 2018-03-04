package com.example.maryjoy.northerdialecttranslator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class phraseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phrase, container, false);

        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        Bundle bundle = getArguments();
        
        String words_input = bundle.getString("Words_Input");
        String words_translate = bundle.getString("Words_Translate");
        String words_dialect = bundle.getString("Words_dialect");
        data.add(words_input);
        data1.add(words_translate);
        data2.add(words_dialect);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data2);

        ListView listview = (ListView) view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        ListView listview1 = (ListView) view.findViewById(R.id.listview1);
        listview1.setAdapter(adapter1);

        ListView listview2 = (ListView) view.findViewById(R.id.listview2);
        listview2.setAdapter(adapter2);


        return view;
    }

}
