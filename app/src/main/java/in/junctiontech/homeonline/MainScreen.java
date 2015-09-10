package in.junctiontech.homeonline;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar tb;
    NavigationView navi;
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    int id;
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

       // id=savedInstanceState.getInt("KEY",0);
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

        menuItem.setChecked(true);
        id=menuItem.getItemId();
        dl.closeDrawer(GravityCompat.START);

      if(id==R.id.two) {
            startActivity(new Intent(this, Appointment.class));
        }
        else if(id==R.id.three) {

            getSharedPreferences("Login", MODE_PRIVATE).edit().clear().commit();
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY",id);
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
}
