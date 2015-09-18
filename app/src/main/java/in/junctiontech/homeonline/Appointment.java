package in.junctiontech.homeonline;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Appointment extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    ListView lv;
    //   String[] title={},address={},phone={};
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    private DBHandler db;
    private String id[],no[];
    public static String clicked;
    private int position;
    private MyAdapter ma;
    private RadioButton rb_complete;
    private RadioButton rb_defrrred;
    private RadioButton rb_cancelled;
    private RadioButton rb_reschedulled;
    private String ststus;
    private RelativeLayout rl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RequestQueue rq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        db=new DBHandler(this,"DB",null,1);
        lv = (ListView) findViewById(R.id.listView);
      rq = Volley.newRequestQueue(this);
    rl= (RelativeLayout) findViewById(R.id.appointmentid);

         mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);

        getDataFromDataBase();
     //   onRefresh();
        //  mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();


       lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
               Appointment.this.position = position;
               clicked = id[position];
//               Toast.makeText(Appointment.this,position+"",Toast.LENGTH_LONG).show();
               return false;
           }
       });

        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//                Toast.makeText(Appointment.this,id[position]+"",Toast.LENGTH_LONG).show();

                clicked = id[position];
                startActivity(new Intent(Appointment.this, PropertyDetails.class));


            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onRefresh() {




        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://junctionerp.com/ankit/appointment.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // for (int i = 0; i < 5; i++)
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(Appointment.this,jsonObject.getString("status"),Toast.LENGTH_LONG).show();
//                            Toast.makeText(Appointment.this,jsonObject.getString("code"),Toast.LENGTH_LONG).show();
                            // Toast.makeText(Appointment.this,jsonObject.getString("data"),Toast.LENGTH_LONG).show();
                            JSONObject js = new JSONObject(jsonObject.getString("data"));
                            //         Toast.makeText(Appointment.this,js.getString("userID"),Toast.LENGTH_LONG).show();

                            //   Toast.makeText(Appointment.this,response,Toast.LENGTH_LONG).show();

                            JSONArray obj = js.getJSONArray("appointment_list");


//                            Toast.makeText(Appointment.this,obj.length()+"",Toast.LENGTH_LONG).show();
                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject n = obj.getJSONObject(i);
//                                Toast.makeText(Appointment.this,n.getString("name"),Toast.LENGTH_LONG).show();
//                                Toast.makeText(Appointment.this,n.getString("phone"),Toast.LENGTH_LONG).show();
//                                Toast.makeText(Appointment.this,n.getString("address"),Toast.LENGTH_LONG).show();

                                db.saveData(n.getString("appointmentID"),
                                        n.getString("name"),
                                        n.getString("address"),
                                        n.getString("phone"),
                                        n.getString("appointmentStatus"),
                                        n.getString("appointmentTime"));
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Appointment.this, "error", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        getDataFromDataBase();
                        // update();
                        mSwipeRefreshLayout.setRefreshing(false);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //for (int i = 0; i < 2; i++)
                //   Toast.makeText(Appointment.this, error.toString(), Toast.LENGTH_LONG).show();
                //  getDataFromDataBase();
//                        getDataFromDataBase();
                mSwipeRefreshLayout.setRefreshing(false);


                String err=error.getMessage();
                if(error instanceof NoConnectionError) {
                    err="Check Your Internet Connection.";

                }

                Snackbar.make(rl, err, Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        onRefresh();
                        // Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();
                    }
                }).show();

            }


        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sp = Appointment.this.getSharedPreferences("Login", Appointment.this.MODE_PRIVATE);
                params.put("userid", sp.getString("userID", "Not Found"));
                //params.put("userid", "1");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("abc", "value");
                return headers;
            }
        };
// Add the request to the RequestQueue.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000,2,2));
        rq.add(stringRequest);


    }


    public void update()
    {
        String[][] abc=db.getAllData();
        ma.updateTitle(abc[0]);
        ma.updateAddress(abc[1]);
        ma.updatePhone(abc[2]);
        ma.updateStatus(abc[4]);
        ma.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();

    }

    public void getDataFromDataBase()
    {
        String[][] abc=db.getAllData();
        id=abc[3];
        no=abc[2];
      //  ma=null;
        ma = new MyAdapter(Appointment.this, abc[0],
                abc[1],
                abc[2],abc[4],abc[5]);

        lv.setAdapter(ma);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_long, menu);
        menu.setHeaderTitle("Select Option");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        String s="";
        switch (item.getItemId()) {


            case R.id.calling:
                s="Call";
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + no[position]));
                startActivity(intent);
                break;
            case R.id.status:
               completeStatus();
                s="status";

                break;

            case R.id.send:
                s="Sync";
                db.sendDataForParticularId(id[position]);
                break;

            case R.id.delete:
                alert();
                s="Delete";
                break;

        }
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);



    }

    public void alert()
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
       // builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteForParticularID();
                deleteImage("thumbnail");
                deleteImage("DB");
                getDataFromDataBase();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteImage(String folder) {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                folder);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return ;
            }
        }
        File[] f = mediaStorageDir.listFiles();
        int check = 0;
        for (int i = 0; i < f.length; i++) {

            if (f[i].isDirectory()) {
            } else if (f[i].getName().endsWith(".jpg") && f[i].getName().contains(Appointment.clicked)) {
                String s[] = f[i].getName().split("_");
                if (s[0].equalsIgnoreCase("ID=" + Appointment.clicked)) {
                    f[i].delete();
                    check = 1;

                }
            }
        }
        if (check == 0)
            Toast.makeText(this, "NO IMAGE FOUND", Toast.LENGTH_LONG).show();


    }

    private void completeStatus() {
        AlertDialog.Builder alt = new AlertDialog.Builder(Appointment.this);
        alt.setTitle(" Appoinment Status");


        alt.setPositiveButton("OK", new MyClick());
        alt.setNegativeButton("CANCEL", new MyClick());
        LinearLayout ll = new LinearLayout(Appointment.this);
        ll.setPadding(16, 16, 16, 16);

        rb_complete = new RadioButton(Appointment.this);
        rb_complete.setText("Complete");
        rb_defrrred = new RadioButton(Appointment.this);
        rb_defrrred.setText("Deferred");
        rb_cancelled = new RadioButton(Appointment.this);
        rb_cancelled.setText("Cancelled");
        rb_reschedulled = new RadioButton(Appointment.this);
        rb_reschedulled.setText("Rescheduled");


        RadioGroup rg = new RadioGroup(Appointment.this);
        rg.addView(rb_complete);
        rg.addView(rb_defrrred);
        rg.addView(rb_cancelled);
        rg.addView(rb_reschedulled);

        ll.addView(rg);

        alt.setView(ll);

        Bundle b=  db.getStatus();

        String s=b.getString("status");
     //   Toast.makeText(Appointment.this, s, Toast.LENGTH_SHORT).show();
        if(s==null);
        else if(s.equalsIgnoreCase("Complete")){
            rb_complete.setChecked(true);
        }
        else if(s.equalsIgnoreCase("Deferred")){
            rb_defrrred.setChecked(true);
        }
        else if(s.equalsIgnoreCase("Cancelled")){
            rb_cancelled.setChecked(true);
        }
        else if(s.equalsIgnoreCase("Rescheduled")){
            rb_reschedulled.setChecked(true);
        }

        alt.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
            startActivity(new Intent(this,Prefs.class));

        }else if(id==R.id.main_screen_aboutus) {
            //  Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AboutUs.class));
        }
        else if(id==R.id.main_screen_help){
            // Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,Help.class));
        }

        return super.onOptionsItemSelected(item);
    }


    class MyAdapter extends ArrayAdapter<String> {
        private final Context context;

        String[] title = {}, address = {}, phone = {},status={},datetime={};
        private LayoutInflater inflater;

        public MyAdapter(Context context, String[] title, String[] address, String[] phone,String []status,String []datetime) {
            super(context, R.layout.mylist, title);
            this.context = context;
            this.title = title;
            this.address = address;
            this.phone = phone;
            this.status=status;
            this.datetime=datetime;


        }

        public void updateStatus(String []status)
        {
            this.status=status;
        }
        public void updateTitle(String []title)
        {
            this.title=title;
        }
        public void updateAddress(String []address)
        {
            this.address=address;
        }
        public void updatePhone(String []phone)
        {
            this.phone=phone;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           // super.getView(position, convertView, parent);

            if (convertView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.mylist, null);
            }

            final ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv1);
            holder.address = (TextView) convertView.findViewById(R.id.tv2);
            holder.phone = (ImageButton) convertView.findViewById(R.id.childbutton);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.datetime = (TextView) convertView.findViewById(R.id.datetime);


            holder.title.setText(title[position]);
            holder.address.setText(address[position]);
          //  holder.phone.setText(phone[position]);
            holder.status.setText(status[position]);
            holder.datetime.setText(datetime[position]);


            final String temp = (phone[position]);


            holder.phone.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //    if (customListner != null) {
                    Toast.makeText(Appointment.this, "CALL",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:" + temp));
                    startActivity(intent);

                }

            });


        return convertView;
        }

        public class ViewHolder {
            TextView title, address, status; ImageButton phone;
            TextView datetime;
        }
    }

    private class MyClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {


            if (rb_complete.isChecked()) {
                ststus = rb_complete.getText().toString();

            }
            if (rb_defrrred.isChecked()) {
                ststus = rb_defrrred.getText().toString();

            }
            if (rb_cancelled.isChecked()) {
                ststus = rb_cancelled.getText().toString();

            }
            if (rb_reschedulled.isChecked()) {
                ststus = rb_reschedulled.getText().toString();

            }

            if (which == -1) {
                db.setStatus(ststus);

            }
            update();
      //      String[][] abc=db.getAllData();

    //        ma.updateStatus(abc[4]);
    //        ma.notifyDataSetChanged();


        }
    }


}
