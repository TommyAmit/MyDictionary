package com.example.mydictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText dictionaryKey, dictionaryValue;
    Button btnAddEntry, btnClearEntries, btnDeleteKey;
    LinearLayout entryButtons;
    HashMap<String, String> myDictionary = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionaryKey = findViewById(R.id.etDictionaryKey);
        dictionaryValue = findViewById(R.id.etDictionaryValue);
        btnAddEntry = findViewById(R.id.btnAddEntry);
        btnClearEntries = findViewById(R.id.btnClearEntries);
        btnDeleteKey = findViewById(R.id.btnDeleteKey);
        entryButtons = findViewById(R.id.llEntryButtons);

        btnAddEntry.setOnClickListener(view -> {
            myDictionary.put(dictionaryKey.getText().toString(), dictionaryValue.getText().toString());
            Button btn = new Button(MainActivity.this);
            btn.setText(dictionaryKey.getText().toString());
            btn.setOnClickListener(v -> Toast.makeText(MainActivity.this,
                    myDictionary.get(dictionaryKey.getText().toString()),
                    Toast.LENGTH_LONG).show());
            entryButtons.addView(btn);
        });

        btnClearEntries.setOnClickListener(view -> {
            entryButtons.removeAllViews();
            ArrayList<String> keys = new ArrayList<>(myDictionary.keySet());
            keys.sort(String::compareTo);
            for (String key : keys) {
                Button btn = new Button(MainActivity.this);
                btn.setText(key);
                btn.setOnClickListener(v -> Toast.makeText(MainActivity.this,
                        myDictionary.get(key),
                        Toast.LENGTH_LONG).show());
                entryButtons.addView(btn);
            }
        });

        btnDeleteKey.setOnClickListener(view -> myDictionary.remove(dictionaryKey.getText().toString()));
    }
}