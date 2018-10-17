package com.example.oving3alt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdaper extends RecyclerView.Adapter<ExampleAdaper.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;


        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.line1);
            textView2 = itemView.findViewById(R.id.line2);
        }
    }

    public ExampleAdaper(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.textView1.setText(currentItem.getTextView_line1());
        holder.textView2.setText(currentItem.getTextView_line2());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
