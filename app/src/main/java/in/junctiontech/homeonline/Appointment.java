package in.junctiontech.homeonline;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Appointment extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ListView lv;
    //   String[] title={},address={},phone={};
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    private DBHandler db;
    private String id[], no[];
    public static String clicked;
    private int position;
    private MyAdapter ma;
    private RadioButton rb_complete;
    private RadioButton rb_defrrred;
    //private RadioButton rb_cancelled;
    private RadioButton rb_reschedulled;
    private String ststus;
    private RelativeLayout rl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RequestQueue rq;
    private boolean test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        db = new DBHandler(this, "DB", null, 1);
        lv = (ListView) findViewById(R.id.listView);
        rq = Volley.newRequestQueue(this);
        rl = (RelativeLayout) findViewById(R.id.appointmentid);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);

        getDataFromDataBase();
        //   onRefresh();
        //  mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.BLACK,
                Color.CYAN, Color.GRAY, Color.YELLOW, Color.MAGENTA, Color.LTGRAY);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                Appointment.this.position = position;
                clicked = id[position];
//               Toast.makeText(Appointment.this,position+"",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//                Toast.makeText(Appointment.this,id[position]+"",Toast.LENGTH_LONG).show();

                clicked = id[position];
                startActivity(new Intent(Appointment.this, PropertyDetails.class));


            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onRefresh() {

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait while fetching data from server...");
        pDialog.setCancelable(false);
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://qc.homeonline.com/dbho/Api/appointment",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // for (int i = 0; i < 5; i++)
                        DBHandler.longInfo(response);
                        //Log.d("response from Server ",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(Appointment.this,jsonObject.getString("status"),Toast.LENGTH_LONG).show();
//                            Toast.makeText(Appointment.this,jsonObject.getString("code"),Toast.LENGTH_LONG).show();
                            // Toast.makeText(Appointment.this,jsonObject.getString("data"),Toast.LENGTH_LONG).show();
                            JSONObject js = new JSONObject(jsonObject.getString("data"));
                            //         Toast.makeText(Appointment.this,js.getString("userID"),Toast.LENGTH_LONG).show();

                            //   Toast.makeText(Appointment.this,response,Toast.LENGTH_LONG).show();

                            JSONArray obj = js.getJSONArray("appointment_list");


//                            Toast.makeText(Appointment.this,obj.length()+"",Toast.LENGTH_LONG).show();
                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject n = obj.getJSONObject(i);
//                                Toast.makeText(Appointment.this,n.getString("name"),Toast.LENGTH_LONG).show();
//                                Toast.makeText(Appointment.this,n.getString("phone"),Toast.LENGTH_LONG).show();
//                                Toast.makeText(Appointment.this,n.getString("address"),Toast.LENGTH_LONG).show();


                                db.saveData(n.getString("appointmentID"),
                                        n.getString("name"),
                                        n.getString("address"),
                                        n.getString("phone"),
                                        n.getString("appointmentStatus"),
                                        n.getString("appointmentTime"),
                                        n.getString("propertyPurpose"));

                             //   String status = n.getString("appointmentStatus");
                                // if("update".equalsIgnoreCase(status))
                                {

                                    boolean check = db.checkStatusUpdateFromServer(n.getString("appointmentID"));
                                    if (!check) {

                                        ContentValues contentValuesAppointment = null;
                                        try {

                                            contentValuesAppointment = new ContentValues();
                                            contentValuesAppointment.put("id", n.getString("appointmentID"));
                                            contentValuesAppointment.put("name", n.getString("name"));
                                            contentValuesAppointment.put("description", n.getString("address"));
                                            contentValuesAppointment.put("address2", n.getString("address2"));// TODO
                                            contentValuesAppointment.put("phone", n.getString("phone"));
                                            contentValuesAppointment.put("status", n.getString("appointmentStatus"));
                                            contentValuesAppointment.put("appointmentTime", n.getString("appointmentTime"));
                                            contentValuesAppointment.put("update_from_server", "true");
                                            contentValuesAppointment.put("property_status", n.optString("property_current_status",""));
                                            JSONObject details = n.getJSONObject("details");

                                  /*   Property Data*/

                                            contentValuesAppointment.put("property_type", details.optString("propertyType","57"));  //  TODO
                                         //   contentValuesAppointment.put("bhk_type", details.getString("bhkType"));
                                            contentValuesAppointment.put("no_of_livingroom", details.optString("numOfLivingRooms", "1"));
                                            contentValuesAppointment.put("no_of_bedroom", details.optString("numOfBedrooms", "1"));
                                            contentValuesAppointment.put("no_of_kitchen", details.optString("numofKitchens", "1"));
                                            contentValuesAppointment.put("no_of_bathroom", details.optString("numOfToilet", "1"));
                                            contentValuesAppointment.put("no_of_balcony", details.optString("balcony", "1"));
                                            contentValuesAppointment.put("preferred_visit_time", details.optString("preferredVisitTime", ""));
                                            contentValuesAppointment.put("possesion_date", details.optString("passessionTime","0000"));
                                          //  contentValuesAppointment.put("property_status", details.optString("property_current_status",""));

                                            //contentValuesAppointment.put("status_property_detail", "true");


                                   /*  Appointment Advertizer details */

                                //            contentValuesAppointment.put("id", n.getString("appointmentID"));
                                            contentValuesAppointment.put("mobile", details.optString("appointmentAlterPhone",""));
                                            contentValuesAppointment.put("email", details.optString("appointmentEmail",""));
                                //            contentValuesAppointment.put("owner_broker", details.optString("advertiserType",""));
                                 //           contentValuesAppointment.put("developer_type", details.getString("developerType"));
                                            contentValuesAppointment.put("owner_type", details.optString("ownerType","Freehold"));
                                  //          contentValuesAppointment.put("building_no", details.getString("building_no_or_name"));
                                            contentValuesAppointment.put("society_name", details.optString("SocietyName",""));
                                   //         contentValuesAppointment.put("flate_number", details.getString("flatNO"));
                                   //         contentValuesAppointment.put("wing", details.getString("apointmentWing"));
                                  //          contentValuesAppointment.put("address1", details.getString("address1"));

                                  //          contentValuesAppointment.put("sub_locality", details.getString("subLocality"));
                                            contentValuesAppointment.put("pincode", details.optString("pincode",""));
                                 //           contentValuesAppointment.put("landmark", details.getString("landmark"));
                                            contentValuesAppointment.put("floor_no", details.optString("floorNum", ""));
                                            //  contentValuesAppointment.put("status_advertiser_detail", "true");

                                     /* Rent Screen Data*/

                                            contentValuesAppointment.put("brokerage_fee", details.optString("brokarageFee", ""));

                                            contentValuesAppointment.put("food", details.optString("apointmentFood","No Preferences"));
                                            contentValuesAppointment.put("lease_type", details.optString("leaseType","Family"));
                                            contentValuesAppointment.put("pets_allowed", details.optString("petsAllowed","No"));
                                            contentValuesAppointment.put("rent_negotiable", details.optString("rentNegotiable","n"));
                                            contentValuesAppointment.put("security_negotiable", details.optString("securityNegotiable","n"));
                                            contentValuesAppointment.put("security_deposit", details.optString("securityDeposit",""));
                                          //  contentValuesAppointment.put("availability_date", details.optString("ap_availability_date","0000"));
                                            //   contentValuesAppointment.put("status_rentscreen", "true");

                                    /*Pricing Data*/

                                            contentValuesAppointment.put("builtup_area", details.optString("builtupArea", ""));
                                            contentValuesAppointment.put("carpet_area", details.optString("carpetArea", ""));
                                            contentValuesAppointment.put("rent_ammount", details.optString("rentAmount", ""));
                                            contentValuesAppointment.put("no_of_floor", details.optString("noOfFloors", ""));
                                            contentValuesAppointment.put("age_of_building", details.optString("buildingAge", ""));
                                            contentValuesAppointment.put("no_of_lift", details.optString("numOfLifts","0"));
                                            contentValuesAppointment.put("pricing_plot_area", details.optString("plotArea",""));
                                            contentValuesAppointment.put("pricing_sale_status", details.optString("salesStatus",""));
                                            contentValuesAppointment.put("maintainance", details.optString("maintainenceFee",""));
                                            contentValuesAppointment.put("maintanancechargefrequency", details.optString("maintenance_frequency",""));
                                            contentValuesAppointment.put("price_plc", details.optString("price_plc",""));
                                            contentValuesAppointment.put("price_parking", details.optString("price_parking",""));
                                            contentValuesAppointment.put("price_club", details.optString("price_club",""));

                                            //   contentValuesAppointment.put("status_pricing", "true");

                                    /*  Residential Data*/

 //                                           contentValuesAppointment.put("no_of_building", details.getString("noOfBuilding"));
                                          //  contentValuesAppointment.put("no_of_storys", details.getString("numOfStory"));

                                   //         contentValuesAppointment.put("no_of_storys", details.optString("numOfStory",null));
                                    //        Toast.makeText(Appointment.this, details.optString("numOfStory",null), Toast.LENGTH_SHORT).show();

                                            contentValuesAppointment.put("servant_room", details.optString("serventRoom","n"));
                                            contentValuesAppointment.put("prayer_room", details.optString("prayerRoom","n"));
                                    //        contentValuesAppointment.put("terrace_access", details.optString("terrace","n"));
                                            contentValuesAppointment.put("private_access", details.optString("privateTerrace","n"));
                                           contentValuesAppointment.put("main_entrance_facing", details.optString("mainEnterenceFacing","North"));
                                            contentValuesAppointment.put("power_backup", details.optString("powerBackup","Partial"));
                                            contentValuesAppointment.put("water_supply_municipal", details.optString("waterSupply_municipal","n"));
                                            contentValuesAppointment.put("water_supply_borewell", details.optString("waterSupply_borewell","n"));
                                            contentValuesAppointment.put("waterbackup_grounded_tanks", details.optString("waterBackUp_grounded_tank","n"));
                                            contentValuesAppointment.put("waterbackup_terrace_tanks", details.optString("waterBackUp_terrace_tank","n"));
                                            contentValuesAppointment.put("wifi", details.optString("wifiInternet","n"));
                                            contentValuesAppointment.put("solar_heater", details.optString("solarWaterHeater","n"));
                                            contentValuesAppointment.put("parking_type", details.optString("parking_type",""));
                                            contentValuesAppointment.put("furnishing_status", details.optString("furnishing_status",""));
                                            contentValuesAppointment.put("balcony", details.optString("balcony_dp",""));
                                            contentValuesAppointment.put("common_area", details.optString("common_area",""));


                                            //   contentValuesAppointment.put("status_residential", "true");


                                     /*   Society Data*/

                                            contentValuesAppointment.put("boundary_wall", details.optString("boundryWall", "NA"));
                                            contentValuesAppointment.put("societydata_gated_community", details.optString("gatedCommunity","n"));
                                            contentValuesAppointment.put("societydata_reg_society", details.optString("registeredSociety","n"));
                                            //          contentValuesAppointment.put("societydata_society_overheadtank", details.getString("societyOverheadTank"));
                                            contentValuesAppointment.put("societydata_cctv_servillance", details.optString("cctvSurvelance","n"));
                                            contentValuesAppointment.put("societydata_fire_hydrant_system", details.optString("fireHyderantSystem","n"));
                                            contentValuesAppointment.put("societydata_swiming_pool", details.optString("swimmingPool","n"));
                                            contentValuesAppointment.put("societydata_multi_purpose", details.optString("multipurposHall","n"));
                                            contentValuesAppointment.put("societydata_security", details.optString("security","n"));
                                            contentValuesAppointment.put("societydata_smoke_detector", details.optString("smokeDetector","n"));
                                            contentValuesAppointment.put("societydata_club_house", details.optString("clubHouse","n"));
                                            contentValuesAppointment.put("societydata_zym", details.optString("gym","n"));
                                            contentValuesAppointment.put("garden_lawn", details.optString("gardenLawn","n"));

                                            contentValuesAppointment.put("society_ck_24HWS", details.optString("24hourswatersupply","n"));
                                            contentValuesAppointment.put("society_ck_aerobic_room", details.optString("aerobicRoom","n"));
                                            contentValuesAppointment.put("society_ck_amphithreater", details.optString("amphithreater","n"));
                                            contentValuesAppointment.put("society_ck_atm_bank", details.optString("atm_or_bank","n"));
                                            contentValuesAppointment.put("society_ck_banquet_hall", details.optString("banquetHall","n"));
                                            contentValuesAppointment.put("society_ck_barbeque_pit", details.optString("barbequePit","n"));
                                            contentValuesAppointment.put("society_ck_basketball_tennis_court", details.optString("basketBall_or_TennisCourt","n"));
                                            contentValuesAppointment.put("society_ck_centralized_ac", details.optString("centralizedAC","n"));
                                            contentValuesAppointment.put("society_ck_conference_room", details.optString("confrenceRoom","n"));
                                            contentValuesAppointment.put("society_ck_day_care_center", details.optString("dayCareCenter","n"));
                                            contentValuesAppointment.put("society_ck_dth_tv_facility", details.optString("dthTvFacilities","n"));
                                            contentValuesAppointment.put("society_ck_early_learning_play_group", details.optString("earlyLearningCentre_playGroup","n"));
                                            contentValuesAppointment.put("society_ck_golf_cource", details.optString("golfCource","n"));
                                            contentValuesAppointment.put("society_ck_guest_accomadation", details.optString("guestAccomadation","n"));
                                            contentValuesAppointment.put("society_ck_indoor_games_room", details.optString("indoorGamesRoom","n"));
                                            contentValuesAppointment.put("society_ck_indoor_bedminton_court", details.optString("indoorSquash_or_bedmintonCourt","n"));
                                            contentValuesAppointment.put("society_ck_intercom", details.optString("intercom","n"));
                                            contentValuesAppointment.put("society_ck_kids_club", details.optString("kidsClub","n"));
                                            contentValuesAppointment.put("society_ck_kids_play_area", details.optString("kidsPlayArea","n"));
                                            contentValuesAppointment.put("society_ck_laundry_service", details.optString("laundryService","n"));
                                            contentValuesAppointment.put("society_ck_meditation_center", details.optString("meditiationCenter","n"));
                                            contentValuesAppointment.put("society_ck_paved_comound", details.optString("pavedCompound","n"));
                                            contentValuesAppointment.put("society_ck_power_backup", details.optString("powerBackupSociety","n"));
                                            contentValuesAppointment.put("society_ck_property_maintenace_staff", details.optString("property_or_MaintenaceStaff","n"));
                                            contentValuesAppointment.put("society_ck_rain_water_harvesting", details.optString("rainWaterHarvesting","n"));
                                            contentValuesAppointment.put("society_ck_recreational_facilities", details.optString("recreationalPool_or_Facilities","n"));
                                            contentValuesAppointment.put("society_ck_rentable_community_space", details.optString("rentableCommunitySpace","n"));
                                            contentValuesAppointment.put("society_ck_reserverd_parking", details.optString("reserverdParking","n"));
                                            contentValuesAppointment.put("society_ck_school", details.optString("school","n"));
                                            contentValuesAppointment.put("society_ck_service_goods_lift", details.optString("service_or_GoodsLift","n"));
                                            contentValuesAppointment.put("society_ck_sevage_treatment_plan", details.optString("sevageTreatmentPlan","n"));
                                            contentValuesAppointment.put("society_ck_shooping_retail", details.optString("shoopingCenter_or_retailShop","n"));
                                            contentValuesAppointment.put("society_ck_skating_court", details.optString("skatingCourt","n"));
                                            contentValuesAppointment.put("society_ck_strolling_cycling_jogging", details.optString("strollingCycling_or_joggingTrack","n"));
                                            contentValuesAppointment.put("society_ck_vaastu_complaint", details.optString("vaastuComplaint","n"));
                                            contentValuesAppointment.put("society_ck_visitor_parking", details.optString("visitorParking","n"));
                                            contentValuesAppointment.put("society_ck_waiting_lounge", details.optString("waitingLounge","n"));
                                            contentValuesAppointment.put("society_ck_waste_disposal", details.optString("wasteDisposal","n"));
                                            contentValuesAppointment.put("society_notes", details.optString("society_notes","HELLO WORLD"));

                                            //   contentValuesAppointment.put("status_society", "true");



                                        } catch (JSONException e) {
                                          //  Toast.makeText(Appointment.this, "No Details for appointment = " + n.getString("appointmentID"), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        } finally {
                                            if (contentValuesAppointment != null)
                                                db.saveAppointmentOtherDetails(contentValuesAppointment);
                                            ;
                                        }


                                        ContentValues contentValues[] = null;
                                        try {

                                            JSONArray bedrooms = n.getJSONArray("bedrooms");
                                            int k = bedrooms.length();
                                            contentValues = new ContentValues[k];
                                            for (int j = 0; j < k; j++) {

                                                JSONObject bedroomObject = bedrooms.getJSONObject(j);
                                                ContentValues contentValues1 = new ContentValues();
                                                contentValues1.put("id", bedroomObject.getString("appointmentID"));
                                                String s[] = bedroomObject.getString("bedroomID").split("m");
                                                contentValues1.put("bedroom_ID", s[1]);
                                                contentValues1.put("bed", bedroomObject.optString("bed","n"));
                                                contentValues1.put("ac", bedroomObject.optString("ac","n"));
                                                contentValues1.put("tv", bedroomObject.optString("tv","n"));
                                                contentValues1.put("dressing_table", bedroomObject.optString("dressingTable","n"));
                                                contentValues1.put("wardrobe", bedroomObject.optString("wardrobe","n"));
                                                contentValues1.put("window", bedroomObject.optString("window","n"));
                                                contentValues1.put("attached_balcony", bedroomObject.optString("attachedBalconey","n"));
                                                contentValues1.put("attached_bathroom", bedroomObject.optString("attachedBathroom","n"));
                                                contentValues1.put("flooring_type", bedroomObject.optString("flooringType",""));
                                                contentValues1.put("false_ceiling", bedroomObject.optString("falseCeiling","n"));
                                                //   contentValues1.put("status_bed", "true");
                                                contentValues[j] = contentValues1;

                                            }
                                        } catch (JSONException e) {
                                            //Log.d("Bedroom","Blank");
                                           // Toast.makeText(Appointment.this, "No Bedroom data for appointment = " + n.getString("appointmentID"), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        } finally {
                                            if (contentValues != null)
                                                db.setDataFromServer(contentValues, "BedRoom");
                                        }

                                        contentValues = null;
                                        try {

                                            JSONArray livingroom = n.getJSONArray("livingroom");
                                            int k = livingroom.length();
                                            contentValues = new ContentValues[k];
                                            for (int j = 0; j < k; j++) {

                                                JSONObject livingroomJSONObject = livingroom.getJSONObject(j);
                                                ContentValues contentValues1 = new ContentValues();
                                                contentValues1.put("id", livingroomJSONObject.getString("appointmentID"));
                                                String s[] = livingroomJSONObject.getString("livingRoomID").split("m");
                                                contentValues1.put("livingRoom_ID", s[1]);
                                                contentValues1.put("sofa", livingroomJSONObject.optString("sofa","n"));
                                                contentValues1.put("dining_table", livingroomJSONObject.optString("dinningTable","n"));
                                                contentValues1.put("ac", livingroomJSONObject.optString("ac","n"));
                                                contentValues1.put("tv", livingroomJSONObject.optString("tv","n"));
                                                contentValues1.put("shoe_rack", livingroomJSONObject.optString("shoeRack","n"));
                                                contentValues1.put("flooring_type", livingroomJSONObject.optString("flooringType",""));
                                                contentValues1.put("false_ceiling", livingroomJSONObject.optString("falseCeiling","n"));
                                                //   contentValues1.put("status_living", "true");

                                                contentValues[j] = contentValues1;

                                            }
                                        } catch (JSONException e) {
                                          //  Toast.makeText(Appointment.this, "No LivingRoom data for appointment = " + n.getString("appointmentID"), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        } finally {
                                            if (contentValues != null) {
                                                db.setDataFromServer(contentValues, "LivingRoom");
                                            }
                                        }

                                        contentValues = null;
                                        try {

                                            JSONArray kitchens = n.getJSONArray("kitchens");
                                            int k = kitchens.length();
                                            contentValues = new ContentValues[k];
                                            for (int j = 0; j < k; j++) {

                                                JSONObject kitchensJSONObject = kitchens.getJSONObject(j);
                                                ContentValues contentValues1 = new ContentValues();
                                                contentValues1.put("id", kitchensJSONObject.getString("appointmentID"));
                                                String s[] = kitchensJSONObject.getString("kitchenID").split("n");
                                                contentValues1.put("kitchen_ID", s[1]);
                                                contentValues1.put("kitchen_cabinetes", kitchensJSONObject.optString("cabinet","na"));
                                                contentValues1.put("kitchen_refridgerator", kitchensJSONObject.optString("refrigerator","n"));
                                                contentValues1.put("kitchen_water_purifier", kitchensJSONObject.optString("waterPurifier","n"));
                                                contentValues1.put("kitchen_loft", kitchensJSONObject.optString("loft","n"));
                                                contentValues1.put("kitchen_gas_pipeline", kitchensJSONObject.optString("gaspipLine","n"));
                                                contentValues1.put("kitchen_microwave", kitchensJSONObject.optString("microwave","n"));
                                                contentValues1.put("kitchen_chimney", kitchensJSONObject.optString("chimneyExhaust","n"));
                                                contentValues1.put("kitchen_plateform_material", kitchensJSONObject.optString("plateformMaterial",""));
                                                contentValues1.put("kitchen_flooring", kitchensJSONObject.optString("kitchen_flooring",""));

                                                //   contentValues1.put("status_kitchen", "true");

                                                contentValues[j] = contentValues1;

                                            }
                                        } catch (JSONException e) {
                                           // Toast.makeText(Appointment.this, "No Kitchen data for appointment = " + n.getString("appointmentID"), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        } finally {
                                            if (contentValues != null) {
                                                db.setDataFromServer(contentValues, "Kitchen");
                                            }
                                        }

                                        contentValues = null;
                                        try {

                                            JSONArray toilets = n.getJSONArray("toilets");
                                            int k = toilets.length();
                                            contentValues = new ContentValues[k];
                                            for (int j = 0; j < k; j++) {

                                                JSONObject toiletsJSONObject = toilets.getJSONObject(j);
                                                ContentValues contentValues1 = new ContentValues();
                                                contentValues1.put("id", toiletsJSONObject.getString("appointmentID"));
                                                String s[] = toiletsJSONObject.getString("toiletID").split("et");
                                                contentValues1.put("toilet_ID", s[1]);
                                                contentValues1.put("bathroom_bath_type",toiletsJSONObject.optString("type","attached"));
                                                contentValues1.put("bathroom_geyser", toiletsJSONObject.optString("geyser","n"));
                                                contentValues1.put("bathroom_toilet", toiletsJSONObject.optString("Style","indian"));
                                                contentValues1.put("bathroom_glass_partition", toiletsJSONObject.optString("glassPartition","n"));
                                                contentValues1.put("bathroom_shower_curtain", toiletsJSONObject.optString("showerPartition","n"));
                                                contentValues1.put("bathroom_bath_tub", toiletsJSONObject.optString("bathTub","n"));
                                                contentValues1.put("bathroom_windows", toiletsJSONObject.optString("window","n"));
                                                contentValues1.put("bathroom_cabinets", toiletsJSONObject.optString("cabinate","n"));
                                                contentValues1.put("bathromm_exhaust_fan", toiletsJSONObject.optString("exhaustFan","n"));
                                                contentValues1.put("bathroom_flooring_type", toiletsJSONObject.optString("flooringType",""));
                                                contentValues1.put("washing_machine", toiletsJSONObject.optString("washing_machine","n"));
                                                //   contentValues1.put("status_bath", "true");

                                                contentValues[j] = contentValues1;

                                            }
                                        } catch (JSONException e) {
                                          //  Toast.makeText(Appointment.this, "No BathRoom data for appointment = " + n.getString("appointmentID"), Toast.LENGTH_LONG).show();
                                            e.printStackTrace();
                                        } finally {
                                            if (contentValues != null) {
                                                db.setDataFromServer(contentValues, "BathRoom");
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Appointment.this, "Invalid Response From Server", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        getDataFromDataBase();
                        // update();
                        mSwipeRefreshLayout.setRefreshing(false);
                        pDialog.dismiss();
                        test=true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //for (int i = 0; i < 2; i++)
                //   Toast.makeText(Appointment.this, error.toString(), Toast.LENGTH_LONG).show();
                //  getDataFromDataBase();
//                        getDataFromDataBase();
                mSwipeRefreshLayout.setRefreshing(false);
                test=true;
                getDataFromDataBase();

                String err = error.getMessage();
                if (error instanceof NoConnectionError) {
                    err = "Check Your Internet Connection.";

                }

                Snackbar.make(rl, err, Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        onRefresh();
                        // Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();
                    }
                }).show();
                pDialog.dismiss();
            }


        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences sp = Appointment.this.getSharedPreferences("Login", Appointment.this.MODE_PRIVATE);
                params.put("userid", sp.getString("userID", "Not Found"));
                //params.put("userid", "1");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                /*Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("abc", "value");
                return headers;*/
                return createBasicAuthHeader("homeonline", "helloworld2016");
            }

            Map<String, String> createBasicAuthHeader(String username, String password) {
                Map<String, String> headerMap = new HashMap<String, String>();

                String credentials = username + ":" + password;
                String encodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
              //  headerMap.put("Authorization", "Basic " + encodedCredentials);
                headerMap.put("Content-Type","application/x-www-form-urlencoded");
                headerMap.put("abc", "value");

                return headerMap;
            }
        };
// Add the request to the RequestQueue.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        rq.add(stringRequest);


    }


    public void update() {
        String[][] abc = db.getAllData();
        id = abc[3];
        no = abc[2];
        ma.setRent_sale_status(abc[6]);
        ma.setDatetime(abc[5]);
        ma.updateTitle(abc[0]);
        ma.updateAddress(abc[1]);
        ma.updatePhone(abc[2]);
        ma.updateStatus(abc[4]);
        ma.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();

    }

    public void getDataFromDataBase() {
        String[][] abc = db.getAllData();
        id = abc[3];
        no = abc[2];


        //  ma=null;

        if (test && abc[3].length == 0)
            Toast.makeText(this,"Currently no appointment available\n Try to refresh the list...!",Toast.LENGTH_LONG).show();
        ma = new MyAdapter(Appointment.this, abc[0],
                abc[1],
                abc[2], abc[4], abc[5], abc[6]);

        lv.setAdapter(ma);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_long, menu);
        menu.setHeaderTitle("Select Option");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        String s = "";
        switch (item.getItemId()) {


            case R.id.calling:
                s = "Call";
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + no[position]));
                startActivity(intent);
                break;
            case R.id.status:
                completeStatus();
                s = "status";

                break;

            case R.id.send:
                s = "Sync";
                db.sendDataForParticularId(id[position]);
                break;

            case R.id.delete:
                alert();
                s = "Delete";
                break;

        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);


    }

    public void alert() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        // builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteForParticularID();
                deleteImage("thumbnail");
                deleteImage("DB");
                deleteImage("medium");

                getDataFromDataBase();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void deleteImage(String folder) {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                folder);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return;
            }
        }
        File[] f = mediaStorageDir.listFiles();
        int check = 0;
        for (int i = 0; i < f.length; i++) {

            if (f[i].isDirectory()) {
            } else if (f[i].getName().endsWith(".jpg") && f[i].getName().contains(Appointment.clicked)) {
                String s[] = f[i].getName().split("_");
                if (s[0].equalsIgnoreCase("ID=" + Appointment.clicked)) {
                    f[i].delete();
                    check = 1;

                }
            }
        }
        if (check == 0)
            Toast.makeText(this, "NO IMAGE FOUND", Toast.LENGTH_LONG).show();


    }

    private void completeStatus() {
        AlertDialog.Builder alt = new AlertDialog.Builder(Appointment.this);
        alt.setTitle(" Appoinment Status");


        alt.setPositiveButton("OK", new MyClick());
        alt.setNegativeButton("CANCEL", new MyClick());
        LinearLayout ll = new LinearLayout(Appointment.this);
        ll.setPadding(16, 16, 16, 16);

        rb_complete = new RadioButton(Appointment.this);
        rb_complete.setText("Complete");
        rb_defrrred = new RadioButton(Appointment.this);
        rb_defrrred.setText("Deferred");
       /* rb_cancelled = new RadioButton(Appointment.this);
        rb_cancelled.setText("Cancelled");*/
        rb_reschedulled = new RadioButton(Appointment.this);
        rb_reschedulled.setText("Rescheduled");


        RadioGroup rg = new RadioGroup(Appointment.this);
        rg.addView(rb_complete);
        rg.addView(rb_defrrred);
       /* rg.addView(rb_cancelled);*/
        rg.addView(rb_reschedulled);

        ll.addView(rg);

        alt.setView(ll);

        Bundle b = db.getStatus();

        String s = b.getString("status");
        //   Toast.makeText(Appointment.this, s, Toast.LENGTH_SHORT).show();
        if (s == null) ;
        else if (s.equalsIgnoreCase("Complete")) {
            rb_complete.setChecked(true);
        } else if (s.equalsIgnoreCase("Deferred")) {
            rb_defrrred.setChecked(true);
        } /*else if (s.equalsIgnoreCase("Cancelled")) {
            rb_cancelled.setChecked(true);
        }*/ else if (s.equalsIgnoreCase("Rescheduled")) {
            rb_reschedulled.setChecked(true);
        }

        alt.show();
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
            startActivity(new Intent(this, Prefs.class));

        } else if (id == R.id.main_screen_aboutus) {
            //  Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AboutUs.class));
        } else if (id == R.id.main_screen_help) {
            // Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, Help.class));
        }
       /* else if(id==R.id.main_screen_junctiontech) {
            //  Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, AboutJunctionTech.class));
        }*/

        return super.onOptionsItemSelected(item);
    }


    class MyAdapter extends ArrayAdapter<String> {
        private final Context context;

        String[] title = {}, address = {}, phone = {}, status = {}, datetime = {}, rent_sale_status = {};
        private LayoutInflater inflater;

        public MyAdapter(Context context, String[] title, String[] address, String[] phone, String[] status, String[] datetime, String[] rent_sale_status) {
            super(context, R.layout.mylist, title);
            this.context = context;
            this.title = title;
            this.address = address;
            this.phone = phone;
            this.status = status;
            this.datetime = datetime;
            this.rent_sale_status = rent_sale_status;


        }

        public void setRent_sale_status(String[] rent_sale_status) {
            this.rent_sale_status = rent_sale_status;
        }

        public void setDatetime(String[] datetime) {
            this.datetime = datetime;
        }

        public void updateStatus(String[] status) {
            this.status = status;
        }

        public void updateTitle(String[] title) {
            this.title = title;
        }

        public void updateAddress(String[] address) {
            this.address = address;
        }

        public void updatePhone(String[] phone) {
            this.phone = phone;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // super.getView(position, convertView, parent);

            if (convertView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.mylist, null);
            }

            final ViewHolder holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv1);
            holder.address = (TextView) convertView.findViewById(R.id.tv2);
            holder.phone = (ImageButton) convertView.findViewById(R.id.childbutton);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.datetime = (TextView) convertView.findViewById(R.id.datetime);
            holder.rent_sale_status = (TextView) convertView.findViewById(R.id.rent_sale_status);


            holder.title.setText(title[position]);
            holder.address.setText(address[position]);
            //  holder.phone.setText(phone[position]);
            holder.status.setText(status[position]);
            holder.datetime.setText(datetime[position]);
            holder.rent_sale_status.setText(rent_sale_status[position]);


            final String temp = (phone[position]);


            holder.phone.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //    if (customListner != null) {
                    Toast.makeText(Appointment.this, "CALL",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:" + temp));
                    startActivity(intent);

                }

            });


            return convertView;
        }

        public class ViewHolder {
            TextView title, address, status;
            ImageButton phone;
            TextView datetime, rent_sale_status;

        }
    }

    private class MyClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {


            if (rb_complete.isChecked()) {
                ststus = rb_complete.getText().toString();

            }
            if (rb_defrrred.isChecked()) {
                ststus = rb_defrrred.getText().toString();

            }
            /*if (rb_cancelled.isChecked()) {
                ststus = rb_cancelled.getText().toString();

            }*/
            if (rb_reschedulled.isChecked()) {
                ststus = rb_reschedulled.getText().toString();

            }

            if (which == -1) {
                db.setStatus(ststus);

            }
            update();
            //      String[][] abc=db.getAllData();

            //        ma.updateStatus(abc[4]);
            //        ma.notifyDataSetChanged();


        }
    }


}
