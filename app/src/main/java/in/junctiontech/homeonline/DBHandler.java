package in.junctiontech.homeonline;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Junction on 8/17/2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    private Context c;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, null, version);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Appointments(" +
                "id TEXT PRIMARY KEY," +
                "description TEXT," +
                "status TEXT," +
                "update_from_server TEXT," +
                "rent_sale_status TEXT," +

                // RentScreen Fileds
                "brokerage_fee TEXT," +
                "food TEXT," +
                "lease_type TEXT," +
                "pets_allowed TEXT," +
                "rent_negotiable TEXT," +
                "security_deposit TEXT," +
                "security_negotiable TEXT," +
                "status_rentscreen TEXT," +
                //    "availability_date," +   //newly added    // end of Rent Field

                // Property Fields
                //    "bhk_type TEXT," +
                "property_type TEXT," +
                "no_of_livingroom TEXT," +
                "no_of_bedroom TEXT," +
                "no_of_kitchen TEXT," +
                "no_of_bathroom TEXT," +
                "no_of_balcony TEXT," +
                "preferred_visit_time TEXT," +
                "possesion_date TEXT," +
                "property_status TEXT," +
                "status_property_detail TEXT," +  // end of Property Field

                // Advertiser Fields
                "name TEXT," +
                "phone TEXT," +
                "mobile TEXT," +
                "email TEXT," +
             //   "owner_broker TEXT," +
                //  "developer_type TEXT," +
                "owner_type TEXT," +
                // "building_no TEXT," +
                "society_name TEXT," +
                //  "flate_number TEXT," +
                //  "wing TEXT," +
                //  "address1 TEXT," +
                "address2 TEXT," +
                //  "sub_locality TEXT," +
                "pincode TEXT," +
                //   "landmark TEXT," +
                "floor_no TEXT," +
                "status_advertiser_detail TEXT," +
                // end of Advertiser Field

                // Pricing Fields
                "builtup_area TEXT," +
                "carpet_area TEXT," +
                "rent_ammount TEXT," +
                "no_of_floor INTEGER," +
                "age_of_building INTEGER," +
                "no_of_lift TEXT," +
                // newly added
                "pricing_plot_area TEXT," +
                "pricing_sale_status TEXT," +
                "maintanancechargefrequency TEXT," +
                "maintainance TEXT," +
                "units TEXT," +
                "price_plc TEXT," +
                "price_parking TEXT," +
                "price_club TEXT," +
                "status_pricing TEXT," +  // end of Pricing Field


                // Society Fields
                "boundary_wall TEXT," +
                "societydata_gated_community TEXT," +
                "societydata_reg_society TEXT," +
                //      "societydata_society_overheadtank TEXT," +
                "societydata_security TEXT," +
                "societydata_cctv_servillance TEXT," +
                "societydata_smoke_detector TEXT," +
                "societydata_fire_hydrant_system TEXT," +
                "societydata_club_house TEXT," +
                "societydata_swiming_pool TEXT," +
                "societydata_zym TEXT," +
                "garden_lawn TEXT," +
                "societydata_multi_purpose TEXT," +
                // Newly Added
                "society_ck_24HWS TEXT," +
                "society_ck_aerobic_room TEXT," +
                "society_ck_amphithreater TEXT," +
                "society_ck_atm_bank TEXT," +
                "society_ck_banquet_hall TEXT," +
                "society_ck_barbeque_pit TEXT," +
                "society_ck_basketball_tennis_court TEXT," +
                "society_ck_centralized_ac TEXT," +
                "society_ck_conference_room TEXT," +
                "society_ck_day_care_center TEXT," +
                "society_ck_dth_tv_facility TEXT," +
                "society_ck_early_learning_play_group TEXT," +
                "society_ck_golf_cource TEXT," +
                "society_ck_guest_accomadation TEXT," +
                "society_ck_indoor_games_room TEXT," +
                "society_ck_indoor_bedminton_court TEXT," +
                "society_ck_intercom TEXT," +
                "society_ck_kids_club TEXT," +
                "society_ck_kids_play_area TEXT," +
                "society_ck_laundry_service TEXT," +
                "society_ck_meditation_center TEXT," +
                "society_ck_paved_comound TEXT," +
                "society_ck_power_backup TEXT," +
                "society_ck_property_maintenace_staff TEXT," +
                "society_ck_rain_water_harvesting TEXT," +
                "society_ck_recreational_facilities TEXT," +
                "society_ck_rentable_community_space TEXT," +
                "society_ck_reserverd_parking TEXT," +
                "society_ck_school TEXT," +
                "society_ck_service_goods_lift TEXT," +
                "society_ck_sevage_treatment_plan TEXT," +
                "society_ck_shooping_retail TEXT," +
                "society_ck_skating_court TEXT," +
                "society_ck_strolling_cycling_jogging TEXT," +
                "society_ck_vaastu_complaint TEXT," +
                "society_ck_visitor_parking TEXT," +
                "society_ck_waiting_lounge TEXT," +
                "society_ck_waste_disposal TEXT," +
                "status_society TEXT," +
                "society_notes TEXT," +

                // ending of Residential Field

                //  Residential Fields
                //     "no_of_building TEXT," +
                //     "no_of_storys INTEGER," +
                "servant_room TEXT," +
                "prayer_room TEXT," +
                //     "terrace_access TEXT," +
                "private_access TEXT," +
                "main_entrance_facing TEXT," +
                "power_backup TEXT," +
                "water_supply_municipal TEXT," +
                "water_supply_borewell TEXT," +
                "waterbackup_grounded_tanks TEXT," +
                "waterbackup_terrace_tanks TEXT," +
                "parking_type TEXT," +
                "furnishing_status TEXT," +
                "balcony TEXT," +
                "common_area TEXT," +
                "wifi TEXT," +
                "solar_heater TEXT," +
                "status_residential TEXT," +  // ending of Residential Field

                "appointmentTime TEXT)");


        db.execSQL("CREATE TABLE LivingRoom(livingRoom_ID TEXT," +
                "id TEXT," +
                "sofa TEXT," +
                "dining_table TEXT," +
                "ac TEXT," +
                "tv TEXT," +
                "shoe_rack TEXT," +
                "flooring_type TEXT," +
                "status_living TEXT," +
                "false_ceiling TEXT," +
                "PRIMARY KEY (id,livingRoom_ID)," +
                "FOREIGN KEY (id) REFERENCES Appointments(id))");

        db.execSQL("CREATE TABLE WashDry(washdry_ID TEXT," +
                "id TEXT," +
                "washdry_washing_machinet TEXT," +
                "washdry_flooring_type TEXT," +
                "status_wash TEXT," +
                "PRIMARY KEY (id,washdry_ID)," +
                "FOREIGN KEY (id) REFERENCES Appointments(id))");

        db.execSQL("CREATE TABLE BedRoom(bedroom_ID TEXT," +
                "id TEXT," +
                "tv TEXT," +
                "ac TEXT," +
                "bed TEXT," +
                "wardrobe TEXT," +
                "attached_balcony TEXT," +
                "dressing_table TEXT," +
                "false_ceiling TEXT," +
                "attached_bathroom TEXT," +
                "window TEXT," +
                "status_bed TEXT," +
                "flooring_type TEXT," +
                "PRIMARY KEY (id,bedroom_ID)," +
                "FOREIGN KEY (id) REFERENCES Appointments(id))");

        db.execSQL("CREATE TABLE Kitchen(kitchen_ID TEXT," +
                "id TEXT," +
                "kitchen_cabinetes TEXT," +
                "kitchen_refridgerator TEXT," +
                "kitchen_water_purifier TEXT," +
                "kitchen_loft TEXT," +
                "kitchen_gas_pipeline TEXT," +
                "kitchen_microwave TEXT," +
                "kitchen_chimney TEXT," +
                "kitchen_plateform_material TEXT," +
                "kitchen_flooring TEXT," +
                "status_kitchen TEXT," +
                "PRIMARY KEY (id,kitchen_ID)," +
                "FOREIGN KEY (id) REFERENCES Appointments(id))");


        db.execSQL("CREATE TABLE BathRoom(toilet_ID TEXT," +
                "id TEXT," +
                "bathroom_bath_type TEXT," +
                "bathroom_geyser TEXT," +
                "bathroom_toilet TEXT," +
                "bathroom_glass_partition TEXT," +
                "bathroom_shower_curtain TEXT," +
                "bathroom_bath_tub TEXT," +
                "bathroom_windows TEXT," +
                "bathroom_cabinets TEXT," +
                "bathromm_exhaust_fan TEXT," +
                "bathroom_flooring_type TEXT," +
                "washing_machine TEXT," +
                "status_bath TEXT," +
                "PRIMARY KEY(id,toilet_ID)," +
                "FOREIGN KEY(id) REFERENCES Appointments(id))");

        db.execSQL("CREATE TABLE ImageSelection(id TEXT," +
                "type TEXT," +
                "room_id TEXT," +
                "image_location TEXT," +
                "image_location_thumbnail TEXT," +
                "image_location_medium TEXT," +
                "status TEXT," +
                "FOREIGN KEY(id) REFERENCES Appointments(id))");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void setWashDry(String washdry_id, String washdry_washing_machinet, String washdry_flooring_type, String status) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("washdry_ID", washdry_id);
        c1.put("id", Appointment.clicked);
        c1.put("washdry_washing_machinet", washdry_washing_machinet);
        c1.put("washdry_flooring_type", washdry_flooring_type);
        c1.put("status_wash", status);


        Cursor cq = db.rawQuery("Select * from WashDry where id=? AND washdry_ID=?", new String[]{Appointment.clicked, washdry_id});

        if (cq.moveToNext()) {
            db.update("WashDry", c1, "id=? AND washdry_ID=?", new String[]{Appointment.clicked, washdry_id});
            //  Toast.makeText(c, "successfull updation in wash dry", Toast.LENGTH_LONG).show();

        } else {
            if (db.insert("WashDry", null, c1) == -1) {
                //         Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else {
            }
            //         Toast.makeText(c, "successfull insertion in wash dry", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();
    }

    public Bundle getWashDry(String washdry_id) {

        Bundle b = new Bundle();
        SQLiteDatabase db = super.getReadableDatabase();
        String click = Appointment.clicked;
        Cursor cs = db.rawQuery("Select * from WashDry where id=? AND washdry_ID=?", new String[]{click, washdry_id});


        if (cs.moveToNext()) {

            b.putString("washdry_washing_machinet", cs.getString(cs.getColumnIndex("washdry_washing_machinet")));
            b.putString("washdry_flooring_type", cs.getString(cs.getColumnIndex("washdry_flooring_type")));
        }
        cs.close();
        db.close();
        return b;
    }

    public void setLivingRoom(String livingRoom_ID, String sofa,
                              String dining_table, String ac, String tv, String shoe_rack,
                              String flooring_type, String false_ceiling, String status) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("livingRoom_ID", livingRoom_ID);
        cv.put("id", Appointment.clicked);
        cv.put("sofa", sofa);
        cv.put("dining_table", dining_table);
        cv.put("ac", ac);
        cv.put("tv", tv);
        cv.put("shoe_rack", shoe_rack);
        cv.put("flooring_type", flooring_type);
        cv.put("false_ceiling", false_ceiling);
        cv.put("status_living", status);


        Cursor cq = db.rawQuery("Select * from LivingRoom where id=? AND livingRoom_ID=?", new String[]{Appointment.clicked, livingRoom_ID});

        if (cq.moveToNext()) {
            db.update("LivingRoom", cv, "id=? AND livingRoom_ID=?", new String[]{Appointment.clicked, livingRoom_ID});
            //  Toast.makeText(c, "successfull updation in living room ", Toast.LENGTH_LONG).show();

        } else {
            if (db.insert("LivingRoom", null, cv) == -1) {
                //       Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else {
            }
            // Toast.makeText(c, "successfull insertion in living room ", Toast.LENGTH_LONG).show();
        }
    }

    public Bundle getLivingRoom(String livingRoom_ID) {
        Bundle b = new Bundle();
        SQLiteDatabase db = super.getReadableDatabase();
        String click = Appointment.clicked;
        Cursor cs = db.rawQuery("Select * from LivingRoom where id=? AND livingRoom_ID=?", new String[]{click, livingRoom_ID});

        if (cs.moveToNext()) {
            b.putString("sofa", cs.getString(cs.getColumnIndex("sofa")));
            b.putString("dining_table", cs.getString(cs.getColumnIndex("dining_table")));
            b.putString("ac", cs.getString(cs.getColumnIndex("ac")));
            b.putString("tv", cs.getString(cs.getColumnIndex("tv")));
            b.putString("shoe_rack", cs.getString(cs.getColumnIndex("shoe_rack")));
            b.putString("flooring_type", cs.getString(cs.getColumnIndex("flooring_type")));
            b.putString("false_ceiling", cs.getString(cs.getColumnIndex("false_ceiling")));
        }
        cs.close();
        db.close();

        return b;
    }

    public void setBedRoom(String bedroom_id, String bedroom_tv, String bedroom_ac, String bedroom_bed,
                           String bedroom_wardrobe, String bedroom_attached_balcony, String bedroom_dressing_table,
                           String bedroom_false_ceiling, String bedroom_attached_bathroom, String bedroom_window, String bedroom_flooring_type, String status) {


        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("id", Appointment.clicked);
        c1.put("bedroom_ID", bedroom_id);
        c1.put("tv", bedroom_tv);
        c1.put("ac", bedroom_ac);
        c1.put("bed", bedroom_bed);
        c1.put("wardrobe", bedroom_wardrobe);
        c1.put("attached_balcony", bedroom_attached_balcony);
        c1.put("dressing_table", bedroom_dressing_table);
        c1.put("false_ceiling", bedroom_false_ceiling);
        c1.put("attached_bathroom", bedroom_attached_bathroom);
        c1.put("window", bedroom_window);
        c1.put("flooring_type", bedroom_flooring_type);
        c1.put("status_bed", status);
        String click = Appointment.clicked;
        Cursor cq = db.rawQuery("Select * from BedRoom where id=? AND bedroom_ID=?", new String[]{click, bedroom_id});

        if (cq.moveToNext()) {

            db.update("BedRoom", c1, "id=? AND bedroom_ID=?", new String[]{Appointment.clicked, bedroom_id});
            //    Toast.makeText(c, "successfull updation in bed room ", Toast.LENGTH_LONG).show();

        } else {

            if (db.insert("BedRoom", null, c1) == -1) {
                //    Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else {
            }
            //     Toast.makeText(c, "successfull insertion in bed room ", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();

    }

    public void setKitchen(String kitchen_id, String kitchen_cabinetes, String kitchen_refridgerator, String kitchen_water_purifier,
                           String kitchen_loft, String kitchen_gas_pipeline, String kitchen_microwave,
                           String kitchen_chimney, String kitchen_plateform_material, String kitchen_flooring, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();
        c1.put("kitchen_ID", kitchen_id);
        c1.put("id", Appointment.clicked);
        c1.put("kitchen_cabinetes", kitchen_cabinetes);
        c1.put("kitchen_refridgerator", kitchen_refridgerator);
        c1.put("kitchen_water_purifier", kitchen_water_purifier);
        c1.put("kitchen_loft", kitchen_loft);
        c1.put("kitchen_gas_pipeline", kitchen_gas_pipeline);
        c1.put("kitchen_microwave", kitchen_microwave);
        c1.put("kitchen_chimney", kitchen_chimney);
        c1.put("kitchen_plateform_material", kitchen_plateform_material);
        c1.put("kitchen_flooring", kitchen_flooring);

        c1.put("status_kitchen", status);


        Cursor cq = db.rawQuery("Select * from Kitchen where id=? AND kitchen_ID=?", new String[]{Appointment.clicked, kitchen_id});

        if (cq.moveToNext()) {

            db.update("Kitchen", c1, "id=? AND kitchen_ID=?", new String[]{Appointment.clicked, kitchen_id});
            //     Toast.makeText(c, "successfull updation in kitchen room ", Toast.LENGTH_LONG).show();

        } else {

            if (db.insert("Kitchen", null, c1) == -1) {
                //    Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else {
            }
            //  Toast.makeText(c, "successfull insertion in kitchen room ", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();
    }


    public void setBathRoom(String toilet_id, String bathroom_bath_type, String bathroom_geyser, String bathroom_toilet,
                            String bathroom_glass_partition, String bathroom_shower_curtain, String bathroom_bath_tub,
                            String bathroom_windows, String bathroom_cabinets, String bathromm_exhaust_fan,
                            String bathroom_flooring_type, String bathroom_washing_machine, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("toilet_ID", toilet_id);
        c1.put("id", Appointment.clicked);
        c1.put("bathroom_bath_type", bathroom_bath_type);
        c1.put("bathroom_geyser", bathroom_geyser);
        c1.put("bathroom_toilet", bathroom_toilet);
        c1.put("bathroom_glass_partition", bathroom_glass_partition);
        c1.put("bathroom_shower_curtain", bathroom_shower_curtain);
        c1.put("bathroom_bath_tub", bathroom_bath_tub);
        c1.put("bathroom_windows", bathroom_windows);
        c1.put("bathroom_cabinets", bathroom_cabinets);
        c1.put("bathromm_exhaust_fan", bathromm_exhaust_fan);
        c1.put("bathroom_flooring_type", bathroom_flooring_type);
        c1.put("washing_machine", bathroom_washing_machine);

        c1.put("status_bath", status);


        Cursor cq = db.rawQuery("Select * from BathRoom where id=? AND toilet_ID=?", new String[]{Appointment.clicked, toilet_id});

        if (cq.moveToNext()) {

            db.update("BathRoom", c1, "id=? AND toilet_ID=?", new String[]{Appointment.clicked, toilet_id});
            //   Toast.makeText(c, "successfull updation in bath room ", Toast.LENGTH_LONG).show();

        } else {

            if (db.insert("BathRoom", null, c1) == -1) {
                //       Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else {
            }
            //        Toast.makeText(c, "successfull insertion in bath room ", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();
    }

    public Bundle getBedRoom(String bedroom_id) {


        Bundle b = new Bundle();
        SQLiteDatabase db = super.getReadableDatabase();
        String click = Appointment.clicked;
        Cursor cq = db.rawQuery("Select * from BedRoom where id=? AND bedroom_ID=?", new String[]{click, bedroom_id});

        if (cq.moveToNext()) {
            b.putString("tv", cq.getString(cq.getColumnIndex("tv")));
            b.putString("ac", cq.getString(cq.getColumnIndex("ac")));
            b.putString("bed", cq.getString(cq.getColumnIndex("bed")));
            b.putString("wardrobe", cq.getString(cq.getColumnIndex("wardrobe")));
            b.putString("attached_balcony", cq.getString(cq.getColumnIndex("attached_balcony")));
            b.putString("dressing_table", cq.getString(cq.getColumnIndex("dressing_table")));
            b.putString("false_ceiling", cq.getString(cq.getColumnIndex("false_ceiling")));
            b.putString("attached_bathroom", cq.getString(cq.getColumnIndex("attached_bathroom")));
            b.putString("window", cq.getString(cq.getColumnIndex("window")));
            b.putString("flooring_type", cq.getString(cq.getColumnIndex("flooring_type")));


        }
        cq.close();
        db.close();

        return b;
    }


    public Bundle getbathroom(String toilet_id) {

        Bundle b = new Bundle();
        SQLiteDatabase db = super.getReadableDatabase();
        String click = Appointment.clicked;
        Cursor cq = db.rawQuery("Select * from BathRoom where id=? AND toilet_ID=?", new String[]{click, toilet_id});

        if (cq.moveToNext()) {

            b.putString("bathroom_bath_type", cq.getString(cq.getColumnIndex("bathroom_bath_type")));
            b.putString("bathroom_geyser", cq.getString(cq.getColumnIndex("bathroom_geyser")));
            b.putString("bathroom_toilet", cq.getString(cq.getColumnIndex("bathroom_toilet")));
            b.putString("bathroom_glass_partition", cq.getString(cq.getColumnIndex("bathroom_glass_partition")));
            b.putString("bathroom_shower_curtain", cq.getString(cq.getColumnIndex("bathroom_shower_curtain")));
            b.putString("bathroom_bath_tub", cq.getString(cq.getColumnIndex("bathroom_bath_tub")));
            b.putString("bathroom_windows", cq.getString(cq.getColumnIndex("bathroom_windows")));
            b.putString("bathroom_cabinets", cq.getString(cq.getColumnIndex("bathroom_cabinets")));
            b.putString("bathromm_exhaust_fan", cq.getString(cq.getColumnIndex("bathromm_exhaust_fan")));
            b.putString("bathroom_flooring_type", cq.getString(cq.getColumnIndex("bathroom_flooring_type")));
            b.putString("washing_machine", cq.getString(cq.getColumnIndex("washing_machine")));
        }
        cq.close();
        db.close();
        return b;
    }


    public Bundle getkitchen(String kitchen_id) {

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Kitchen where id=? AND kitchen_ID=?", new String[]{Appointment.clicked, kitchen_id});

        Bundle b = new Bundle();
        if (cq.moveToNext()) {
            b.putString("kitchen_cabinetes", cq.getString(cq.getColumnIndex("kitchen_cabinetes")));
            b.putString("kitchen_refridgerator", cq.getString(cq.getColumnIndex("kitchen_refridgerator")));
            b.putString("kitchen_water_purifier", cq.getString(cq.getColumnIndex("kitchen_water_purifier")));
            b.putString("kitchen_loft", cq.getString(cq.getColumnIndex("kitchen_loft")));
            b.putString("kitchen_gas_pipeline", cq.getString(cq.getColumnIndex("kitchen_gas_pipeline")));
            b.putString("kitchen_microwave", cq.getString(cq.getColumnIndex("kitchen_microwave")));
            b.putString("kitchen_chimney", cq.getString(cq.getColumnIndex("kitchen_chimney")));
            b.putString("kitchen_plateform_material", cq.getString(cq.getColumnIndex("kitchen_plateform_material")));
            b.putString("kitchen_flooring", cq.getString(cq.getColumnIndex("kitchen_flooring")));

        }
        cq.close();
        db.close();
        return b;
    }


    public void saveData(String id, String name, String description, String phone, String status, String appointmentTime, String rent_sale_status) {
        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{id + ""});
        if (cq.moveToNext()) {
            //   Toast.makeText(c, cq.getInt(cq.getColumnIndex("id")) + "\nId Selected!", Toast.LENGTH_LONG).show();

        } else {
            db = super.getWritableDatabase();
            ContentValues c1 = new ContentValues();

            c1.put("id", id);
            c1.put("name", name);
            c1.put("description", description);
            c1.put("phone", phone);
            c1.put("status", status);
            c1.put("appointmentTime", appointmentTime);
            c1.put("rent_sale_status", rent_sale_status);

            if (db.insert("Appointments", null, c1) == -1) {
                //      Toast.makeText(c, "Problem", Toast.LENGTH_LONG).show();

            } else {
            }
//                Toast.makeText(c, "New Entry Successfull", Toast.LENGTH_LONG).show();
        }

        db.close();

    }

    public void addData(int id, String mobile, String email) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("id", id);
        c1.put("mobile", mobile);
        c1.put("email", email);

        if (db.insert("Appointments", null, c1) == -1) {
            //        Toast.makeText(c, "Problem", Toast.LENGTH_LONG).show();


        } else {
            //
            //
            // Toast.makeText(c, "New Entry Successfull", Toast.LENGTH_LONG).show();
        }
        db.close();

    }


    public String[][] getAllData() {

        SQLiteDatabase db = super.getReadableDatabase();
            //ASC LIMIT 1
        //     Select * from Appointments ORDER BY status DESC, appointmentTime ASC
        Cursor cq = db.rawQuery("Select * from Appointments ORDER BY appointmentTime ASC", null);
        String name[] = new String[cq.getCount()];
        String desc[] = new String[cq.getCount()];
        String phone[] = new String[cq.getCount()];
        String id[] = new String[cq.getCount()];
        String status[] = new String[cq.getCount()];
        String datetime[] = new String[cq.getCount()];
        String rent_sale_status[] = new String[cq.getCount()];
        for (int i = 0; cq.moveToNext(); i++) {

            name[i] = cq.getString(cq.getColumnIndex("name"));
            desc[i] = cq.getString(cq.getColumnIndex("description"));
            phone[i] = cq.getString(cq.getColumnIndex("phone"));
            id[i] = cq.getString(cq.getColumnIndex("id"));
            status[i] = cq.getString(cq.getColumnIndex("status"));
            datetime[i] = cq.getString(cq.getColumnIndex("appointmentTime"));

            String local = cq.getString(cq.getColumnIndex("rent_sale_status"));
            if ("1".equalsIgnoreCase(local))
                rent_sale_status[i] = "Rent";
            else if ("2".equalsIgnoreCase(local))
                rent_sale_status[i] = "Sale";
            else
                rent_sale_status[i] = local;

        }

        String[][] abc = {name, desc, phone, id, status, datetime, rent_sale_status};
        cq.close();
        db.close();
        return abc;
    }

    public void setPricing(String built_up_area, String carpet_area, String rent_ammount,
                           String no_of_floors,
                           String age_of_building, String no_of_lift, String plot_area, String sale_status,
                           String units, String maintanancefee, String maintanancechargefrequency,String rent,
                           String price_plc, String price_parking,String price_club,String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();
        c1.put("rent_negotiable", rent);
        c1.put("builtup_area", built_up_area);
        c1.put("carpet_area", carpet_area);
        c1.put("rent_ammount", rent_ammount);
        c1.put("no_of_floor", no_of_floors);
        c1.put("age_of_building", age_of_building);
        c1.put("no_of_lift", no_of_lift);
        c1.put("pricing_plot_area", plot_area);
        c1.put("pricing_sale_status", sale_status);
        c1.put("units", units);
        c1.put("maintainance", maintanancefee);
        c1.put("maintanancechargefrequency", maintanancechargefrequency);
        c1.put("price_plc", price_plc);
        c1.put("price_parking", price_parking);
        c1.put("price_club", price_club);

        c1.put("status_pricing", status);
        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

//        Toast.makeText(c, i + "", Toast.LENGTH_LONG).show();
        db.close();
    }

    public Bundle getPricing() {

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();
        if (cq.moveToNext()) {
            b.putString("rent_negotiable", cq.getString(cq.getColumnIndex("rent_negotiable")));
            b.putString("units", cq.getString(cq.getColumnIndex("units")));
            b.putString("maintainance", cq.getString(cq.getColumnIndex("maintainance")));
            b.putString("builtup_area", cq.getString(cq.getColumnIndex("builtup_area")));
            b.putString("carpet_area", cq.getString(cq.getColumnIndex("carpet_area")));
            b.putString("rent_ammount", cq.getString(cq.getColumnIndex("rent_ammount")));
            b.putString("no_of_floors", cq.getString(cq.getColumnIndex("no_of_floor")));
            b.putString("age_of_building", cq.getString(cq.getColumnIndex("age_of_building")));
            b.putString("no_of_lift", cq.getString(cq.getColumnIndex("no_of_lift")));
            b.putString("plot_area", cq.getString(cq.getColumnIndex("pricing_plot_area")));
            b.putString("sale_status", cq.getString(cq.getColumnIndex("pricing_sale_status")));
            b.putString("pricing_spinner_maintenance_charge_frequency", cq.getString(cq.getColumnIndex("maintanancechargefrequency")));
            b.putString("price_plc", cq.getString(cq.getColumnIndex("price_plc")));
            b.putString("price_parking", cq.getString(cq.getColumnIndex("price_parking")));
            b.putString("price_club", cq.getString(cq.getColumnIndex("price_club")));
        }
        cq.close();
        db.close();
        return b;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void sendDataForParticularId(String s) {
        SQLiteDatabase db = super.getReadableDatabase();
        final Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{s});
        final String currentAppointment = Appointment.clicked;
        final ProgressDialog pDialog = new ProgressDialog(c);
        pDialog.setMessage("Sync...");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(c);
        if (cq.moveToNext()) {

            SharedPreferences sp = c.getSharedPreferences("Login", c.MODE_PRIVATE);

            final Map<String, Object> params = new LinkedHashMap<>();

            //Appoinment Details

            params.put("ap_id", cq.getString(cq.getColumnIndex("id")));
            params.put("userid", sp.getString("userID", "Not Found"));
           /* params.put("ap_name", cq.getString(cq.getColumnIndex("name")));
            params.put("ap_phone", cq.getString(cq.getColumnIndex("phone")));*/
            params.put("ap_address", cq.getString(cq.getColumnIndex("description")));
            params.put("ap_status", cq.getString(cq.getColumnIndex("status")));
            params.put("propertyPurpose", cq.getString(cq.getColumnIndex("rent_sale_status")));

            //Advetiser Details
           // params.put("ap_advertiser_type", cq.getString(cq.getColumnIndex("owner_broker")));  // Change owner_broker to ap_Advertiser_type
            params.put("ap_ownership_type", cq.getString(cq.getColumnIndex("owner_type")));   // Change ap_owner_type to ap_ownership_type
            params.put("ap_name", cq.getString(cq.getColumnIndex("name")));
            params.put("ap_phone", cq.getString(cq.getColumnIndex("phone")));
            params.put("ap_alternate_phone_no", cq.getString(cq.getColumnIndex("mobile")));
            params.put("ap_email", cq.getString(cq.getColumnIndex("email")));
            //    params.put("ap_building_no_name", cq.getString(cq.getColumnIndex("building_no")));  // Change ap_building_no to ap_building_no_name
            //    params.put("ap_flate_number", cq.getString(cq.getColumnIndex("flate_number")));
            params.put("ap_floor_no", cq.getString(cq.getColumnIndex("floor_no")));
            params.put("ap_society_name", cq.getString(cq.getColumnIndex("society_name")));  // newly added
            //    params.put("ap_wing", cq.getString(cq.getColumnIndex("wing")));
            //    params.put("ap_address_1", cq.getString(cq.getColumnIndex("address1")));
            params.put("ap_address_2", cq.getString(cq.getColumnIndex("address2")));
            //     params.put("ap_sub_locality", cq.getString(cq.getColumnIndex("sub_locality")));
            params.put("ap_pincode", cq.getString(cq.getColumnIndex("pincode")));
            //     params.put("ap_landmark", cq.getString(cq.getColumnIndex("landmark")));
            //    params.put("ap_developer_type", cq.getString(cq.getColumnIndex("developer_type")));

            //Property Details
            //    params.put("ap_bhk_type", cq.getString(cq.getColumnIndex("bhk_type")));
            params.put("ap_property_type", cq.getString(cq.getColumnIndex("property_type")));
            params.put("ap_total_livingroom", cq.getString(cq.getColumnIndex("no_of_livingroom")));
            params.put("ap_total_bedroom", cq.getString(cq.getColumnIndex("no_of_bedroom")));
            params.put("ap_total_kitchen", cq.getString(cq.getColumnIndex("no_of_kitchen")));
            params.put("ap_no_of_toilet", cq.getString(cq.getColumnIndex("no_of_bathroom")));
            params.put("ap_total_balcony", cq.getString(cq.getColumnIndex("no_of_balcony")));
            params.put("ap_preferred_visit_time", cq.getString(cq.getColumnIndex("preferred_visit_time")));
            params.put("ap_possesion_compilation_date", cq.getString(cq.getColumnIndex("possesion_date")));
            params.put("property_current_status", cq.getString(cq.getColumnIndex("property_status")));
            // Change
            // Change from ap_possesion_date to ap_possesion_compilation_date


            // Rent Screen
           // Toast.makeText(c,cq.getString(cq.getColumnIndex("rent_sale_status")),Toast.LENGTH_LONG).show();
            if(!"Sell".equalsIgnoreCase(cq.getString(cq.getColumnIndex("rent_sale_status")))){
                params.put("ap_brokerage_fee", cq.getString(cq.getColumnIndex("brokerage_fee")));
                params.put("ap_food", cq.getString(cq.getColumnIndex("food")));
                params.put("ap_lease_type", cq.getString(cq.getColumnIndex("lease_type")));
                params.put("ap_pets_allowed", cq.getString(cq.getColumnIndex("pets_allowed")));
                params.put("ap_security_deposit", cq.getString(cq.getColumnIndex("security_deposit")));
                params.put("ap_security_negotiable", cq.getString(cq.getColumnIndex("security_negotiable")));
            }

            params.put("ap_maintainance", cq.getString(cq.getColumnIndex("maintainance")));

            params.put("ap_rent_negotiable", cq.getString(cq.getColumnIndex("rent_negotiable")));


            //  params.put("ap_availability_date", cq.getString(cq.getColumnIndex("availability_date")));


            //Pricing Details
            params.put("ap_builtup_area", cq.getString(cq.getColumnIndex("builtup_area")));
            params.put("ap_carpet_area", cq.getString(cq.getColumnIndex("carpet_area")));
            //   params.put("ap_units", cq.getString(cq.getColumnIndex("units")));  // newly added
            params.put("ap_rent_ammount", cq.getString(cq.getColumnIndex("rent_ammount")));
            params.put("ap_no_of_floor", cq.getString(cq.getColumnIndex("no_of_floor")));
            params.put("ap_age_of_building", cq.getString(cq.getColumnIndex("age_of_building")));
            params.put("ap_no_of_lift", cq.getString(cq.getColumnIndex("no_of_lift")));
            //   Newly Added
            params.put("ap_pricing_plot_area", cq.getString(cq.getColumnIndex("pricing_plot_area")));
            params.put("ap_pricing_sale_status", cq.getString(cq.getColumnIndex("pricing_sale_status")));
            params.put("maintenance_frequency", cq.getString(cq.getColumnIndex("maintanancechargefrequency")));
            params.put("price_plc", cq.getString(cq.getColumnIndex("price_plc")));
            params.put("price_parking", cq.getString(cq.getColumnIndex("price_parking")));
            params.put("price_club", cq.getString(cq.getColumnIndex("price_club")));


            //Residential Details
            //       params.put("ap_no_of_building", cq.getString(cq.getColumnIndex("no_of_building")));
            //       params.put("ap_no_of_storys", cq.getString(cq.getColumnIndex("no_of_storys")));
            params.put("ap_servant_room", cq.getString(cq.getColumnIndex("servant_room")));
            params.put("ap_prayer_room", cq.getString(cq.getColumnIndex("prayer_room")));
            //       params.put("ap_terrace", cq.getString(cq.getColumnIndex("terrace_access")));  // change
            params.put("ap_private_terrace", cq.getString(cq.getColumnIndex("private_access"))); // change
            params.put("ap_main_entrance_facing", cq.getString(cq.getColumnIndex("main_entrance_facing")));
            params.put("ap_power_backup", cq.getString(cq.getColumnIndex("power_backup")));
            params.put("ap_water_supply_municipal", cq.getString(cq.getColumnIndex("water_supply_municipal")));
            params.put("ap_water_supply_borewell", cq.getString(cq.getColumnIndex("water_supply_borewell")));
            params.put("ap_waterbackup_grounded_tank", cq.getString(cq.getColumnIndex("waterbackup_grounded_tanks")));
            params.put("ap_waterbackup_terrace_tank", cq.getString(cq.getColumnIndex("waterbackup_terrace_tanks")));
            params.put("parking_type", cq.getString(cq.getColumnIndex("parking_type")));
            params.put("furnishing_status", cq.getString(cq.getColumnIndex("furnishing_status")));
            params.put("balcony", cq.getString(cq.getColumnIndex("balcony")));
            params.put("common_area", cq.getString(cq.getColumnIndex("common_area")));
            params.put("ap_wifi", cq.getString(cq.getColumnIndex("wifi")));
            params.put("ap_solar_heater", cq.getString(cq.getColumnIndex("solar_heater")));


            //Society Details

            params.put("ap_boundary_wall", cq.getString(cq.getColumnIndex("boundary_wall")));
            params.put("ap_societydata_gated_community", cq.getString(cq.getColumnIndex("societydata_gated_community")));
            //         params.put("ap_societydata_society_overheadtank", cq.getString(cq.getColumnIndex("societydata_society_overheadtank")));
            params.put("ap_societydata_reg_society", cq.getString(cq.getColumnIndex("societydata_reg_society")));
            params.put("ap_societydata_security", cq.getString(cq.getColumnIndex("societydata_security")));
            params.put("ap_societydata_cctv_servillance", cq.getString(cq.getColumnIndex("societydata_cctv_servillance")));
            params.put("ap_societydata_smoke_detector", cq.getString(cq.getColumnIndex("societydata_smoke_detector")));
            params.put("ap_societydata_fire_hydrant_system", cq.getString(cq.getColumnIndex("societydata_fire_hydrant_system")));
            params.put("ap_societydata_club_house", cq.getString(cq.getColumnIndex("societydata_club_house")));
            params.put("ap_societydata_swiming_pool", cq.getString(cq.getColumnIndex("societydata_swiming_pool")));
            params.put("ap_societydata_gym", cq.getString(cq.getColumnIndex("societydata_zym")));  // Change
            params.put("ap_garden_lawn", cq.getString(cq.getColumnIndex("garden_lawn")));
            params.put("ap_societydata_multi_purpose", cq.getString(cq.getColumnIndex("societydata_multi_purpose")));
//       Newly Added
            params.put("ap_society_ck_24HWS", cq.getString(cq.getColumnIndex("society_ck_24HWS")));
            params.put("ap_society_ck_aerobic_room", cq.getString(cq.getColumnIndex("society_ck_aerobic_room")));
            params.put("ap_society_ck_amphithreater", cq.getString(cq.getColumnIndex("society_ck_amphithreater")));
            params.put("ap_society_ck_atm_bank", cq.getString(cq.getColumnIndex("society_ck_atm_bank")));
            params.put("ap_society_ck_banquet_hall", cq.getString(cq.getColumnIndex("society_ck_banquet_hall")));
            params.put("ap_society_ck_barbeque_pit", cq.getString(cq.getColumnIndex("society_ck_barbeque_pit")));
            params.put("ap_society_ck_basketball_tennis_court", cq.getString(cq.getColumnIndex("society_ck_basketball_tennis_court")));
            params.put("ap_society_ck_centralized_ac", cq.getString(cq.getColumnIndex("society_ck_centralized_ac")));
            params.put("ap_society_ck_conference_room", cq.getString(cq.getColumnIndex("society_ck_conference_room")));
            params.put("ap_society_ck_day_care_center", cq.getString(cq.getColumnIndex("society_ck_day_care_center")));
            params.put("ap_society_ck_dth_tv_facility", cq.getString(cq.getColumnIndex("society_ck_dth_tv_facility")));
            params.put("ap_society_ck_early_learning_play_group", cq.getString(cq.getColumnIndex("society_ck_early_learning_play_group")));
            params.put("ap_society_ck_golf_cource", cq.getString(cq.getColumnIndex("society_ck_golf_cource")));
            params.put("ap_society_ck_guest_accomadation", cq.getString(cq.getColumnIndex("society_ck_guest_accomadation")));
            params.put("ap_society_ck_indoor_games_room", cq.getString(cq.getColumnIndex("society_ck_indoor_games_room")));
            params.put("ap_society_ck_indoor_bedminton_court", cq.getString(cq.getColumnIndex("society_ck_indoor_bedminton_court")));
            params.put("ap_society_ck_intercom", cq.getString(cq.getColumnIndex("society_ck_intercom")));
            params.put("ap_society_ck_kids_club", cq.getString(cq.getColumnIndex("society_ck_kids_club")));
            params.put("ap_society_ck_kids_play_area", cq.getString(cq.getColumnIndex("society_ck_kids_play_area")));
            params.put("ap_society_ck_laundry_service", cq.getString(cq.getColumnIndex("society_ck_laundry_service")));
            params.put("ap_society_ck_meditation_center", cq.getString(cq.getColumnIndex("society_ck_meditation_center")));
            params.put("ap_society_ck_paved_comound", cq.getString(cq.getColumnIndex("society_ck_paved_comound")));
            params.put("ap_society_ck_power_backup", cq.getString(cq.getColumnIndex("society_ck_power_backup")));
            params.put("ap_society_ck_property_maintenace_staff", cq.getString(cq.getColumnIndex("society_ck_property_maintenace_staff")));
            params.put("ap_society_ck_rain_water_harvesting", cq.getString(cq.getColumnIndex("society_ck_rain_water_harvesting")));
            params.put("ap_society_ck_recreational_facilities", cq.getString(cq.getColumnIndex("society_ck_recreational_facilities")));
            params.put("ap_society_ck_rentable_community_space", cq.getString(cq.getColumnIndex("society_ck_rentable_community_space")));
            params.put("ap_society_ck_reserverd_parking", cq.getString(cq.getColumnIndex("society_ck_reserverd_parking")));
            params.put("ap_society_ck_school", cq.getString(cq.getColumnIndex("society_ck_school")));
            params.put("ap_society_ck_service_goods_lift", cq.getString(cq.getColumnIndex("society_ck_service_goods_lift")));
            params.put("ap_society_ck_sevage_treatment_plan", cq.getString(cq.getColumnIndex("society_ck_sevage_treatment_plan")));
            params.put("ap_society_ck_shooping_retail", cq.getString(cq.getColumnIndex("society_ck_shooping_retail")));
            params.put("ap_society_ck_skating_court", cq.getString(cq.getColumnIndex("society_ck_skating_court")));
            params.put("ap_society_ck_strolling_cycling_jogging", cq.getString(cq.getColumnIndex("society_ck_strolling_cycling_jogging")));
            params.put("ap_society_ck_vaastu_complaint", cq.getString(cq.getColumnIndex("society_ck_vaastu_complaint")));
            params.put("ap_society_ck_visitor_parking", cq.getString(cq.getColumnIndex("society_ck_visitor_parking")));
            params.put("ap_society_ck_waiting_lounge", cq.getString(cq.getColumnIndex("society_ck_waiting_lounge")));
            params.put("ap_society_ck_waste_disposal", cq.getString(cq.getColumnIndex("society_ck_waste_disposal")));
            params.put("society_notes", cq.getString(cq.getColumnIndex("society_notes")));



            //   params.put("img", new FileBody[]{new FileBody(new File("")),new File(""),new File("")});

            Map<String, JSONObject> param_new = new LinkedHashMap<>();
            if (cq.getString(cq.getColumnIndex("no_of_livingroom")) != null) {
                try {
                    int livingroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_livingroom")));

                    // JSONObject obj3[] = new JSONObject[livingroom];
                    for (int i = 0; i < livingroom; i++) {

                        final Cursor l = db.rawQuery("Select * from LivingRoom where id=? AND livingRoom_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                        if (l.moveToNext()) {

                            Map<String, Object> param1 = new LinkedHashMap<String, Object>();
                            param1.put("id", "LivingRoom" + (1 + i));
                            param1.put("sofa", l.getString(l.getColumnIndex("sofa")));
                            param1.put("dining_table", l.getString(l.getColumnIndex("dining_table")));
                            param1.put("ac", l.getString(l.getColumnIndex("ac")));
                            param1.put("tv", l.getString(l.getColumnIndex("tv")));
                            param1.put("shoe_rack", l.getString(l.getColumnIndex("shoe_rack")));
                            param1.put("flooring_type", l.getString(l.getColumnIndex("flooring_type")));
                            param1.put("false_ceiling", l.getString(l.getColumnIndex("false_ceiling")));


                            JSONObject obj1 = new JSONObject(param1);

                            param_new.put("LivingRoom" + (1 + i), obj1);

                            //      JSONObject obj2 = new JSONObject(param_new);

                            //   obj3[i] = obj2;

                        }
                        l.close();


                    }
                    params.put("ap_living_room", param_new);
                }catch(NumberFormatException e)
                {
                    Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
                }
            }

            Log.d("Vishal", new JSONObject(params).toString());
           /* param_new = null;
            param_new = new HashMap<String, JSONObject>();
            if (cq.getString(cq.getColumnIndex("no_of_washdry")) != null) {
                int washdry = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_washdry")));


                //      JSONObject wash[] = new JSONObject[washdry];
                for (int i = 0; i < washdry; i++) {

                    final Cursor l = db.rawQuery("Select * from WashDry where id=? AND washdry_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new HashMap<String, Object>();
                        param1.put("id","WashDry" + (1 + i));
                        param1.put("washing_machine", l.getString(l.getColumnIndex("washdry_washing_machinet")));
                        param1.put("flooring_type", l.getString(l.getColumnIndex("washdry_flooring_type")));


                        JSONObject obj1 = new JSONObject(param1);

                        param_new.put("WashDry" + (i + 1), obj1);

                        //       JSONObject obj2 = new JSONObject(param_new);

                        //     wash[i] = obj2;

                    }
                    l.close();


                }
                //   params.put("ap_washdry_area", wash);
                params.put("ap_washdry_area", param_new);
            }*/

            param_new = null;
            param_new = new LinkedHashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_kitchen")) != null) {
                try{
                int kitchen = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_kitchen")));


                //   JSONObject obj5[] = new JSONObject[kitchen];
                for (int i = 0; i < kitchen; i++) {

                    final Cursor l = db.rawQuery("Select * from Kitchen where id=? AND kitchen_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new LinkedHashMap<String, Object>();
                        param1.put("id", "Kitchen" + (1 + i));
                        param1.put("cabinet", l.getString(l.getColumnIndex("kitchen_cabinetes")));
                        param1.put("gas_pipeline", l.getString(l.getColumnIndex("kitchen_gas_pipeline")));
                        param1.put("refrigerator", l.getString(l.getColumnIndex("kitchen_refridgerator")));
                        param1.put("water_purifier", l.getString(l.getColumnIndex("kitchen_water_purifier")));
                        param1.put("microwave", l.getString(l.getColumnIndex("kitchen_microwave")));
                        param1.put("loft", l.getString(l.getColumnIndex("kitchen_loft")));
                        param1.put("platform_material", l.getString(l.getColumnIndex("kitchen_plateform_material")));
                        param1.put("kitchen_flooring", l.getString(l.getColumnIndex("kitchen_flooring")));
                        param1.put("chimney_exhaust", l.getString(l.getColumnIndex("kitchen_chimney")));


                        JSONObject obj1 = new JSONObject(param1);

                        param_new.put("Kitchen" + (i + 1), obj1);

                        //       JSONObject obj2 = new JSONObject(param_new);

                        //      obj5[i] = obj2;

                    }

                    l.close();
                }

                params.put("ap_kitchens", param_new);
                }catch(NumberFormatException e)
                {
                    Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
                }
            }
            param_new = null;
            param_new = new LinkedHashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_bedroom")) != null) {
                try{
                int bedroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_bedroom")));


                //  JSONObject obj4[] = new JSONObject[bedroom];
                for (int i = 0; i < bedroom; i++) {

                    final Cursor l = db.rawQuery("Select * from BedRoom where id=? AND bedroom_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new LinkedHashMap<String, Object>();
                        param1.put("id", "BedRoom" + (1 + i));
                        param1.put("bed", l.getString(l.getColumnIndex("bed")));
                        param1.put("ac", l.getString(l.getColumnIndex("ac")));
                        param1.put("tv", l.getString(l.getColumnIndex("tv")));
                        param1.put("dressing_table", l.getString(l.getColumnIndex("dressing_table")));
                        param1.put("wardrobe", l.getString(l.getColumnIndex("wardrobe")));
                        param1.put("attached_balconey", l.getString(l.getColumnIndex("attached_balcony")));
                        param1.put("attached_bathroom", l.getString(l.getColumnIndex("attached_bathroom")));
                        param1.put("false_ceiling", l.getString(l.getColumnIndex("false_ceiling")));
                        param1.put("window", l.getString(l.getColumnIndex("window")));
                        param1.put("flooring_type", l.getString(l.getColumnIndex("flooring_type")));


                        JSONObject obj1 = new JSONObject(param1);

                        param_new.put("BedRoom" + (i + 1), obj1);

                        //   JSONObject obj2 = new JSONObject(param_new);
//
                        //        obj4[i] = obj2;

                    }
                    l.close();

                }
                params.put("ap_bedrooms", param_new);
                }catch(NumberFormatException e)
                {
                    Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
                }
            }
            param_new = null;
            param_new = new LinkedHashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_bathroom")) != null) {
                try{
                int bathroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_bathroom")));


                //JSONObject obje[] = new JSONObject[bathroom];
                for (int i = 0; i < bathroom; i++) {

                    final Cursor l = db.rawQuery("Select * from BathRoom where id=? AND toilet_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param_living = new LinkedHashMap<String, Object>();
                        param_living.put("id", "Toilet" + (1 + i));
                        param_living.put("type", l.getString(l.getColumnIndex("bathroom_bath_type")));
                        param_living.put("style", l.getString(l.getColumnIndex("bathroom_toilet")));
                        param_living.put("geyser", l.getString(l.getColumnIndex("bathroom_geyser")));  // Changes
                        param_living.put("glass_partition", l.getString(l.getColumnIndex("bathroom_glass_partition")));
                        param_living.put("shower_curtain", l.getString(l.getColumnIndex("bathroom_shower_curtain")));
                        param_living.put("bath_tub", l.getString(l.getColumnIndex("bathroom_bath_tub")));
                        param_living.put("cabinet", l.getString(l.getColumnIndex("bathroom_cabinets")));
                        param_living.put("window", l.getString(l.getColumnIndex("bathroom_windows")));
                        param_living.put("exhaust_fan", l.getString(l.getColumnIndex("bathromm_exhaust_fan")));
                        param_living.put("flooring_type", l.getString(l.getColumnIndex("bathroom_flooring_type")));
                        param_living.put("washing_machine", l.getString(l.getColumnIndex("washing_machine")));

                        JSONObject obj1 = new JSONObject(param_living);

                        param_new.put("Toilet" + (i + 1), obj1);

                        //  JSONObject obj2 = new JSONObject(param_new);

                        //       obje[i] = obj2;

                    }

                    l.close();
                }
                params.put("ap_toilets", param_new);
                }catch(NumberFormatException e)
                {
                    Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
                }
            }


            //   Log.d("JSONDATA", new JSONObject(params).toString());
            longInfo(new JSONObject(params).toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://qc.homeonline.com/dbho/Api/update",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            // Display the first 500 characters of the response string.
                           /* for (int i = 0; i < 2; i++)
                                Toast.makeText(c, response, Toast.LENGTH_LONG).show();*/
                            longInfo(response);
                            pDialog.dismiss();

                            try {
                                JSONObject js = new JSONObject(response);
                                String status = js.getString("status");
                                if ("success".equalsIgnoreCase(status)) {
                                    Toast.makeText(c, "Property Synced Successfully", Toast.LENGTH_LONG).show();
                                    ContentValues cv = new ContentValues();
                                    cv.put("update_from_server", "false");
                                    setUpdateFromServerStatus(cv, currentAppointment);

                                }

                            } catch (JSONException e) {
                                Toast.makeText(c, "Invalid response from server", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String err = null;
                    if (error instanceof NoConnectionError) {
                        err = "No Internet Access\nCheck Your Internet Connection.";
                    }
                    if (err == null)
                        err = "Something happened wrong with server try to resync\n Error:" + error.getMessage();
                    Toast.makeText(c, err, Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                }


            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("jsondata", new JSONObject(params).toString());
                    return param;
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
                    headerMap.put("Content-Type", "application/x-www-form-urlencoded");
                    headerMap.put("abc", "value");

                    return headerMap;
                }
            };
// Add the request to the RequestQueue.
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
            queue.add(stringRequest);

        }
        getImageURL();

    }

    public static void longInfo(String str) {
        if (str.length() > 4000) {
            Log.i("JSON_Format", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("JSON_Format", str);
    }

    public void deleteForParticularID() {

        SQLiteDatabase db = super.getWritableDatabase();
        String table[] = {
                "LivingRoom",
                "WashDry",    // TODO Wash dry Remove
                "BedRoom",
                "Kitchen",
                "BathRoom",
                "ImageSelection",
                "Appointments"};
        for (int i = 0; i < table.length; i++) {
            long j = db.delete(table[i], "id=?", new String[]{Appointment.clicked});
            //  Toast.makeText(c,table[i]+ "="+j,Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("NewApi")
    public boolean flushAllData() {
        SQLiteDatabase db = super.getWritableDatabase();
        File f = new File(db.getPath());
        return db.deleteDatabase(f);


    }

    public boolean checkSpinnerNo(final String table_name, final String room_id_name, final String s, final String status) {
        SQLiteDatabase db = super.getReadableDatabase();

        long total = DatabaseUtils.queryNumEntries(db,
                table_name,
                "id=? AND " + room_id_name + "=? AND " + status + "=?",
                new String[]{Appointment.clicked, s, "true"});
        db.close();

        return total > 0 ? true : false;
    }

    public void setBedRoomData(ContentValues[] contentValues) {

        SQLiteDatabase db = super.getWritableDatabase();
        for (int i = 0; i < contentValues.length; i++) {
            if (db.insert("BedRoom", null, contentValues[i]) != -1)
                Toast.makeText(c, "Inserted=" + i, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(c, "Not Inserted=" + i, Toast.LENGTH_LONG).show();
        }

    }

    public void saveAppointmentOtherDetails(ContentValues contentValuesAppointment) {
        //    ContentValues obj=new ContentValues(new BaseContentValuesClass(5));
        SQLiteDatabase db = super.getWritableDatabase();
        if (db.update("Appointments", contentValuesAppointment, "id=?", new String[]{contentValuesAppointment.getAsString("id")}) != -1)
            ;
        //Toast.makeText(c, "Inserted", Toast.LENGTH_LONG).show();

        db.close();

    }

    public void setDataFromServer(ContentValues[] contentValues, final String table_name) {
        SQLiteDatabase db = super.getWritableDatabase();
        for (int i = 0; i < contentValues.length; i++) {
            if (db.insert(table_name, null, contentValues[i]) != -1) ;
            //        Toast.makeText(c,table_name+" Inserted="+i,Toast.LENGTH_LONG).show();
           /* else
                Toast.makeText(c, table_name + " Not Inserted=" + i, Toast.LENGTH_LONG).show();*/
        }

        db.close();

    }

    public Bundle checkImageAvailable(String type, String no_of_room_key) {
        String no_of_room = getNoOfRoom(no_of_room_key);
        Bundle b = new Bundle();
        if (no_of_room == null) ;
        //   return no_of_room;
        try {
            int rooms = Integer.parseInt(no_of_room);
            Cursor cur = null;
            SQLiteDatabase db = super.getReadableDatabase();
            for (int i = 1; i <= rooms; i++) {

            /*long total = DatabaseUtils.queryNumEntries(db,
                    table_name,
                    "id=? AND " + room_field_name + "=?  AND " + table_status + "=?",
                    new String[]{Appointment.clicked, i + "", "false"});
            if (total == 0) {
                no_of_room = "false";
                break;
            }*/

                cur = db.rawQuery("Select * from ImageSelection where id=? AND type=? AND room_id=?", new String[]{Appointment.clicked, type, i + ""});
                if (!cur.moveToNext()) {
                    b.putString("value", i + "");
                    break;
                } else ;


            }
            if (cur != null)
                cur.close();
            db.close();
        }catch (NumberFormatException e)
        {
            Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
        }
        return b;
    }

    public boolean getRentSaleStatus() {
        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        boolean status=false;
        if (cq.moveToNext()&& "Rent".equalsIgnoreCase(cq.getString(cq.getColumnIndex("rent_sale_status"))) )
            status= true;
        cq.close();
        db.close();
        return status;
    }


    public class UploadFileToServer extends AsyncTask<Void, Void, String> {


        private final String image_location, id, type, room_id;

        public UploadFileToServer(String id, String type, String room_id, String image_location) {
            this.image_location = image_location;
            this.id = id;
            this.type = type;
            this.room_id = room_id;
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://qc.homeonline.com/dbho/Api/image");

            File sourceFile = new File(image_location);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                //   publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                //          SharedPreferences sp =c.getSharedPreferences("Login", c.MODE_PRIVATE);
                // Adding file data to http body
                //          entity.addPart("userID", new StringBody(sp.getString("userID", "Not Found")));

                SharedPreferences sp = c.getSharedPreferences("Login", c.MODE_PRIVATE);

                entity.addPart("userid", new StringBody(sp.getString("userID", "Not Found")));
                entity.addPart("image", new FileBody(sourceFile));
                entity.addPart("app_id", new StringBody(id));
                entity.addPart("type", new StringBody(type));
                entity.addPart("room_id", new StringBody(room_id));

                // Extra parameters if you want to pass to server

                httppost.setEntity(entity);


                String authorizationString = "Basic " + Base64.encodeToString(("homeonline" + ":" + "helloworld2016")
                        .getBytes(), Base64.NO_WRAP); //this line is diffe
              //  httppost.setHeader("Authorization", authorizationString);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                /*response.addHeader("Authorization", "Basic " +
                        Base64.encodeToString(("homeonline"+":"+"helloworld2016").getBytes(), Base64.DEFAULT));
              */


                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

         /*   if(responseString.contains("success")) {
                SQLiteDatabase db = DBHandler.super.getReadableDatabase();
                ContentValues cv= new ContentValues();
                cv.put("status", "success");
                long i= db.update("ImageSelection", cv, "id=? AND room_id=? AND type=?", new String[]{room_id, id, type});
                Toast.makeText(c,i+"",Toast.LENGTH_LONG).show();
                db.close();
            }*/
            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            //Log.e(TAG, "Response from server: " + result);
            super.onPostExecute(result);
            // showing the server response in an alert dialog
            //     Toast.makeText(c,result,Toast.LENGTH_LONG).show();
            long i = 0;
            if (result.contains("Success")) {
                SQLiteDatabase db = DBHandler.super.getReadableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("status", "success");
                i = db.update("ImageSelection", cv, "image_location_medium=?", new String[]{image_location});
                // Toast.makeText(c,i+"",Toast.LENGTH_LONG).show();
                db.close();

            }

            if (i > 0)
                Toast.makeText(c, "Image Synced Successfully for " + type + " " + room_id, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(c, "Image not synced try to resend " + type + " " + room_id, Toast.LENGTH_LONG).show();
           /* showAlert(result);
            Toast.makeText(c,"Image Synced Successfully for " +type+ " "+id,Toast.LENGTH_LONG).show();*/

            /*SQLiteDatabase db = DBHandler.super.getWritableDatabase();
            Cursor cq = db.rawQuery("Select * from ImageSelection where id=?", new String[]{Appointment.clicked});
            for (int i=0; cq.moveToNext();i++) {

                if(cq.getString(cq.getColumnIndex("livingRoom_image_URL")).equalsIgnoreCase(s))

                washroom[i]=(cq.getString(cq.getColumnIndex("WashDry_image_URL")));
                bedroom[i]=(cq.getString(cq.getColumnIndex("BedRoom_image_URL")));
                bathroom[i]=(cq.getString(cq.getColumnIndex("BathRoom_image_URL")));
                kitchen[i]=(cq.getString(cq.getColumnIndex("Kitchen_image_URL")));
                other[i]=(cq.getString(cq.getColumnIndex("Others_image_URL")));

            }
            db.delete("ImageSelection", "id=? AND livingRoom_image_URL=? OR WashDry_image_URL=", new String[]{Appointment.clicked});*/
        }


    }

    /**
     * Method to show alert dialog
     */
    private void showAlert(String message) {

        Toast.makeText(c, message, Toast.LENGTH_LONG).show();

       /* Context current;


        current = NewGallery.getContext();
        if(current!=null)
        if (((Activity) current).isFinishing()) {
            if (((Activity) c).isFinishing()) {
                //Toast
                current = MainScreen.getContext();
                if (((Activity) current).isFinishing())
                    return;
                else
                    ((MainScreen) current).onResume();
            } else
                current = c;
        } else
            ((NewGallery) current).onResume();


        AlertDialog.Builder builder = new AlertDialog.Builder(current);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();*/

    }


    private void getImageURL() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                SQLiteDatabase db = DBHandler.super.getReadableDatabase();
                Cursor cq = db.rawQuery("Select * from ImageSelection where id=? AND status=?", new String[]{Appointment.clicked, "pending"});
                for (int i = 0; cq.moveToNext(); i++) {
                    new UploadFileToServer((cq.getString(cq.getColumnIndex("id"))),
                            (cq.getString(cq.getColumnIndex("type"))),
                            (cq.getString(cq.getColumnIndex("room_id"))),
                            (cq.getString(cq.getColumnIndex("image_location_medium")))).execute();
                }
                cq.close();
                db.close();
            }
        });
    }

    /*        this method is not use in any where  */
    public void setBasicDetail(String name, String phone, String email,
                               String mobile, String building_no, String building_name,
                               String flate_number, String wing, String street, String locality,
                               String sub_locality, String pincode, String landmark, String possesion_date, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("name", name);
        c1.put("phone", phone);
        c1.put("email", email);
        c1.put("mobile", mobile);
        c1.put("building_no", building_no);
        c1.put("building_name", building_name);
        c1.put("flate_number", flate_number);
        c1.put("wing", wing);
        c1.put("street", street);
        c1.put("locality", locality);
        c1.put("sub_locality", sub_locality);
        c1.put("pincode", pincode);
        c1.put("landmark", landmark);
        c1.put("possesion_date", possesion_date);
        c1.put("status_basic_detail", status);
        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

        db.close();
    }


    public Bundle getBasicDetail() {


        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();
        if (cq.moveToNext()) {

            b.putString("name", cq.getString(cq.getColumnIndex("name")));
            b.putString("phone", cq.getString(cq.getColumnIndex("phone")));
            b.putString("email", cq.getString(cq.getColumnIndex("email")));
            b.putString("mobile", cq.getString(cq.getColumnIndex("mobile")));
            b.putString("building_no", cq.getString(cq.getColumnIndex("building_no")));
            b.putString("building_name", cq.getString(cq.getColumnIndex("building_name")));
            b.putString("flate_number", cq.getString(cq.getColumnIndex("flate_number")));
            b.putString("wing", cq.getString(cq.getColumnIndex("wing")));
            b.putString("street", cq.getString(cq.getColumnIndex("street")));
            b.putString("locality", cq.getString(cq.getColumnIndex("locality")));
            b.putString("sub_locality", cq.getString(cq.getColumnIndex("sub_locality")));
            b.putString("pincode", cq.getString(cq.getColumnIndex("pincode")));
            b.putString("landmark", cq.getString(cq.getColumnIndex("landmark")));
            b.putString("possesion_date", cq.getString(cq.getColumnIndex("possesion_date")));
        }

        db.close();
        return b;


    }

    public void setSocietyData(String societydata_boundary_wall, String societydata_gated_community,
                               /*String societydata_society_overheadtank,*/ String societydata_cctv_servillance,
                               String societydata_fire_hydrant_system, String societydata_swiming_pool,
                               String societydata_multi_purpose, String societydata_reg_society,
                               String societydata_security, String societydata_smoke_detector,
                               String societydata_club_house, String societydata_zym, String garden_lawn,

                               String society_ck_24HWS,
                               String society_ck_aerobic_room,
                               String society_ck_amphithreater,
                               String society_ck_atm_bank,
                               String society_ck_banquet_hall,
                               String society_ck_barbeque_pit,
                               String society_ck_basketball_tennis_court,
                               String society_ck_centralized_ac,
                               String society_ck_conference_room,
                               String society_ck_day_care_center,
                               String society_ck_dth_tv_facility,
                               String society_ck_early_learning_play_group,
                               String society_ck_golf_cource,
                               String society_ck_guest_accomadation,
                               String society_ck_indoor_games_room,
                               String society_ck_indoor_bedminton_court,
                               String society_ck_intercom,
                               String society_ck_kids_club,
                               String society_ck_kids_play_area,
                               String society_ck_laundry_service,
                               String society_ck_meditation_center,
                               String society_ck_paved_comound,
                               String society_ck_power_backup,
                               String society_ck_property_maintenace_staff,
                               String society_ck_rain_water_harvesting,
                               String society_ck_recreational_facilities,
                               String society_ck_rentable_community_space,
                               String society_ck_reserverd_parking,
                               String society_ck_school,
                               String society_ck_service_goods_lift,
                               String society_ck_sevage_treatment_plan,
                               String society_ck_shooping_retail,
                               String society_ck_skating_court,
                               String society_ck_strolling_cycling_jogging,
                               String society_ck_vaastu_complaint,
                               String society_ck_visitor_parking,
                               String society_ck_waiting_lounge,
                               String society_ck_waste_disposal,
                               String society_notes,
                               String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("boundary_wall", societydata_boundary_wall);
        c1.put("societydata_gated_community", societydata_gated_community);
        c1.put("societydata_reg_society", societydata_reg_society);
        // c1.put("societydata_society_overheadtank", societydata_society_overheadtank);
        c1.put("societydata_cctv_servillance", societydata_cctv_servillance);
        c1.put("societydata_fire_hydrant_system", societydata_fire_hydrant_system);
        c1.put("societydata_swiming_pool", societydata_swiming_pool);
        c1.put("societydata_multi_purpose", societydata_multi_purpose);
        c1.put("societydata_security", societydata_security);
        c1.put("societydata_smoke_detector", societydata_smoke_detector);
        c1.put("societydata_club_house", societydata_club_house);
        c1.put("societydata_zym", societydata_zym);
        c1.put("garden_lawn", garden_lawn);

        c1.put("society_ck_24HWS", society_ck_24HWS);
        c1.put("society_ck_aerobic_room", society_ck_aerobic_room);
        c1.put("society_ck_amphithreater", society_ck_amphithreater);
        c1.put("society_ck_atm_bank", society_ck_atm_bank);
        c1.put("society_ck_banquet_hall", society_ck_banquet_hall);
        c1.put("society_ck_barbeque_pit", society_ck_barbeque_pit);
        c1.put("society_ck_basketball_tennis_court", society_ck_basketball_tennis_court);
        c1.put("society_ck_centralized_ac", society_ck_centralized_ac);
        c1.put("society_ck_conference_room", society_ck_conference_room);
        c1.put("society_ck_day_care_center", society_ck_day_care_center);
        c1.put("society_ck_dth_tv_facility", society_ck_dth_tv_facility);
        c1.put("society_ck_early_learning_play_group", society_ck_early_learning_play_group);
        c1.put("society_ck_golf_cource", society_ck_golf_cource);
        c1.put("society_ck_guest_accomadation", society_ck_guest_accomadation);
        c1.put("society_ck_indoor_games_room", society_ck_indoor_games_room);
        c1.put("society_ck_indoor_bedminton_court", society_ck_indoor_bedminton_court);
        c1.put("society_ck_intercom", society_ck_intercom);
        c1.put("society_ck_kids_club", society_ck_kids_club);
        c1.put("society_ck_kids_play_area", society_ck_kids_play_area);
        c1.put("society_ck_laundry_service", society_ck_laundry_service);
        c1.put("society_ck_meditation_center", society_ck_meditation_center);
        c1.put("society_ck_paved_comound", society_ck_paved_comound);
        c1.put("society_ck_power_backup", society_ck_power_backup);
        c1.put("society_ck_property_maintenace_staff", society_ck_property_maintenace_staff);
        c1.put("society_ck_rain_water_harvesting", society_ck_rain_water_harvesting);
        c1.put("society_ck_recreational_facilities", society_ck_recreational_facilities);
        c1.put("society_ck_rentable_community_space", society_ck_rentable_community_space);
        c1.put("society_ck_reserverd_parking", society_ck_reserverd_parking);
        c1.put("society_ck_school", society_ck_school);
        c1.put("society_ck_service_goods_lift", society_ck_service_goods_lift);
        c1.put("society_ck_sevage_treatment_plan", society_ck_sevage_treatment_plan);
        c1.put("society_ck_shooping_retail", society_ck_shooping_retail);
        c1.put("society_ck_skating_court", society_ck_skating_court);
        c1.put("society_ck_strolling_cycling_jogging", society_ck_strolling_cycling_jogging);
        c1.put("society_ck_vaastu_complaint", society_ck_vaastu_complaint);
        c1.put("society_ck_visitor_parking", society_ck_visitor_parking);
        c1.put("society_ck_waiting_lounge", society_ck_waiting_lounge);
        c1.put("society_ck_waste_disposal", society_ck_waste_disposal);
        c1.put("society_notes", society_notes);


        c1.put("status_society", status);

        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

//        Toast.makeText(c, i + "society data saved", Toast.LENGTH_LONG).show();

        db.close();
    }

    public Bundle getSocietyData() {

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();
        cq.moveToNext();


        b.putString("societydata_boundary_wall", cq.getString(cq.getColumnIndex("boundary_wall")));
        b.putString("societydata_gated_community", cq.getString(cq.getColumnIndex("societydata_gated_community")));
        b.putString("societydata_reg_society", cq.getString(cq.getColumnIndex("societydata_reg_society")));
        //     b.putString("societydata_society_overheadtank", cq.getString(cq.getColumnIndex("societydata_society_overheadtank")));
        b.putString("societydata_security", cq.getString(cq.getColumnIndex("societydata_security")));
        b.putString("societydata_cctv_servillance", cq.getString(cq.getColumnIndex("societydata_cctv_servillance")));
        b.putString("societydata_smoke_detector", cq.getString(cq.getColumnIndex("societydata_smoke_detector")));
        b.putString("societydata_fire_hydrant_system", cq.getString(cq.getColumnIndex("societydata_fire_hydrant_system")));
        b.putString("societydata_club_house", cq.getString(cq.getColumnIndex("societydata_club_house")));
        b.putString("societydata_swiming_pool", cq.getString(cq.getColumnIndex("societydata_swiming_pool")));
        b.putString("societydata_zym", cq.getString(cq.getColumnIndex("societydata_zym")));
        b.putString("garden_lawn", cq.getString(cq.getColumnIndex("garden_lawn")));
        b.putString("societydata_multi_purpose", cq.getString(cq.getColumnIndex("societydata_multi_purpose")));

        b.putString("society_ck_24HWS", cq.getString(cq.getColumnIndex("society_ck_24HWS")));
        b.putString("society_ck_aerobic_room", cq.getString(cq.getColumnIndex("society_ck_aerobic_room")));
        b.putString("society_ck_amphithreater", cq.getString(cq.getColumnIndex("society_ck_amphithreater")));
        b.putString("society_ck_atm_bank", cq.getString(cq.getColumnIndex("society_ck_atm_bank")));
        b.putString("society_ck_banquet_hall", cq.getString(cq.getColumnIndex("society_ck_banquet_hall")));
        b.putString("society_ck_barbeque_pit", cq.getString(cq.getColumnIndex("society_ck_barbeque_pit")));
        b.putString("society_ck_basketball_tennis_court", cq.getString(cq.getColumnIndex("society_ck_basketball_tennis_court")));
        b.putString("society_ck_centralized_ac", cq.getString(cq.getColumnIndex("society_ck_centralized_ac")));
        b.putString("society_ck_conference_room", cq.getString(cq.getColumnIndex("society_ck_conference_room")));
        b.putString("society_ck_day_care_center", cq.getString(cq.getColumnIndex("society_ck_day_care_center")));
        b.putString("society_ck_dth_tv_facility", cq.getString(cq.getColumnIndex("society_ck_dth_tv_facility")));
        b.putString("society_ck_early_learning_play_group", cq.getString(cq.getColumnIndex("society_ck_early_learning_play_group")));
        b.putString("society_ck_golf_cource", cq.getString(cq.getColumnIndex("society_ck_golf_cource")));
        b.putString("society_ck_guest_accomadation", cq.getString(cq.getColumnIndex("society_ck_guest_accomadation")));
        b.putString("society_ck_indoor_games_room", cq.getString(cq.getColumnIndex("society_ck_indoor_games_room")));
        b.putString("society_ck_intercom", cq.getString(cq.getColumnIndex("society_ck_intercom")));
        b.putString("society_ck_kids_club", cq.getString(cq.getColumnIndex("society_ck_kids_club")));
        b.putString("society_ck_kids_play_area", cq.getString(cq.getColumnIndex("society_ck_kids_play_area")));
        b.putString("society_ck_laundry_service", cq.getString(cq.getColumnIndex("society_ck_laundry_service")));
        b.putString("society_ck_meditation_center", cq.getString(cq.getColumnIndex("society_ck_meditation_center")));
        b.putString("society_ck_paved_comound", cq.getString(cq.getColumnIndex("society_ck_paved_comound")));
        b.putString("society_ck_power_backup", cq.getString(cq.getColumnIndex("society_ck_power_backup")));
        b.putString("society_ck_property_maintenace_staff", cq.getString(cq.getColumnIndex("society_ck_property_maintenace_staff")));
        b.putString("society_ck_rain_water_harvesting", cq.getString(cq.getColumnIndex("society_ck_rain_water_harvesting")));
        b.putString("society_ck_recreational_facilities", cq.getString(cq.getColumnIndex("society_ck_recreational_facilities")));
        b.putString("society_ck_rentable_community_space", cq.getString(cq.getColumnIndex("society_ck_rentable_community_space")));
        b.putString("society_ck_reserverd_parking", cq.getString(cq.getColumnIndex("society_ck_reserverd_parking")));
        b.putString("society_ck_school", cq.getString(cq.getColumnIndex("society_ck_school")));
        b.putString("society_ck_service_goods_lift", cq.getString(cq.getColumnIndex("society_ck_service_goods_lift")));
        b.putString("society_ck_sevage_treatment_plan", cq.getString(cq.getColumnIndex("society_ck_sevage_treatment_plan")));
        b.putString("society_ck_shooping_retail", cq.getString(cq.getColumnIndex("society_ck_shooping_retail")));
        b.putString("society_ck_skating_court", cq.getString(cq.getColumnIndex("society_ck_skating_court")));
        b.putString("society_ck_strolling_cycling_jogging", cq.getString(cq.getColumnIndex("society_ck_strolling_cycling_jogging")));
        b.putString("society_ck_vaastu_complaint", cq.getString(cq.getColumnIndex("society_ck_vaastu_complaint")));
        b.putString("society_ck_visitor_parking", cq.getString(cq.getColumnIndex("society_ck_visitor_parking")));
        b.putString("society_ck_waiting_lounge", cq.getString(cq.getColumnIndex("society_ck_waiting_lounge")));
        b.putString("society_ck_waste_disposal", cq.getString(cq.getColumnIndex("society_ck_waste_disposal")));
        b.putString("society_ck_indoor_bedminton_court", cq.getString(cq.getColumnIndex("society_ck_indoor_bedminton_court")));
        b.putString("society_notes", cq.getString(cq.getColumnIndex("society_notes")));

      /*  b.putString("societydata_society_name", cq.getString(cq.getColumnIndex("society_name")));
        b.putString("societydata_no_of_building", cq.getString(cq.getColumnIndex("no_of_building")));
        b.putString("kids_playarea1", cq.getString(cq.getColumnIndex("kids_playarea1")));
        b.putString("saunna_steam", cq.getString(cq.getColumnIndex("saunna_steam")));
        b.putString("yogaroom", cq.getString(cq.getColumnIndex("yogaroom")));
        b.putString("billiards", cq.getString(cq.getColumnIndex("billiards")));
        b.putString("tennis", cq.getString(cq.getColumnIndex("tennis")));
        b.putString("volleyball", cq.getString(cq.getColumnIndex("volleyball")));
        b.putString("batminton", cq.getString(cq.getColumnIndex("batminton")));
        b.putString("table_tennis", cq.getString(cq.getColumnIndex("table_tennis")));
        b.putString("squash", cq.getString(cq.getColumnIndex("squash")));*/


        cq.close();
        db.close();
        return b;
    }

    public void setResidential(/*String no_of_building, String residential_no_of_storeys,*/ String residential_servent_room,
                               String residential_prayersroom,
                               /*String residential_terrace_access,*/ String residential_private_access,
                               String residential_main_enterance_facing, String residential_power_backup,
                               String residential_water_supply_municipal, String residential_water_supply_borewell,
                               String waterbackup_grounded_tanks, String waterbackup_terrace_tanks,
                               String residential_wifi_internet,
                               String residential_solar_water_heater, String parking_type, String furnishing, String balcony, String common_area
            , String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        //   c1.put("no_of_building", no_of_building);
        //   c1.put("no_of_storys", residential_no_of_storeys);
        c1.put("servant_room", residential_servent_room);
        c1.put("prayer_room", residential_prayersroom);
        //    c1.put("terrace_access", residential_terrace_access);
        c1.put("private_access", residential_private_access);
        c1.put("main_entrance_facing", residential_main_enterance_facing);
        c1.put("power_backup", residential_power_backup);

        c1.put("water_supply_municipal", residential_water_supply_municipal);
        c1.put("water_supply_borewell", residential_water_supply_borewell);

        c1.put("waterbackup_grounded_tanks", waterbackup_grounded_tanks);
        c1.put("waterbackup_terrace_tanks", waterbackup_terrace_tanks);
        c1.put("wifi", residential_wifi_internet);
        c1.put("solar_heater", residential_solar_water_heater);
        c1.put("parking_type", parking_type);
        c1.put("furnishing_status", furnishing);
        c1.put("balcony", balcony);
        c1.put("common_area", common_area);

        c1.put("status_residential", status);


        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

//        Toast.makeText(c, i + "", Toast.LENGTH_LONG).show();

        db.close();
    }

    public Bundle getResidential() {

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();
        if (cq.moveToNext()) {


            //    b.putString("no_of_building", cq.getString(cq.getColumnIndex("no_of_building")));
            //    b.putString("residential_no_of_storeys", cq.getString(cq.getColumnIndex("no_of_storys")));
            b.putString("residential_servent_room", cq.getString(cq.getColumnIndex("servant_room")));
            b.putString("residential_prayersroom", cq.getString(cq.getColumnIndex("prayer_room")));
            //    b.putString("residential_terrace_access", cq.getString(cq.getColumnIndex("terrace_access")));
            b.putString("residential_private_access", cq.getString(cq.getColumnIndex("private_access")));
            b.putString("residential_main_enterance_facing", cq.getString(cq.getColumnIndex("main_entrance_facing")));
            b.putString("residential_power_backup", cq.getString(cq.getColumnIndex("power_backup")));
            b.putString("water_supply_municipal", cq.getString(cq.getColumnIndex("water_supply_municipal")));
            b.putString("water_supply_borewell", cq.getString(cq.getColumnIndex("water_supply_borewell")));
            b.putString("residential_wifi_internet", cq.getString(cq.getColumnIndex("wifi")));
            b.putString("residential_solar_water_heater", cq.getString(cq.getColumnIndex("solar_heater")));
            b.putString("waterbackup_grounded_tanks", cq.getString(cq.getColumnIndex("waterbackup_grounded_tanks")));
            b.putString("waterbackup_terrace_tanks", cq.getString(cq.getColumnIndex("waterbackup_terrace_tanks")));
            b.putString("residentiel_spiner_parking_type", cq.getString(cq.getColumnIndex("parking_type")));
            b.putString("residentiel_spiner_furnishing_status", cq.getString(cq.getColumnIndex("furnishing_status")));
            b.putString("balcony", cq.getString(cq.getColumnIndex("balcony")));
            b.putString("common_area", cq.getString(cq.getColumnIndex("common_area")));


        }
        cq.close();
        db.close();
        return b;
    }

    public void setStatus(String status) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("status", status);

        db.update("Appointments", cv, "id=?", new String[]{Appointment.clicked});

        db.close();
    }


    public Bundle getStatus() {
        Bundle b = new Bundle();

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        if (cur.moveToNext())
            b.putString("status", cur.getString(cur.getColumnIndex("status")));
        cur.close();
        db.close();
        return b;
    }


    public String getNoOfRoom(String check) {

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        String ret = "1";
        if (cur.moveToNext()) {
            ret = (cur.getString(cur.getColumnIndex(check)));
        }
        cur.close();
        db.close();
        return ret;

    }

    public String getButtonStatus(String tablename, String status) {

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from " + tablename + " where id=?", new String[]{Appointment.clicked});
        String check = null;
        while (cur.moveToNext()) {
            if (!"true".equalsIgnoreCase(cur.getString(cur.getColumnIndex(status)))) {
                check = "false";
                break;
            } else
                check = "true";
        }
        cur.close();
        db.close();
        return check;

    }

    public void setImageURL(String selected, String room_id, String path) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String local = path;
        String local_medium = path;
        cv.put("id", Appointment.clicked);
        cv.put("type", selected);
        cv.put("room_id", room_id);
        cv.put("image_location", local);
        Log.d("db", local);
        cv.put("status", "pending");
        String s = path.replace("DB", "thumbnail");
        cv.put("image_location_thumbnail", s);
        Log.d("thumbnail", s);
        String medium = local_medium.replace("DB", "medium");
        cv.put("image_location_medium", medium);
        Log.d("medium", medium);
        //  db.update("ImageSelection", cv, "id=?", new String[]{Appointment.clicked});
        long i = db.insert("ImageSelection", null, cv);
        db.close();

    }

    public Bundle getIdName() {
        Bundle b = new Bundle();

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        if (cur.moveToNext()) {
            b.putString("name", cur.getString(cur.getColumnIndex("name")));
            b.putString("description", cur.getString(cur.getColumnIndex("description")));
        } else Toast.makeText(c, "error", Toast.LENGTH_SHORT).show();
        cur.close();
        db.close();
        return b;
    }


    public void setPropertyDetail(/*String bhk_type,*/ String property_type, String total_livingroom, String total_bedroom,
                                  String total_kitchen, String total_bathroom, String total_balcony,
                                  String preferred_visit_time, String possesion_date, String property_status, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();


        c1.put("property_type", property_type);
        //c1.put("bhk_type", bhk_type);
        c1.put("no_of_livingroom", total_livingroom);
        c1.put("no_of_bedroom", total_bedroom);
        c1.put("no_of_kitchen", total_kitchen);
        c1.put("no_of_bathroom", total_bathroom);
        c1.put("no_of_balcony", total_balcony);
        c1.put("status_property_detail", status);
        c1.put("preferred_visit_time", preferred_visit_time);
        c1.put("possesion_date", possesion_date);
        c1.put("property_status", property_status);


        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

//        Toast.makeText(c, i + "", Toast.LENGTH_LONG).show();

        db.close();
    }

    public Bundle getPropertyDetail() {

        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();
        if (cq.moveToNext()) {

            b.putString("property_type", cq.getString(cq.getColumnIndex("property_type")));
            //    b.putString("bhk_type", cq.getString(cq.getColumnIndex("bhk_type")));
            b.putString("total_livingroom", cq.getString(cq.getColumnIndex("no_of_livingroom")));
            b.putString("total_bedroom", cq.getString(cq.getColumnIndex("no_of_bedroom")));
            b.putString("total_kitchen", cq.getString(cq.getColumnIndex("no_of_kitchen")));
            b.putString("total_bathroom", cq.getString(cq.getColumnIndex("no_of_bathroom")));
            b.putString("total_balcony", cq.getString(cq.getColumnIndex("no_of_balcony")));
            b.putString("preferred_visit_time", cq.getString(cq.getColumnIndex("preferred_visit_time")));
            b.putString("possesion_date", cq.getString(cq.getColumnIndex("possesion_date")));
            b.putString("property_spinner_property_status", cq.getString(cq.getColumnIndex("property_status")));


        }
        cq.close();
        db.close();
        return b;

    }

    public void setAdvertiserDetail(String owner_name, String owner_number, String owner_alternate_number,
                                    String owner_email, /*String owner_broker,*/
                                    /*String developer_type,*/ String owner_type,
                                    /*String building_no,*/ String society_name,
                                    /*/String flate_number, String wing,*/ String address1, String address2,
                                    /*String sub_locality,*/ String pincode, /*String landmark,*/ String floor_no, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("name", owner_name);
        c1.put("phone", owner_number);
        c1.put("mobile", owner_alternate_number);
        c1.put("email", owner_email);
        //c1.put("owner_broker", owner_broker);
        //  c1.put("developer_type", developer_type);
        c1.put("owner_type", owner_type);
        //  c1.put("building_no", building_no);
        c1.put("society_name", society_name);
        //   c1.put("flate_number", flate_number);
        //   c1.put("wing", wing);
        c1.put("description", address1);
        c1.put("address2", address2);
        //   c1.put("sub_locality", sub_locality);
        c1.put("pincode", pincode);
        //    c1.put("landmark", landmark);
        c1.put("floor_no", floor_no);
        c1.put("status_advertiser_detail", status);
        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

        db.close();

    }

    public Bundle getAdvertiserDetail() {
        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();

        if (cq.moveToNext()) {
            b.putString("owner_name", cq.getString(cq.getColumnIndex("name")));
            b.putString("owner_number", cq.getString(cq.getColumnIndex("phone")));
            b.putString("owner_alternate_number", cq.getString(cq.getColumnIndex("mobile")));
            b.putString("owner_email", cq.getString(cq.getColumnIndex("email")));
          //  b.putString("owner_broker", cq.getString(cq.getColumnIndex("owner_broker")));
            //     b.putString("developer_type", cq.getString(cq.getColumnIndex("developer_type")));
            b.putString("owner_type", cq.getString(cq.getColumnIndex("owner_type")));
            //     b.putString("building_no", cq.getString(cq.getColumnIndex("building_no")));
            b.putString("society_name", cq.getString(cq.getColumnIndex("society_name")));
            //     b.putString("flate_number", cq.getString(cq.getColumnIndex("flate_number")));
            //     b.putString("wing", cq.getString(cq.getColumnIndex("wing")));
            b.putString("address1", cq.getString(cq.getColumnIndex("description")));
            b.putString("address2", cq.getString(cq.getColumnIndex("address2")));
            //     b.putString("sub_locality", cq.getString(cq.getColumnIndex("sub_locality")));
            b.putString("pincode", cq.getString(cq.getColumnIndex("pincode")));
            //     b.putString("landmark", cq.getString(cq.getColumnIndex("landmark")));
            b.putString("floor_no", cq.getString(cq.getColumnIndex("floor_no")));
        }
        cq.close();
        db.close();
        return b;
    }

    public int[] getDashBoardStatus() {
        SQLiteDatabase db = super.getReadableDatabase();
        int check[] = {0, 0};
        Cursor cq = db.rawQuery("Select * from Appointments", null);
        check[0] = cq.getCount();
        cq.close();
        cq = db.rawQuery("Select * from Appointments where status=?", new String[]{"Complete"});
        check[1] = cq.getCount();

        cq.close();
        db.close();
        return check;
    }

    public String[][] getAppoinmentDetail() {
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cq = db.rawQuery("Select * from Appointments", null);
        //  Cursor cs = db.rawQuery("Select * from ImageSelection",null);


        String[] id = new String[cq.getCount()];
        String[] status = new String[cq.getCount()];
        String[] imagestatus = new String[cq.getCount()];

        for (int i = 0; cq.moveToNext(); i++) {
            id[i] = cq.getString(cq.getColumnIndex("id"));
            status[i] = (cq.getString(cq.getColumnIndex("status")));


            Cursor cs = db.rawQuery("Select * from ImageSelection where id=?", new String[]{id[i]}); // TODO FIRE QUERY ON THE BASIS OF ID AND STATUS, NO NEED OF FOR LOOP
            int count = cs.getCount();

            int k = 0;
            for (; cs.moveToNext() && "success".equalsIgnoreCase(cs.getString(cs.getColumnIndex("status"))); k++) // TODO PUT IF CONDITION IN FOR LOOP TO CHECK EVERY STATUS
                ;
            imagestatus[i] = k + "/" + count;
            cs.close();

        }

        cq.close();
        db.close();
        String a[][] = new String[][]{id, status, imagestatus};
        return a;
    }

    public ArrayList[] giveImageURL(String type) {

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cq = db.rawQuery("Select * from ImageSelection where id=? AND type=?", new String[]{Appointment.clicked, type});
        ArrayList<File> arr = new ArrayList<File>();
        ArrayList<String> brr = new ArrayList<String>();
        for (int i = 0; cq.moveToNext(); i++) {
            arr.add(new File(cq.getString(cq.getColumnIndex("image_location_thumbnail"))));
            brr.add(new String(cq.getString(cq.getColumnIndex("status"))));

        }
        cq.close();
        db.close();
        return (new ArrayList[]{arr, brr});
    }

    public void setRentScreen(String brokeragefee, /*String maintanancefee,*/ String food, String lease_type, String pet, /*String rent_*/ String security_, String deposite_,/* String availability_date,*/ String aTrue) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();


        c1.put("brokerage_fee", brokeragefee);

        c1.put("food", food);
        c1.put("lease_type", lease_type);
        c1.put("pets_allowed", pet);
       /* c1.put("rent_negotiable", rent_);*/
        c1.put("security_negotiable", security_);
        c1.put("security_deposit", deposite_);
        //   c1.put("availability_date", availability_date);
        c1.put("status_rentscreen", aTrue);
        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

        db.close();

    }

    public Bundle getRentScreen() {
        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();

        if (cq.moveToNext()) {
            b.putString("brokerage_fee", cq.getString(cq.getColumnIndex("brokerage_fee")));
            b.putString("food", cq.getString(cq.getColumnIndex("food")));
            b.putString("lease_type", cq.getString(cq.getColumnIndex("lease_type")));
            b.putString("pets_allowed", cq.getString(cq.getColumnIndex("pets_allowed")));
            b.putString("security_negotiable", cq.getString(cq.getColumnIndex("security_negotiable")));
            b.putString("security_deposit", cq.getString(cq.getColumnIndex("security_deposit")));
            //   b.putString("availability_date", cq.getString(cq.getColumnIndex("availability_date")));
        }
        cq.close();
        db.close();
        return b;
    }


    public String getRoomButtonStatus(final String table_name, final String room_field_name, final String no_of_room_key, final String table_status) {
        String no_of_room = getNoOfRoom(no_of_room_key);

        if (no_of_room == null)
            return no_of_room;
        try {
            int rooms = Integer.parseInt(no_of_room);
            Cursor cur = null;
            no_of_room = null;
            SQLiteDatabase db = super.getReadableDatabase();
            for (int i = 1; i <= rooms; i++) {

            /*long total = DatabaseUtils.queryNumEntries(db,
                    table_name,
                    "id=? AND " + room_field_name + "=?  AND " + table_status + "=?",
                    new String[]{Appointment.clicked, i + "", "false"});
            if (total == 0) {
                no_of_room = "false";
                break;
            }*/

                cur = db.rawQuery("Select " + table_status + " from " + table_name + " where id=? AND " + room_field_name + "=?", new String[]{Appointment.clicked, i + ""});
                if (!cur.moveToNext() || !"true".equalsIgnoreCase(cur.getString(cur.getColumnIndex(table_status)))) {
                    no_of_room = "false";
                    break;
                } else
                    no_of_room = "true";

            }
            if (cur != null)
                cur.close();
            db.close();
        }catch(NumberFormatException e)
        {
            Toast.makeText(c,"NumberFormatException",Toast.LENGTH_LONG).show();
        }
        return no_of_room;
    }


    public void setUpdateFromServerStatus(final ContentValues cv, final String appointment_clicked) {
        SQLiteDatabase db = super.getWritableDatabase();

        if (db.update("Appointments", cv, "id=?", new String[]{appointment_clicked}) != 0) ;
           /* Toast.makeText(c, "Status Update", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(c, "Status Fail To Update", Toast.LENGTH_LONG).show();*/
        db.close();

    }

    public boolean checkStatusUpdateFromServer(String appointmentID) {
        SQLiteDatabase db = super.getReadableDatabase();

        long total = DatabaseUtils.queryNumEntries(db,
                "Appointments",
                "id=? AND update_from_server=?",
                new String[]{appointmentID, "true"});
        db.close();

        return total > 0 ? true : false;

    }


    /*  End of methods rest is useless  */


    //this is method not use in any where
    public void saveDetails(
            String ap_id,
            String ap_property_type,
            /*String ap_bhk_type,*/
            String ap_possesion_compilation_date,
            String ap_availability_date,
            String ap_pets_allowed, String ap_food,
            String ap_preferred_visit_time,
            String ap_advertiser_type,
            String ap_name,
            String ap_phone,
            String ap_alternate_phone_no,
            String ap_email,
            String ap_ownership_type,
            //     String ap_developer_type,
            //     String ap_building_no_name,
            //     String ap_flate_number,
            String ap_floor_no,
            //     String ap_wing,
            //     String ap_street,
            //      String ap_locality,
            //     String ap_sub_locality,
            String ap_pincode,
            //     String ap_landmark,
            String ap_security_deposit,
            String ap_brokerage_fee,
            String ap_rent_negotiable,
            String ap_security_negotiable,
            String ap_maintainance,
            String ap_rent_ammount,
            String ap_no_of_floor,
            String ap_no_of_lift,
            String ap_age_of_building,
            String ap_builtup_area,
            String ap_carpet_area,
            // String ap_no_of_storys,
            String ap_servant_room,
            String ap_prayer_room,
            String ap_total_balcony,
            //   String ap_terrace,
            String ap_private_terrace,
            String ap_main_entrance_facing,
            String ap_power_backup,
            String ap_wifi,
            String ap_water_supply_municipal,
            String ap_water_supply_borewell,
            String ap_solar_heater,
            String ap_societydata_gated_community,
            String ap_society_name,
            //       String ap_no_of_building,
            /*String ap_societydata_society_overheadtank,*/
            String ap_boundary_wall,
            String ap_societydata_cctv_servillance,
            String ap_societydata_smoke_detector,
            String ap_societydata_fire_hydrant_system,
            String ap_societydata_security,
            String ap_societydata_club_house,
            String ap_societydata_swiming_pool,
            String ap_societydata_gym,
            String ap_societydata_multi_purpose,
            String ap_garden_lawn,
            String ap_no_of_toilet,
            String ap_total_kitchen,
            String ap_total_livingroom,
            String ap_total_bedroom,
            String ap_pricing_plot_area,
            String ap_pricing_sale_status,
            String ap_waterbackup_grounded_tank,
            String ap_waterbackup_terrace_tank,
            String ap_society_ck_24HWS,
            String ap_society_ck_aerobic_room,
            String ap_society_ck_amphithreater,
            String ap_society_ck_atm_bank,
            String ap_society_ck_banquet_hall,
            String ap_society_ck_barbeque_pit,
            String ap_society_ck_basketball_tennis_court,
            String ap_society_ck_centralized_ac,
            String ap_society_ck_conference_room,
            String ap_society_ck_day_care_center,
            String ap_society_ck_dth_tv_facility,
            String ap_society_ck_early_learning_play_group,
            String ap_society_ck_golf_cource,
            String ap_society_ck_guest_accomadation,
            String ap_society_ck_indoor_games_room,
            String ap_society_ck_indoor_bedminton_court,
            String ap_society_ck_intercom,
            String ap_society_ck_kids_club,
            String ap_society_ck_kids_play_area,
            String ap_society_ck_laundry_service,
            String ap_society_ck_meditation_center,
            String ap_society_ck_paved_comound,
            String ap_society_ck_property_maintenace_staff,
            String ap_society_ck_rain_water_harvesting,
            String ap_society_ck_recreational_facilities,
            String ap_society_ck_rentable_community_space,
            String ap_society_ck_reserverd_parking,
            String ap_society_ck_school,
            String ap_society_ck_service_goods_lift,
            String ap_society_ck_sevage_treatment_plan,
            String ap_society_ck_shooping_retail,
            String ap_society_ck_skating_court,
            String ap_society_ck_strolling_cycling_jogging,
            String ap_society_ck_vaastu_complaint,
            String ap_society_ck_visitor_parking,
            String ap_society_ck_waiting_lounge,
            String ap_society_ck_waste_disposal,
            String ap_lease_type,
            String ap_societydata_reg_society) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

          /* Property Data*/

        c1.put("property_type", ap_property_type);
        //   c1.put("bhk_type", ap_bhk_type);
        c1.put("no_of_livingroom", ap_total_livingroom);
        c1.put("no_of_bedroom", ap_total_bedroom);
        c1.put("no_of_kitchen", ap_total_kitchen);
        c1.put("no_of_bathroom", ap_no_of_toilet);
        c1.put("no_of_balcony", ap_total_balcony);
        //    c1.put("status_property_detail", status);
        c1.put("preferred_visit_time", ap_preferred_visit_time);
        c1.put("possesion_date", ap_possesion_compilation_date);


        /*  Residential Data*/

        // c1.put("no_of_building", ap_no_of_building);
        //  c1.put("no_of_storys", ap_no_of_storys);
        c1.put("servant_room", ap_servant_room);
        c1.put("prayer_room", ap_prayer_room);
        //  c1.put("terrace_access", ap_terrace);
        c1.put("private_access", ap_private_terrace);
        c1.put("main_entrance_facing", ap_main_entrance_facing);
        c1.put("power_backup", ap_power_backup);
        c1.put("water_supply_municipal", ap_water_supply_municipal);
        c1.put("water_supply_borewell", ap_water_supply_borewell);
        c1.put("waterbackup_grounded_tanks", ap_waterbackup_grounded_tank);
        c1.put("waterbackup_terrace_tanks", ap_waterbackup_terrace_tank);
        c1.put("wifi", ap_wifi);
        c1.put("solar_heater", ap_solar_heater);

       /* Appointment main details*/

        c1.put("id", ap_id);
        c1.put("name", ap_name);
        c1.put("phone", ap_phone);
        c1.put("mobile", ap_alternate_phone_no);
        c1.put("email", ap_email);
        c1.put("owner_broker", ap_advertiser_type);
        //   c1.put("developer_type", ap_developer_type);
        c1.put("owner_type", ap_ownership_type);
        //   c1.put("building_no", ap_building_no_name);
        c1.put("society_name", ap_society_name);
        //   c1.put("flate_number", ap_flate_number);
        //    c1.put("wing", ap_wing);
        //    c1.put("street", ap_street);
        //     c1.put("locality", ap_locality);
        //      c1.put("sub_locality", ap_sub_locality);
        c1.put("pincode", ap_pincode);
        //      c1.put("landmark", ap_landmark);
        c1.put("floor_no", ap_floor_no);
        //   c1.put("status_advertiser_detail", status);


          /* Rent Screen Data*/

        c1.put("brokerage_fee", ap_brokerage_fee);
        c1.put("maintainance", ap_maintainance);
        c1.put("food", ap_food);
        c1.put("lease_type", ap_lease_type);
        c1.put("pets_allowed", ap_pets_allowed);
        c1.put("rent_negotiable", ap_rent_negotiable);
        c1.put("security_negotiable", ap_security_negotiable);
        c1.put("security_deposit", ap_security_deposit);
        //  c1.put("availability_date", ap_availability_date);
        //   c1.put("status_rentscreen", ap_rent_ammount);
        //    String click = Appointment.clicked;

      /*   Society Data*/

        c1.put("boundary_wall", ap_boundary_wall);
        c1.put("societydata_gated_community", ap_societydata_gated_community);
        c1.put("societydata_reg_society", ap_societydata_reg_society);
        //   c1.put("societydata_society_overheadtank", ap_societydata_society_overheadtank);
        c1.put("societydata_cctv_servillance", ap_societydata_cctv_servillance);
        c1.put("societydata_fire_hydrant_system", ap_societydata_fire_hydrant_system);
        c1.put("societydata_swiming_pool", ap_societydata_swiming_pool);
        c1.put("societydata_multi_purpose", ap_societydata_multi_purpose);
        c1.put("societydata_security", ap_societydata_security);
        c1.put("societydata_smoke_detector", ap_societydata_smoke_detector);
        c1.put("societydata_club_house", ap_societydata_club_house);
        c1.put("societydata_zym", ap_societydata_gym);
        c1.put("garden_lawn", ap_garden_lawn);

        c1.put("society_ck_24HWS", ap_society_ck_24HWS);
        c1.put("society_ck_aerobic_room", ap_society_ck_aerobic_room);
        c1.put("society_ck_amphithreater", ap_society_ck_amphithreater);
        c1.put("society_ck_atm_bank", ap_society_ck_atm_bank);
        c1.put("society_ck_banquet_hall", ap_society_ck_banquet_hall);
        c1.put("society_ck_barbeque_pit", ap_society_ck_barbeque_pit);
        c1.put("society_ck_basketball_tennis_court", ap_society_ck_basketball_tennis_court);
        c1.put("society_ck_centralized_ac", ap_society_ck_centralized_ac);
        c1.put("society_ck_conference_room", ap_society_ck_conference_room);
        c1.put("society_ck_day_care_center", ap_society_ck_day_care_center);
        c1.put("society_ck_dth_tv_facility", ap_society_ck_dth_tv_facility);
        c1.put("society_ck_early_learning_play_group", ap_society_ck_early_learning_play_group);
        c1.put("society_ck_golf_cource", ap_society_ck_golf_cource);
        c1.put("society_ck_guest_accomadation", ap_society_ck_guest_accomadation);
        c1.put("society_ck_indoor_games_room", ap_society_ck_indoor_games_room);
        c1.put("society_ck_indoor_bedminton_court", ap_society_ck_indoor_bedminton_court);
        c1.put("society_ck_intercom", ap_society_ck_intercom);
        c1.put("society_ck_kids_club", ap_society_ck_kids_club);
        c1.put("society_ck_kids_play_area", ap_society_ck_kids_play_area);
        c1.put("society_ck_laundry_service", ap_society_ck_laundry_service);
        c1.put("society_ck_meditation_center", ap_society_ck_meditation_center);
        c1.put("society_ck_paved_comound", ap_society_ck_paved_comound);
        c1.put("society_ck_power_backup", ap_power_backup);
        c1.put("society_ck_property_maintenace_staff", ap_society_ck_property_maintenace_staff);
        c1.put("society_ck_rain_water_harvesting", ap_society_ck_rain_water_harvesting);
        c1.put("society_ck_recreational_facilities", ap_society_ck_recreational_facilities);
        c1.put("society_ck_rentable_community_space", ap_society_ck_rentable_community_space);
        c1.put("society_ck_reserverd_parking", ap_society_ck_reserverd_parking);
        c1.put("society_ck_school", ap_society_ck_school);
        c1.put("society_ck_service_goods_lift", ap_society_ck_service_goods_lift);
        c1.put("society_ck_sevage_treatment_plan", ap_society_ck_sevage_treatment_plan);
        c1.put("society_ck_shooping_retail", ap_society_ck_shooping_retail);
        c1.put("society_ck_skating_court", ap_society_ck_skating_court);
        c1.put("society_ck_strolling_cycling_jogging", ap_society_ck_strolling_cycling_jogging);
        c1.put("society_ck_vaastu_complaint", ap_society_ck_vaastu_complaint);
        c1.put("society_ck_visitor_parking", ap_society_ck_visitor_parking);
        c1.put("society_ck_waiting_lounge", ap_society_ck_waiting_lounge);
        c1.put("society_ck_waste_disposal", ap_society_ck_waste_disposal);


        /*Pricing Data*/

        c1.put("builtup_area", ap_builtup_area);
        c1.put("carpet_area", ap_carpet_area);
        c1.put("rent_ammount", ap_rent_ammount);
        c1.put("no_of_floor", ap_no_of_floor);
        c1.put("age_of_building", ap_age_of_building);
        c1.put("no_of_lift", ap_no_of_lift);
        c1.put("pricing_plot_area", ap_pricing_plot_area);
        c1.put("pricing_sale_status", ap_pricing_sale_status);

      /* // missing
        c1.put("units", un);


        c1.put("status_pricing", status);
        c1.put("status_society", status);
        */

        if (db.insert("Appointments", null, c1) == -1) {
            Toast.makeText(c, "Error: while inserting appointment data id=1", Toast.LENGTH_LONG).show();
        }

        db.close();


    }

    public void testingMethod(String i) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();
        c1.put("builtup_area", "hello");
        c1.put("carpet_area", "hi");
        c1.put("rent_ammount", "tata");
        c1.put("no_of_floor", "bye");
        c1.put("age_of_building", "hello");
        //  c1.put("no_of_lift", "hi");
        c1.put("pricing_plot_area", "tata");
        //  c1.put("pricing_sale_status", "bye");*/

        if (db.update("Appointments", c1, "id=?", new String[]{i}) == 0) {
            Toast.makeText(c, "Error: while updating appointment data id=" + i, Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(c, "Success: updating appointment data id=" + i, Toast.LENGTH_LONG).show();
        db.close();
    }

    public void setStatusForTypeOfRoom(final ContentValues cv) {

        SQLiteDatabase db = super.getWritableDatabase();
        db.update("Appointments", cv, "id=?", new String[]{Appointment.clicked});
        db.close();
    }


}




