package in.junctiontech.homeonline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity {
    GridView gv;
    ArrayList<File> list;
    private String click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Intent i=getIntent();
        click=i.getStringExtra("click");
        list = imageReader(getOutputMediaFile());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.gallery_gridview);
        gv.setAdapter(new myGridAdapter());


        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Gallery.this,ViewImage.class).putExtra("imag",list.get(position).toString()));
            }
        });

        gv.setAdapter(new myGridAdapter());

        registerForContextMenu(gv);
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
       // startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.send_delete, menu);

        menu.setHeaderTitle("Select Any One");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.gallery_send) {
            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "thumbnail");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {

                return null;
            }
        }
        return mediaStorageDir;
    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {

        ArrayList<File> a=new ArrayList<File>();

        File[] f=externalStorageDirectory.listFiles();
        int check=0;
        for(int i=0;i<f.length;i++){

            if(f[i].isDirectory()){}
            else if(f[i].getName().endsWith(".jpg")&&f[i].getName().contains("ID="+Appointment.clicked)&&f[i].getName().contains(click)) {
                    check=1;
                a.add(f[i]);
            }
        }
        if(check==0)
            Toast.makeText(this,"NO IMAGE",Toast.LENGTH_LONG).show();


        return a;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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

    private class myGridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.simple_image, parent, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);

            iv.setImageURI(Uri.parse(getItem(position).toString()));


            return convertView;
        }
    }
}
