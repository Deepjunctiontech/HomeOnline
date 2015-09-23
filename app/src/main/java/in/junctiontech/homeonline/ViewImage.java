package in.junctiontech.homeonline;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewImage extends AppCompatActivity {

    private ImageView imag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
       // getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        String f=getIntent().getStringExtra("imag");
     //   Toast.makeText(this,f,Toast.LENGTH_LONG).show();
     //   f= f.replace("thumbnail","DB");
     //   Toast.makeText(this,f,Toast.LENGTH_LONG).show();
        imag=(ImageView)findViewById(R.id.imageView_gallery);
        imag.setImageURI(Uri.parse(f));   //OUT OF MEMORY EXCEPTION IF REPLACE

    }

    public void onPause()
    {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_image, menu);
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
}
