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
    private ArrayList<Person> mPersons;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonAddItem;
    private EditText editTextName;
    private EditText editTextBirthday;
    private ImageView imageViewCheck;
    private int editPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();
        createExampleList();
        buildRecyclerView();
    }

    private void setWidgets() {
        buttonAddItem = findViewById(R.id.button_addItem);
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
                buttonAddItem.setVisibility(View.VISIBLE);
                emptyTextFields();
            }
        });
    }

    private void createExampleList() {
        mPersons = new ArrayList<>();
        mPersons.add(new Person("Sondre Kvisli", "06.01.93"));
        mPersons.add(new Person("Martin Stigen", "08.10.94"));
        mPersons.add(new Person("Sondre Kvisli", "06.01.93"));
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ExampleAdapter(mPersons);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
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
                buttonAddItem.setVisibility(View.INVISIBLE);
                imageViewCheck.setVisibility(View.VISIBLE);
                // Stores position of edited item in global variable for easy access
                editPos = pos;
                String name = mPersons.get(pos).getName();
                String birthday = mPersons.get(pos).getBirthday().replace("Bursdag: ", "");
                editTextName.setText(name);
                editTextBirthday.setText(birthday);
            }
        });
    }

    public void addItem(View view) {
        int pos = mAdapter.getItemCount();
        String name = editTextName.getText().toString();
        String birthday = editTextBirthday.getText().toString();
        mPersons.add(pos, new Person(name, birthday));
        mAdapter.notifyItemInserted(pos);
        emptyTextFields();
    }

    private void removeItem(int pos) {
        mPersons.remove(pos);
        mAdapter.notifyItemRemoved(pos);
        exitEditMode();
    }

    private void changeItem(int pos, String name, String birthday) {
        mPersons.get(pos).changeName(name);
        mPersons.get(pos).changeBirthday(birthday);
        mAdapter.notifyItemChanged(pos);
    }

    private void exitEditMode() {
        buttonAddItem.setVisibility(View.VISIBLE);
        imageViewCheck.setVisibility(View.INVISIBLE);
    }

    private void emptyTextFields() {
        editTextName.setText("");
        editTextBirthday.setText("");
    }
}
