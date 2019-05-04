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

    private ArrayList<String> items = new ArrayList<>();
    private int lastNumb = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button generate = findViewById(R.id.btnGenerate);
        final TextView number = findViewById(R.id.txtRandNumber);


        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editItems = findViewById(R.id.editItems);

                items.clear();
                items.addAll(Arrays.asList(editItems.getText().toString().split(",")));

                number.setText(items.get(getRandomNumber()));
            }
        });
    }

    int getRandomNumber() {

        SecureRandom random = new SecureRandom();
        Random rand = new Random();
        random.generateSeed(rand.nextInt(20));
        int length = items.size();
        int n = random.nextInt(length);
        while (n == lastNumb) {
            n = random.nextInt(length);
        }
        lastNumb = n;

        return n;

    }
}
