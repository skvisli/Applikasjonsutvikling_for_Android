package com.example.oving3alt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdaper extends RecyclerView.Adapter<ExampleAdaper.ExampleViewHolder> {


    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mLIstener;


    public interface OnItemClickListener {
        void onItemClick(int pos);
        void onDeleteClick(int pos);
        void onEditClick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mLIstener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public ImageView imageViewDelete;
        public ImageView imageViewEdit;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.line1);
            textView2 = itemView.findViewById(R.id.line2);
            imageViewDelete = itemView.findViewById(R.id.imageView_delete);
            imageViewEdit = itemView.findViewById(R.id.imageView_edit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });

            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(pos);
                        }
                    }
                }
            });

            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onEditClick(pos);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdaper(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mLIstener);
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
