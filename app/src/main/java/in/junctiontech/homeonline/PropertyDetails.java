package in.junctiontech.homeonline;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class PropertyDetails extends AppCompatActivity {

    private Spinner property_spiner_bhk_type, property_spiner_property_type;
    private DBHandler db;
    private String property_type = "Flat", bhk_type = "1 RK", preferred_visit_time = "By Appointment";
    private Spinner property_spinner_livingroom, property_spinner_bedroom,
            property_spinner_kitchen, property_spinner_bathroom, property_spinner_balcony, property_spiner_preferred_visit_time;
    public static String total_livingroom = "1", total_bedroom = "1", total_bathroom = "1", total_kitchen = "1", total_balcony = "1";
    private String property_array[], preferred_visit_time_array[], bhk_type_array[], property_type_array[], property_id[];
    private Button property_et_possesion_date;
    private Calendar calendar;
    private int year, month, day;
    private boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        Intent i = this.getIntent();
        db = new DBHandler(this, "DB", null, 1);
        Bundle b = db.getIdName();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        property_spinner_livingroom = (Spinner) this.findViewById(R.id.property_spinner_livingroom);
        property_spinner_bedroom = (Spinner) this.findViewById(R.id.property_spinner_bedroom);
        property_spinner_kitchen = (Spinner) this.findViewById(R.id.property_spinner_kitchen);
        property_spinner_bathroom = (Spinner) this.findViewById(R.id.property_spinner_bathroom);
        property_spinner_balcony = (Spinner) this.findViewById(R.id.property_spinner_balcony);

        property_spiner_bhk_type = (Spinner) findViewById(R.id.property_spiner_bhk_type);
        property_spiner_property_type = (Spinner) findViewById(R.id.property_spiner_property_type);

        property_spiner_preferred_visit_time = (Spinner) findViewById(R.id.property_spiner_preferred_visit_time);
        property_et_possesion_date = (Button) findViewById(R.id.property_et_possesion_date);
        String curr = day + "/" + (month + 1) + "/" + year + "";
        property_et_possesion_date.setText(curr);


        Resources r = this.getResources();
        property_type_array = r.getStringArray(R.array.property_type);
        property_array = r.getStringArray(R.array.property);
        property_id = r.getStringArray(R.array.property_id);
        preferred_visit_time_array = r.getStringArray(R.array.preferred_visit_time);
        bhk_type_array = r.getStringArray(R.array.bhk_type);


        property_spiner_preferred_visit_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preferred_visit_time = preferred_visit_time_array[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        property_spiner_bhk_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bhk_type = bhk_type_array[position];


                char c = bhk_type.charAt(0);
                //   Toast.makeText(PropertyDetails.this,c+"",Toast.LENGTH_LONG).show();
               // if (check == true)
                    property_spinner_bedroom.setSelection(c - 49);
               // check = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        property_spiner_property_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             //   property_type = property_type_array[position];
                property_type = property_id[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        property_spinner_livingroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_livingroom = property_array[position];
                //  Toast.makeText(PropertyDetails.this, total_livingroom, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        property_spinner_bedroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_bedroom = property_array[position];
                //  Toast.makeText(PropertyDetails.this, total_bedroom, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        property_spinner_kitchen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_kitchen = property_array[position];
                //   Toast.makeText(PropertyDetails.this, total_kitchen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        property_spinner_bathroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_bathroom = property_array[position];
                //Toast.makeText(PropertyDetails.this, total_bathroom, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        property_spinner_balcony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_balcony = property_array[position];
                //  Toast.makeText(PropertyDetails.this, total_washdry, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        getMenuInflater().inflate(R.menu.actionbar_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.action_my_next) {
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            item.setEnabled(false);
            savePropertyDetail();
            startActivity(new Intent(this, AdvertiserDetail.class));
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void send() {

        //  Toast.makeText(STEP1.this, "SAVE", Toast.LENGTH_SHORT).show();


//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest sr = new StringRequest(Request.Method.POST,"http://junctionerp.com/vishal/new.php",
//                new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(STEP1.this, response, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(STEP1.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("username","Vishal Yadav");
//                params.put("phone","0755");
//                params.put("mobile", "9893394203");
//                params.put("email","engineer@gmail.com");
//
//
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//
//        queue.add(sr);
//
    }

    private void savePropertyDetail() {

        String possession_date = property_et_possesion_date.getText().toString();
        db.setPropertyDetail(bhk_type, property_type, total_livingroom, total_bedroom, total_kitchen, total_bathroom, total_balcony,
                preferred_visit_time, possession_date, "true");
        ContentValues cv= new ContentValues();
        cv.put("update_from_server","true");
        db.setUpdateFromServerStatus(cv,Appointment.clicked);

    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle b = db.getPropertyDetail();

        if (b.getString("possesion_date") == null) ;
        else
            property_et_possesion_date.setText(b.getString("possesion_date"));

        String s = b.getString("property_type");

        if (s == null) ;
        else {
            int i = 0;
            for (; i < property_type_array.length; i++) {
                if (property_id[i].equalsIgnoreCase(s)) {
                    property_spiner_property_type.setSelection(i);
                    break;
                }
            }

        }


        String s1 = b.getString("bhk_type");
        if (s1 == null) ;
        else {
            int j = 0;
            for (; j < bhk_type_array.length; j++) {
                if (bhk_type_array[j].equalsIgnoreCase(s1)) {
                    property_spiner_bhk_type.setSelection(j);
                    break;
                }
            }

        }

        String s2 = b.getString("preferred_visit_time");

        if (s2 == null) ;
        else {
            int i = 0;
            for (; i < preferred_visit_time_array.length; i++) {
                if (preferred_visit_time_array[i].equalsIgnoreCase(s2)) {
                    property_spiner_preferred_visit_time.setSelection(i);
                    break;
                }
            }

        }


        String livingroom = b.getString("total_livingroom");

        if (livingroom == null) ;
        else {
            int i = 0;
            for (; i < property_array.length; i++) {

                if (property_array[i].equalsIgnoreCase(livingroom)) {
                    property_spinner_livingroom.setSelection(i);
                    break;
                }
            }

        }

        String bedroom = b.getString("total_bedroom");

        if (bedroom == null) ;
        else {
            int i = 0;
            for (; i < property_array.length; i++) {

                if (property_array[i].equalsIgnoreCase(bedroom)) {
                    property_spinner_bedroom.setSelection(i);
                    break;
                }
            }

        }

        String kitchen = b.getString("total_kitchen");

        if (kitchen == null) ;
        else {
            int i = 0;
            for (; i < property_array.length; i++) {

                if (property_array[i].equalsIgnoreCase(kitchen)) {
                    property_spinner_kitchen.setSelection(i);
                    break;
                }
            }

        }

        String bathroom = b.getString("total_bathroom");

        if (bathroom == null) ;
        else {
            int i = 0;
            for (; i < property_array.length; i++) {

                if (property_array[i].equalsIgnoreCase(bathroom)) {
                    property_spinner_bathroom.setSelection(i);
                    break;
                }
            }

        }

        String balcony = b.getString("total_balcony");

        if (balcony == null) ;
        else {
            int i = 0;
            for (; i < property_array.length; i++) {

                if (property_array[i].equalsIgnoreCase(balcony)) {
                    property_spinner_balcony.setSelection(i);
                    break;
                }
            }

        }


    }


    public void myClick(View v) {
        v.setEnabled(false);
        savePropertyDetail();
        startActivity(new Intent(this, AdvertiserDetail.class));
        finish();
    }

    public void selectPossessionDate(View v) {
        showDialog(999);
        Toast.makeText(this, "Date Selection", Toast.LENGTH_LONG).show();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            property_et_possesion_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

        }
    };


}


