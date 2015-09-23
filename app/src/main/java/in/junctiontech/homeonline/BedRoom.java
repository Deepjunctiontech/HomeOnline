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
import android.widget.Spinner;
import android.widget.TextView;

public class BedRoom extends AppCompatActivity {

    private String bedroom_flooringtype = "Marble";
    private CheckBox bed, dresing, ac, tv, wardrobe, ceiling, balcony, bathroom, window;
    private Spinner bedroom_spinner_floring;
    private DBHandler db;
    private String bedroom_id="1";
    private Spinner bed_spinner_total;
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_room);
        db = new DBHandler(this, "DB", null, 1);
        bed = (CheckBox) findViewById(R.id.bedroom_ck_bed);
        dresing = (CheckBox) findViewById(R.id.bedroom_ck_dressing);
        ac = (CheckBox) findViewById(R.id.bedroom_ck_ac);
        tv = (CheckBox) findViewById(R.id.bedroom_ck_tv);
        wardrobe = (CheckBox) findViewById(R.id.bedroom_ck_ward);
        window = (CheckBox) findViewById(R.id.bedroom_ck_window);
        ceiling = (CheckBox) findViewById(R.id.bedroom_ck_falseceiling);
        balcony = (CheckBox) findViewById(R.id.bedroom_ck_attachbalcony);
        bathroom = (CheckBox) findViewById(R.id.bedroom_ck_attachbathroom);
        bedroom_spinner_floring = (Spinner) findViewById(R.id.bedroom_spiner_flooringtype);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

        bedroom_spinner_floring.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bedroom_flooringtype = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String check=db.getNoOfRoom("no_of_bedroom");
        if(check==null)
            check="1";
        int c= Integer.parseInt(check);
        String []total=new String[c];

        for(int i=0;i<c;i++)
            total[i]=i+1+"";

        bed_spinner_total= (Spinner) findViewById(R.id.bed_spinner_total);
        ArrayAdapter<String> obj=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,total);
        bed_spinner_total.setAdapter(obj);



        bed_spinner_total.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(status==true)
                setBedRoom();
                status=true;

                bedroom_id = ((TextView) view).getText().toString();
    //            Toast.makeText(BedRoom.this, bedroom_id, Toast.LENGTH_SHORT).show();
                getBedRoom(bedroom_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getBedRoom(String bedroom_id) {

        Bundle b = db.getBedRoom(bedroom_id);


        if (b.getString("tv") == null)
            tv.setChecked(false);
        else if ((b.getString("tv")).equalsIgnoreCase("Y"))
            tv.setChecked(true);
        else if ((b.getString("tv")).equalsIgnoreCase("N"))
            tv.setChecked(false);

        if (b.getString("ac") == null)
            ac.setChecked(false);
        else if ((b.getString("ac")).equalsIgnoreCase("Y"))
            ac.setChecked(true);
        else if ((b.getString("ac")).equalsIgnoreCase("N"))
            ac.setChecked(false);

        if (b.getString("bed") == null)
            bed.setChecked(false);
        else if ((b.getString("bed")).equalsIgnoreCase("Y"))
            bed.setChecked(true);
        else if ((b.getString("bed")).equalsIgnoreCase("N"))
            bed.setChecked(false);

        if (b.getString("wardrobe") == null)
            wardrobe.setChecked(false);
        else if ((b.getString("wardrobe")).equalsIgnoreCase("Y"))
            wardrobe.setChecked(true);
        else if ((b.getString("wardrobe")).equalsIgnoreCase("N"))
            wardrobe.setChecked(false);

        if (b.getString("attached_balcony") == null)
            balcony.setChecked(false);
        else if ((b.getString("attached_balcony")).equalsIgnoreCase("Y"))
            balcony.setChecked(true);
        else if ((b.getString("attached_balcony")).equalsIgnoreCase("N"))
            balcony.setChecked(false);

        if (b.getString("dressing_table") == null)
            dresing.setChecked(false);
        else if ((b.getString("dressing_table")).equalsIgnoreCase("Y"))
            dresing.setChecked(true);
        else if ((b.getString("dressing_table")).equalsIgnoreCase("N"))
            dresing.setChecked(false);

        if (b.getString("false_ceiling") == null)
            ceiling.setChecked(false);
        else if ((b.getString("false_ceiling")).equalsIgnoreCase("Y"))
            ceiling.setChecked(true);
        else if ((b.getString("false_ceiling")).equalsIgnoreCase("N"))
            ceiling.setChecked(false);

        if (b.getString("attached_bathroom") == null)
            bathroom.setChecked(false);
        else if ((b.getString("attached_bathroom")).equalsIgnoreCase("Y"))
            bathroom.setChecked(true);
        else if ((b.getString("attached_bathroom")).equalsIgnoreCase("N"))
            bathroom.setChecked(false);

        if (b.getString("window") == null)
            window.setChecked(false);
        else if ((b.getString("window")).equalsIgnoreCase("Y"))
            window.setChecked(true);
        else if ((b.getString("window")).equalsIgnoreCase("N"))
            window.setChecked(false);



        Resources r = this.getResources();
        String flooringtype[] = r.getStringArray(R.array.flooring);

        String s = b.getString("flooring_type");

        if (s == null)
        bedroom_spinner_floring.setSelection(0);
        else {
            int i = 0;
            for (; i < flooringtype.length; i++) {
                if (flooringtype[i].equalsIgnoreCase(s))
                    break;
            }
            bedroom_spinner_floring.setSelection(i);
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


        if (id == R.id.action_my_next) {
            item.setEnabled(false);
            setBedRoom();
          //  Toast.makeText(this,"NEXT",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, BathRoom.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void setBedRoom() {

        String bed1 = (bed.isChecked() ? "Y" : "N");
        String dressing_table = (dresing.isChecked() ? "Y" : "N");
        String ac1 = (ac.isChecked() ? "Y" : "N");
        String tv1 = (tv.isChecked() ? "Y" : "N");
        String wardrobe1 = (wardrobe.isChecked() ? "Y" : "N");
        String windows = (window.isChecked() ? "Y" : "N");
        String attached_balcony = (balcony.isChecked() ? "Y" : "N");
        String attached_bathroom = (bathroom.isChecked() ? "Y" : "N");
        String false_ceiling = (ceiling.isChecked() ? "Y" : "N");
        db.setBedRoom(bedroom_id,tv1, ac1, bed1, wardrobe1, attached_balcony, dressing_table, false_ceiling, attached_bathroom, windows, bedroom_flooringtype,"true");


    }

@Override
    public void onResume() {
        super.onResume();
        getBedRoom(bedroom_id);

    }
    public void myClick(View v){
        v.setEnabled(false);
        setBedRoom();
        startActivity(new Intent(this, BathRoom.class));
        finish();
    }

}
