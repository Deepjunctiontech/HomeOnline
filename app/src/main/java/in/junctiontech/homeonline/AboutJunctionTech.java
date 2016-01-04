package in.junctiontech.homeonline;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutJunctionTech extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_junction_tech);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Junction Software Pvt Ltd.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setCollapsedTitleTextColor(Color.parseColor("#f77c14"));
        collapsingToolbar.setContentScrimColor(Color.parseColor("#ffffff"));
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#f77c14"));

    }


    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
