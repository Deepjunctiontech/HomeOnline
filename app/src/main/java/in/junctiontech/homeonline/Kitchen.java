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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Kitchen extends AppCompatActivity {


    private CheckBox fridge, gaspipeline, waterpurifier, microwave, loft, chimney;
    private Spinner kitchen_spinner_plateform;
    private RadioButton modular;
    private RadioButton na;
    private DBHandler db;
    private String plateform_material="Simple";
    private Spinner kitchen_spinner_total;
    private String kitchen_id="1";
    private String[] plateform;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);
        db = new DBHandler(this, "DB", null, 1);
      //  name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

        fridge = (CheckBox) findViewById(R.id.kitchen_ck_refridge);
        gaspipeline = (CheckBox) findViewById(R.id.kitchen_ck_gaspipeline);
        waterpurifier = (CheckBox) findViewById(R.id.kitchen_ck_waterpurifier);
        microwave = (CheckBox) findViewById(R.id.kitchen_ck_microwave);
        loft = (CheckBox) findViewById(R.id.kitchen_ck_loft);
        chimney = (CheckBox) findViewById(R.id.kitchen_ck_chimney);
        modular = (RadioButton) findViewById(R.id.kitchen_rb_cabinates_modular);
        na = (RadioButton) findViewById(R.id.kitchen_rb_cabinates_na);
        kitchen_spinner_plateform = (Spinner) findViewById(R.id.kitchen_spiner_plteform);

        Resources r= this.getResources();
       plateform=r.getStringArray(R.array.plateform);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kitchen_spinner_plateform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plateform_material = plateform[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        kitchen_spinner_total=(Spinner)findViewById(R.id.kitchen_spinner_total);
        String check=db.getNoOfRoom("no_of_kitchen");

        if(check==null)
            check="1";
        int c= Integer.parseInt(check);
        String []total=new String[c];

        for(int i=0;i<c;i++)
            total[i]=i+1+"";


        ArrayAdapter<String> obj=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,total);
        kitchen_spinner_total.setAdapter(obj);



        kitchen_spinner_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(status==true)
                setKitchen();
                status=true;
                kitchen_id = ((TextView) view).getText().toString();
                //            Toast.makeText(BedRoom.this, bedroom_id, Toast.LENGTH_SHORT).show();
                getKitchen(kitchen_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getKitchen(String kitchen_id) {

        Bundle b = db.getkitchen(kitchen_id);



        if(b.getString("kitchen_refridgerator")==null)
            fridge.setChecked(false);
        else if((b.getString("kitchen_refridgerator")).equalsIgnoreCase("Y"))
            fridge.setChecked(true);
        else if((b.getString("kitchen_refridgerator")).equalsIgnoreCase("N"))
            fridge.setChecked(false);

        if(b.getString("kitchen_water_purifier")==null)
            waterpurifier.setChecked(false);
        else if((b.getString("kitchen_water_purifier")).equalsIgnoreCase("Y"))
            waterpurifier.setChecked(true);
        else if((b.getString("kitchen_water_purifier")).equalsIgnoreCase("N"))
            waterpurifier.setChecked(false);


        if(b.getString("kitchen_loft")==null)
            loft.setChecked(false);
        else if((b.getString("kitchen_loft")).equalsIgnoreCase("Y"))
            loft.setChecked(true);
        else if((b.getString("kitchen_loft")).equalsIgnoreCase("N"))
            loft.setChecked(false);

        if(b.getString("kitchen_gas_pipeline")==null)
            gaspipeline.setChecked(false);
        else if((b.getString("kitchen_gas_pipeline")).equalsIgnoreCase("Y"))
            gaspipeline.setChecked(true);
        else if((b.getString("kitchen_gas_pipeline")).equalsIgnoreCase("N"))
            gaspipeline.setChecked(false);

        if(b.getString("kitchen_microwave")==null)
            microwave.setChecked(false);
        else if((b.getString("kitchen_microwave")).equalsIgnoreCase("Y"))
            microwave.setChecked(true);
        else if((b.getString("kitchen_microwave")).equalsIgnoreCase("N"))
            microwave.setChecked(false);


        if(b.getString("kitchen_chimney")==null)
            chimney.setChecked(false);
        else if((b.getString("kitchen_chimney")).equalsIgnoreCase("Y"))
            chimney.setChecked(true);
        else if((b.getString("kitchen_chimney")).equalsIgnoreCase("N"))
            chimney.setChecked(false);




        if(b.getString("kitchen_cabinetes")==null)
            na.setChecked(true);
        else if((b.getString("kitchen_cabinetes")).equalsIgnoreCase("Modular"))
            modular.setChecked(true);
        else if((b.getString("kitchen_cabinetes")).equalsIgnoreCase("NA"))
            na.setChecked(true);


        String s=b.getString("kitchen_plateform_material");

        if(s==null)
            kitchen_spinner_plateform.setSelection(0);
        else {
            int i=0;
            for(;i<plateform.length;i++){
                if(plateform[i].equalsIgnoreCase(s))
                    break;
            }
            kitchen_spinner_plateform.setSelection(i);
        }
    }


    public void onResume() {
        super.onResume();
        getKitchen(kitchen_id);

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


       if(id==R.id.action_my_next){
           item.setEnabled(false);
            setKitchen();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,WashDry.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    private void setKitchen() {
        String  cabinate_modular = (modular.isChecked() ? "Modular" : "NA");
        String refridge = (fridge.isChecked() ? "Y" : "N");
        String gas_pipeline = (gaspipeline.isChecked() ? "Y" : "N");
        String water_purifier = (waterpurifier.isChecked() ? "Y" : "N");
        String microwave1 = (microwave.isChecked() ? "Y" : "N");
        String loft1 = (loft.isChecked() ? "Y" : "N");
        String chimney1 = (chimney.isChecked() ? "Y" : "N");

        db.setKitchen(kitchen_id,cabinate_modular,refridge,water_purifier,loft1,gas_pipeline,microwave1,chimney1,plateform_material,"true");

    }
    public void myClick(View v){
        v.setEnabled(false);
        setKitchen();
        startActivity(new Intent(this,WashDry.class));
        finish();
    }
}
