package in.junctiontech.homeonline;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar tb;
    NavigationView navi;
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    int id;
    private TextView tv_status_completed, tv_status_inProcess,dashboard;
    private DBHandler db;
    private ListView lv_for_status;

    public myAdapter myadp;
    private static Context c;

    public static Context getContext() {
        return c;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        tb= (Toolbar) this.findViewById(R.id.app_bar);
        navi= (NavigationView) this.findViewById(R.id.navi);
        dl= (DrawerLayout) this.findViewById(R.id.dl);
       this.setSupportActionBar(tb);
        abdt=new ActionBarDrawerToggle(this,dl,tb,R.string.open,R.string.close);
        navi.setNavigationItemSelectedListener(this);
        dl.setDrawerListener(abdt);
        abdt.syncState();
        dl.openDrawer(GravityCompat.START);
        db = new DBHandler(this, "DB", null, 1);


        lv_for_status = (ListView) findViewById(R.id.lv_for_status);
        tv_status_completed = (TextView) findViewById(R.id.tv_status_completed);
        tv_status_inProcess = (TextView) findViewById(R.id.tv_status_inProcess);


       // id=savedInstanceState.getInt("KEY",0);
    }


    @Override
    public void onResume()
    {
        myadp=null;
        this.c=this;
        super.onResume();
        updateTable();
    }

    @Override
    public void onPause()
    {

        super.onPause();
        myadp=null;
      //  finish();

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
        else {
            // Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this,Help.class));
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

      //  menuItem.setChecked(true);
        id=menuItem.getItemId();
        dl.closeDrawer(GravityCompat.START);

      if(id==R.id.two) {
            startActivity(new Intent(this, Appointment.class));
        }
        else if(id==R.id.three) {
          alert();


        }
        return false;
    }

    public void alert()
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        // builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to logout\nAll data will be flush...?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(db.flushAllData())
                {
                   // Toast.makeText(this,"Database Delete",Toast.LENGTH_LONG).show();
                    deleteImage("thumbnail");
                    deleteImage("DB");
                }
                getSharedPreferences("Login", MODE_PRIVATE).edit().clear().commit();
                finish();
                startActivity(new Intent(MainScreen.this, LoginScreen.class));
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
                return ;
            }
        }


        File[] f = mediaStorageDir.listFiles();

        for (int i = 0; i < f.length; i++) {
                    f[i].delete();
                }
        mediaStorageDir.delete();
        //Toast.makeText(this,mediaStorageDir.delete()+"",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY", id);
    }

    class MyPager extends FragmentStatePagerAdapter
    {

        public MyPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

    public void updateTable() {
        int check[]=db.getDashBoardStatus();
        tv_status_completed.setText(check[1]+"");
        tv_status_inProcess.setText((check[0]-check[1])+"");

        String[][] data = db.getAppoinmentDetail();

        String[] id = data[0];
        String[] status = data[1];
        String[] imagestatus = data[2];
        myadp=null;

        if(id.length!=0)
        {
            myadp = new myAdapter(this, id, status,imagestatus);
            lv_for_status.setAdapter(myadp);
        }
        else
            lv_for_status.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"Currently No Apppointment Available"}));


    }

    class myAdapter extends ArrayAdapter<String> {
        String[] appoinment_id;
        String[] appoinment_status;
        String[] image_status;
        Context context;

        public myAdapter(Context context1, String[] id, String[] status,String[] imagestatus) {
            super(context1, R.layout.for_status, id);
            this.context = context1;
            appoinment_id = id;
            appoinment_status = status;
            image_status=imagestatus;

        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = getLayoutInflater().inflate(R.layout.for_status, parent, false);
            }

            final ViewHolder holder = new ViewHolder();
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status_name);
            holder.tv_complete = (TextView) convertView.findViewById(R.id.tv_status_rank);

            holder.tv_id.setText(appoinment_id[position]);
            holder.tv_status.setText(appoinment_status[position]);
            holder.tv_complete.setText(image_status[position]);

            return convertView;
        }

        public class ViewHolder {
            TextView tv_id, tv_status, tv_complete;
        }
    }


}
