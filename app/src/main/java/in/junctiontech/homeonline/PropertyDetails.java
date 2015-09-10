package in.junctiontech.homeonline;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyDetails extends AppCompatActivity {

    private TextView name;
    private Spinner property_spiner_bhk_type, property_spiner_property_type, property_spiner_lease_type;
    private DBHandler db;
    private String property_type = "Flate", bhk_type = "1 BHK", preferred_visit_time = "By Appointment", lease_type = "No Restriction";
    private Spinner property_spinner_livingroom, step1_spiner_preferred_visit_time, property_spinner_bedroom,
            property_spinner_kitchen, property_spinner_bathroom, property_spinner_washdry,property_spiner_preferred_visit_time;
    public static String total_livingroom = "1", total_bedroom = "1", total_bathroom = "1", total_kitchen = "1", total_washdry = "1";
    private String property_array[],lease_type_array[],preferred_visit_time_array[],bhk_type_array[],property_type_array[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        Intent i = this.getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        property_spinner_livingroom=(Spinner)this.findViewById(R.id.property_spinner_livingroom);
        property_spinner_bedroom=(Spinner)this.findViewById(R.id.property_spinner_bedroom);
        property_spinner_kitchen=(Spinner)this.findViewById(R.id.property_spinner_kitchen);
        property_spinner_bathroom=(Spinner)this.findViewById(R.id.property_spinner_bathroom);
        property_spinner_washdry=(Spinner)this.findViewById(R.id.property_spinner_washdry);

        property_spiner_bhk_type = (Spinner) findViewById(R.id.property_spiner_bhk_type);
        property_spiner_property_type = (Spinner) findViewById(R.id.property_spiner_property_type);
        property_spiner_lease_type = (Spinner) findViewById(R.id.property_spiner_lease_type);
        property_spiner_preferred_visit_time = (Spinner) findViewById(R.id.property_spiner_preferred_visit_time);

        db = new DBHandler(this, "DB", null, 1);
        name=(TextView)findViewById(R.id.tv_property_detail);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));

        Resources r = this.getResources();
        property_type_array = r.getStringArray(R.array.property_type);
        property_array = r.getStringArray(R.array.property);
        lease_type_array=r.getStringArray(R.array.lease_type);
        preferred_visit_time_array=r.getStringArray(R.array.preferred_visit_time);
        bhk_type_array=r.getStringArray(R.array.bhk_type);

        property_spiner_lease_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lease_type = lease_type_array[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        property_spiner_property_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                property_type=property_type_array[position];


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
        property_spinner_washdry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total_washdry = property_array[position];
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
            savePropertyDetail();
            startActivity(new Intent(this, STEP1.class));
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


        db.setPropertyDetail(bhk_type,property_type,total_livingroom, total_bedroom, total_kitchen, total_bathroom, total_washdry,
                lease_type, preferred_visit_time,"true");

    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle b = db.getPropertyDetail();
        String s = b.getString("property_type");

        if (s == null) ;
        else {
            int i = 0;
            for (; i < property_type_array.length; i++) {
                if (property_type_array[i].equalsIgnoreCase(s))
                    break;
            }
            property_spiner_property_type.setSelection(i);
        }



        String s1 = b.getString("bhk_type");
        if (s1 == null) ;
        else {
            int j = 0;
            for (; j < bhk_type_array.length; j++) {
                if (bhk_type_array[j].equalsIgnoreCase(s1))
                    break;
            }
            property_spiner_bhk_type.setSelection(j);
        }

        String s2=b.getString("preferred_visit_time");

        if(s2==null);
        else {
            int i=0;
            for(;i<preferred_visit_time_array.length;i++){
                if(preferred_visit_time_array[i].equalsIgnoreCase(s2))
                    break;
            }
            property_spiner_preferred_visit_time.setSelection(i);
        }

        String s3=b.getString("lease_type");

        if(s3==null);
        else {
            int i=0;
            for(;i<lease_type_array.length;i++){
                if(lease_type_array[i].equalsIgnoreCase(s3))
                    break;
            }
            property_spiner_lease_type.setSelection(i);

    }

        String livingroom=b.getString("total_livingroom");

        if(livingroom==null);
        else {
            int i=0;
            for(;i<property_array.length;i++){

                if(property_array[i].equalsIgnoreCase(livingroom))
                    break;
            }
            property_spinner_livingroom.setSelection(i);
        }

        String bedroom=b.getString("total_bedroom");

        if(bedroom==null);
        else {
            int i=0;
            for(;i<property_array.length;i++){

                if(property_array[i].equalsIgnoreCase(bedroom))
                    break;
            }
            property_spinner_bedroom.setSelection(i);
        }

        String kitchen=b.getString("total_kitchen");

        if(kitchen==null);
        else {
            int i=0;
            for(;i<property_array.length;i++){

                if(property_array[i].equalsIgnoreCase(kitchen))
                    break;
            }
            property_spinner_kitchen.setSelection(i);
        }

        String bathroom=b.getString("total_bathroom");

        if(bathroom==null);
        else {
            int i=0;
            for(;i<property_array.length;i++){

                if(property_array[i].equalsIgnoreCase(bathroom))
                    break;
            }
            property_spinner_bathroom.setSelection(i);
        }

        String washdry=b.getString("total_washdry");

        if(washdry==null);
        else {
            int i=0;
            for(;i<property_array.length;i++){

                if(property_array[i].equalsIgnoreCase(washdry))
                    break;
            }
            property_spinner_washdry.setSelection(i);
        }


    }



    public void myClick(View v) {
        savePropertyDetail();
        startActivity(new Intent(this, STEP1.class));
        finish();
    }


}


