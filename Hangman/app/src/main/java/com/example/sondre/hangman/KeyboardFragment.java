package com.example.sondre.hangman;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KeyboardFragment.OnFragmentKeyPressListener} interface
 * to handle interaction events.
 * Use the {@link KeyboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyboardFragment extends Fragment {

    private OnFragmentKeyPressListener mListener;
    private SharedViewModel sharedViewModel;
    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.button_q,
            R.id.button_w,
            R.id.button_e,
            R.id.button_r,
            R.id.button_t,
            R.id.button_y,
            R.id.button_u,
            R.id.button_i,
            R.id.button_o,
            R.id.button_p,
            R.id.button_å,

            R.id.button_a,
            R.id.button_s,
            R.id.button_d,
            R.id.button_f,
            R.id.button_g,
            R.id.button_h,
            R.id.button_j,
            R.id.button_k,
            R.id.button_l,
            R.id.button_ø,
            R.id.button_æ,

            R.id.button_z,
            R.id.button_x,
            R.id.button_c,
            R.id.button_v,
            R.id.button_b,
            R.id.button_n,
            R.id.button_m,
    };

    public KeyboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment KeyboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeyboardFragment newInstance() {
        KeyboardFragment fragment = new KeyboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keyboard, container, false);
        buttons = new ArrayList<>(BUTTON_IDS.length);
        for(int id : BUTTON_IDS) {
            final Button button = view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mListener.onKeyPress(button.getText().charAt(0));
                    button.setEnabled(false);
                    Log.i("Sondre", "clicked");
                }
            });
            buttons.add(button);
        }

        return view;
    }

    public interface OnFragmentKeyPressListener {
        void onKeyPress(char ch);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(char ch) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(ch);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentKeyPressListener) {
            mListener = (OnFragmentKeyPressListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public void clearKeysPressed() {
        for (Button button : buttons) {
            button.setEnabled(true);
        }
    }

    public void disableAllKeys() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }
}

