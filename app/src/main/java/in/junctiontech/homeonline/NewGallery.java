package in.junctiontech.homeonline;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewGallery extends AppCompatActivity {

    private GridView gv;
    Integer[] imageIDs = {
            R.drawable.living,
            R.drawable.bed,
            R.drawable.bath,
            R.drawable.kitchen,
            R.drawable.washdry,


    };
    private static final String IMAGE_DIRECTORY_NAME = "DB";
    private static String path;

    String room_name[] = {"Living Room", "Bed Room", "Bath Room", "Kitchen", "Wash Dry"};
    private String[] brr={"no_of_livingroom","no_of_bedroom","no_of_bathroom","no_of_kitchen","no_of_washdry"};
    private int itemBackground;
    int click;
    private ArrayList<File> list;
    private ArrayList<String> status;
    private ArrayList total[];
    private Uri fileUri;
    private DBHandler db;
    private myGridAdapter obj;
    private Spinner image_spinner_particular;
    private String selected_particular="1";
    private ArrayAdapter<String> obj1;
    private String[] spinnerArray={};
    private TextView textGallery;
    private static Context c;

    public static Context getContext() {
        return c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gallery);

        gv = (GridView) findViewById(R.id.new_open_gridView);
        android.widget.Gallery gl = (android.widget.Gallery) findViewById(R.id.my_gallery_new);


                image_spinner_particular = (Spinner) findViewById(R.id.spinnerGallery);
        gl.setAdapter(new ImageAdapter(this));
        textGallery= (TextView) findViewById(R.id.textGallery);
        textGallery.setPaintFlags(textGallery.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //list = imageReader(getOutputMediaFile());
        db = new DBHandler(this, "DB", null, 1);
       //  updateGridView();
        checkSpinner();
        textGallery.setText(room_name[click]);
        gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click = position;
                updateGridView();
                checkSpinner();

                textGallery.setText(room_name[click]);


                // Toast.makeText(NewGallery.this, room_name[position], Toast.LENGTH_SHORT).show();
            }
        });




        image_spinner_particular.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null)
                    selected_particular = spinnerArray[position];
                //   Toast.makeText(ImageSelection.this,selected_particular,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NewGallery.this, ViewImage.class).putExtra("imag", list.get(position).toString()));

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

    }

    public void checkSpinner()
    {
        String check = db.getNoOfRoom(brr[click]);
        if (check == null)
            check = "1";
        int c = Integer.parseInt(check);
        spinnerArray = new String[c];

        for (int i = 0; i < spinnerArray.length; i++)
            spinnerArray[i] = i + 1 + "";

        obj1=null;
       obj1 = new ArrayAdapter<String>(NewGallery.this, android.R.layout.simple_list_item_1, spinnerArray);

        image_spinner_particular.setAdapter(obj1);
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }

    private File getOutputMediaFile() {

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
        private View v=null;

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
            imageView.setLayoutParams(new android.widget.Gallery.LayoutParams(180, 180));
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

            TextView tv = (TextView) convertView.findViewById(R.id.statusimage);
            tv.setText(status.get(position).toString());

            iv.setImageURI(Uri.parse(getItem(position).toString()));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setLayoutParams(new android.widget.FrameLayout.LayoutParams(220, 220));


            return convertView;
        }


    }
    public void fabClick(View v)
    {
      //  Toast.makeText(this,"camera",Toast.LENGTH_LONG).show();
        captureImage();


    }

    private void captureImage() {
        if (!getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "Sorry! Your device doesn't support camera",Toast.LENGTH_LONG).show();
            return ;

        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //  Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

        fileUri = getOutputMediaFileUri(1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


        // start the image capture Intent
        startActivityForResult(intent, 2);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
        outState.putInt("CLICK", click);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
        click=savedInstanceState.getInt("CLICK");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // if the result is capturing Image
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                db.setImageURL(room_name[click], selected_particular, fileUri.getPath());
                BitmapFactory.Options options = new BitmapFactory.Options();

                // downsizing image as it throws OutOfMemory Exception for larger
                // images
                options.inSampleSize = 8;

                final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),  options);


                File mediaStorageDir = new File(
                        Environment
                                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        "thumbnail");
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                File abc = new File(mediaStorageDir + path);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(abc);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                updateGridView();


            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }


        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private  File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "DB");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        //String s[]=selected.split(" ");
        File mediaFile;
        if (type == 1) {
            path= File.separator
                    + "ID="+Appointment.clicked+"_"+room_name[click]+"_"+selected_particular+"_" + timeStamp + ".jpg";
            mediaFile = new File(mediaStorageDir.getPath() + path);

        } else {
            return null;
        }

        return mediaFile;
    }


    public void onPause()
    {
        obj = null;
        super.onPause();
        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show();

    }

    public void onResume()
    {
        super.onResume();
        this.c=this;
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();
        updateGridView();

    }

    private void updateGridView()
    {

        obj = null;
        //  list = imageReader(getOutputMediaFile());
        total = db.giveImageURL(room_name[click]);
        list = total[0];
        status = total[1];
        obj = new myGridAdapter();

        gv.setAdapter(obj);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_HOME) {
            item.setEnabled(false);
            Toast.makeText(this,"HOME",Toast.LENGTH_LONG).show();
            finish();


        }

        return super.onOptionsItemSelected(item);
    }

}


