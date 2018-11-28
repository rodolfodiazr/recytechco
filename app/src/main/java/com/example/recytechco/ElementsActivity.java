package com.example.recytechco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recytechco.adapters.ElementsAdapter;
import com.example.recytechco.models.Element;
import com.example.recytechco.models.History;
import com.example.recytechco.util.Config;
import com.example.recytechco.util.DatabaseQuery;

public class ElementsActivity extends AppCompatActivity {

    private ElementsAdapter mElementsAdapter;

    private DatabaseQuery mDatabaseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);

        mDatabaseQuery = new DatabaseQuery(this);

        mElementsAdapter = new ElementsAdapter(this, Config.getElements(), new ElementsAdapter.RecycledElementListener() {
            @Override
            public void onRecycledElement(Element element) {
                if (element.getAmountInString().trim().equals("")) {
                    return;
                }
                addElementToHistory(element);
            }
        });
    }

    private void addElementToHistory(Element element) {
        mDatabaseQuery.add(new History(element.getId(), element.getName(),
                        Integer.parseInt(element.getAmountInString()), "",
                        Config.getCurrentDateInString(), Config.getUserId(this)),
                new DatabaseQuery.DatabaseQueryListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ElementsActivity.this, "Success!!",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
