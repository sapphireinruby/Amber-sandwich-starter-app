package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details); //from strings.xml
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        ImageView sandwichesIv = findViewById(R.id.image_iv);

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage()) // load(URL)
                .placeholder(R.drawable.souv)
                .into(sandwichesIv);
        // placeholder() to display a placeholder until the image is loaded and processed.

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView aka = findViewById(R.id.also_known_tv);
        for(int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
            aka.append(sandwich.getAlsoKnownAs().get(i) + " ");
        }

        TextView PlaceOfOrigin = findViewById(R.id.origin_tv);
        PlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());

        TextView mDescription = findViewById(R.id.description_tv);
        mDescription.setText(sandwich.getDescription());

        TextView Ingredients = findViewById(R.id.ingredients_tv);
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            Ingredients.append(sandwich.getIngredients().get(i) + " ");

        }


    }
}
