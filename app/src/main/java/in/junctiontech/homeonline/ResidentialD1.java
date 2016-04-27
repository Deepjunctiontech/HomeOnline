package in.junctiontech.homeonline;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ResidentialD1 extends AppCompatActivity {

    private CheckBox serventroom, prayer, /*taccess,*/
            paccess, wifi, solar,
            residential_rb_water_municipal_corp, residential_rb_water_borewell,
            waterbackup_grounded_tanks, waterbackup_terrace_tanks;

    private Spinner /*no_of_storeys,*/ main_enterance_facing, residentiel_spiner_parking_type, residentiel_spiner_furnishing_status,
            residentiel_spiner_balcony,
            residentiel_spiner_common_area;
    private String /*storeys = "0",*/ enterance = "N", parking_type = "No Parking";
    private DBHandler db;
    private RadioButton power_partial, power_full, power_nobackup;
    // private String[] no_of_storey;
    private String[] mainenterancefacing1, parking_type_array, furnishing_status;
    private String furnishing = "None";
    private String[] balcony_array;
    private String balcony="None";
    private String common_area="None";
    //private EditText residential_no_of_building_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_d1);
        db = new DBHandler(this, "DB", null, 1);
        //    name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

        //   residential_no_of_building_edit= (EditText) findViewById(R.id.residential_no_of_building_edit);

        serventroom = (CheckBox) findViewById(R.id.residential_ck_serventroom);
        prayer = (CheckBox) findViewById(R.id.residential_ck_prayerroom);
        //   taccess = (CheckBox) findViewById(R.id.residential_ck_tarraceaccess);
        paccess = (CheckBox) findViewById(R.id.residential_ck_privateaccess);
        wifi = (CheckBox) findViewById(R.id.residential_ck_wifi);
        solar = (CheckBox) findViewById(R.id.residential_cksolar_water_heater);
        residential_rb_water_municipal_corp = (CheckBox) findViewById(R.id.residential_rb_water_municipal_corp);
        residential_rb_water_borewell = (CheckBox) findViewById(R.id.residential_rb_water_borewell);
        waterbackup_grounded_tanks = (CheckBox) findViewById(R.id.tv_waterbackup_grounded_tanks);
        waterbackup_terrace_tanks = (CheckBox) findViewById(R.id.tv_waterbackup_terrace_tanks);

        power_partial = (RadioButton) findViewById(R.id.residential_rb_partial_power);
        power_full = (RadioButton) findViewById(R.id.residential_rb_full_poersupply);
        power_nobackup = (RadioButton) findViewById(R.id.residential_rb_power_no_backup);

        //  no_of_storeys = (Spinner) findViewById(R.id.residentiel_spiner_storey);
        main_enterance_facing = (Spinner) findViewById(R.id.residentiel_spiner_enterance);
        residentiel_spiner_parking_type = (Spinner) findViewById(R.id.residentiel_spiner_parking_type);
        residentiel_spiner_furnishing_status = (Spinner) findViewById(R.id.residentiel_spiner_furnishing_status);
        residentiel_spiner_balcony = (Spinner) findViewById(R.id.residentiel_spiner_balcony);
        residentiel_spiner_common_area = (Spinner) findViewById(R.id.residentiel_spiner_common_area);


        Resources r = this.getResources();
        // no_of_storey = r.getStringArray(R.array.no_of_storey);
        mainenterancefacing1 = r.getStringArray(R.array.enterance);
        parking_type_array = r.getStringArray(R.array.parking_type);
        furnishing_status = r.getStringArray(R.array.furnishing_status);
        balcony_array = r.getStringArray(R.array.balcony);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         ArrayAdapter adapterBusinessType = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_checked,parking_type_array);
        residentiel_spiner_parking_type.setAdapter(adapterBusinessType);

     /*   no_of_storeys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeys = no_of_storey[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        residentiel_spiner_balcony.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                balcony = balcony_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        residentiel_spiner_common_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                common_area = balcony_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        main_enterance_facing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                enterance = mainenterancefacing1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        residentiel_spiner_parking_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parking_type = parking_type_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        residentiel_spiner_furnishing_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                furnishing = furnishing_status[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void onResume() {
        super.onResume();

        Bundle b = db.getResidential();

        //    residential_no_of_building_edit.setText(b.getString("no_of_building"));


        if (b.getString("residential_power_backup") == null) ;
        else if ((b.getString("residential_power_backup")).equalsIgnoreCase("Partial")) {
            power_partial.setChecked(true);
        } else if ((b.getString("residential_power_backup")).equalsIgnoreCase("Full")) {
            power_full.setChecked(true);
        }



     /*   String s = b.getString("residential_no_of_storeys");

        if (s == null) ;
        else {
            int i = 0;
            for (; i < no_of_storey.length; i++) {
                if (no_of_storey[i].equalsIgnoreCase(s)) {
                    no_of_storeys.setSelection(i);
                    break;
                }
            }

        }*/

        String s1 = b.getString("residential_main_enterance_facing");
        if (s1 == null) ;
        else {
            int j = 0;
            for (j = 0; j < mainenterancefacing1.length; j++) {
                if (mainenterancefacing1[j].equalsIgnoreCase(s1)) {
                    main_enterance_facing.setSelection(j);
                    break;
                }
            }

        }

        String s2 = b.getString("residentiel_spiner_parking_type");
        if (s2 == null) ;
        else {
            int j = 0;
            for (j = 0; j < parking_type_array.length; j++) {
                if (parking_type_array[j].equalsIgnoreCase(s2)) {
                    residentiel_spiner_parking_type.setSelection(j);
                    break;
                }
            }

        }

        String s3 = b.getString("residentiel_spiner_furnishing_status");
        if (s3 == null) ;
        else {
            int j = 0;
            for (j = 0; j < furnishing_status.length; j++) {
                if (furnishing_status[j].equalsIgnoreCase(s3)) {
                    residentiel_spiner_furnishing_status.setSelection(j);
                    break;
                }
            }

        }

        if (b.getString("water_supply_municipal") == null) ;
        else if ((b.getString("water_supply_municipal")).equalsIgnoreCase("Y"))
            residential_rb_water_municipal_corp.setChecked(true);

        if (b.getString("water_supply_borewell") == null) ;
        else if ((b.getString("water_supply_borewell")).equalsIgnoreCase("Y"))
            residential_rb_water_borewell.setChecked(true);

        if (b.getString("residential_servent_room") == null) ;
        else if ((b.getString("residential_servent_room")).equalsIgnoreCase("Y"))
            serventroom.setChecked(true);

        if (b.getString("residential_prayersroom") == null) ;
        else if ((b.getString("residential_prayersroom")).equalsIgnoreCase("Y"))
            prayer.setChecked(true);


       /* if (b.getString("residential_terrace_access") == null) ;
        else if ((b.getString("residential_terrace_access")).equalsIgnoreCase("Y"))
            taccess.setChecked(true);*/

        if (b.getString("residential_private_access") == null) ;
        else if ((b.getString("residential_private_access")).equalsIgnoreCase("Y"))
            paccess.setChecked(true);


        if (b.getString("residential_wifi_internet") == null) ;
        else if ((b.getString("residential_wifi_internet")).equalsIgnoreCase("Y"))
            wifi.setChecked(true);

        if (b.getString("residential_solar_water_heater") == null) ;
        else if ((b.getString("residential_solar_water_heater")).equalsIgnoreCase("Y"))
            solar.setChecked(true);


        if (b.getString("waterbackup_grounded_tanks") == null) ;
        else if ((b.getString("waterbackup_grounded_tanks")).equalsIgnoreCase("Y"))
            waterbackup_grounded_tanks.setChecked(true);

        if (b.getString("waterbackup_terrace_tanks") == null) ;
        else if ((b.getString("waterbackup_terrace_tanks")).equalsIgnoreCase("Y"))
            waterbackup_terrace_tanks.setChecked(true);

        String string_balcony = b.getString("balcony");
        if (string_balcony == null) ;
        else {
            int j = 0;
            for (j = 0; j < balcony_array.length; j++) {
                if (balcony_array[j].equalsIgnoreCase(string_balcony)) {
                    residentiel_spiner_balcony.setSelection(j);
                    break;
                }
            }

        }

        String common_area = b.getString("common_area");
        if (common_area == null) ;
        else {
            int j = 0;
            for (j = 0; j < balcony_array.length; j++) {
                if (balcony_array[j].equalsIgnoreCase(common_area)) {
                    residentiel_spiner_common_area.setSelection(j);
                    break;
                }
            }

        }



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
            item.setEnabled(false);
            saveResidential();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, SocietyData1.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


    private void saveResidential() {
        String powersupply, no_of_building;
        // no_of_building=residential_no_of_building_edit.getText().toString();

        String serventroom1 = (serventroom.isChecked() ? "Y" : "N");
        String prayerroom = (prayer.isChecked() ? "Y" : "N");
        //    String terraceaccess = (taccess.isChecked() ? "Y" : "N");
        String privateaccess = (paccess.isChecked() ? "Y" : "N");
        String wifi_internet = (wifi.isChecked() ? "Y" : "N");
        String solarwater_heater1 = (solar.isChecked() ? "Y" : "N");
        String terrace_tanks = (waterbackup_terrace_tanks.isChecked() ? "Y" : "N");
        String grounded_tanks = (waterbackup_grounded_tanks.isChecked() ? "Y" : "N");
        String water_supply_municipal = (residential_rb_water_municipal_corp.isChecked() ? "Y" : "N");
        String water_supply_borewell = (residential_rb_water_borewell.isChecked() ? "Y" : "N");
        if (power_partial.isChecked()) {
            powersupply = "Partial";
        } else if (power_full.isChecked()) {
            powersupply = "Full";
        } else powersupply = "No backup";


        db.setResidential(/*no_of_building,storeys, */serventroom1, prayerroom, /*terraceaccess,*/ privateaccess,
                enterance, powersupply, water_supply_municipal, water_supply_borewell, grounded_tanks, terrace_tanks, wifi_internet,
                solarwater_heater1, parking_type, furnishing,balcony,common_area, "true"
        );
        /*ContentValues cv= new ContentValues();
        cv.put("update_from_server","true");
        db.setUpdateFromServerStatus(cv, Appointment.clicked);*/
    }


    public void myClick(View v) {
        v.setEnabled(false);
        saveResidential();
        startActivity(new Intent(this, SocietyData1.class));
        finish();
    }

}
