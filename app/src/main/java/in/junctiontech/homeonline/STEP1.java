package in.junctiontech.homeonline;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class STEP1 extends AppCompatActivity {

    private EditText step1_et_name_edit, step1_et_phone_edit, step1_et_mobile_edit, step1_et_email_edit;
    private TextInputLayout step1_et_name_text, step1_et_phone_text, step1_et_mobile_text, step1_et_email_text;

    private DBHandler db;

    private Button blablabla;

    private String property_array[];
    private TextView name;
    private EditText step1_et_locality_edit;
    private EditText step1_et_sublocality_edit;
    private EditText step1_et_pincode_edit;
    private EditText step1_et_landmark_edit;
    private EditText step1_et_building_no_edit;
    private EditText step1_et_flate_no_edit;
    private EditText step1_et_building_name_edit;
    private EditText step1_et_wing_edit;
    private EditText step1_et_street_edit;
    private TextView step1_et_possesion_date_edit;
    private Calendar calendar;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        db = new DBHandler(this, "DB", null, 1);

        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       /* blablabla= (Button) findViewById(R.id.blablabla);


        blablabla.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    blablabla.setBackgroundColor(Color.RED);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    blablabla.setBackgroundColor(Color.BLUE);
                }
                return false;
            }

        });*/

        name = (TextView) findViewById(R.id.tv_basicDetail);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));

        step1_et_name_edit = (EditText) findViewById(R.id.step1_et_name_edit);
        step1_et_phone_edit = (EditText) findViewById(R.id.step1_et_phone_edit);
        step1_et_mobile_edit = (EditText) findViewById(R.id.step1_et_mobile_edit);
        step1_et_email_edit = (EditText) findViewById(R.id.step1_et_email_edit);
        step1_et_locality_edit = (EditText) findViewById(R.id.step1_et_locality_edit);
        step1_et_sublocality_edit = (EditText) findViewById(R.id.step1_et_sublocality_edit);
        step1_et_pincode_edit = (EditText) findViewById(R.id.step1_et_pincode_edit);
        step1_et_landmark_edit = (EditText) findViewById(R.id.step1_et_landmark_edit);
        step1_et_building_no_edit = (EditText) findViewById(R.id.step1_et_building_no_edit);
        step1_et_building_name_edit = (EditText) findViewById(R.id.step1_et_building_name_edit);
        step1_et_flate_no_edit = (EditText) findViewById(R.id.step1_et_flate_no_edit);
        step1_et_wing_edit = (EditText) findViewById(R.id.step1_et_wing_edit);
        step1_et_street_edit = (EditText) findViewById(R.id.step1_et_street_edit);
        step1_et_possesion_date_edit = (TextView) findViewById(R.id.step1_et_possesion_date_edit);


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


        if (id == R.id.action_my_next) {
            saveBasicDetail();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AdvertiserDetail.class));
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBasicDetail();
    }


    private void saveBasicDetail() {
        String name, phone, email, moblile, building_no;


        name = step1_et_name_edit.getText().toString();
        phone = step1_et_phone_edit.getText().toString();
        email = step1_et_email_edit.getText().toString();
        moblile = step1_et_mobile_edit.getText().toString();
        building_no = step1_et_building_no_edit.getText().toString();
        String building_name = step1_et_building_name_edit.getText().toString();
        String flate_number = step1_et_flate_no_edit.getText().toString();
        String wing = step1_et_wing_edit.getText().toString();
        String street = step1_et_street_edit.getText().toString();
        String locality = step1_et_locality_edit.getText().toString();
        String sub_locality = step1_et_sublocality_edit.getText().toString();
        String pincode = step1_et_pincode_edit.getText().toString();
        String landmark = step1_et_landmark_edit.getText().toString();
        String possesion_date = step1_et_possesion_date_edit.getText().toString();


        db.setBasicDetail(name, phone, email, moblile, building_no, building_name,
                flate_number, wing, street, locality, sub_locality, pincode, landmark,
                possesion_date, "true");


    }


    public void myClick(View v) {
        saveBasicDetail();
        startActivity(new Intent(this, AdvertiserDetail.class));
        finish();
    }

    public void getBasicDetail() {

        Bundle b = db.getBasicDetail();

        step1_et_name_edit.setText(b.getString("name"));
        step1_et_phone_edit.setText(b.getString("phone"));
        step1_et_email_edit.setText(b.getString("email"));
        step1_et_mobile_edit.setText(b.getString("mobile"));
        step1_et_building_no_edit.setText(b.getString("building_no"));
        step1_et_building_name_edit.setText(b.getString("building_name"));
        step1_et_flate_no_edit.setText(b.getString("flate_number"));
        step1_et_wing_edit.setText(b.getString("wing"));
        step1_et_street_edit.setText(b.getString("street"));
        step1_et_locality_edit.setText(b.getString("locality"));
        step1_et_sublocality_edit.setText(b.getString("sub_locality"));
        step1_et_pincode_edit.setText(b.getString("pincode"));
        step1_et_landmark_edit.setText(b.getString("landmark"));
        step1_et_possesion_date_edit.setText(b.getString("possesion_date"));


    }

    public void selectPossessionDate(View v)
    {
        showDialog(999);
        Toast.makeText(this,"Date",Toast.LENGTH_LONG).show();
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
            step1_et_possesion_date_edit.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

        }
    };

}
