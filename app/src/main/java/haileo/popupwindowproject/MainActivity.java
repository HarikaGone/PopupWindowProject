package haileo.popupwindowproject;

import android.app.Dialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Button launch, okbutton,bt;
    EditText bp, sugar, height, weight, age;
    TextView bp1, sugar1, height1, weight1, age1,Quick;
    Dialog dialog;
    AlertDialog alertDialog = (AlertDialog) dialog;
    Label label1 = new Label("Label 1");
    final private static int DIALOG_LOGIN = 1;
    RelativeLayout RLayout;

    private String UploadUrl = "http://localhost:8080//quickhealthservay//health.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up strict mode policy
        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        setContentView(R.layout.activity_main);

        Button launch_button = (Button) findViewById(R.id.btn_launch);


        launch_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DIALOG_LOGIN);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog dialogDetails = null;

        switch (id) {
            case DIALOG_LOGIN:
                LayoutInflater inflater = LayoutInflater.from(this);
                View dialogview = inflater.inflate(R.layout.dialog_layout, null);

                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
               // dialogbuilder.setTitle(<font><bold>"Quick Health survey");
                dialogbuilder.setView(dialogview);
                dialogDetails = dialogbuilder.create();

                break;
        }

        return dialogDetails;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {

        switch (id) {
            case DIALOG_LOGIN:
              final AlertDialog alertDialog = (AlertDialog) dialog;

                okbutton = (Button) alertDialog.findViewById(R.id.button2);
                sugar = (EditText) alertDialog.findViewById(R.id.txt_sugar);
                 weight = (EditText) alertDialog.findViewById(R.id.txt_weight);
                height = (EditText) alertDialog.findViewById(R.id.txt_height);
                age = (EditText) alertDialog.findViewById(R.id.txt_age);
                bp = (EditText) alertDialog.findViewById(R.id.txt_BP);



                 Quick = (TextView) alertDialog.findViewById(R.id.textView);
                bp1 = (TextView) alertDialog.findViewById(R.id.bp);
                sugar1 = (TextView) alertDialog.findViewById(R.id.sugar);
                height1 = (TextView) alertDialog.findViewById(R.id.height);
                weight1 = (TextView) alertDialog.findViewById(R.id.weight);
               age1 = (TextView) alertDialog.findViewById(R.id.age);

                launch = (Button) findViewById(R.id.btn_launch);


              bt = (Button) alertDialog.findViewById(R.id.button2);
                StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(threadPolicy);
                setContentView(R.layout.activity_main);


                okbutton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Toast.makeText(
                                MainActivity.this,
                                       "B.P:"+bp.getText().toString()
                                        +"Sugar:" + sugar.getText().toString()
                                        +"Height:" + height.getText().toString()
                                        +"Weight:" + weight.getText().toString()
                                        +"Age:" + age.getText().toString(),
                                Toast.LENGTH_LONG).show();
                        final  String Bbp = bp.getText().toString();
                        final   String Ssugar = sugar.getText().toString();
                        final  String Hheight = height.getText().toString();
                        final String Wweight = weight.getText().toString();
                        final String Aage = age.getText().toString();



                        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl, new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                                    String code = jsonObject.getString("code");

                                    Snackbar.make(RLayout, code, Snackbar.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(MainActivity.this, "Connectivity Problem", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> params = new HashMap<>();
                                params.put("B.P", Bbp);
                                params.put("Sugar", Ssugar);
                                params.put("Height", Hheight);
                                params.put("Weight", Wweight);
                                params.put("age", Aage);


                                return params;
                            }
                        };

                        Sigleton.getInstance(MainActivity.this).

                                addToRequestQue(stringRequest);


                    }
                });





        bt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });
                break;
        }
    }
}