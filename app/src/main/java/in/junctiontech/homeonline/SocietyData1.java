package in.junctiontech.homeonline;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SocietyData1 extends AppCompatActivity {
    private RadioButton glass,grill,barbed_wire,electric_wiring,boundarywall_na;
    private CheckBox community,reg,overhead,security,cctv,smoke,fire,club,swiming,zym,multipurpose;
    private CheckBox kids_play_area,society_saunna_steam,society_yogaroom,society_billiards,society_Squash,
            society_table_tennis,society_kids_play_area,
            society_tennis,society_volleyball,society_batminton,society_garden_lawn;
    private TextView name;
    private EditText societyname_edit,no_of_building_edit;
    private DBHandler db;
    private RadioButton society_rb_brickwall,society_rb_cementedwall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(this, "DB", null, 1);
        setContentView(R.layout.activity_society_d1);
        name=(TextView)findViewById(R.id.tv_societydata);
      //  name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        name.setText(b.getString("name"));
        society_Squash= (CheckBox) findViewById(R.id.society_Squash);
        society_table_tennis= (CheckBox) findViewById(R.id.society_table_tennis);
        society_kids_play_area= (CheckBox) findViewById(R.id.society_kids_play_area);
        community= (CheckBox) findViewById(R.id.society_ck_gc);
        reg= (CheckBox) findViewById(R.id.society_ck_rs);
        overhead= (CheckBox) findViewById(R.id.society_ck_sot);
        cctv= (CheckBox) findViewById(R.id.society_ck_cctv);
        smoke= (CheckBox) findViewById(R.id.society_ck_smoke);
        fire= (CheckBox) findViewById(R.id.society_ck_fire);
        club= (CheckBox) findViewById(R.id.society_ck_clubhouse);
        swiming= (CheckBox) findViewById(R.id.society_ck_swimmingpool);
        zym= (CheckBox) findViewById(R.id.society_ck_zym);
        multipurpose= (CheckBox) findViewById(R.id.society_ck_multipurpose);
        security= (CheckBox) findViewById(R.id.society_ck_security);
        kids_play_area=(CheckBox)findViewById(R.id.society_kids_play_area);
        society_saunna_steam=(CheckBox)findViewById(R.id.society_saunna_steam);
        society_yogaroom=(CheckBox)findViewById(R.id.society_yogaroom);
        society_billiards=(CheckBox)findViewById(R.id.society_billiards);
        society_tennis=(CheckBox)findViewById(R.id.society_tennis);
        society_volleyball=(CheckBox)findViewById(R.id.society_volleyball);
        society_batminton=(CheckBox)findViewById(R.id.society_batminton);
        society_garden_lawn=(CheckBox)findViewById(R.id.society_garden_lawn);

        glass= (RadioButton) findViewById(R.id.society_rb_glass);
        grill= (RadioButton) findViewById(R.id.society_rb_grill);
        barbed_wire= (RadioButton) findViewById(R.id.society_rb_Barbed_wire);
        electric_wiring= (RadioButton) findViewById(R.id.society_rb_electric_wiring);
        society_rb_brickwall= (RadioButton) findViewById(R.id.society_rb_brickwall);
        society_rb_cementedwall= (RadioButton) findViewById(R.id.society_rb_cementedwall);
        boundarywall_na= (RadioButton) findViewById(R.id.society_rb_boundarywall_na);

        societyname_edit=(EditText)findViewById(R.id.society_society_edit);
        no_of_building_edit=(EditText)findViewById(R.id.society_buildingy_edit);

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


        Bundle b = db.getSocietyData();
        societyname_edit.setText(b.getString("societydata_society_name"));
        no_of_building_edit.setText(b.getString("societydata_no_of_building"));


        if(b.getString("societydata_boundary_wall")==null)
        {
            boundarywall_na.setChecked(true);
        }
        else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Glass")) {
            glass.setChecked(true);
        }else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Grill")){
            grill.setChecked(true);
        }else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Barbed Wire")){
            barbed_wire.setChecked(true);}
        else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Electric Wiring")){
            electric_wiring.setChecked(true);}
        else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Brick Wall")){
            society_rb_brickwall.setChecked(true);}
        else if((b.getString("societydata_boundary_wall")).equalsIgnoreCase("Cemented Wall")){
            society_rb_cementedwall.setChecked(true);}
        else boundarywall_na.setChecked(true);

        if(b.getString("societydata_gated_community")==null);
        else if((b.getString("societydata_gated_community")).equalsIgnoreCase("Y"))
            community.setChecked(true);

        if(b.getString("societydata_society_overheadtank")==null);
        else if((b.getString("societydata_society_overheadtank")).equalsIgnoreCase("Y"))
            overhead.setChecked(true);

        if(b.getString("societydata_cctv_servillance")==null);
        else if((b.getString("societydata_cctv_servillance")).equalsIgnoreCase("Y"))
            cctv.setChecked(true);

        if(b.getString("societydata_fire_hydrant_system")==null);
        else if((b.getString("societydata_fire_hydrant_system")).equalsIgnoreCase("Y"))
            fire.setChecked(true);

        if(b.getString("societydata_swiming_pool")==null);
        else if((b.getString("societydata_swiming_pool")).equalsIgnoreCase("Y"))
            swiming.setChecked(true);

        if(b.getString("societydata_multi_purpose")==null);
        else if((b.getString("societydata_multi_purpose")).equalsIgnoreCase("Y"))
            multipurpose.setChecked(true);

        if(b.getString("societydata_reg_society")==null);
        else if((b.getString("societydata_reg_society")).equalsIgnoreCase("Y"))
            reg.setChecked(true);

        if(b.getString("societydata_security")==null);
        else if((b.getString("societydata_security")).equalsIgnoreCase("Y"))
            security.setChecked(true);

        if(b.getString("societydata_smoke_detector")==null);
        else if((b.getString("societydata_smoke_detector")).equalsIgnoreCase("Y"))
            smoke.setChecked(true);

        if(b.getString("societydata_club_house")==null);
        else if((b.getString("societydata_club_house")).equalsIgnoreCase("Y"))
            club.setChecked(true);

        if(b.getString("societydata_zym")==null);
        else if((b.getString("societydata_zym")).equalsIgnoreCase("Y"))
            zym.setChecked(true);


        if(b.getString("kids_playarea1")==null);
        else if((b.getString("kids_playarea1")).equalsIgnoreCase("Y"))
            kids_play_area.setChecked(true);

        if(b.getString("saunna_steam")==null);
        else if((b.getString("saunna_steam")).equalsIgnoreCase("Y"))
            society_saunna_steam.setChecked(true);

        if(b.getString("yogaroom")==null);
        else if((b.getString("yogaroom")).equalsIgnoreCase("Y"))
            society_yogaroom.setChecked(true);

        if(b.getString("billiards")==null);
        else if((b.getString("billiards")).equalsIgnoreCase("Y"))
            society_billiards.setChecked(true);

        if(b.getString("tennis")==null);
        else if((b.getString("tennis")).equalsIgnoreCase("Y"))
            society_tennis.setChecked(true);

        if(b.getString("volleyball")==null);
        else if((b.getString("volleyball")).equalsIgnoreCase("Y"))
            society_volleyball.setChecked(true);

        if(b.getString("batminton")==null);
        else if((b.getString("batminton")).equalsIgnoreCase("Y"))
            society_batminton.setChecked(true);

        if(b.getString("garden_lawn")==null);
        else if((b.getString("garden_lawn")).equalsIgnoreCase("Y"))
            society_garden_lawn.setChecked(true);

        if(b.getString("squash")==null);
        else if((b.getString("squash")).equalsIgnoreCase("Y"))
            society_Squash.setChecked(true);

        if(b.getString("table_tennis")==null);
        else if((b.getString("table_tennis")).equalsIgnoreCase("Y"))
            society_table_tennis.setChecked(true);





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
            saveSociety();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,ImageSelection.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void saveSociety() {
        String boundarywall;
        String societyname=societyname_edit.getText().toString();
        String no_of_building=no_of_building_edit.getText().toString();

        if(glass.isChecked()){
            boundarywall="Glass";
        }else if(grill.isChecked()){
            boundarywall="Grill";
        }else if(barbed_wire.isChecked())
            boundarywall="Barbed Wire";
        else if(electric_wiring.isChecked())
            boundarywall="Electric Wiring";
        else if(society_rb_brickwall.isChecked())
            boundarywall="Brick Wall";
        else if(society_rb_cementedwall.isChecked())
            boundarywall="Cemented Wall";
        else
        boundarywall="NA";

        String gated_comunity = (community.isChecked() ? "Y" : "N");
        String reg_society = (reg.isChecked() ? "Y" : "N");
        String overhead_tank = (overhead.isChecked() ? "Y" : "N");
        String security1 = (security.isChecked() ? "Y" : "N");
        String cctv_servillance = (cctv.isChecked() ? "Y" : "N");
        String smoke_detector = (smoke.isChecked() ? "Y" : "N");
        String fire_hydrant_system = (fire.isChecked() ? "Y" : "N");
        String club_house = (club.isChecked() ? "Y" : "N");
        String swimming_pool = (swiming.isChecked() ? "Y" : "N");
        String zym1 = (zym.isChecked() ? "Y" : "N");
        String multipurpose1 = (multipurpose.isChecked() ? "Y" : "N");
        String kids_playarea1 = (kids_play_area.isChecked() ? "Y" : "N");
        String saunna_steam = (society_saunna_steam.isChecked() ? "Y" : "N");
        String yogaroom = (society_yogaroom.isChecked() ? "Y" : "N");
        String billiards = (society_billiards.isChecked() ? "Y" : "N");
        String tennis = (society_tennis.isChecked() ? "Y" : "N");
        String volleyball = (society_volleyball.isChecked() ? "Y" : "N");
        String batminton = (society_batminton.isChecked() ? "Y" : "N");

        String table_tennis = (society_table_tennis.isChecked() ? "Y" : "N");
        String squash = (society_Squash.isChecked() ? "Y" : "N");
        String garden_lawn = (society_garden_lawn.isChecked() ? "Y" : "N");


        db.setSocietyData(societyname,no_of_building,boundarywall,gated_comunity,
                overhead_tank,cctv_servillance,fire_hydrant_system,
                swimming_pool,multipurpose1,reg_society,security1,smoke_detector,club_house,zym1,
                kids_playarea1,saunna_steam,yogaroom,billiards,tennis,volleyball,batminton,table_tennis,squash,garden_lawn,"true");

    }
    public void myClick(View v){
        saveSociety();
        startActivity(new Intent(this,ImageSelection.class));
        finish();
    }
}
