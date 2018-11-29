package com.example.recytechco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recytechco.adapters.HistoryAdapter;
import com.example.recytechco.models.Element;
import com.example.recytechco.models.History;
import com.example.recytechco.util.Config;
import com.example.recytechco.util.DatabaseQuery;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mFullNameTextView, mPointsTextView;
    private Button mRecycleButton, mLogoutButton;
    private RecyclerView mHistoryRecyclerView;

    private HistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPointsTextView = findViewById(R.id.pointsValueTextView);
        mPointsTextView.setText(getPoints() + "");

        mFullNameTextView = findViewById(R.id.fullNameTextView);
        mFullNameTextView.setText(Config.getFullName(this));

        mRecycleButton = findViewById(R.id.recycleButton);
        mRecycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, ElementsActivity.class));
            }
        });

        mLogoutButton = findViewById(R.id.logoutButton);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config.setFullName(MainActivity.this, "");
                Config.setUserId(MainActivity.this, -1);
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mHistoryAdapter = new HistoryAdapter(new DatabaseQuery(this).getHistories());
        mHistoryRecyclerView = findViewById(R.id.historyRecyclerView);
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setHasFixedSize(true);

    }

    private int getPoints() {
        ArrayList<History> histories = new DatabaseQuery(this).getHistories();
        int points = 0;
        for (History history : histories) {
            points += (history.getAmount() * getPointsBy(history.getElementId()));
        }
        return points;
    }

    private int getPointsBy(int elementId) {
        for (Element element : Config.getElements()) {
            if (elementId == element.getId()) {
                return element.getPoints();
            }
        }
        return 0;
    }


}
