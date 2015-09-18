package in.junctiontech.homeonline;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageSelection extends AppCompatActivity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;


    // directory name to store captured images
    private static final String IMAGE_DIRECTORY_NAME = "DB";
    private static String path;
    private DBHandler db;
    private Uri fileUri; // file url to store image

    private ImageView imgPreview;

    private Button btnCapturePicture;
    private Spinner image_spinner;
    public static String selected="Living Room";
    private Spinner image_spinner_particular;
    private String strn;
    private String[] arr,brr={"no_of_livingroom","no_of_bedroom","no_of_bathroom","no_of_kitchen","no_of_washdry"};
    private static String selected_particular="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        image_spinner= (Spinner) findViewById(R.id.image_spinner);
        image_spinner_particular= (Spinner) findViewById(R.id.image_spinner_particular);
        db = new DBHandler(this, "DB", null, 1);
    //    imgPreview = (ImageView) findViewById(R.id.image);
        btnCapturePicture = (Button) findViewById(R.id.openCamera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Resources r=this.getResources();
        arr=r.getStringArray(R.array.imageselection);
        strn="no_of_livingroom";

        image_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    selected = ((TextView) view).getText().toString();// brr[position]

                    for (int i = 0; arr.length > i; i++) {
                        if (arr[i].equalsIgnoreCase(selected)) {
                            strn = brr[i];
                            break;
                        }
                    }


                    String check = db.getNoOfRoom(strn);
                    if (check == null)
                        check = "1";
                    int c = Integer.parseInt(check);
                    String[] total = new String[c];

                    for (int i = 0; i < total.length; i++)
                        total[i] = i + 1 + "";


                    ArrayAdapter<String> obj = new ArrayAdapter<String>(ImageSelection.this, android.R.layout.simple_list_item_1, total);
                    image_spinner_particular.setAdapter(obj);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        image_spinner_particular.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null)
                    selected_particular = ((TextView) view).getText().toString();
         //   Toast.makeText(ImageSelection.this,selected_particular,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCapturePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // capture picture
                captureImage();
            }
        });

		/*
		 * Record video button click event
		 */


        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /*
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);


    }

    /*
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
        outState.putString("type_spinner", selected);
        outState.putString("room_id",selected_particular);
        outState.putString("strn",strn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
        selected=savedInstanceState.getString("type_spinner");
        selected_particular=savedInstanceState.getString("room_id");
        strn=savedInstanceState.getString("strn");
    }


    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                db.setImageURL(selected,selected_particular,fileUri.getPath());
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

    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {


            imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imgPreview.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * ------------ Helper Methods ----------------------
     * */

	/*
	 * Creating file uri to store image/video
	 */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

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
        if (type == MEDIA_TYPE_IMAGE) {
           path= File.separator
                    + "ID="+Appointment.clicked+"_"+selected+"_"+selected_particular+"_" + timeStamp + ".jpg";
            mediaFile = new File(mediaStorageDir.getPath() + path);

        } else {
            return null;
        }

        return mediaFile;
    }

    public void send_To_Server(View v)
    {

//        Intent i=new Intent(this,UploadIamge.class);
//        i.putExtra("filePath",fileUri.getPath());
//        i.putExtra("path",path);
//        startActivity(i);
//        String appPath = this.getApplicationContext().getFilesDir().getAbsolutePath();
//        Toast.makeText(this,appPath,Toast.LENGTH_LONG).show();
//        File f=new File(appPath);
//        try {
//            f.createNewFile();
//            Toast.makeText(this,f.createNewFile()+"",Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ContextWrapper c = new ContextWrapper(this);
//        Toast.makeText(this, c.getFilesDir().getPath(), Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,NewGallery.class);
       startActivity(i);
        finish();
    }

}

