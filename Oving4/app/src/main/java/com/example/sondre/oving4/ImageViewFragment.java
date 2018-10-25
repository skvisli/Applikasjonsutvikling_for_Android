package com.example.sondre.oving4;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageViewFragment extends Fragment {

    private SharedViewModel mViewModel;
    ImageView mImageView;
    private int[] drawables = {
            R.drawable.ic_android_24dp,
            R.drawable.ic_accessibility_black_24dp,
            R.drawable.ic_airport_shuttle_black_24dp
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mViewModel.getCurrentDrawable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.i("Sondre", "hallo");
                setImage(drawables[integer]);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imageview, container, false);
        mImageView = view.findViewById(R.id.imageview);
        // Inflate the layout for this fragment
        return view;
    }

    public void setImage(int drawable) {
    mImageView.setImageResource(drawable);
        /*ImageViewFragment fragment = (ImageViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.setImageView(drawable);*/
    }
}