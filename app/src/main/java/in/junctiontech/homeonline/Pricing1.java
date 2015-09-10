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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Pricing1 extends AppCompatActivity {
    private CheckBox rent, security, deposite, terrace, terracegarden;

    private RadioButton garden1, garden2, garden3, garden4, garden5, manual, automatic;
    private Spinner pricing_spinner_garden,pricing_spinner_lifts;
    private RelativeLayout rl;
    private TextInputLayout build_text, carpetarea_text, rentammount_text, brokeragefee_text, maintanance_text, no_of_floor_text, buildingage_text;
    private EditText build_edit, carpetarea_edit, rentamount_edit, brokeragefee_edit, maintanance_edit, no_of_floors_edit, buildingage_edit;
    private String no_of_garden = "0";
    private String built_up_area, carpet_area, rent_ammount, brokarege_fee, maintainance, no_of_floors, age_of_building,no_of_lifts;
    private DBHandler db;
    private TextView name;
    private String[] no_of_lifts_array;
    private String[] no_of_garden_array;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing1);
        db = new DBHandler(this, "DB", null, 1);
        name=(TextView)findViewById(R.id.tv_pricing);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));
//        rl = (RelativeLayout) findViewById(R.id.pricing);
        rent = (CheckBox) findViewById(R.id.pricing1_ck_rent);
        security = (CheckBox) findViewById(R.id.pricing1_ck_security);
        deposite = (CheckBox) findViewById(R.id.pricing1_ck_deposite);
        terrace = (CheckBox) findViewById(R.id.pricing1_ck_terrace);
        terracegarden = (CheckBox) findViewById(R.id.pricing1_ck_garden);
        pricing_spinner_garden = (Spinner) findViewById(R.id.pricing_spiner_garden);


        manual = (RadioButton) findViewById(R.id.pricing1_rb_manual);
        automatic = (RadioButton) findViewById(R.id.pricing1_rb_automatic);

        build_text = (TextInputLayout) findViewById(R.id.pricing1_build_text);
        carpetarea_text = (TextInputLayout) findViewById(R.id.pricing1_capet_text);
        rentammount_text = (TextInputLayout) findViewById(R.id.pricing1_rent_text);
        brokeragefee_text = (TextInputLayout) findViewById(R.id.pricing1_Brokerage_text);
        maintanance_text = (TextInputLayout) findViewById(R.id.pricing1_Maintanance_text);
        no_of_floor_text = (TextInputLayout) findViewById(R.id.pricing1_floors_text);
        buildingage_text = (TextInputLayout) findViewById(R.id.pricing1_age_text);

        build_edit = (EditText) findViewById(R.id.pricing1_build_edit);
        carpetarea_edit = (EditText) findViewById(R.id.pricing1_carpet_edit);
        rentamount_edit = (EditText) findViewById(R.id.pricing1_rent_edit);
        brokeragefee_edit = (EditText) findViewById(R.id.pricing1_Brokerage_edit);
        maintanance_edit = (EditText) findViewById(R.id.pricing1_Maintanance_edit);
        no_of_floors_edit = (EditText) findViewById(R.id.pricing1_floors_edit);
        buildingage_edit = (EditText) findViewById(R.id.pricing1_age_edit);

        pricing_spinner_lifts=(Spinner)findViewById(R.id.pricing_spinner_lifts);

        Resources r=this.getResources();
       no_of_lifts_array= r.getStringArray(R.array.no_of_lift);
        pricing_spinner_lifts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_lifts = no_of_lifts_array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        no_of_garden_array=r.getStringArray(R.array.garden);
        pricing_spinner_garden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_garden = no_of_garden_array[position];
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

    public void onResume()
    {
      super.onResume();
//
        Bundle b=db.getPricing();

      build_edit.setText(b.getString("builtup_area"));
       carpetarea_edit.setText(b.getString("carpet_area"));
       rentamount_edit.setText(b.getString("rent_ammount"));
        brokeragefee_edit.setText(b.getString("brokerage_fee"));
        maintanance_edit.setText(b.getString("maintainance"));
        no_of_floors_edit.setText(b.getString("no_of_floors"));
        buildingage_edit.setText(b.getString("age_of_building"));
        if(b.getString("terrace")==null);
        else if((b.getString("terrace")).equalsIgnoreCase("Y"))
            terrace.setChecked(true);

        if(b.getString("terrace_garden")==null);
        else if((b.getString("terrace_garden")).equalsIgnoreCase("Y"))
            terracegarden.setChecked(true);

        if(b.getString("rent_negotiable")==null);
        else if((b.getString("rent_negotiable")).equalsIgnoreCase("Y"))
            rent.setChecked(true);

        if(b.getString("security_negotiable")==null);
        else if((b.getString("security_negotiable")).equalsIgnoreCase("Y"))
            security.setChecked(true);

        if(b.getString("security_deposit")==null);
        else if((b.getString("security_deposit")).equalsIgnoreCase("Y"))
            deposite.setChecked(true);

        if(b.getString("lift_type")==null);
        else if((b.getString("lift_type")).equalsIgnoreCase("Manual"))
            manual.setChecked(true);


        String temp=b.getString("no_of_garden");
        if(temp==null);
        else {
            int i=0;
        for (; no_of_garden_array.length >i; i++) {

            if (no_of_garden_array[i].equalsIgnoreCase(temp))
                break;
        }
            pricing_spinner_garden.setSelection(i);
    }

        String temp1=b.getString("no_of_lift");
        if(temp1==null);
        else {
            int i=0;
            for (; no_of_lifts_array.length >i; i++) {

                if (no_of_lifts_array[i].equalsIgnoreCase(temp1))
                    break;
            }
            pricing_spinner_lifts.setSelection(i);
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

       if(id==R.id.action_my_next){
           savePricing();
            Toast.makeText(this,"NEXT",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,ResidentialD1.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void savePricing() {

        built_up_area = build_edit.getText().toString();
        carpet_area = carpetarea_edit.getText().toString();
        rent_ammount = rentamount_edit.getText().toString();
        brokarege_fee = brokeragefee_edit.getText().toString();
        maintainance = maintanance_edit.getText().toString();
        no_of_floors = no_of_floors_edit.getText().toString();
        age_of_building = buildingage_edit.getText().toString();

        String lifttype = (manual.isChecked() ? "Manual" : "Automatic");
        String terrace_pricing = (terrace.isChecked() ? "Y" : "N");
        String terrace_garden = (terracegarden.isChecked() ? "Y" : "N");
        String rent_nego = (rent.isChecked() ? "Y" : "N");
        String security_negotiable = (security.isChecked() ? "Y" : "N");
        String security_deposite = (deposite.isChecked() ? "Y" : "N");

        db.setPricing(built_up_area, carpet_area, rent_ammount, brokarege_fee,
                maintainance, no_of_floors, age_of_building, lifttype, terrace_pricing, terrace_garden,
                rent_nego, security_negotiable, security_deposite, no_of_garden,no_of_lifts,"true");


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

    }


    public boolean isBuiltUpArea() {
        return built_up_area == null || built_up_area.equals("") || built_up_area.isEmpty();
    }

    public boolean isCarpetArea() {
        return carpet_area == null || carpet_area.equals("") || carpet_area.isEmpty();
    }


    public boolean isRentAmmount() {
        return rent_ammount == null || rent_ammount.equals("") || rent_ammount.isEmpty();
    }

    public boolean isBrokageFee() {
        return brokarege_fee == null || brokarege_fee.equals("") || brokarege_fee.isEmpty();
    }

    public boolean isMaintainance() {
        return maintainance == null || maintainance.equals("") || maintainance.isEmpty();

    }

    public boolean isNoOfFloors() {
        return no_of_floors == null || no_of_floors.toString().equals("") || no_of_floors.isEmpty();
    }

    public boolean isAgeOfBuilding() {
        return age_of_building == null || age_of_building == null
                || age_of_building.equals("") || age_of_building.isEmpty();
    }

    public void myClick(View v) {
        savePricing();
        startActivity(new Intent(this, ResidentialD1.class));
        finish();
    }

}
