package in.junctiontech.homeonline;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Junction on 8/17/2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    private Context c;
    private String abc;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, null, version);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Appointments(" +
                "id TEXT PRIMARY KEY," +
                "description TEXT," +
                "name TEXT," +
                "phone TEXT," +
                "status TEXT," +
                "mobile TEXT," +
                "email TEXT," +
                "building_no TEXT," +
                "building_name TEXT," +
                "flate_number TEXT," +
                "wing TEXT," +
                "street TEXT," +
                "locality TEXT," +
                "sub_locality TEXT," +
                "pincode TEXT," +
                "landmark TEXT," +
                "possesion_date TEXT," +
                "address TEXT," +
                "owner_name TEXT," +
                "owner_number TEXT," +
                "owner_alternate_number TEXT," +
                "owner_email TEXT," +
                "developer_name TEXT," +
                "owner_broker TEXT," +
                "owner_lives_in_same_building TEXT," +
                "developer_type TEXT," +
                "owner_type TEXT," +
                "builtup_area TEXT," +
                "carpet_area TEXT," +
                "rent_ammount REAL," +
                "brokerage_fee REAL," +
                "maintainance REAL," +
                "rent_negotiable TEXT," +
                "security_negotiable TEXT," +
                "security_deposit TEXT," +
                "no_of_floor INTEGER," +
                "age_of_building INTEGER," +
                "terrace TEXT," +
                "terrace_garden TEXT," +
                "no_of_garden INTEGER," +
                "lift_type TEXT," +
                "no_of_lift TEXT," +
                "no_of_storys INTEGER," +
                "servant_room TEXT," +
                "prayer_room TEXT," +
                "balcony TEXT," +
                "terrace_access TEXT," +
                "private_access TEXT," +
                "main_entrance_facing TEXT," +
                "power_backup TEXT," +
                "water_supply TEXT," +
                "wifi TEXT," +
                "solar_heater TEXT," +
                "no_of_residential INTEGER," +
                "society_name TEXT," +
                "no_of_building INTEGER," +
                "boundary_wall TEXT," +
                "pets_allowed TEXT," +
                "parkingtype_street_parking TEXT," +
                "parkingtype_individual_floor TEXT," +
                "parkingtype_individual_open_air TEXT," +
                "property_type TEXT," +
                "bhk_type TEXT," +
                "lease_type TEXT," +
                "food TEXT," +
                "ready_to_move TEXT," +
                "preferred_visit_time TEXT," +
                "residential_visitor_parking_inside TEXT," +
                "residential_visitor_parking_outside TEXT," +
                "residential_parking_car TEXT," +
                "residential_parking_two_wheeler TEXT," +
                "residential_parking_na TEXT," +
                "residential_parking_type_basement TEXT," +
                "residential_parking_type_covered TEXT," +
                "residential_parking_type_na TEXT," +
                "societydata_gated_community TEXT," +
                "societydata_society_overheadtank TEXT," +
                "societydata_cctv_servillance TEXT," +
                "societydata_fire_hydrant_system TEXT," +
                "societydata_swiming_pool TEXT," +
                "societydata_multi_purpose TEXT," +
                "societydata_reg_society TEXT," +
                "societydata_security TEXT," +
                "societydata_zym TEXT," +
                "societydata_smoke_detector TEXT," +
                "societydata_club_house TEXT," +
                "kids_playarea1 TEXT," +
                "saunna_steam TEXT," +
                "yogaroom TEXT," +
                "billiards TEXT," +
                "tennis TEXT," +
                "volleyball TEXT," +
                "batminton TEXT," +
                "table_tennis TEXT," +
                "squash TEXT," +
                "garden_lawn TEXT," +
                "no_of_livingroom TEXT," +
                "no_of_bedroom TEXT," +
                "no_of_kitchen TEXT," +
                "no_of_bathroom TEXT," +
                "no_of_washdry TEXT," +
                "status_property_detail TEXT," +
                "status_advertiser_detail TEXT," +
                "status_basic_detail TEXT," +
                "status_pricing TEXT," +
                "status_residential TEXT," +
                "appointmentTime TEXT," +
                "status_society TEXT)");



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
                "status_kitchen TEXT," +
                "PRIMARY KEY (id,kitchen_ID)," +
                "FOREIGN KEY (id) REFERENCES Appointments(id))");


        db.execSQL("CREATE TABLE BathRoom(toilet_ID TEXT," +
                "id TEXT," +
                "bathroom_bath_type TEXT," +
                "bathroom_hot_water_supply TEXT," +
                "bathroom_toilet TEXT," +
                "bathroom_glass_partition TEXT," +
                "bathroom_shower_curtain TEXT," +
                "bathroom_bath_tub TEXT," +
                "bathroom_windows TEXT," +
                "bathroom_cabinets TEXT," +
                "bathromm_exhaust_fan TEXT," +
                "bathroom_flooring_type TEXT," +
                "status_bath TEXT," +
                "PRIMARY KEY(id,toilet_ID)," +
                "FOREIGN KEY(id) REFERENCES Appointments(id))");

        db.execSQL("CREATE TABLE ImageSelection(id TEXT," +
                "type TEXT," +
                "room_id TEXT," +
                "image_location TEXT," +
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
            } else{}
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
            } else{}
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
            } else{}
           //     Toast.makeText(c, "successfull insertion in bed room ", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();

    }

    public void setKitchen(String kitchen_id, String kitchen_cabinetes, String kitchen_refridgerator, String kitchen_water_purifier,
                           String kitchen_loft, String kitchen_gas_pipeline, String kitchen_microwave,
                           String kitchen_chimney, String kitchen_plateform_material, String status) {
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
        c1.put("status_kitchen", status);


        Cursor cq = db.rawQuery("Select * from Kitchen where id=? AND kitchen_ID=?", new String[]{Appointment.clicked, kitchen_id});

        if (cq.moveToNext()) {

            db.update("Kitchen", c1, "id=? AND kitchen_ID=?", new String[]{Appointment.clicked, kitchen_id});
       //     Toast.makeText(c, "successfull updation in kitchen room ", Toast.LENGTH_LONG).show();

        } else {

            if (db.insert("Kitchen", null, c1) == -1) {
            //    Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else{}
              //  Toast.makeText(c, "successfull insertion in kitchen room ", Toast.LENGTH_LONG).show();
        }
        cq.close();
        db.close();
    }


    public void setBathRoom(String toilet_id, String bathroom_bath_type, String bathroom_hot_water_supply, String bathroom_toilet,
                            String bathroom_glass_partition, String bathroom_shower_curtain, String bathroom_bath_tub,
                            String bathroom_windows, String bathroom_cabinets, String bathromm_exhaust_fan,
                            String bathroom_flooring_type, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("toilet_ID", toilet_id);
        c1.put("id", Appointment.clicked);
        c1.put("bathroom_bath_type", bathroom_bath_type);
        c1.put("bathroom_hot_water_supply", bathroom_hot_water_supply);
        c1.put("bathroom_toilet", bathroom_toilet);
        c1.put("bathroom_glass_partition", bathroom_glass_partition);
        c1.put("bathroom_shower_curtain", bathroom_shower_curtain);
        c1.put("bathroom_bath_tub", bathroom_bath_tub);
        c1.put("bathroom_windows", bathroom_windows);
        c1.put("bathroom_cabinets", bathroom_cabinets);
        c1.put("bathromm_exhaust_fan", bathromm_exhaust_fan);
        c1.put("bathroom_flooring_type", bathroom_flooring_type);
        c1.put("status_bath", status);


        Cursor cq = db.rawQuery("Select * from BathRoom where id=? AND toilet_ID=?", new String[]{Appointment.clicked, toilet_id});

        if (cq.moveToNext()) {

            db.update("BathRoom", c1, "id=? AND toilet_ID=?", new String[]{Appointment.clicked, toilet_id});
         //   Toast.makeText(c, "successfull updation in bath room ", Toast.LENGTH_LONG).show();

        } else {

            if (db.insert("BathRoom", null, c1) == -1) {
         //       Toast.makeText(c, "error in insertion", Toast.LENGTH_LONG).show();
            } else{}
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
            b.putString("bathroom_hot_water_supply", cq.getString(cq.getColumnIndex("bathroom_hot_water_supply")));
            b.putString("bathroom_toilet", cq.getString(cq.getColumnIndex("bathroom_toilet")));
            b.putString("bathroom_glass_partition", cq.getString(cq.getColumnIndex("bathroom_glass_partition")));
            b.putString("bathroom_shower_curtain", cq.getString(cq.getColumnIndex("bathroom_shower_curtain")));
            b.putString("bathroom_bath_tub", cq.getString(cq.getColumnIndex("bathroom_bath_tub")));
            b.putString("bathroom_windows", cq.getString(cq.getColumnIndex("bathroom_windows")));
            b.putString("bathroom_cabinets", cq.getString(cq.getColumnIndex("bathroom_cabinets")));
            b.putString("bathromm_exhaust_fan", cq.getString(cq.getColumnIndex("bathromm_exhaust_fan")));
            b.putString("bathroom_flooring_type", cq.getString(cq.getColumnIndex("bathroom_flooring_type")));
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
        }
        cq.close();
        db.close();
        return b;
    }


    public void saveData(String id, String name, String description, String phone, String status, String appointmentTime) {
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

        Cursor cq = db.rawQuery("Select * from Appointments ORDER BY appointmentTime DESC", null);
        String name[] = new String[cq.getCount()];
        String desc[] = new String[cq.getCount()];
        String phone[] = new String[cq.getCount()];
        String id[] = new String[cq.getCount()];
        String status[] = new String[cq.getCount()];
        String datetime[] = new String[cq.getCount()];
        for (int i = 0; cq.moveToNext(); i++) {

            name[i] = cq.getString(cq.getColumnIndex("name"));
            desc[i] = cq.getString(cq.getColumnIndex("description"));
            phone[i] = cq.getString(cq.getColumnIndex("phone"));
            id[i] = cq.getString(cq.getColumnIndex("id"));
            status[i] = cq.getString(cq.getColumnIndex("status"));
            datetime[i] = cq.getString(cq.getColumnIndex("appointmentTime"));

        }

        String[][] abc = {name, desc, phone, id, status, datetime};
        cq.close();
        db.close();
        return abc;
    }

    public void setPricing(String built_up_area, String carpet_area, String rent_ammount,
                           String brokarege_fee, String maintainance, String no_of_floors,
                           String age_of_building, String lifttype, String terrace_pricing,
                           String terrace_garden, String rent_nego, String security_negotiable,
                           String security_deposite, String no_of_garden, String no_of_lift, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("builtup_area", built_up_area);
        c1.put("carpet_area", carpet_area);
        c1.put("rent_ammount", rent_ammount);
        c1.put("brokerage_fee", brokarege_fee);
        c1.put("maintainance", maintainance);
        c1.put("no_of_floor", no_of_floors);
        c1.put("age_of_building", age_of_building);
        c1.put("terrace", terrace_pricing);
        c1.put("terrace_garden", terrace_garden);
        c1.put("rent_negotiable", rent_nego);
        c1.put("security_negotiable", security_negotiable);
        c1.put("security_deposit", security_deposite);
        c1.put("no_of_garden", no_of_garden);
        c1.put("lift_type", lifttype);
        c1.put("no_of_lift", no_of_lift);
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
        cq.moveToNext();

        b.putString("builtup_area", cq.getString(cq.getColumnIndex("builtup_area")));
        b.putString("carpet_area", cq.getString(cq.getColumnIndex("carpet_area")));
        b.putString("rent_ammount", cq.getString(cq.getColumnIndex("rent_ammount")));
        b.putString("brokerage_fee", cq.getString(cq.getColumnIndex("brokerage_fee")));
        b.putString("maintainance", cq.getString(cq.getColumnIndex("maintainance")));
        b.putString("no_of_floors", cq.getString(cq.getColumnIndex("no_of_floor")));
        b.putString("age_of_building", cq.getString(cq.getColumnIndex("age_of_building")));
        b.putString("terrace", cq.getString(cq.getColumnIndex("terrace")));
        b.putString("terrace_garden", cq.getString(cq.getColumnIndex("terrace_garden")));
        b.putString("rent_negotiable", cq.getString(cq.getColumnIndex("rent_negotiable")));
        b.putString("security_negotiable", cq.getString(cq.getColumnIndex("security_negotiable")));
        b.putString("security_deposit", cq.getString(cq.getColumnIndex("security_deposit")));
        b.putString("no_of_garden", cq.getString(cq.getColumnIndex("no_of_garden")));
        b.putString("lift_type", cq.getString(cq.getColumnIndex("lift_type")));
        b.putString("no_of_lift", cq.getString(cq.getColumnIndex("no_of_lift")));
        db.close();
        return b;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void sendDataForParticularId(String s) {
        SQLiteDatabase db = super.getReadableDatabase();
        final Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{s});

        RequestQueue queue = Volley.newRequestQueue(c);
        if (cq.moveToNext()) {

            SharedPreferences sp = c.getSharedPreferences("Login", c.MODE_PRIVATE);

            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("ap_id", cq.getString(cq.getColumnIndex("id")));
            params.put("userid", sp.getString("userID", "Not Found"));
            params.put("ap_name", cq.getString(cq.getColumnIndex("name")));
            params.put("ap_phone", cq.getString(cq.getColumnIndex("phone")));
       //     params.put("ap_address",cq.getString(cq.getColumnIndex("address")));
            params.put("ap_status", cq.getString(cq.getColumnIndex("status")));
           // params.put("ap_total_livingroom", cq.getString(cq.getColumnIndex("no_of_livingroom")));
/*
            params.put("ap_builtup_area", cq.getString(cq.getColumnIndex("builtup_area")));
            params.put("ap_builtup_area", cq.getString(cq.getColumnIndex("builtup_area")));
            params.put("ap_carpet_area", cq.getString(cq.getColumnIndex("carpet_area")));
            params.put("ap_rent_ammount", cq.getString(cq.getColumnIndex("rent_ammount")));
            params.put("ap_brokerage_fee", cq.getString(cq.getColumnIndex("brokerage_fee")));
            params.put("ap_maintainance", cq.getString(cq.getColumnIndex("maintainance")));
            params.put("ap_rent_negotiable", cq.getString(cq.getColumnIndex("rent_negotiable")));
            params.put("ap_security_negotiable", cq.getString(cq.getColumnIndex("security_negotiable")));
            params.put("ap_security_deposit", cq.getString(cq.getColumnIndex("security_deposit")));
            params.put("ap_no_of_floor", cq.getString(cq.getColumnIndex("no_of_floor")));
            params.put("ap_age_of_building", cq.getString(cq.getColumnIndex("age_of_building")));
            params.put("ap_terrace", cq.getString(cq.getColumnIndex("terrace")));
            params.put("ap_terrace_garden", cq.getString(cq.getColumnIndex("terrace_garden")));
            params.put("ap_no_of_garden", cq.getString(cq.getColumnIndex("no_of_garden")));
            params.put("ap_lift_type", cq.getString(cq.getColumnIndex("lift_type")));
            params.put("ap_no_of_storys", cq.getString(cq.getColumnIndex("no_of_storys")));
            params.put("ap_servant_room", cq.getString(cq.getColumnIndex("servant_room")));
            params.put("ap_prayer_room", cq.getString(cq.getColumnIndex("prayer_room")));
            params.put("ap_balconey", cq.getString(cq.getColumnIndex("balcony")));
            params.put("ap_terrace_access", cq.getString(cq.getColumnIndex("terrace_access")));
            params.put("ap_private_terrace", cq.getString(cq.getColumnIndex("private_access")));
            params.put("ap_main_entrance_facing", cq.getString(cq.getColumnIndex("main_entrance_facing")));
            params.put("ap_power_backup", cq.getString(cq.getColumnIndex("power_backup")));
            params.put("ap_water_supply", cq.getString(cq.getColumnIndex("water_supply")));
            params.put("ap_wifi", cq.getString(cq.getColumnIndex("wifi")));
            params.put("ap_solar_heater", cq.getString(cq.getColumnIndex("solar_heater")));
            params.put("ap_no_of_residential", cq.getString(cq.getColumnIndex("no_of_residential")));
            params.put("ap_society_name", cq.getString(cq.getColumnIndex("society_name")));
            params.put("ap_no_of_building", cq.getString(cq.getColumnIndex("no_of_building")));
            params.put("ap_boundary_wall", cq.getString(cq.getColumnIndex("boundary_wall")));
            params.put("ap_property_type", cq.getString(cq.getColumnIndex("property_type")));
            params.put("ap_bhk_type", cq.getString(cq.getColumnIndex("bhk_type")));
            params.put("ap_residential_visitor_parking_inside", cq.getString(cq.getColumnIndex("residential_visitor_parking_inside")));
            params.put("ap_residential_visitor_parking_outside", cq.getString(cq.getColumnIndex("residential_visitor_parking_outside")));
            params.put("ap_residential_parking_car", cq.getString(cq.getColumnIndex("residential_parking_car")));
            params.put("ap_residential_parking_two_wheeler", cq.getString(cq.getColumnIndex("residential_parking_two_wheeler")));
            params.put("ap_residential_parking_na", cq.getString(cq.getColumnIndex("residential_parking_na")));
            params.put("ap_residential_parking_type_basement", cq.getString(cq.getColumnIndex("residential_parking_type_basement")));
            params.put("ap_residential_parking_type_covered", cq.getString(cq.getColumnIndex("residential_parking_type_covered")));
            params.put("ap_societydata_gated_community", cq.getString(cq.getColumnIndex("societydata_gated_community")));
            params.put("ap_societydata_society_overheadtank", cq.getString(cq.getColumnIndex("societydata_society_overheadtank")));
            params.put("ap_societydata_cctv_servillance", cq.getString(cq.getColumnIndex("societydata_cctv_servillance")));
            params.put("ap_societydata_fire_hydrant_system", cq.getString(cq.getColumnIndex("societydata_fire_hydrant_system")));
            params.put("ap_societydata_swiming_pool", cq.getString(cq.getColumnIndex("societydata_swiming_pool")));
            params.put("ap_societydata_multi_purpose", cq.getString(cq.getColumnIndex("societydata_multi_purpose")));
            params.put("ap_societydata_reg_society", cq.getString(cq.getColumnIndex("societydata_reg_society")));
            params.put("ap_societydata_security", cq.getString(cq.getColumnIndex("societydata_security")));
            params.put("ap_societydata_zym", cq.getString(cq.getColumnIndex("societydata_zym")));
            params.put("ap_societydata_smoke_detector", cq.getString(cq.getColumnIndex("societydata_smoke_detector")));
            params.put("ap_societydata_club_house", cq.getString(cq.getColumnIndex("societydata_club_house")));
            params.put("ap_total_livingroom", cq.getString(cq.getColumnIndex("no_of_livingroom")));
            params.put("ap_total_bedroom", cq.getString(cq.getColumnIndex("no_of_bedroom")));
            params.put("ap_total_kitchen", cq.getString(cq.getColumnIndex("no_of_kitchen")));
            params.put("ap_no_of_toilet", cq.getString(cq.getColumnIndex("no_of_bathroom")));
            params.put("ap_total_washdry", cq.getString(cq.getColumnIndex("no_of_washdry")));

            params.put("ap_building_no", cq.getString(cq.getColumnIndex("building_no")));
            params.put("ap_building_name", cq.getString(cq.getColumnIndex("building_name")));
            params.put("ap_flate_number", cq.getString(cq.getColumnIndex("flate_number")));
            params.put("ap_wing", cq.getString(cq.getColumnIndex("wing")));
            params.put("ap_street", cq.getString(cq.getColumnIndex("street")));
            params.put("ap_locality", cq.getString(cq.getColumnIndex("locality")));
            params.put("ap_sub_locality", cq.getString(cq.getColumnIndex("sub_locality")));
            params.put("ap_pincode", cq.getString(cq.getColumnIndex("pincode")));
            params.put("ap_landmark", cq.getString(cq.getColumnIndex("landmark")));
            params.put("ap_possesion_date", cq.getString(cq.getColumnIndex("possesion_date")));
            params.put("ap_no_of_lift", cq.getString(cq.getColumnIndex("no_of_lift")));
            params.put("ap_pets_allowed", cq.getString(cq.getColumnIndex("pets_allowed")));
           // params.put("ap_residential_parking_type_na", cq.getString(cq.getColumnIndex("residential_parking_type_na")));
            params.put("ap_kids_playarea1", cq.getString(cq.getColumnIndex("kids_playarea1")));
            params.put("ap_saunna_steam", cq.getString(cq.getColumnIndex("saunna_steam")));
            params.put("ap_yogaroom", cq.getString(cq.getColumnIndex("yogaroom")));
            params.put("ap_billiards", cq.getString(cq.getColumnIndex("billiards")));
            params.put("ap_tennis", cq.getString(cq.getColumnIndex("tennis")));
            params.put("ap_volleyball", cq.getString(cq.getColumnIndex("volleyball")));
            params.put("ap_batminton", cq.getString(cq.getColumnIndex("batminton")));
            params.put("ap_table_tennis", cq.getString(cq.getColumnIndex("table_tennis")));
            params.put("ap_squash", cq.getString(cq.getColumnIndex("squash")));
            params.put("ap_garden_lawn", cq.getString(cq.getColumnIndex("garden_lawn")));
            params.put("ap_property_type", cq.getString(cq.getColumnIndex("property_type")));
            params.put("ap_bhk_type", cq.getString(cq.getColumnIndex("bhk_type")));
            params.put("ap_lease_type", cq.getString(cq.getColumnIndex("lease_type")));
            params.put("ap_food", cq.getString(cq.getColumnIndex("food")));
            params.put("ap_ready_to_move", cq.getString(cq.getColumnIndex("ready_to_move")));
            params.put("ap_preferred_visit_time", cq.getString(cq.getColumnIndex("preferred_visit_time")));


            //   params.put("img", new FileBody[]{new FileBody(new File("")),new File(""),new File("")});
*/
            Map<String, JSONObject> param_new = new HashMap<>();
            if (cq.getString(cq.getColumnIndex("no_of_livingroom")) != null) {
                int livingroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_livingroom")));

                // JSONObject obj3[] = new JSONObject[livingroom];
                for (int i = 0; i < livingroom; i++) {

                    final Cursor l = db.rawQuery("Select * from LivingRoom where id=? AND livingRoom_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new HashMap<String, Object>();
                        param1.put("id","LivingRoom" + (1 + i));
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
            }

            Log.d("Vishal", new JSONObject(params).toString());
            param_new = null;
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
            }

            param_new = null;
            param_new = new HashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_kitchen")) != null) {
                int kitchen = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_kitchen")));


                //   JSONObject obj5[] = new JSONObject[kitchen];
                for (int i = 0; i < kitchen; i++) {

                    final Cursor l = db.rawQuery("Select * from Kitchen where id=? AND kitchen_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new HashMap<String, Object>();
                        param1.put("id","Kitchen" + (1 + i));
                        param1.put("cabinet", l.getString(l.getColumnIndex("kitchen_cabinetes")));
                        param1.put("gas_pipeline", l.getString(l.getColumnIndex("kitchen_gas_pipeline")));
                        param1.put("refrigerator", l.getString(l.getColumnIndex("kitchen_refridgerator")));
                        param1.put("water_purifier", l.getString(l.getColumnIndex("kitchen_water_purifier")));
                        param1.put("microwave", l.getString(l.getColumnIndex("kitchen_microwave")));
                        param1.put("loft", l.getString(l.getColumnIndex("kitchen_loft")));
                        param1.put("platform_material", l.getString(l.getColumnIndex("kitchen_plateform_material")));
                        param1.put("chimney_exhaust", l.getString(l.getColumnIndex("kitchen_chimney")));


                        JSONObject obj1 = new JSONObject(param1);

                        param_new.put("Kitchen" + (i + 1), obj1);

                        //       JSONObject obj2 = new JSONObject(param_new);

                        //      obj5[i] = obj2;

                    }

                    l.close();
                }

                params.put("ap_kitchens", param_new);
            }
            param_new = null;
            param_new = new HashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_bedroom")) != null) {
                int bedroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_bedroom")));


                //  JSONObject obj4[] = new JSONObject[bedroom];
                for (int i = 0; i < bedroom; i++) {

                    final Cursor l = db.rawQuery("Select * from BedRoom where id=? AND bedroom_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param1 = new HashMap<String, Object>();
                        param1.put("id","BedRoom" + (1 + i));
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
            }
            param_new = null;
            param_new = new HashMap<String, JSONObject>();

            if (cq.getString(cq.getColumnIndex("no_of_bathroom")) != null) {
                int bathroom = Integer.parseInt(cq.getString(cq.getColumnIndex("no_of_bathroom")));


                //JSONObject obje[] = new JSONObject[bathroom];
                for (int i = 0; i < bathroom; i++) {

                    final Cursor l = db.rawQuery("Select * from BathRoom where id=? AND toilet_ID=?", new String[]{Appointment.clicked, (i + 1) + ""});


                    if (l.moveToNext()) {

                        Map<String, Object> param_living = new HashMap<String, Object>();
                        param_living.put("id","Toilet" + (1 + i));
                        param_living.put("type", l.getString(l.getColumnIndex("bathroom_bath_type")));
                        param_living.put("style", l.getString(l.getColumnIndex("bathroom_toilet")));
                        param_living.put("hot_water_supply", l.getString(l.getColumnIndex("bathroom_hot_water_supply")));
                        param_living.put("glass_partition", l.getString(l.getColumnIndex("bathroom_glass_partition")));
                        param_living.put("shower_curtain", l.getString(l.getColumnIndex("bathroom_shower_curtain")));
                        param_living.put("bath_tub", l.getString(l.getColumnIndex("bathroom_bath_tub")));
                        param_living.put("cabinet", l.getString(l.getColumnIndex("bathroom_cabinets")));
                        param_living.put("window", l.getString(l.getColumnIndex("bathroom_windows")));
                        param_living.put("exhaust_fan", l.getString(l.getColumnIndex("bathromm_exhaust_fan")));
                        param_living.put("flooring_type", l.getString(l.getColumnIndex("bathroom_flooring_type")));

                        JSONObject obj1 = new JSONObject(param_living);

                        param_new.put("Toilet" + (i + 1), obj1);

                        //  JSONObject obj2 = new JSONObject(param_new);

                        //       obje[i] = obj2;

                    }

                    l.close();
                }
                params.put("ap_toilets", param_new);
            }



           Log.d("JSONDATA", new JSONObject(params).toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://junctionerp.com/ankit/vishal.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            for (int i = 0; i < 2; i++)

                                Toast.makeText(c, response, Toast.LENGTH_LONG).show();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    for (int i = 0; i < 2; i++)
                        Toast.makeText(c, error.toString(), Toast.LENGTH_LONG).show();
                }


            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("vishal", new JSONObject(params).toString());
                    return param;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/x-www-form-urlencoded");
                    headers.put("abc", "value");
                    return headers;
                }
            };
// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
        getImageURL();

    }

    public void deleteForParticularID() {

        SQLiteDatabase db = super.getWritableDatabase();
        String table[]={
                "LivingRoom",
                "WashDry",
                "BedRoom",
                "Kitchen",
                "BathRoom",
                "ImageSelection",
                "Appointments"};
        for(int i=0;i<table.length;i++) {
           long j= db.delete(table[i], "id=?", new String[]{Appointment.clicked});
            Toast.makeText(c,table[i]+ "="+j,Toast.LENGTH_LONG).show();
        }


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
            HttpPost httppost = new HttpPost("http://dbproperties.ooo/mobile/image.php");

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
                entity.addPart("image", new FileBody(sourceFile));
                entity.addPart("app_id", new StringBody(id));
                entity.addPart("type", new StringBody(type));
                entity.addPart("room_id", new StringBody(room_id));

                // Extra parameters if you want to pass to server

                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

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

            // showing the server response in an alert dialog
            showAlert(result);

            super.onPostExecute(result);
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


        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(message).setTitle("Response from Servers")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void getImageURL() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                SQLiteDatabase db = DBHandler.super.getReadableDatabase();
                Cursor cq = db.rawQuery("Select * from ImageSelection where id=?", new String[]{Appointment.clicked});
                for (int i = 0; cq.moveToNext(); i++) {
                    new UploadFileToServer((cq.getString(cq.getColumnIndex("id"))),
                            (cq.getString(cq.getColumnIndex("type"))),
                            (cq.getString(cq.getColumnIndex("room_id"))),
                            (cq.getString(cq.getColumnIndex("image_location")))).execute();
                }
                cq.close();
                db.close();
            }
        });
    }


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

    public void setSocietyData(String societydata_society_name, String societydata_no_of_building,
                               String societydata_boundary_wall, String societydata_gated_community,
                               String societydata_society_overheadtank, String societydata_cctv_servillance,
                               String societydata_fire_hydrant_system, String societydata_swiming_pool,
                               String societydata_multi_purpose, String societydata_reg_society,
                               String societydata_security, String societydata_smoke_detector,
                               String societydata_club_house, String societydata_zym, String kids_playarea1,
                               String saunna_steam, String yogaroom, String billiards, String tennis, String volleyball,
                               String batminton, String table_tennis, String squash, String garden_lawn, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("society_name", societydata_society_name);
        c1.put("no_of_building", societydata_no_of_building);
        c1.put("boundary_wall", societydata_boundary_wall);
        c1.put("societydata_gated_community", societydata_gated_community);
        c1.put("societydata_society_overheadtank", societydata_society_overheadtank);
        c1.put("societydata_cctv_servillance", societydata_cctv_servillance);
        c1.put("societydata_fire_hydrant_system", societydata_fire_hydrant_system);
        c1.put("societydata_swiming_pool", societydata_swiming_pool);
        c1.put("societydata_multi_purpose", societydata_multi_purpose);
        c1.put("societydata_reg_society", societydata_reg_society);
        c1.put("societydata_security", societydata_security);
        c1.put("societydata_smoke_detector", societydata_smoke_detector);
        c1.put("societydata_club_house", societydata_club_house);
        c1.put("societydata_zym", societydata_zym);
        c1.put("kids_playarea1", kids_playarea1);
        c1.put("saunna_steam", saunna_steam);
        c1.put("yogaroom", yogaroom);
        c1.put("billiards", billiards);
        c1.put("tennis", tennis);
        c1.put("volleyball", volleyball);
        c1.put("batminton", batminton);
        c1.put("table_tennis", table_tennis);
        c1.put("squash", squash);
        c1.put("garden_lawn", garden_lawn);


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

        b.putString("societydata_society_name", cq.getString(cq.getColumnIndex("society_name")));
        b.putString("societydata_no_of_building", cq.getString(cq.getColumnIndex("no_of_building")));
        b.putString("societydata_boundary_wall", cq.getString(cq.getColumnIndex("boundary_wall")));
        b.putString("societydata_gated_community", cq.getString(cq.getColumnIndex("societydata_gated_community")));
        b.putString("societydata_society_overheadtank", cq.getString(cq.getColumnIndex("societydata_society_overheadtank")));
        b.putString("societydata_cctv_servillance", cq.getString(cq.getColumnIndex("societydata_cctv_servillance")));
        b.putString("societydata_fire_hydrant_system", cq.getString(cq.getColumnIndex("societydata_fire_hydrant_system")));
        b.putString("societydata_swiming_pool", cq.getString(cq.getColumnIndex("societydata_swiming_pool")));
        b.putString("societydata_multi_purpose", cq.getString(cq.getColumnIndex("societydata_multi_purpose")));
        b.putString("societydata_reg_society", cq.getString(cq.getColumnIndex("societydata_reg_society")));
        b.putString("societydata_security", cq.getString(cq.getColumnIndex("societydata_security")));
        b.putString("societydata_smoke_detector", cq.getString(cq.getColumnIndex("societydata_smoke_detector")));
        b.putString("societydata_club_house", cq.getString(cq.getColumnIndex("societydata_club_house")));
        b.putString("societydata_zym", cq.getString(cq.getColumnIndex("societydata_zym")));
        b.putString("kids_playarea1", cq.getString(cq.getColumnIndex("kids_playarea1")));
        b.putString("saunna_steam", cq.getString(cq.getColumnIndex("saunna_steam")));
        b.putString("yogaroom", cq.getString(cq.getColumnIndex("yogaroom")));
        b.putString("billiards", cq.getString(cq.getColumnIndex("billiards")));
        b.putString("tennis", cq.getString(cq.getColumnIndex("tennis")));
        b.putString("volleyball", cq.getString(cq.getColumnIndex("volleyball")));
        b.putString("batminton", cq.getString(cq.getColumnIndex("batminton")));
        b.putString("table_tennis", cq.getString(cq.getColumnIndex("table_tennis")));
        b.putString("squash", cq.getString(cq.getColumnIndex("squash")));
        b.putString("garden_lawn", cq.getString(cq.getColumnIndex("garden_lawn")));

        cq.close();
        db.close();
        return b;
    }

    public void setResidential(String residential_no_of_storeys, String residential_servent_room,
                               String residential_prayersroom, String residential_balcony,
                               String residential_terrace_access, String residential_private_access,
                               String residential_main_enterance_facing, String residential_inside_parking,
                               String residential_outside_parking, String parking_car,
                               String parking_two_wheeler, String parking_na,
                               String parking_type_basement, String parking_type_covered,
                               String parkingtype_street_parking, String parkingtype_individual_floor, String parkingtype_individual_open_air, String residential_power_backup,
                               String residential_water_backup, String residential_wifi_internet,
                               String residential_solar_water_heater, String no_of_residential_unit, String pets_allowed,
                               String food,String ready_to_move, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("no_of_storys", residential_no_of_storeys);
        c1.put("servant_room", residential_servent_room);
        c1.put("prayer_room", residential_prayersroom);
        c1.put("balcony", residential_balcony);
        c1.put("terrace_access", residential_terrace_access);
        c1.put("private_access", residential_private_access);
        c1.put("main_entrance_facing", residential_main_enterance_facing);
        c1.put("residential_visitor_parking_inside", residential_inside_parking);
        c1.put("residential_visitor_parking_outside", residential_outside_parking);
        c1.put("residential_parking_car", parking_car);
        c1.put("residential_parking_two_wheeler", parking_two_wheeler);
        c1.put("residential_parking_na", parking_na);
        c1.put("residential_parking_type_basement", parking_type_basement);
        c1.put("residential_parking_type_covered", parking_type_covered);
        c1.put("parkingtype_street_parking", parkingtype_street_parking);
        c1.put("parkingtype_individual_floor", parkingtype_individual_floor);
        c1.put("parkingtype_individual_open_air", parkingtype_individual_open_air);
        c1.put("power_backup", residential_power_backup);
        c1.put("water_supply", residential_water_backup);
        c1.put("wifi", residential_wifi_internet);
        c1.put("solar_heater", residential_solar_water_heater);
        c1.put("no_of_residential", no_of_residential_unit);
        c1.put("status_residential", status);
        c1.put("pets_allowed", pets_allowed);
        c1.put("food", food);
        c1.put("ready_to_move", ready_to_move);


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

            b.putString("residential_no_of_storeys", cq.getString(cq.getColumnIndex("no_of_storys")));
            b.putString("residential_servent_room", cq.getString(cq.getColumnIndex("servant_room")));
            b.putString("residential_prayersroom", cq.getString(cq.getColumnIndex("prayer_room")));
            b.putString("residential_balcony", cq.getString(cq.getColumnIndex("balcony")));
            b.putString("residential_terrace_access", cq.getString(cq.getColumnIndex("terrace_access")));
            b.putString("residential_private_access", cq.getString(cq.getColumnIndex("private_access")));
            b.putString("residential_main_enterance_facing", cq.getString(cq.getColumnIndex("main_entrance_facing")));
            b.putString("residential_inside_parking", cq.getString(cq.getColumnIndex("residential_visitor_parking_inside")));
            b.putString("residential_outside_parking", cq.getString(cq.getColumnIndex("residential_visitor_parking_outside")));
            b.putString("parking_car", cq.getString(cq.getColumnIndex("residential_parking_car")));
            b.putString("parking_two_wheeler", cq.getString(cq.getColumnIndex("residential_parking_two_wheeler")));
            b.putString("parking_na", cq.getString(cq.getColumnIndex("residential_parking_na")));
            b.putString("parking_type_basement", cq.getString(cq.getColumnIndex("residential_parking_type_basement")));
            b.putString("parking_type_covered", cq.getString(cq.getColumnIndex("residential_parking_type_covered")));
            b.putString("parkingtype_street_parking", cq.getString(cq.getColumnIndex("parkingtype_street_parking")));
            b.putString("parkingtype_individual_floor", cq.getString(cq.getColumnIndex("parkingtype_individual_floor")));
            b.putString("parkingtype_individual_open_air", cq.getString(cq.getColumnIndex("parkingtype_individual_open_air")));
            b.putString("residential_power_backup", cq.getString(cq.getColumnIndex("power_backup")));
            b.putString("residential_water_backup", cq.getString(cq.getColumnIndex("water_supply")));
            b.putString("residential_wifi_internet", cq.getString(cq.getColumnIndex("wifi")));
            b.putString("residential_solar_water_heater", cq.getString(cq.getColumnIndex("solar_heater")));
            b.putString("no_of_residential_unit", cq.getString(cq.getColumnIndex("no_of_residential")));
            b.putString("pets_allowed", cq.getString(cq.getColumnIndex("pets_allowed")));
            b.putString("food", cq.getString(cq.getColumnIndex("food")));
            b.putString("ready_to_move", cq.getString(cq.getColumnIndex("ready_to_move")));

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
        String ret = "false";
        if (cur.moveToNext()) {
            ret = (cur.getString(cur.getColumnIndex(status)));
        }
        cur.close();
        db.close();
        return ret;

    }

    public void setImageURL(String selected, String room_id, String path) {

        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("id", Appointment.clicked);
        cv.put("type", selected);
        cv.put("room_id", room_id);
        cv.put("image_location", path);
        cv.put("status", "pending");

        //  db.update("ImageSelection", cv, "id=?", new String[]{Appointment.clicked});
        long i = db.insert("ImageSelection", null, cv);
        db.close();

    }

    public Bundle getIdName() {
        Bundle b = new Bundle();

        SQLiteDatabase db = super.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        if (cur.moveToNext())
            b.putString("name", cur.getString(cur.getColumnIndex("name")));
        else Toast.makeText(c, "error", Toast.LENGTH_SHORT).show();
        cur.close();
        db.close();
        return b;
    }


    public void setPropertyDetail(String bhk_type, String property_type, String total_livingroom, String total_bedroom,
                                  String total_kitchen, String total_bathroom, String total_washdry, String lease_type, String preferred_visit_time, String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();


        c1.put("property_type", property_type);
        c1.put("bhk_type", bhk_type);
        c1.put("no_of_livingroom", total_livingroom);
        c1.put("no_of_bedroom", total_bedroom);
        c1.put("no_of_kitchen", total_kitchen);
        c1.put("no_of_bathroom", total_bathroom);
        c1.put("no_of_washdry", total_washdry);
        c1.put("status_property_detail", status);
        c1.put("lease_type", lease_type);
        c1.put("preferred_visit_time", preferred_visit_time);

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
            b.putString("bhk_type", cq.getString(cq.getColumnIndex("bhk_type")));
            b.putString("total_livingroom", cq.getString(cq.getColumnIndex("no_of_livingroom")));
            b.putString("total_bedroom", cq.getString(cq.getColumnIndex("no_of_bedroom")));
            b.putString("total_kitchen", cq.getString(cq.getColumnIndex("no_of_kitchen")));
            b.putString("total_bathroom", cq.getString(cq.getColumnIndex("no_of_bathroom")));
            b.putString("total_washdry", cq.getString(cq.getColumnIndex("no_of_washdry")));
            b.putString("lease_type", cq.getString(cq.getColumnIndex("lease_type")));
            b.putString("preferred_visit_time", cq.getString(cq.getColumnIndex("preferred_visit_time")));


        }
        cq.close();
        db.close();
        return b;

    }

    public void setAdvertiserDetail(String owner_name, String owner_number, String owner_alternate_number,
                                    String owner_email, String developer_name, String owner_broker,
                                    String owner_lives_in_same_building, String developer_type, String owner_type,String status) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues c1 = new ContentValues();

        c1.put("owner_name", owner_name);
        c1.put("owner_number", owner_number);
        c1.put("owner_alternate_number", owner_alternate_number);
        c1.put("owner_email", owner_email);
        c1.put("developer_name", developer_name);
        c1.put("owner_broker", owner_broker);
        c1.put("owner_lives_in_same_building", owner_lives_in_same_building);
        c1.put("developer_type", developer_type);
        c1.put("owner_type", owner_type);
        c1.put("status_advertiser_detail", status);
        String click = Appointment.clicked;
        int i = db.update("Appointments", c1, "id=?", new String[]{click});

        db.close();

    }

    public Bundle getAdvertiserDetail() {
        SQLiteDatabase db = super.getReadableDatabase();

        Cursor cq = db.rawQuery("Select * from Appointments where id=?", new String[]{Appointment.clicked});
        Bundle b = new Bundle();

        if(cq.moveToNext()) {
            b.putString("owner_name", cq.getString(cq.getColumnIndex("owner_name")));
            b.putString("owner_number", cq.getString(cq.getColumnIndex("owner_number")));
            b.putString("owner_alternate_number", cq.getString(cq.getColumnIndex("owner_alternate_number")));
            b.putString("owner_email", cq.getString(cq.getColumnIndex("owner_email")));
            b.putString("developer_name", cq.getString(cq.getColumnIndex("developer_name")));
            b.putString("owner_broker", cq.getString(cq.getColumnIndex("owner_broker")));
            b.putString("owner_lives_in_same_building", cq.getString(cq.getColumnIndex("owner_lives_in_same_building")));
            b.putString("developer_type", cq.getString(cq.getColumnIndex("developer_type")));
            b.putString("owner_type", cq.getString(cq.getColumnIndex("owner_type")));
        }
        db.close();
        return b;
    }



    }
