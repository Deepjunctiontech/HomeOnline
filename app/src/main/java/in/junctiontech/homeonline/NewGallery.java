package in.junctiontech.homeonline;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class NewGallery extends AppCompatActivity {

    private GridView gv;
    Integer[] imageIDs = {
            R.drawable.living,
            R.drawable.bed,
            R.drawable.bath,
            R.drawable.kitchen,
            R.drawable.washdry,


    };
    String room_name[] = {"Living Room", "Bed Room", "Bath Room", "Kitchen", "Wash Dry"};
    private int itemBackground;
    private int click;
    private ArrayList<File> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gallery);

        gv = (GridView) findViewById(R.id.new_open_gridView);
        android.widget.Gallery gl = (android.widget.Gallery) findViewById(R.id.my_gallery_new);
        gl.setAdapter(new ImageAdapter(this));
        gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click = position;

                list = imageReader(getOutputMediaFile());
                gv.setAdapter(new myGridAdapter());
               // Toast.makeText(NewGallery.this, room_name[position], Toast.LENGTH_SHORT).show();
            }
        });

        list = imageReader(getOutputMediaFile());
        gv.setAdapter(new myGridAdapter());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NewGallery.this, ViewImage.class).putExtra("imag", list.get(position).toString()));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, ImageSelection.class));
        finish();
        return true;
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

        ArrayList<File> arr = new ArrayList<File>();

        File[] f = externalStorageDirectory.listFiles();
        int check = 0;
        for (int i = 0; i < f.length; i++) {

            if (f[i].isDirectory()) {
            } else if (f[i].getName().endsWith(".jpg") && f[i].getName().contains(Appointment.clicked)&&f[i].getName().contains(room_name[click])) {

                String s[]=f[i].getName().split("_");
                if(s[0].equalsIgnoreCase("ID="+Appointment.clicked)) {
                    arr.add(f[i]);
                    check = 1;
                }
            }
        }
        if (check == 0)
            Toast.makeText(this, "NO IMAGE", Toast.LENGTH_LONG).show();


        return arr;

    }





    class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;

        public ImageAdapter(Context c) {
            context = c;
            TypedArray a = obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }


        // returns the number of images
        public int getCount() {
            return imageIDs.length;
        }

        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }

        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }

        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new android.widget.Gallery.LayoutParams(130,130));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

    public class myGridAdapter extends BaseAdapter {
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


