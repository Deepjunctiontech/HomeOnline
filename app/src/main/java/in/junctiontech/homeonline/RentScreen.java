package in.junctiontech.homeonline;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class RentScreen extends AppCompatActivity {

    private Spinner rentscreen_spinner_lease_type;
    private String[] lease_type_array;
    private String lease_type = "No Restriction";;
    private CheckBox rent,security,deposite;
    private EditText brokeragefee_edit,maintanance_edit;
    private DBHandler db;
    private RadioButton pets_no,pets_yes,food_veg,food_nonveg,food_nopreference;
    private Calendar calendar;
    private int year,month,day;
    private Button rentscreen_et_possesion_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_screen);
        db = new DBHandler(this, "DB", null, 1);
        //    name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rent = (CheckBox) findViewById(R.id.rentscreen_ck_rent);
        security = (CheckBox) findViewById(R.id.rentscreen_ck_security);
        deposite = (CheckBox) findViewById(R.id.rentscreen_ck_deposite);

        brokeragefee_edit = (EditText) findViewById(R.id.rentscreen_brokerage_edit);
        maintanance_edit = (EditText) findViewById(R.id.rentscreen_maintanance_edit);

        pets_no = (RadioButton) findViewById(R.id.rentscreen_rb_pets_allowed_no);
        pets_yes = (RadioButton) findViewById(R.id.rentscreen_rb_pets_allowed_yes);
        food_veg = (RadioButton) findViewById(R.id.rentscreen_food_veg);
        food_nonveg = (RadioButton) findViewById(R.id.rentscreen_food_nonveg);
        food_nopreference = (RadioButton) findViewById(R.id.rentscreen_food_no_preference);



        rentscreen_spinner_lease_type = (Spinner) findViewById(R.id.rentscreen_spinner_lease_type);
        Resources r = this.getResources();
        lease_type_array = r.getStringArray(R.array.lease_type);

        rentscreen_spinner_lease_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lease_type = lease_type_array[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rentscreen_et_possesion_date = (Button) findViewById(R.id.rentscreen_et_possesion_date);
        String curr=day+"/"+(month+1)+"/"+year+"";
        rentscreen_et_possesion_date.setText(curr);


    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle b = db.getRentScreen();

        if(b.getString("availability_date")==null);
        else
           rentscreen_et_possesion_date.setText(b.getString("availability_date"));

        String s3=b.getString("lease_type");

        if(s3==null);
        else {
            int i=0;
            for(;i<lease_type_array.length;i++){
                if(lease_type_array[i].equalsIgnoreCase(s3)) {
                    rentscreen_spinner_lease_type.setSelection(i);
                    break;
                }
            }


        }


        brokeragefee_edit.setText(b.getString("brokerage_fee"));
        maintanance_edit.setText(b.getString("maintainance"));


        if (b.getString("food") == null) ;
        else if ((b.getString("food")).equalsIgnoreCase("Veg")) {
            food_veg.setChecked(true);
        } else if ((b.getString("food")).equalsIgnoreCase("Non Veg")) {
            food_nonveg.setChecked(true);
        }
        else
            food_nopreference.setChecked(true);

        if (b.getString("pets_allowed") == null) ;
        else if ((b.getString("pets_allowed")).equalsIgnoreCase("Yes")) {
            pets_yes.setChecked(true);
        } else if ((b.getString("pets_allowed")).equalsIgnoreCase("No"))
            pets_no.setChecked(true);

        if (b.getString("rent_negotiable") == null) ;
        else if ((b.getString("rent_negotiable")).equalsIgnoreCase("Y"))
            rent.setChecked(true);

        if (b.getString("security_negotiable") == null) ;
        else if ((b.getString("security_negotiable")).equalsIgnoreCase("Y"))
            security.setChecked(true);

        if (b.getString("security_deposit") == null) ;
        else if ((b.getString("security_deposit")).equalsIgnoreCase("Y"))
            deposite.setChecked(true);



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
            saveRentScreen();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Pricing1.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    private void saveRentScreen() {
        String availability_date =rentscreen_et_possesion_date.getText().toString();
        String maintanancefee, brokeragefee,food,pet;
        brokeragefee = brokeragefee_edit.getText().toString();
        maintanancefee = maintanance_edit.getText().toString();


        String rent_ = (rent.isChecked() ? "Y" : "N");
        String security_ = (security.isChecked() ? "Y" : "N");
        String deposite_ = (deposite.isChecked() ? "Y" : "N");

        if (food_veg.isChecked()) {
            food = "Veg";
        } else if (food_nonveg.isChecked()) {
            food="Non Veg";
        }
        else
            food="No Preference";


        if (pets_no.isChecked()) {
            pet = "No";
        } else
            pet="Yes";

        db.setRentScreen(brokeragefee, maintanancefee, food, lease_type, pet, rent_, security_, deposite_,availability_date, "true");
       /* ContentValues cv= new ContentValues();
        cv.put("update_from_server","true");
        db.setUpdateFromServerStatus(cv, Appointment.clicked);*/
    }

    public void myClick(View v) {
        v.setEnabled(false);
        saveRentScreen();
        startActivity(new Intent(this, Pricing1.class));
        finish();
    }

    public void selectAvailabilityDate(View v)
    {
        showDialog(999);
        Toast.makeText(this,"Date Selection",Toast.LENGTH_LONG).show();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            rentscreen_et_possesion_date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

        }
    };

}
