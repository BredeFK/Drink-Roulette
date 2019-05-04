package no.fritjof.drinkroulette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declare arrayList with items and lastNumb
    private ArrayList<String> items = new ArrayList<>();
    private int lastNumb = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view elements
        Button generate = findViewById(R.id.btnGenerate);
        final TextView number = findViewById(R.id.txtRandNumber);

        // Set on click listener
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get input
                EditText editItems = findViewById(R.id.editItems);
                String s = editItems.getText().toString();

                // Make sure input is not empty
                if (!s.isEmpty()) {

                    // Clear previous list and add new items
                    items.clear();
                    items.addAll(Arrays.asList(s.split(",")));

                    number.setText(items.get(getRandomNumber()));
                }
            }
        });
    }

    // getRandomNumber Gets a random number and makes sure it's not the same as last
    int getRandomNumber() {

        // Get random number
        SecureRandom random = new SecureRandom();
        Random rand = new Random();

        // Add seed
        random.generateSeed(rand.nextInt(20));

        // If only 1 item, index is always 0
        int length = items.size();
        if (length == 1) {
            return 0;
        }

        // Make sure new random number
        int n = random.nextInt(length);

        // If there's only two, actually have random selection
        if (length == 2) {
            return n;
        }

        // If more than 2, make sure the new number is not the same as last TODO : add sound to better indicate new drink
        while (n == lastNumb) {
            n = random.nextInt(length);
        }
        lastNumb = n;

        // Return number
        return n;
    }
}
