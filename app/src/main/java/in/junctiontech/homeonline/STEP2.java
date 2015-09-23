package in.junctiontech.homeonline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class STEP2 extends AppCompatActivity {

    private DBHandler db;
    private Button detail_btn_basic,detail_btn_living,detail_btn_bed,detail_btn_bath,detail_btn_kitchen,detail_btn_wash,detail_btn_pricing;
    private Button detail_btn_residential,detail_btn_society;
    private Button detail_btn_property,detail_btn_advertiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        detail_btn_living= (Button) findViewById(R.id.detail_btn_living);
        detail_btn_bed= (Button) findViewById(R.id.detail_btn_bed);
        detail_btn_bath= (Button) findViewById(R.id.detail_btn_bath);
        detail_btn_kitchen= (Button) findViewById(R.id.detail_btn_kitchen);
        detail_btn_wash= (Button) findViewById(R.id.detail_btn_wash);
        detail_btn_pricing= (Button) findViewById(R.id.detail_btn_pricing);
        detail_btn_residential= (Button) findViewById(R.id.detail_btn_residential);
        detail_btn_society= (Button) findViewById(R.id.detail_btn_society);
        detail_btn_property= (Button) findViewById(R.id.detail_btn_property);
        detail_btn_advertiser= (Button) findViewById(R.id.detail_btn_advertiser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new DBHandler(this,"DB",null,1);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));


        String status_basic_property=db.getButtonStatus("Appointments","status_property_detail");
        if(status_basic_property==null);
        else if(status_basic_property.equalsIgnoreCase("true"))
            detail_btn_property.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
           // detail_btn_property.setBackgroundColor(Color.GREEN);

        String status_basic_advertiser=db.getButtonStatus("Appointments","status_advertiser_detail");//
        if(status_basic_advertiser==null);
        else if(status_basic_advertiser.equalsIgnoreCase("true"))
            detail_btn_advertiser.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_living=db.getButtonStatus("LivingRoom","status_living");
        if(status_living==null);
        else if(status_living.equalsIgnoreCase("true"))
            detail_btn_living.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_bed=db.getButtonStatus("BedRoom","status_bed");
        if(status_bed==null);
        else if(status_bed.equalsIgnoreCase("true"))
            detail_btn_bed.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_bath=db.getButtonStatus("BathRoom","status_bath");
        if(status_bath==null);
        else if(status_bath.equalsIgnoreCase("true"))
            detail_btn_bath.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_kitchen=db.getButtonStatus("Kitchen","status_kitchen");
        if(status_kitchen==null);
        else if(status_kitchen.equalsIgnoreCase("true"))
            detail_btn_kitchen.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_wash=db.getButtonStatus("WashDry","status_wash");
        if(status_wash==null);
        else if(status_wash.equalsIgnoreCase("true"))
            detail_btn_wash.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_pricing=db.getButtonStatus("Appointments","status_pricing");
        if(status_pricing==null);
        else if(status_pricing.equalsIgnoreCase("true"))
            detail_btn_pricing.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_residential=db.getButtonStatus("Appointments","status_residential");
        if(status_residential==null);
        else if(status_residential.equalsIgnoreCase("true"))
            detail_btn_residential.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

        String status_society=db.getButtonStatus("Appointments","status_society");
        if(status_society==null);
        else if(status_society.equalsIgnoreCase("true"))
            detail_btn_society.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
    }
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return true;
    }
    @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,Prefs.class));

        }else if(id==R.id.main_screen_aboutus) {
            //  Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AboutUs.class));
        }
        else if(id==R.id.main_screen_help){
            // Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,Help.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void myClick(View v) {
        v.setEnabled(false);
        int id = v.getId();
        Class s = null;

         if (id == R.id.detail_btn_property)
            s = PropertyDetails.class;
        else if (id == R.id.detail_btn_living)
            s = LivingRoom.class;
        else if (id == R.id.detail_btn_advertiser)
            s = AdvertiserDetail.class;
        else if (id == R.id.detail_btn_bed)
            s = BedRoom.class;
        else if (id == R.id.detail_btn_bath)
            s = BathRoom.class;
        else if (id == R.id.detail_btn_kitchen)
            s = Kitchen.class;
        else if (id == R.id.detail_btn_wash)
            s = WashDry.class;
        else if (id == R.id.detail_btn_image)
            s = NewGallery.class;
        else if (id == R.id.detail_btn_pricing)
            s = Pricing1.class;
        else if (id == R.id.detail_btn_residential)
            s = ResidentialD1.class;
        else if (id == R.id.detail_btn_society)
            s = SocietyData1.class;

        startActivity(new Intent(this,s));
        finish();
    }
}
