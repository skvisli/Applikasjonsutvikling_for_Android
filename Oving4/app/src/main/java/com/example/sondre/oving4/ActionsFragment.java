package com.example.sondre.oving4;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ActionsFragment extends Fragment {
    private SharedViewModel mViewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actions, container, false);


        TextView android = view.findViewById(R.id.textView_android);
        TextView man = view.findViewById(R.id.textView_man);
        TextView bus = view.findViewById(R.id.textView_bus);
        Button prev = view.findViewById(R.id.button_prev);
        Button next = view.findViewById(R.id.button_next);

        android.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { mViewModel.setCurrentDrawable(0); }
        });
        man.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { mViewModel.setCurrentDrawable(1); }
        });
        bus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) { mViewModel.setCurrentDrawable(2); }
        });
        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int newDrawable = mViewModel.getCurrentDrawable().getValue() - 1;
                if (newDrawable >= 0) {
                    mViewModel.setCurrentDrawable(newDrawable);
                    mViewModel.setCurrentDrawable(newDrawable);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int newDrawable = mViewModel.getCurrentDrawable().getValue() + 1;
                if (newDrawable < 3) {
                    mViewModel.setCurrentDrawable(newDrawable);
                    mViewModel.setCurrentDrawable(newDrawable);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
