package in.junctiontech.homeonline;

import android.content.ContentValues;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Pricing1 extends AppCompatActivity {

    private Spinner pricing_spinner_lifts, pricing_spinner_salestatus,pricing_spinner_units;
    private EditText build_edit, carpetarea_edit, rentamount_edit, no_of_floors_edit, buildingage_edit, pricing1_plot_edit;
    private String no_of_lifts, salestatus,units;
    private DBHandler db;
    private String[] no_of_lifts_array, sale_status,units_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing1);
        db = new DBHandler(this, "DB", null, 1);
        //    name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

        pricing_spinner_salestatus = (Spinner) findViewById(R.id.pricing_spinner_salestatus);
        pricing_spinner_lifts = (Spinner) findViewById(R.id.pricing_spinner_lifts);
        pricing_spinner_units=(Spinner) findViewById(R.id.pricing_spinner_units);

        build_edit = (EditText) findViewById(R.id.pricing1_build_edit);
        carpetarea_edit = (EditText) findViewById(R.id.pricing1_carpet_edit);
        rentamount_edit = (EditText) findViewById(R.id.pricing1_rent_edit);
        no_of_floors_edit = (EditText) findViewById(R.id.pricing1_floors_edit);
        buildingage_edit = (EditText) findViewById(R.id.pricing1_age_edit);
        pricing1_plot_edit = (EditText) findViewById(R.id.pricing1_plot_edit);


        Resources r = this.getResources();
        no_of_lifts_array = r.getStringArray(R.array.no_of_lift);
        pricing_spinner_lifts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_lifts = no_of_lifts_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sale_status = r.getStringArray(R.array.sale_status);
        pricing_spinner_salestatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                salestatus = sale_status[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        units_array = r.getStringArray(R.array.units);
        pricing_spinner_units.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                units = units_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }

    public void onResume() {
        super.onResume();

        Bundle b = db.getPricing();

        build_edit.setText(b.getString("builtup_area"));
        carpetarea_edit.setText(b.getString("carpet_area"));
        rentamount_edit.setText(b.getString("rent_ammount"));
        no_of_floors_edit.setText(b.getString("no_of_floors"));
        buildingage_edit.setText(b.getString("age_of_building"));
        pricing1_plot_edit.setText(b.getString("plot_area"));
        String temp1 = b.getString("no_of_lift");
        if (temp1 == null) ;
        else {
            int i = 0;
            for (; no_of_lifts_array.length > i; i++) {

                if (no_of_lifts_array[i].equalsIgnoreCase(temp1)) {
                    pricing_spinner_lifts.setSelection(i);
                    break;
                }
            }

        }

        String sale_status_temp = b.getString("sale_status");
        if (sale_status_temp == null) ;
        else {
            int i = 0;
            for (; sale_status.length > i; i++) {

                if (sale_status[i].equalsIgnoreCase(sale_status_temp)) {
                    pricing_spinner_salestatus.setSelection(i);
                    break;
                }
            }

        }

        String unittemp = b.getString("units");
        if (unittemp == null) ;
        else {
            int i = 0;
            for (; units_array.length > i; i++) {

                if (units_array[i].equalsIgnoreCase(unittemp)) {
                    pricing_spinner_units.setSelection(i);
                    break;
                }
            }

        }
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

        if (id == R.id.action_my_next) {
            item.setEnabled(false);
            savePricing();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ResidentialD1.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void savePricing() {
        String built_up_area, carpet_area, rent_ammount, no_of_floors, age_of_building, plot_area;

        built_up_area = build_edit.getText().toString();
        carpet_area = carpetarea_edit.getText().toString();
        rent_ammount = rentamount_edit.getText().toString();
        plot_area = pricing1_plot_edit.getText().toString();
        no_of_floors = no_of_floors_edit.getText().toString();
        age_of_building = buildingage_edit.getText().toString();

        db.setPricing(built_up_area, carpet_area, rent_ammount, no_of_floors, age_of_building,
                no_of_lifts, plot_area, salestatus, units,"true");
        ContentValues cv= new ContentValues();
        cv.put("update_from_server","true");
        db.setUpdateFromServerStatus(cv, Appointment.clicked);
    }



    public void myClick(View v) {
        v.setEnabled(false);
        savePricing();
        startActivity(new Intent(this, ResidentialD1.class));
        finish();
    }

}


//  Toast.makeText(this, lifttype, Toast.LENGTH_LONG).show();

//        boolean b1 = isBuiltUpArea();
//        boolean b2 = isCarpetArea();
//        boolean b3 = isRentAmmount();
//        boolean b4 = isBrokageFee();
//        boolean b5 = isMaintainance();
//        boolean b6 = isNoOfFloors();
//        boolean b7 = isAgeOfBuilding();
//
//        if (b1 || b2 || b3 || b4 || b5 || b6 || b7) {
//            Snackbar.make(rl, "One Or More Field Are Blank", Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                     Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();
//                }
//            }).show();
//            build_text.setError(null);
//            carpetarea_text.setError(null);
//            rentammount_text.setError(null);
//            brokeragefee_text.setError(null);
//            maintanance_text.setError(null);
//            no_of_floor_text.setError(null);
//            buildingage_text.setError(null);
//        }
//        else
//        {
//        Toast.makeText(this, built_up_area + "\n" + carpet_area + "\n" +
//                rent_ammount + "\n" + brokarege_fee + "\n" +
//                maintainance + "\n" + no_of_floors + "\n" +
//                age_of_building + "\n" +
//                rent_nego + "\n" +
//                security_negotiable + "\n" +
//                security_deposite + "\n" +
//                terrace_pricing + "\n" + terrace_garden + "\n"
//                + lifttype + "\n" + no_of_garden, Toast.LENGTH_LONG).show();


//        }



/*
    public boolean isBuiltUpArea() {
        return built_up_area == null || built_up_area.equals("") || built_up_area.isEmpty();
    }

    public boolean isCarpetArea() {
        return carpet_area == null || carpet_area.equals("") || carpet_area.isEmpty();
    }


    public boolean isRentAmmount() {
        return rent_ammount == null || rent_ammount.equals("") || rent_ammount.isEmpty();
    }


    public boolean isNoOfFloors() {
        return no_of_floors == null || no_of_floors.toString().equals("") || no_of_floors.isEmpty();
    }

    public boolean isAgeOfBuilding() {
        return age_of_building == null || age_of_building == null
                || age_of_building.equals("") || age_of_building.isEmpty();
    }*/
