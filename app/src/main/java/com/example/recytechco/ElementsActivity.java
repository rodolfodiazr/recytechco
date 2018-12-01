package com.example.recytechco;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recytechco.adapters.ElementsAdapter;
import com.example.recytechco.models.Element;
import com.example.recytechco.models.History;
import com.example.recytechco.util.Config;
import com.example.recytechco.util.DatabaseQuery;

public class ElementsActivity extends AppCompatActivity {

    private RecyclerView mElementsRecyclerView;
    private ElementsAdapter mElementsAdapter;
    private Button mGoButton, mMapButton;

    private DatabaseQuery mDatabaseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);

        getSupportActionBar().setTitle(R.string.lbl_elements);

        mElementsRecyclerView = findViewById(R.id.elementsRecyclerView);
        mElementsAdapter = new ElementsAdapter(this, Config.getElements(), new ElementsAdapter.RecycledElementListener() {
            @Override
            public void onRecycledElement(int position, Element element) {
                if (element.getAmountInString().trim().equals("")) {
                    return;
                }
                addElementToHistory(position, element);
            }
        });
        mElementsRecyclerView.setAdapter(mElementsAdapter);
        mElementsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mElementsRecyclerView.setHasFixedSize(true);

        mGoButton = findViewById(R.id.goButton);
        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ElementsActivity.this, MainActivity.class));
            }
        });
        mMapButton = findViewById(R.id.mapButton);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:10.995038,-74.822686");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        mDatabaseQuery = new DatabaseQuery(this);

    }

    private void addElementToHistory(final int pos, Element element) {
        mDatabaseQuery.add(new History(element.getId(), element.getName(),
                        Integer.parseInt(element.getAmountInString()), "",
                        Config.getCurrentDateInString(), Config.getUserId(this)),
                new DatabaseQuery.DatabaseQueryListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ElementsActivity.this, R.string.message_success,
                                Toast.LENGTH_SHORT).show();
                        mElementsAdapter.notifyItemChanged(pos);
                    }

                    @Override
                    public void onSuccess(int id) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ElementsActivity.this, MainActivity.class));
    }
}