package com.example.oving3alt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView recyclerView_persons;
    private ExampleAdaper adapter_persons;
    private RecyclerView.LayoutManager layoutManager_persons;

    private Button buttonAdd;
    private EditText editTextName;
    private EditText editTextBirthday;
    private ImageView imageViewCheck;
    private int editPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        setButtons();
    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem("Sondre Kvisli", "06.01.93"));
        mExampleList.add(new ExampleItem("Martin Stigen", "08.10.94"));
        mExampleList.add(new ExampleItem("Sondre Kvisli", "06.01.93"));
    }

    private void buildRecyclerView() {
        recyclerView_persons = findViewById(R.id.recycler_view);
        recyclerView_persons.setHasFixedSize(true);
        adapter_persons = new ExampleAdaper(mExampleList);
        layoutManager_persons = new LinearLayoutManager(this);

        recyclerView_persons.setLayoutManager(layoutManager_persons);
        recyclerView_persons.setAdapter(adapter_persons);

        adapter_persons.setOnItemClickListener(new ExampleAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                changeItem(pos, "Clicked", "Clicked");
            }

            @Override
            public void onDeleteClick(int pos) {
                removeItem(pos);

            }

            @Override
            public void onEditClick(int pos) {
                buttonAdd.setVisibility(View.INVISIBLE);
                imageViewCheck.setVisibility(View.VISIBLE);
                editPos = pos;
            }
        });
    }

    private void setButtons() {
        buttonAdd = findViewById(R.id.button_add);
        editTextName = findViewById(R.id.editText_name);
        editTextBirthday = findViewById(R.id.editText_birthday);
        imageViewCheck = findViewById(R.id.imageView_check);
        imageViewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String birthday = editTextBirthday.getText().toString();
                changeItem(editPos, name, birthday);
                imageViewCheck.setVisibility(View.INVISIBLE);
                buttonAdd.setVisibility(View.VISIBLE);
            }
        });
    }

    public void add(View view) {
        int pos = mExampleList.size();
        String name = editTextName.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        insertItem(pos, name, birthday );

    }

    private void insertItem(int pos, String name, String birthday) {
        mExampleList.add(pos, new ExampleItem(name, birthday));
        adapter_persons.notifyItemInserted(pos);
    }

    private void removeItem(int pos) {
        mExampleList.remove(pos);
        adapter_persons.notifyItemRemoved(pos);
    }

    private void changeItem(int pos, String name, String birthday) {
        mExampleList.get(pos).changeName(name);
        mExampleList.get(pos).changeBirthday(birthday);
        adapter_persons.notifyItemChanged(pos);
    }


}
