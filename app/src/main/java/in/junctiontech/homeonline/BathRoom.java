package in.junctiontech.homeonline;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

public class BathRoom extends AppCompatActivity {
    private static String path;
    private CheckBox glasspartion, shower, bath, cabinates, window, exhaust;

    private RadioButton attached, common, geyser, gas, indian, westurn, marble, wood, ceramic, stone, latimate;
    private Spinner bathroom_spinner_floringtype;
    private DBHandler db;
    private String flooringtype = "Marble";
    private Spinner bath_spinner_total;
    private static String bathroom_id = "1";
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bath_room);
        db = new DBHandler(this, "DB", null, 1);
        //  name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

        glasspartion = (CheckBox) findViewById(R.id.bathroom_ck_glasspartition);
        shower = (CheckBox) findViewById(R.id.bathroom_ck_showercurtain);
        bath = (CheckBox) findViewById(R.id.bathroom_ck_bathtub);
        cabinates = (CheckBox) findViewById(R.id.bathroom_ck_cabinets);
        window = (CheckBox) findViewById(R.id.bathroom_ck_windows);
        exhaust = (CheckBox) findViewById(R.id.bathroom_ck_exhaustfan);


        attached = (RadioButton) findViewById(R.id.bathroom_rb_attatched);
        common = (RadioButton) findViewById(R.id.bathroom_rb_common);
        geyser = (RadioButton) findViewById(R.id.bathroom_rb_geyser);
        gas = (RadioButton) findViewById(R.id.bathroom_rb_gas);
        indian = (RadioButton) findViewById(R.id.bathroom_rb_indian);
        westurn = (RadioButton) findViewById(R.id.bathroom_rb_western);
        bathroom_spinner_floringtype = (Spinner) findViewById(R.id.bathroom_spiner_flooringtype);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bathroom_spinner_floringtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flooringtype = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bath_spinner_total = (Spinner) findViewById(R.id.bath_spinner_total);

        String check = db.getNoOfRoom("no_of_bathroom");

        if (check == null)
            check = "1";
        int c = Integer.parseInt(check);
        String[] total = new String[c];

        for (int i = 0; i < c; i++)
            total[i] = i + 1 + "";

        ArrayAdapter<String> obj = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, total);
        bath_spinner_total.setAdapter(obj);


        bath_spinner_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (status == true)
                    setbathRoom();
                status = true;

                bathroom_id = ((TextView) view).getText().toString();
                //            Toast.makeText(BedRoom.this, bedroom_id, Toast.LENGTH_SHORT).show();
                getBathRoom(bathroom_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getBathRoom(String bathroom_id) {

        Bundle b = db.getbathroom(bathroom_id);


        if (b.getString("bathroom_bath_type") == null)
            common.setChecked(true);

        else if ((b.getString("bathroom_bath_type")).equalsIgnoreCase("Attached"))
            attached.setChecked(true);
        else if ((b.getString("bathroom_bath_type")).equalsIgnoreCase("Common"))
            common.setChecked(true);


        if (b.getString("bathroom_hot_water_supply") == null)
            gas.setChecked(true);
        else if ((b.getString("bathroom_hot_water_supply")).equalsIgnoreCase("Geyser"))
            geyser.setChecked(true);
        else if ((b.getString("bathroom_hot_water_supply")).equalsIgnoreCase("Gas"))
            gas.setChecked(true);


        if (b.getString("bathroom_toilet") == null)
            westurn.setChecked(true);
        else if ((b.getString("bathroom_toilet")).equalsIgnoreCase("Indian"))
            indian.setChecked(true);
        else if ((b.getString("bathroom_toilet")).equalsIgnoreCase("Western"))
            westurn.setChecked(true);


        if (b.getString("bathroom_glass_partition") == null)
            glasspartion.setChecked(false);
        else if ((b.getString("bathroom_glass_partition")).equalsIgnoreCase("Y"))
            glasspartion.setChecked(true);
        else if ((b.getString("bathroom_glass_partition")).equalsIgnoreCase("N"))
            glasspartion.setChecked(false);

        if (b.getString("bathroom_shower_curtain") == null)
            shower.setChecked(false);
        else if ((b.getString("bathroom_shower_curtain")).equalsIgnoreCase("Y"))
            shower.setChecked(true);
        else if ((b.getString("bathroom_shower_curtain")).equalsIgnoreCase("N"))
            shower.setChecked(false);

        if (b.getString("bathroom_bath_tub") == null)
            bath.setChecked(false);
        else if ((b.getString("bathroom_bath_tub")).equalsIgnoreCase("Y"))
            bath.setChecked(true);
        else if ((b.getString("bathroom_bath_tub")).equalsIgnoreCase("N"))
            bath.setChecked(false);

        if (b.getString("bathroom_cabinets") == null)
            cabinates.setChecked(false);
        else if ((b.getString("bathroom_cabinets")).equalsIgnoreCase("Y"))
            cabinates.setChecked(true);
        else if ((b.getString("bathroom_cabinets")).equalsIgnoreCase("N"))
            cabinates.setChecked(false);

        if (b.getString("bathroom_windows") == null)
            window.setChecked(false);
        else if ((b.getString("bathroom_windows")).equalsIgnoreCase("Y"))
            window.setChecked(true);
        else if ((b.getString("bathroom_windows")).equalsIgnoreCase("N"))
            window.setChecked(false);

        if (b.getString("bathromm_exhaust_fan") == null)
            exhaust.setChecked(false);
        else if ((b.getString("bathromm_exhaust_fan")).equalsIgnoreCase("Y"))
            exhaust.setChecked(true);
        else if ((b.getString("bathromm_exhaust_fan")).equalsIgnoreCase("N"))
            exhaust.setChecked(false);


        Resources r = this.getResources();
        String flooring[] = r.getStringArray(R.array.flooring);

        String s = b.getString("bathroom_flooring_type");

        if (s == null)
            bathroom_spinner_floringtype.setSelection(0);
        else {
            int i = 0;
            for (; i < flooring.length; i++) {
                if (flooring[i].equalsIgnoreCase(s)) {
                    bathroom_spinner_floringtype.setSelection(i);
                    break;
                }
            }

        }
    }

    public void onResume() {
        super.onResume();
        getBathRoom(bathroom_id);

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
            //     item.setEnabled(false);
            setbathRoom();
            //      Toast.makeText(this,"NEXT",Toast.LENGTH_LONG).show();


            boolean ch = checkRemainingSpinnerValue();
            if (ch) {
                Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Kitchen.class));
                finish();
            }

        }


        return super.onOptionsItemSelected(item);
    }


    private void setbathRoom() {

        String bathroom_type, hot_water_supply, toilet, glass_partition, shower_curtain,
                bathtub, cabinate, windows, exhaust_fan;

        bathroom_type = (attached.isChecked() ? "Attached" : "Common");
        hot_water_supply = (geyser.isChecked() ? "Geyser" : "gas");
        toilet = (indian.isChecked() ? "Indian" : "Western");

        glass_partition = (glasspartion.isChecked() ? "Y" : "N");
        shower_curtain = (shower.isChecked() ? "Y" : "N");
        bathtub = (bath.isChecked() ? "Y" : "N");
        cabinate = (cabinates.isChecked() ? "Y" : "N");
        windows = (window.isChecked() ? "Y" : "N");
        exhaust_fan = (exhaust.isChecked() ? "Y" : "N");

        db.setBathRoom(bathroom_id, bathroom_type, hot_water_supply, toilet, glass_partition, shower_curtain, bathtub, windows, cabinate, exhaust_fan, flooringtype, "true");
        ContentValues cv= new ContentValues();
        cv.put("update_from_server","true");
        db.setUpdateFromServerStatus(cv, Appointment.clicked);

    }


    public void myClick(View v) {
        //  v.setEnabled(false);
        setbathRoom();
        boolean ch = checkRemainingSpinnerValue();
        if (ch) {
            startActivity(new Intent(this, Kitchen.class));
            finish();
        }
    }

    private boolean checkRemainingSpinnerValue() {
        String check = db.getNoOfRoom("no_of_bathroom");
        if (check == null)
            check = "1";
        int c = Integer.parseInt(check);
        for (int i = 1; i <= c; i++) {
            if (!db.checkSpinnerNo("BathRoom", "toilet_ID", i + "")) {
                TextView errorText = (TextView) bath_spinner_total.getSelectedView();
                errorText.setError("Please fill data");
                bath_spinner_total.setFocusableInTouchMode(true);
                bath_spinner_total.requestFocus();
                errorText.setTextColor(Color.RED);//just to highlight that this is an error
                // errorText.setText("my actual error text");//changes the selected item text to this
                Toast.makeText(this, "Please fill data for room = " + i, Toast.LENGTH_LONG).show();
                // property_spinner_total_living.setSelection(i-1,true);  for when automatic select spinner value which is nopt filled in database
                return false;
                //   property_spinner_total_living.setSelection(i);
                //   property_spinner_total_living
            }
        }
        return true;

    }

}
