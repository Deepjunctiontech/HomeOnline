package in.junctiontech.homeonline;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ResidentialD1 extends AppCompatActivity {private CheckBox serventroom,prayer,balcony,taccess,paccess,wifi,solar;
    private CheckBox insideparking,outsideparking,parking_car,parking_two_wheeler,
            parking_na,parkingtype_basement,parkingtype_covered,parkingtype_na;
    private Spinner no_of_storeys,main_enterance_facing;
    private String storeys="1",enterance="N";
    private DBHandler db;
    private RadioButton power_partial,power_full,power_nobackup;
    private RadioButton water_partial,water_full,water_nobackup;
    private TextInputLayout residential_unit_text;
    private EditText residential_unit_edit;
    private RadioButton residential_rb_water_municipal_corp,residential_rb_water_borewell,residential_rb_water_both;
    private RadioButton pets_allowed_yes,pets_allowed_no;
    private CheckBox residential_parkingtype_open;
    private CheckBox residential_parkingtype_individual_floor;
    private CheckBox residential_parkingtype_street_parking;
    private String[] no_of_storey;
    private String[] mainenterancefacing1;
    private RadioButton property_food_veg, property_food_alltype, property_ready_to_move_yes, property_ready_to_move_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_d1);
        db = new DBHandler(this, "DB", null, 1);
        TextView name = (TextView) findViewById(R.id.tv_residential);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));
        serventroom = (CheckBox) findViewById(R.id.residential_ck_serventroom);
        prayer = (CheckBox) findViewById(R.id.residential_ck_prayerroom);
        balcony = (CheckBox) findViewById(R.id.residential_ck_balcony);
        taccess = (CheckBox) findViewById(R.id.residential_ck_tarraceaccess);
        paccess = (CheckBox) findViewById(R.id.residential_ck_privateaccess);
        wifi = (CheckBox) findViewById(R.id.residential_ck_wifi);
        solar = (CheckBox) findViewById(R.id.residential_cksolar_water_heater);
        insideparking = (CheckBox) findViewById(R.id.residential_inside_parking);
        outsideparking = (CheckBox) findViewById(R.id.residential_outside_parking);
        parking_car = (CheckBox) findViewById(R.id.residential_parking_car);
        parking_two_wheeler = (CheckBox) findViewById(R.id.residential_parking_twe_wheeler);
        parking_na = (CheckBox) findViewById(R.id.residential_parking_na);
        parkingtype_covered = (CheckBox) findViewById(R.id.residential_parkingtype_covered);
        parkingtype_basement = (CheckBox) findViewById(R.id.residential_parkingtype_basement);
//        parkingtype_na = (CheckBox) findViewById(R.id.residential_parkingtype_na);

        residential_parkingtype_open = (CheckBox) findViewById(R.id.residential_parkingtype_open);
        residential_parkingtype_individual_floor = (CheckBox) findViewById(R.id.residential_parkingtype_individual_floor);
        residential_parkingtype_street_parking = (CheckBox) findViewById(R.id.residential_parkingtype_street_parking);

        no_of_storeys=(Spinner)findViewById(R.id.residentiel_spiner_storey);
        main_enterance_facing=(Spinner)findViewById(R.id.residentiel_spiner_enterance);

        pets_allowed_yes = (RadioButton) findViewById(R.id.residential_rb_pets_allowed_yes);
        pets_allowed_no = (RadioButton) findViewById(R.id.residential_rb_pets_allowed_no);


        power_partial = (RadioButton) findViewById(R.id.residential_rb_partial_power);
        power_full = (RadioButton) findViewById(R.id.residential_rb_full_poersupply);
        power_nobackup = (RadioButton) findViewById(R.id.residential_rb_power_no_backup);

        residential_rb_water_municipal_corp = (RadioButton) findViewById(R.id.residential_rb_water_municipal_corp);
        residential_rb_water_borewell = (RadioButton) findViewById(R.id.residential_rb_water_borewell);
        residential_rb_water_both = (RadioButton) findViewById(R.id.residential_rb_water_both);

        residential_unit_text = (TextInputLayout) findViewById(R.id.residential_residential_text);

        residential_unit_edit = (EditText) findViewById(R.id.residential_residential_edit);

        property_food_veg = (RadioButton) findViewById(R.id.property_food_veg);
        property_food_alltype = (RadioButton) findViewById(R.id.property_food_alltype);
        property_ready_to_move_yes = (RadioButton) findViewById(R.id.property_ready_to_move_yes);
        property_ready_to_move_no = (RadioButton) findViewById(R.id.property_ready_to_move_no);

        Resources r= this.getResources();
       no_of_storey=r.getStringArray(R.array.bhk);
       mainenterancefacing1=r.getStringArray(R.array.enterance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        no_of_storeys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeys = no_of_storey[position];
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


    }


    public void onResume() {
        super.onResume();

        Bundle b = db.getResidential();


        residential_unit_edit.setText(b.getString("no_of_residential_unit"));

        if(b.getString("residential_power_backup")==null);
        else if((b.getString("residential_power_backup")).equalsIgnoreCase("Partial")) {
            power_partial.setChecked(true);
        }else if((b.getString("residential_power_backup")).equalsIgnoreCase("Full")){
            power_full.setChecked(true);
        }

        if(b.getString("residential_water_backup")==null);
        else if((b.getString("residential_water_backup")).equalsIgnoreCase("Municipal Corp")) {
            residential_rb_water_municipal_corp.setChecked(true);
        }else if((b.getString("residential_water_backup")).equalsIgnoreCase("Bore Well")) {
            residential_rb_water_borewell.setChecked(true);
        }
            else
            residential_rb_water_both.setChecked(true);



        String s=b.getString("residential_no_of_storeys");

        if(s==null);
        else {
            int i=0;
            for(;i<no_of_storey.length;i++){
                if(no_of_storey[i].equalsIgnoreCase(s))
                    break;
            }
            no_of_storeys.setSelection(i);
        }

        String s1=b.getString("residential_main_enterance_facing");
        if(s1==null);
        else {
            int j=0;
            for(j=0;j<mainenterancefacing1.length;j++){
                if(mainenterancefacing1[j].equalsIgnoreCase(s1))
                    break;
            }
            main_enterance_facing.setSelection(j);
        }

        if(b.getString("residential_servent_room")==null);
        else if((b.getString("residential_servent_room")).equalsIgnoreCase("Y"))
            serventroom.setChecked(true);

        if(b.getString("residential_prayersroom")==null);
        else if((b.getString("residential_prayersroom")).equalsIgnoreCase("Y"))
            prayer.setChecked(true);

        if(b.getString("residential_balcony")==null);
        else if((b.getString("residential_balcony")).equalsIgnoreCase("Y"))
            balcony.setChecked(true);

        if(b.getString("residential_terrace_access")==null);
        else if((b.getString("residential_terrace_access")).equalsIgnoreCase("Y"))
            taccess.setChecked(true);

        if(b.getString("residential_private_access")==null);
        else if((b.getString("residential_private_access")).equalsIgnoreCase("Y"))
            paccess.setChecked(true);

        if(b.getString("residential_inside_parking")==null);
        else if((b.getString("residential_inside_parking")).equalsIgnoreCase("Y"))
            insideparking.setChecked(true);

        if(b.getString("residential_outside_parking")==null);
        else if((b.getString("residential_outside_parking")).equalsIgnoreCase("Y"))
            outsideparking.setChecked(true);

        if(b.getString("parking_car")==null);
        else if((b.getString("parking_car")).equalsIgnoreCase("Y")) {
            parking_car.setChecked(true);
            parking_na.setChecked(false);
        }

        if(b.getString("parking_two_wheeler")==null);
        else if((b.getString("parking_two_wheeler")).equalsIgnoreCase("Y")) {
            parking_na.setChecked(false);
            parking_two_wheeler.setChecked(true);
        }

        if(b.getString("parking_na")==null);
        else if((b.getString("parking_na")).equalsIgnoreCase("Y"))
            parking_na.setChecked(true);

        if(b.getString("parking_type_basement")==null);
        else if((b.getString("parking_type_basement")).equalsIgnoreCase("Y"))
            parkingtype_basement.setChecked(true);

        if(b.getString("parking_type_covered")==null);
        else if((b.getString("parking_type_covered")).equalsIgnoreCase("Y"))
            parkingtype_covered.setChecked(true);

        if(b.getString("parkingtype_individual_open_air")==null);
        else if((b.getString("parkingtype_individual_open_air")).equalsIgnoreCase("Y"))
            residential_parkingtype_open.setChecked(true);

        if(b.getString("parkingtype_individual_floor")==null);
        else if((b.getString("parkingtype_individual_floor")).equalsIgnoreCase("Y"))
            residential_parkingtype_individual_floor.setChecked(true);

        if(b.getString("parkingtype_street_parking")==null);
        else if((b.getString("parkingtype_street_parking")).equalsIgnoreCase("Y"))
            residential_parkingtype_street_parking.setChecked(true);

        if(b.getString("residential_wifi_internet")==null);
        else if((b.getString("residential_wifi_internet")).equalsIgnoreCase("Y"))
            wifi.setChecked(true);

        if(b.getString("residential_solar_water_heater")==null);
        else if((b.getString("residential_solar_water_heater")).equalsIgnoreCase("Y"))
            solar.setChecked(true);

        if(b.getString("residential_visitor_parking_inside")==null);
        else if((b.getString("residential_visitor_parking_inside")).equalsIgnoreCase("Y"))
            insideparking.setChecked(true);

        if(b.getString("residential_visitor_parking_outside")==null);
        else if((b.getString("residential_visitor_parking_outside")).equalsIgnoreCase("Y"))
            outsideparking.setChecked(true);



        if(b.getString("pets_allowed")==null)
            pets_allowed_no.setChecked(true);
        else if((b.getString("pets_allowed")).equalsIgnoreCase("Yes"))
            pets_allowed_yes.setChecked(true);
        else pets_allowed_no.setChecked(true);

        if (b.getString("food") == null);
        else if ((b.getString("food")).equalsIgnoreCase("Veg"))
            property_food_veg.setChecked(true);
        else property_food_alltype.setChecked(true);

        if (b.getString("ready_to_move") == null);
        else if ((b.getString("ready_to_move")).equalsIgnoreCase("Y"))
            property_ready_to_move_yes.setChecked(true);
        else property_ready_to_move_no.setChecked(true);


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

       if(id==R.id.action_my_next){
           saveResidential();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,SocietyData1.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


    private void saveResidential() {
        String powersupply,watersupply;

        String serventroom1 = (serventroom.isChecked() ? "Y" : "N");
        String prayerroom = (prayer.isChecked() ? "Y" : "N");
        String balcony1 = (balcony.isChecked() ? "Y" : "N");
        String terraceaccess = (taccess.isChecked() ? "Y" : "N");
        String privateaccess = (paccess.isChecked() ? "Y" : "N");
        String inside = (insideparking.isChecked() ? "Y" : "N");
        String outside = (outsideparking.isChecked() ? "Y" : "N");
        String parking_car1 = (parking_car.isChecked() ? "Y" : "N");
        String parking_two_wheeler1 = (parking_two_wheeler.isChecked() ? "Y" : "N");
        String parking_na1 = (parking_na.isChecked() ? "Y" : "N");
        String parkingtype_basement1 = (parkingtype_basement.isChecked() ? "Y" : "N");
        String parkingtype_covered1 = (parkingtype_covered.isChecked() ? "Y" : "N");
        String parkingtype_street_parking = (residential_parkingtype_street_parking.isChecked() ? "Y" : "N");
        String parkingtype_individual_floor = (residential_parkingtype_individual_floor.isChecked() ? "Y" : "N");
        String parkingtype_individual_open_air = (residential_parkingtype_open.isChecked() ? "Y" : "N");
        String wifi_internet = (wifi.isChecked() ? "Y" : "N");
        String solarwater_heater1 = (solar.isChecked() ? "Y" : "N");
        String  pets_allowed = (pets_allowed_yes.isChecked() ? "Yes" : "No");

        String   food = (property_food_veg.isChecked() ? "Veg" : "All Type");
        String   ready_to_move = (property_ready_to_move_yes.isChecked() ? "Y" : "N");

        if(power_partial.isChecked()){
            powersupply="Partial";
        }else if(power_full.isChecked()){
            powersupply="Full";
        }else powersupply="No backup";

        if(residential_rb_water_municipal_corp.isChecked()){
            watersupply="Municipal Corp";
        }else if(residential_rb_water_borewell.isChecked()){
            watersupply="Bore Well";
        }else watersupply="Both";

        String residential_unit=residential_unit_edit.getText().toString();



        db.setResidential(storeys,serventroom1,prayerroom,balcony1,terraceaccess,privateaccess,
                enterance,inside,outside,parking_car1,parking_two_wheeler1,parking_na1,parkingtype_basement1,parkingtype_covered1,
                parkingtype_street_parking,parkingtype_individual_floor,parkingtype_individual_open_air,
                powersupply,watersupply,wifi_internet,solarwater_heater1,residential_unit,pets_allowed,food,ready_to_move
                ,"true"
        );
    }


    public void checkParking(View v){
        if(v.getId()==R.id.residential_parking_na)
        {
            parking_car.setChecked(false);
            parking_two_wheeler.setChecked(false);
        }

       else if((parking_car.isChecked()|| parking_two_wheeler.isChecked()))
           parking_na.setChecked(false);

        else if(!parking_car.isChecked()&& !parking_two_wheeler.isChecked())
            parking_na.setChecked(true);



    }

    public void myClick(View v){
        saveResidential();
        startActivity(new Intent(this,SocietyData1.class));
        finish();
    }

}
