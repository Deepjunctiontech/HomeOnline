package in.junctiontech.homeonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OpenGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void myGalleryV(View v)
    {
        String s=null;
        switch (v.getId())
        {

            case R.id.galleryLiving:
                s="Living Room";
                break;
            case R.id.galleryBedroom:
                s="Bed Room";
                break;
            case R.id.galleryKitchen:
                s="Kitchen";
                break;
            case R.id.galleryBathroom:
                s="Bath Room";
                break;
            case R.id.galleryWashdry:
                s="Wash Dry";
                break;


        }
        startActivity(new Intent(this,Gallery.class).putExtra("click",s));
    }
}
