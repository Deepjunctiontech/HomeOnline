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
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WashDry extends AppCompatActivity {
    private RadioButton yes, no;
    private DBHandler db;
    private Spinner washarea_spinner_flooringtype;
    private String washarea_flooringtype = "Marble";
    private Spinner washdry_spinner_total;
    private String washdry_id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_dry);

        db = new DBHandler(this, "DB", null, 1);
        TextView name   = (TextView) findViewById(R.id.tv_washdry);
        name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));
        yes = (RadioButton) findViewById(R.id.washarea_rb_yes);
        no = (RadioButton) findViewById(R.id.washarea_rb__no);

        washarea_spinner_flooringtype = (Spinner) findViewById(R.id.washarea_spiner_floring);

        washarea_spinner_flooringtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                washarea_flooringtype = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        washdry_spinner_total = (Spinner) findViewById(R.id.washdry_spinner_total);

        String check=db.getNoOfRoom("no_of_washdry");

        if(check==null)
            check="1";
        int c= Integer.parseInt(check);

        String[] total = new String[c];

        for (int i = 0; i < c; i++)
            total[i] = i + 1 + "";

        ArrayAdapter<String> obj = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, total);
        washdry_spinner_total.setAdapter(obj);


        washdry_spinner_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)
                clickWashArea();
                washdry_id = ((TextView) view).getText().toString();
                getWashDry(washdry_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getWashDry(String washdry_id) {
        Bundle b = db.getWashDry(washdry_id);


        Resources r = this.getResources();
        String flooring[] = r.getStringArray(R.array.flooring);

        String s = b.getString("washdry_flooring_type");

        if(s==null)
            washarea_spinner_flooringtype.setSelection(0);
        else {
            int i=0;
            for(;i<flooring.length;i++){
                if(flooring[i].equalsIgnoreCase(s))
                    break;
            }
            washarea_spinner_flooringtype.setSelection(i);
        }




        if (b.getString("washdry_washing_machinet") == null)
        no.setChecked(true);
        else if ((b.getString("washdry_washing_machinet")).equalsIgnoreCase("Y"))
            yes.setChecked(true);
        else if ((b.getString("washdry_washing_machinet")).equalsIgnoreCase("N"))
            no.setChecked(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, STEP2.class));
        finish();
        return true;
    }


    public void onResume() {
        super.onResume();
        getWashDry(washdry_id);

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
            clickWashArea();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Pricing1.class));
            finish();

        }

        return super.onOptionsItemSelected(item);

    }


    private void clickWashArea() {

        String washing_machine = (yes.isChecked() ? "Y" : "N");

        db.setWashDry(washdry_id, washing_machine, washarea_flooringtype,"true");
    }

    public void myClick(View v){
        clickWashArea();
        startActivity(new Intent(this,Pricing1.class));
        finish();
    }
}
