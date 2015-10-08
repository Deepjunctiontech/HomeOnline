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
    private RadioButton glass,grill,barbed_wire,electric_wiring,boundarywall_na,society_rb_brickwall,society_rb_cementedwall;
    private CheckBox community,reg,overhead,security,cctv,smoke,fire,club,swiming,zym,multipurpose,society_garden_lawn;


    private DBHandler db;

    private CheckBox society_ck_24HWS,society_ck_aerobic_room,society_ck_amphithreater,
            society_ck_atm_bank,society_ck_banquet_hall,society_ck_barbeque_pit,society_ck_basketball_tennis_court,society_ck_centralized_ac
            ,society_ck_conference_room,society_ck_day_care_center,society_ck_dth_tv_facility,society_ck_early_learning_play_group,
            society_ck_golf_cource, society_ck_guest_accomadation,society_ck_indoor_games_room,society_ck_indoor_bedminton_court,
            society_ck_intercom,society_ck_kids_club,society_ck_kids_play_area,society_ck_laundry_service,society_ck_meditation_center,
            society_ck_paved_comound,society_ck_power_backup,society_ck_property_maintenace_staff,
            society_ck_rain_water_harvesting,society_ck_recreational_facilities,society_ck_rentable_community_space,
            society_ck_reserverd_parking,society_ck_school,society_ck_service_goods_lift,society_ck_sevage_treatment_plan,
            society_ck_shooping_retail,society_ck_skating_court,society_ck_strolling_cycling_jogging,society_ck_vaastu_complaint,society_ck_visitor_parking,
            society_ck_waiting_lounge,society_ck_waste_disposal ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(this, "DB", null, 1);
        setContentView(R.layout.activity_society_d1);

      //  name.setPaintFlags(name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        Bundle b = db.getIdName();
        getSupportActionBar().setTitle(
                getSupportActionBar().getTitle() + " - " + b.getString("name"));
        //getSupportActionBar().setBackgroundDrawable( new ColorDrawable(getResources().getColor(R.color.highlight)));
        getSupportActionBar().setSubtitle(b.getString("description"));

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
        society_garden_lawn=(CheckBox)findViewById(R.id.society_garden_lawn);

        glass= (RadioButton) findViewById(R.id.society_rb_glass);
        grill= (RadioButton) findViewById(R.id.society_rb_grill);
        barbed_wire= (RadioButton) findViewById(R.id.society_rb_Barbed_wire);
        electric_wiring= (RadioButton) findViewById(R.id.society_rb_electric_wiring);
        society_rb_brickwall= (RadioButton) findViewById(R.id.society_rb_brickwall);
        society_rb_cementedwall= (RadioButton) findViewById(R.id.society_rb_cementedwall);
        boundarywall_na= (RadioButton) findViewById(R.id.society_rb_boundarywall_na);

        society_ck_24HWS= (CheckBox) findViewById(R.id.society_ck_24HWS);
        society_ck_aerobic_room= (CheckBox) findViewById(R.id.society_ck_aerobic_room);
        society_ck_amphithreater= (CheckBox) findViewById(R.id.society_ck_amphithreater);
        society_ck_atm_bank=(CheckBox)findViewById(R.id.society_ck_atm_bank);
        society_ck_banquet_hall=(CheckBox)findViewById(R.id.society_ck_banquet_hall);
        society_ck_barbeque_pit=(CheckBox)findViewById(R.id.society_ck_barbeque_pit);
        society_ck_basketball_tennis_court=(CheckBox)findViewById(R.id.society_ck_basketball_tennis_court);
        society_ck_centralized_ac=(CheckBox)findViewById(R.id.society_ck_centralized_ac);
        society_ck_conference_room= (CheckBox) findViewById(R.id.society_ck_conference_room);
        society_ck_day_care_center= (CheckBox) findViewById(R.id.society_ck_day_care_center);
        society_ck_dth_tv_facility= (CheckBox) findViewById(R.id.society_ck_dth_tv_facility);
        society_ck_early_learning_play_group=(CheckBox)findViewById(R.id.society_ck_early_learning_play_group);
        society_ck_golf_cource=(CheckBox)findViewById(R.id.society_ck_golf_cource);
        society_ck_guest_accomadation=(CheckBox)findViewById(R.id.society_ck_guest_accomadation);
        society_ck_indoor_games_room=(CheckBox)findViewById(R.id.society_ck_indoor_games_room);
        society_ck_indoor_bedminton_court=(CheckBox)findViewById(R.id.society_ck_indoor_bedminton_court);
        society_ck_intercom=(CheckBox)findViewById(R.id.society_ck_intercom);
        society_ck_kids_club=(CheckBox)findViewById(R.id.society_ck_kids_club);
        society_ck_kids_play_area= (CheckBox) findViewById(R.id.society_ck_kids_play_area);
        society_ck_laundry_service= (CheckBox) findViewById(R.id.society_ck_laundry_service);
        society_ck_meditation_center= (CheckBox) findViewById(R.id.society_ck_meditation_center);
        society_ck_paved_comound=(CheckBox)findViewById(R.id.society_ck_paved_comound);
        society_ck_power_backup=(CheckBox)findViewById(R.id.society_ck_power_backup);
        society_ck_property_maintenace_staff=(CheckBox)findViewById(R.id.society_ck_property_maintenace_staff);
        society_ck_rain_water_harvesting=(CheckBox)findViewById(R.id.society_ck_rain_water_harvesting);
        society_ck_recreational_facilities=(CheckBox)findViewById(R.id.society_ck_recreational_facilities);
        society_ck_rentable_community_space=(CheckBox)findViewById(R.id.society_ck_rentable_community_space);
        society_ck_reserverd_parking=(CheckBox)findViewById(R.id.society_ck_reserverd_parking);
        society_ck_school= (CheckBox) findViewById(R.id.society_ck_school);
        society_ck_service_goods_lift= (CheckBox) findViewById(R.id.society_ck_service_goods_lift);
        society_ck_sevage_treatment_plan=(CheckBox)findViewById(R.id.society_ck_sevage_treatment_plan);
        society_ck_shooping_retail=(CheckBox)findViewById(R.id.society_ck_shooping_retail);
        society_ck_skating_court=(CheckBox)findViewById(R.id.society_ck_skating_court);
        society_ck_strolling_cycling_jogging=(CheckBox)findViewById(R.id.society_ck_strolling_cycling_jogging);
        society_ck_vaastu_complaint=(CheckBox)findViewById(R.id.society_ck_vaastu_complaint);
        society_ck_visitor_parking=(CheckBox)findViewById(R.id.society_ck_visitor_parking);
        society_ck_waiting_lounge=(CheckBox)findViewById(R.id.society_ck_waiting_lounge);
        society_ck_waste_disposal=(CheckBox)findViewById(R.id.society_ck_waste_disposal);



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


        if(b.getString("garden_lawn")==null);
        else if((b.getString("garden_lawn")).equalsIgnoreCase("Y"))
            society_garden_lawn.setChecked(true);



        if(b.getString("society_ck_24HWS")==null);
        else if((b.getString("society_ck_24HWS")).equalsIgnoreCase("Y"))
            society_ck_24HWS.setChecked(true);

        if(b.getString("society_ck_aerobic_room")==null);
        else if((b.getString("society_ck_aerobic_room")).equalsIgnoreCase("Y"))
            society_ck_aerobic_room.setChecked(true);

        if(b.getString("society_ck_amphithreater")==null);
        else if((b.getString("society_ck_amphithreater")).equalsIgnoreCase("Y"))
            society_ck_amphithreater.setChecked(true);

        if(b.getString("society_ck_atm_bank")==null);
        else if((b.getString("society_ck_atm_bank")).equalsIgnoreCase("Y"))
            society_ck_atm_bank.setChecked(true);

        if(b.getString("society_ck_banquet_hall")==null);
        else if((b.getString("society_ck_banquet_hall")).equalsIgnoreCase("Y"))
            society_ck_banquet_hall.setChecked(true);

        if(b.getString("society_ck_barbeque_pit")==null);
        else if((b.getString("society_ck_barbeque_pit")).equalsIgnoreCase("Y"))
            society_ck_barbeque_pit.setChecked(true);

        if(b.getString("society_ck_basketball_tennis_court")==null);
        else if((b.getString("society_ck_basketball_tennis_court")).equalsIgnoreCase("Y"))
            society_ck_basketball_tennis_court.setChecked(true);

        if(b.getString("society_ck_centralized_ac")==null);
        else if((b.getString("society_ck_centralized_ac")).equalsIgnoreCase("Y"))
            society_ck_centralized_ac.setChecked(true);

        if(b.getString("society_ck_conference_room")==null);
        else if((b.getString("society_ck_conference_room")).equalsIgnoreCase("Y"))
            society_ck_conference_room.setChecked(true);

        if(b.getString("society_ck_day_care_center")==null);
        else if((b.getString("society_ck_day_care_center")).equalsIgnoreCase("Y"))
            society_ck_day_care_center.setChecked(true);

        if(b.getString("society_ck_dth_tv_facility")==null);
        else if((b.getString("society_ck_dth_tv_facility")).equalsIgnoreCase("Y"))
            society_ck_dth_tv_facility.setChecked(true);

        if(b.getString("society_ck_early_learning_play_group")==null);
        else if((b.getString("society_ck_early_learning_play_group")).equalsIgnoreCase("Y"))
            society_ck_early_learning_play_group.setChecked(true);

        if(b.getString("society_ck_golf_cource")==null);
        else if((b.getString("society_ck_golf_cource")).equalsIgnoreCase("Y"))
            society_ck_golf_cource.setChecked(true);

        if(b.getString("society_ck_guest_accomadation")==null);
        else if((b.getString("society_ck_guest_accomadation")).equalsIgnoreCase("Y"))
            society_ck_guest_accomadation.setChecked(true);

        if(b.getString("society_ck_indoor_games_room")==null);
        else if((b.getString("society_ck_indoor_games_room")).equalsIgnoreCase("Y"))
            society_ck_indoor_games_room.setChecked(true);

        if(b.getString("society_ck_indoor_bedminton_court")==null);
        else if((b.getString("society_ck_indoor_bedminton_court")).equalsIgnoreCase("Y"))
            society_ck_indoor_bedminton_court.setChecked(true);

        if(b.getString("society_ck_intercom")==null);
        else if((b.getString("society_ck_intercom")).equalsIgnoreCase("Y"))
            society_ck_intercom.setChecked(true);

        if(b.getString("society_ck_kids_club")==null);
        else if((b.getString("society_ck_kids_club")).equalsIgnoreCase("Y"))
            society_ck_kids_club.setChecked(true);

        if(b.getString("society_ck_kids_play_area")==null);
        else if((b.getString("society_ck_kids_play_area")).equalsIgnoreCase("Y"))
            society_ck_kids_play_area.setChecked(true);

        if(b.getString("society_ck_laundry_service")==null);
        else if((b.getString("society_ck_laundry_service")).equalsIgnoreCase("Y"))
            society_ck_laundry_service.setChecked(true);

        if(b.getString("society_ck_meditation_center")==null);
        else if((b.getString("society_ck_meditation_center")).equalsIgnoreCase("Y"))
            society_ck_meditation_center.setChecked(true);

        if(b.getString("society_ck_paved_comound")==null);
        else if((b.getString("society_ck_paved_comound")).equalsIgnoreCase("Y"))
            society_ck_paved_comound.setChecked(true);

        if(b.getString("society_ck_power_backup")==null);
        else if((b.getString("society_ck_power_backup")).equalsIgnoreCase("Y"))
            society_ck_power_backup.setChecked(true);

        if(b.getString("society_ck_property_maintenace_staff")==null);
        else if((b.getString("society_ck_property_maintenace_staff")).equalsIgnoreCase("Y"))
            society_ck_property_maintenace_staff.setChecked(true);

        if(b.getString("society_ck_rain_water_harvesting")==null);
        else if((b.getString("society_ck_rain_water_harvesting")).equalsIgnoreCase("Y"))
            society_ck_rain_water_harvesting.setChecked(true);

        if(b.getString("society_ck_recreational_facilities")==null);
        else if((b.getString("society_ck_recreational_facilities")).equalsIgnoreCase("Y"))
            society_ck_recreational_facilities.setChecked(true);

        if(b.getString("society_ck_rentable_community_space")==null);
        else if((b.getString("society_ck_rentable_community_space")).equalsIgnoreCase("Y"))
            society_ck_rentable_community_space.setChecked(true);

        if(b.getString("society_ck_reserverd_parking")==null);
        else if((b.getString("society_ck_reserverd_parking")).equalsIgnoreCase("Y"))
            society_ck_reserverd_parking.setChecked(true);

        if(b.getString("society_ck_school")==null);
        else if((b.getString("society_ck_school")).equalsIgnoreCase("Y"))
            society_ck_school.setChecked(true);

        if(b.getString("society_ck_service_goods_lift")==null);
        else if((b.getString("society_ck_service_goods_lift")).equalsIgnoreCase("Y"))
            society_ck_service_goods_lift.setChecked(true);

        if(b.getString("society_ck_sevage_treatment_plan")==null);
        else if((b.getString("society_ck_sevage_treatment_plan")).equalsIgnoreCase("Y"))
            society_ck_sevage_treatment_plan.setChecked(true);

        if(b.getString("society_ck_shooping_retail")==null);
        else if((b.getString("society_ck_shooping_retail")).equalsIgnoreCase("Y"))
            society_ck_shooping_retail.setChecked(true);

        if(b.getString("society_ck_skating_court")==null);
        else if((b.getString("society_ck_skating_court")).equalsIgnoreCase("Y"))
            society_ck_skating_court.setChecked(true);

        if(b.getString("society_ck_strolling_cycling_jogging")==null);
        else if((b.getString("society_ck_strolling_cycling_jogging")).equalsIgnoreCase("Y"))
            society_ck_strolling_cycling_jogging.setChecked(true);

        if(b.getString("society_ck_vaastu_complaint")==null);
        else if((b.getString("society_ck_vaastu_complaint")).equalsIgnoreCase("Y"))
            society_ck_vaastu_complaint.setChecked(true);

        if(b.getString("society_ck_visitor_parking")==null);
        else if((b.getString("society_ck_visitor_parking")).equalsIgnoreCase("Y"))
            society_ck_visitor_parking.setChecked(true);

        if(b.getString("society_ck_waiting_lounge")==null);
        else if((b.getString("society_ck_waiting_lounge")).equalsIgnoreCase("Y"))
            society_ck_waiting_lounge.setChecked(true);

        if(b.getString("society_ck_waste_disposal")==null);
        else if((b.getString("society_ck_waste_disposal")).equalsIgnoreCase("Y"))
            society_ck_waste_disposal.setChecked(true);


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
            saveSociety();
            Toast.makeText(this, "NEXT", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,NewGallery.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void saveSociety() {
        String boundarywall;

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
        String garden_lawn = (society_garden_lawn.isChecked() ? "Y" : "N");







        db.setSocietyData(boundarywall,gated_comunity,
                overhead_tank,cctv_servillance,fire_hydrant_system,
                swimming_pool,multipurpose1,reg_society,security1,smoke_detector,club_house,zym1
                ,garden_lawn,
                (society_ck_24HWS.isChecked() ? "Y" : "N"),
                (society_ck_aerobic_room.isChecked() ? "Y" : "N"),
                (society_ck_amphithreater.isChecked() ? "Y" : "N"),
                (society_ck_atm_bank.isChecked() ? "Y" : "N"),
                (society_ck_banquet_hall.isChecked() ? "Y" : "N"),
                (society_ck_barbeque_pit.isChecked() ? "Y" : "N"),
                (society_ck_basketball_tennis_court.isChecked() ? "Y" : "N"),
                (society_ck_centralized_ac.isChecked() ? "Y" : "N"),
                (society_ck_conference_room.isChecked() ? "Y" : "N"),
                (society_ck_day_care_center.isChecked() ? "Y" : "N"),
                (society_ck_dth_tv_facility.isChecked() ? "Y" : "N"),
                (society_ck_early_learning_play_group.isChecked() ? "Y" : "N"),
                (society_ck_golf_cource.isChecked() ? "Y" : "N"),
                (society_ck_guest_accomadation.isChecked() ? "Y" : "N"),
                (society_ck_indoor_games_room.isChecked() ? "Y" : "N"),
                (society_ck_indoor_bedminton_court.isChecked() ? "Y" : "N"),
                (society_ck_intercom.isChecked() ? "Y" : "N"),
                (society_ck_kids_club.isChecked() ? "Y" : "N"),
                (society_ck_kids_play_area.isChecked() ? "Y" : "N"),
                (society_ck_laundry_service.isChecked() ? "Y" : "N"),
                (society_ck_meditation_center.isChecked() ? "Y" : "N"),
                (society_ck_paved_comound.isChecked() ? "Y" : "N"),
                (society_ck_power_backup.isChecked() ? "Y" : "N"),
                (society_ck_property_maintenace_staff.isChecked() ? "Y" : "N"),
                (society_ck_rain_water_harvesting.isChecked() ? "Y" : "N"),
                (society_ck_recreational_facilities.isChecked() ? "Y" : "N"),
                (society_ck_rentable_community_space.isChecked() ? "Y" : "N"),
                (society_ck_reserverd_parking.isChecked() ? "Y" : "N"),
                (society_ck_school.isChecked() ? "Y" : "N"),
                (society_ck_service_goods_lift.isChecked() ? "Y" : "N"),
                (society_ck_sevage_treatment_plan.isChecked() ? "Y" : "N"),
                (society_ck_shooping_retail.isChecked() ? "Y" : "N"),
                (society_ck_skating_court.isChecked() ? "Y" : "N"),
                (society_ck_strolling_cycling_jogging.isChecked() ? "Y" : "N"),
                (society_ck_vaastu_complaint.isChecked() ? "Y" : "N"),
                (society_ck_visitor_parking.isChecked() ? "Y" : "N"),
                (society_ck_waiting_lounge.isChecked() ? "Y" : "N"),
                (society_ck_waste_disposal.isChecked() ? "Y" : "N"),
                "true");

    }
    public void myClick(View v){
        v.setEnabled(false);
        saveSociety();
        startActivity(new Intent(this,NewGallery.class));
        finish();
    }
}
