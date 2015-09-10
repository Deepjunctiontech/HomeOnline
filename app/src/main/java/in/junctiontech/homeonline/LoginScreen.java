package in.junctiontech.homeonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginScreen extends AppCompatActivity {

    TextInputLayout user_text, pass_text;
    RelativeLayout rl;
    EditText username, password;
    private SharedPreferences sp;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("Login", this.MODE_PRIVATE);
        check();
        setContentView(R.layout.activity_login_screen);
        user_text = (TextInputLayout) this.findViewById(R.id.user_text);
        pass_text = (TextInputLayout) this.findViewById(R.id.pass_text);

        username = (EditText) this.findViewById(R.id.user_edit);
        password = (EditText) this.findViewById(R.id.pass_edit);
        rl= (RelativeLayout) this.findViewById(R.id.rl);
    }

    public void onPause()
    {
        super.onPause();
        finish();
    }

    private void check()
    {
        if( !sp.getString("user_name", "Not Found").equals("Not Found")&&
               ! sp.getString("user_pass", "Not Found").equals("Not Found")) {
            startActivity(new Intent(LoginScreen.this, MainScreen.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submit(View v) {
        boolean b1 = isEmptyEmail();
        boolean b2 = isEmptyPassword();

        if (b1 && b2) {
            Snackbar.make(rl, "One Or More Field Are Blank", Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sp = getSharedPreferences("Login", MODE_PRIVATE);
                    String get_user = sp.getString("user_name", "Couldn't loaded Username...");
                    String get_pass = sp.getString("user_pass", "Couldn't loaded Password...");

                   // Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();
                }
            }).show();
            user_text.setError(null);
            pass_text.setError(null);
        } else if (b1 && !b2) {
            user_text.setError("User Name filed cannot be blank");
            pass_text.setError(null);
        } else if (!b1 && b2) {
            pass_text.setError("Password filed cannot be blank");
            user_text.setError(null);
        } else {

         //   startActivity(new Intent(LoginScreen.this, MainScreen.class));
          //  finish();

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    "http://dbproperties.ooo/mobile/login.php", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("TAG", "Register Response: " + response.toString());


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String check=jsonObject.getString("status");

                        if(check.equalsIgnoreCase("success"))
                        {
                            JSONObject js = new JSONObject(jsonObject.getString("data"));
                            String userID=js.getString("userid");
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("user_name", username.getText().toString());
                            editor.putString("user_pass", password.getText().toString());
                            editor.putString("userID", userID);

                            editor.commit();
                            startActivity(new Intent(LoginScreen.this, MainScreen.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "INVALID USER ID PASSWORD", Toast.LENGTH_LONG).show();
                            password.setText("");
                        }
                        //         Toast.makeText(Appointment.this,js.getString("userID"),Toast.LENGTH_LONG).show();

                        //   Toast.makeText(Appointment.this,response,Toast.LENGTH_LONG).show();



//                            Toast.makeText(Appointment.this,obj.length()+"",Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                        e.printStackTrace();}}
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", "Registration Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            "Error"+error.getMessage(), Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();

                }

            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username.getText().toString());
                    params.put("password", password.getText().toString());

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> headers = new HashMap<String, String>();
                    headers.put("Content-Type","application/x-www-form-urlencoded");
                    headers.put("abc", "value");
                    return headers;
                }
            };

            queue.add(strReq);
        }

    }



    public boolean isEmptyEmail() {
        return username.getText() == null || username.getText().toString() == null
                || username.getText().toString().equals("") || username.getText().toString().isEmpty();
    }

    public boolean isEmptyPassword() {
        return password.getText() == null || password.getText().toString() == null
                || password.getText().toString().equals("") || password.getText().toString().isEmpty();
    }

}
