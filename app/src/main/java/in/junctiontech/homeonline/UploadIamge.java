package in.junctiontech.homeonline;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class UploadIamge extends AppCompatActivity {


    private TextView txtPercentage;
    private ProgressBar progressBar;
    private static final String TAG = ImageSelection.class.getSimpleName();
    long totalSize = 0;
    private String filePath;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_iamge);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");
        path=i.getStringExtra("path");

        // boolean flag to identify the media type, image or video


        if (filePath != null) {
            // Displaying the image or video on the screen
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }
    }


    public void sendToServer(View v)
        {
            new UploadFileToServer().execute();
        }


        private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
            @Override
            protected void onPreExecute() {
                // setting progress bar to zero
                progressBar.setProgress(0);
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Integer... progress) {
                // Making progress bar visible
                progressBar.setVisibility(View.VISIBLE);

                // updating progress bar value
                progressBar.setProgress(progress[0]);

                // updating percentage value
                txtPercentage.setText(String.valueOf(progress[0]) + "%");
            }

            @Override
            protected String doInBackground(Void... params) {
                return uploadFile();
            }

            @SuppressWarnings("deprecation")
            private String uploadFile() {
                String responseString = null;

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://junctionerp.com/vishal/image.php");

                try {
                    AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                            new AndroidMultiPartEntity.ProgressListener() {

                                @Override
                                public void transferred(long num) {
                                    publishProgress((int) ((num / (float) totalSize) * 100));
                                }
                            });

                   BitmapFactory.Options options = new BitmapFactory.Options();

                    // downsizing image as it throws OutOfMemory Exception for larger
                    // images
                    options.inSampleSize = 8;

                    final Bitmap bitmap = BitmapFactory.decodeFile(filePath,  options);


                    File mediaStorageDir = new File(
                            Environment
                                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                            "thumbnail");
                    if (!mediaStorageDir.exists()) {
                        if (!mediaStorageDir.mkdirs()) {
                            Toast.makeText(UploadIamge.this, "no", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                    }

                    File abc = new File(mediaStorageDir+path);
                    FileOutputStream out = new FileOutputStream(abc);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,50, out);
                    out.flush();
                    out.close();

                    File sourceFile = new File(abc.getPath());

                    // Adding file data to http body
                    entity.addPart("image", new FileBody(sourceFile));

                    // Extra parameters if you want to pass to server
                    entity.addPart("website",
                            new StringBody("www.bhasketnet.com"));
                    entity.addPart("email", new StringBody("db@gmail.com"));

                    totalSize = entity.getContentLength();
                    httppost.setEntity(entity);

                    // Making server call
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity r_entity = response.getEntity();

                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        // Server response
                        responseString = EntityUtils.toString(r_entity);
                    } else {
                        responseString = "Error occurred! Http Status Code: "
                                + statusCode;
                    }

                } catch (ClientProtocolException e) {
                    responseString = e.toString();
                } catch (IOException e) {
                    responseString = e.toString();
                }

                return responseString;

            }

            @Override
            protected void onPostExecute(String result) {
                Log.e(TAG, "Response from server: " + result);

                // showing the server response in an alert dialog
                showAlert(result);

                super.onPostExecute(result);
            }

            private File savebitmap(Bitmap bmp) {
                String extStorageDirectory = Environment.getExternalStorageDirectory()
                        .toString();
                OutputStream outStream = null;

                File file = new File(bmp + ".png");
                if (file.exists()) {
                    file.delete();
                    file = new File(extStorageDirectory, bmp + ".png");
                    Log.e("file exist", "" + file + ",Bitmap= " + bmp);
                }
                try {
                    outStream = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("file", "" + file);
                return file;

            }




       }

        /**
         * Method to show alert dialog
         * */
    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



}

 class AndroidMultiPartEntity extends MultipartEntity

{

    private final ProgressListener listener;

    public AndroidMultiPartEntity(final ProgressListener listener) {
        super();
        this.listener = listener;
    }

    public AndroidMultiPartEntity(final HttpMultipartMode mode,
                                  final ProgressListener listener) {
        super(mode);
        this.listener = listener;
    }

    public AndroidMultiPartEntity(HttpMultipartMode mode, final String boundary,
                                  final Charset charset, final ProgressListener listener) {
        super(mode, boundary, charset);
        this.listener = listener;
    }

    @Override
    public void writeTo(final OutputStream outstream) throws IOException {
        super.writeTo(new CountingOutputStream(outstream, this.listener));
    }

    public static interface ProgressListener {
        void transferred(long num);
    }

    public static class CountingOutputStream extends FilterOutputStream {

        private final ProgressListener listener;
        private long transferred;

        public CountingOutputStream(final OutputStream out,
                                    final ProgressListener listener) {
            super(out);
            this.listener = listener;
            this.transferred = 0;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            this.transferred += len;
            this.listener.transferred(this.transferred);
        }

        public void write(int b) throws IOException {
            out.write(b);
            this.transferred++;
            this.listener.transferred(this.transferred);
        }
    }


}
