package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button city_Add;
    Button city_Delete;

    LinearLayout hidden_Row;

    EditText city_Name;

    Button confirm;

    int selected_Position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "New York"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        hidden_Row = findViewById(R.id.hidden_Row);
        city_Name = findViewById(R.id.city_Name);
        confirm = findViewById(R.id.confirm);

        city_Add = findViewById(R.id.city_Add);
        city_Delete = findViewById(R.id.city_Delete);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_Position = position;
            }
        });

        city_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden_Row.setVisibility(View.VISIBLE);

            }
        });

        city_Delete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected_Position != -1) {
                    dataList.remove(selected_Position);
                    cityAdapter.notifyDataSetChanged();
                    selected_Position = -1;
                }
            }
        }));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_city_Name = city_Name.getText().toString();
                if (new_city_Name.isEmpty()) {
                    hidden_Row.setVisibility(View.GONE);
                    city_Name.setText("");
                    return;
                }
                dataList.add(new_city_Name);
                cityAdapter.notifyDataSetChanged();
                hidden_Row.setVisibility(View.GONE);
                city_Name.setText("");

            }
        });





    }
}