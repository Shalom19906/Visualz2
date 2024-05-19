package com.example.eonline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Maing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maing);

        // Assuming you have an ImageButton in your layout with the ID "artButton"
        ImageButton artButton = findViewById(R.id.art);

        // Set OnClickListener for the artButton
        artButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ArtActivity when the ImageButton is clicked
                Intent intent = new Intent(Maing.this, Art.class);
                startActivity(intent);
            }
        });

        ImageButton landscapeButton = findViewById(R.id.land);

        // Set OnClickListener for the artButton
        landscapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ArtActivity when the ImageButton is clicked
                Intent intent = new Intent(Maing.this, land.class);
                startActivity(intent);
            }
        });

        ImageButton travelbutton = findViewById(R.id.travel);

        // Set OnClickListener for the artButton
        travelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ArtActivity when the ImageButton is clicked
                Intent intent = new Intent(Maing.this, Travel.class);
                startActivity(intent);
            }
        });

        ImageButton nightbutton = findViewById(R.id.night);

        // Set OnClickListener for the artButton
        nightbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ArtActivity when the ImageButton is clicked
                Intent intent = new Intent(Maing.this, night.class);
                startActivity(intent);
            }
        });
        ImageButton cloudbutton = findViewById(R.id.cloud);

        // Set OnClickListener for the artButton
        cloudbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ArtActivity when the ImageButton is clicked
                Intent intent = new Intent(Maing.this, cloud.class);
                startActivity(intent);
            }
        });
    }
}
