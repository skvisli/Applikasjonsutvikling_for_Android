package com.example.oving3alt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_persons;
    private RecyclerView.Adapter adapter_persons;
    private RecyclerView.LayoutManager layoutManager_persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));
        exampleList.add(new ExampleItem("Sondre", "Kvisli"));
        exampleList.add(new ExampleItem("Martin", "Stigen"));

        recyclerView_persons = findViewById(R.id.recycler_view);
        recyclerView_persons.setHasFixedSize(true);
        adapter_persons = new ExampleAdaper(exampleList);
        layoutManager_persons = new LinearLayoutManager(this);

        recyclerView_persons.setLayoutManager(layoutManager_persons);
        recyclerView_persons.setAdapter(adapter_persons);
    }
}
