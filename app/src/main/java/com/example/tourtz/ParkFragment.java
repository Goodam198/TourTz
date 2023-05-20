package com.example.tourtz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ParkFragment extends Fragment {

    //Button button;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_park, container, false);
       //button = view.findViewById(R.id.to_mikumi_page);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MikumiActivity.class);
                startActivity(intent);

            }
        });*/
        return view;
    }
}