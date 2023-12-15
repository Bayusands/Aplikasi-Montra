package com.example.aplikasimontra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        EditText editTextSource = findViewById(R.id.Source);
        EditText editTextDestination = findViewById(R.id.Destination);
        Button button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Source = editTextSource.getText().toString();
                String Destination = editTextDestination.getText().toString();
                if (Source.equals("") && Destination.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter both source and destination", Toast.LENGTH_SHORT).show();
                }
                else{
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + Source + "/" + Destination);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });
    }
}