package haileo.popupwindowproject;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Label label1 = new Label("Label 1");
    final private static int DIALOG_LOGIN = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                Button okbutton = (Button) alertDialog
                        .findViewById(R.id.button2);
                final EditText sugar = (EditText) alertDialog
                        .findViewById(R.id.txt_sugar);

                final EditText weight = (EditText) alertDialog
                        .findViewById(R.id.txt_weight);

                final EditText height = (EditText) alertDialog
                        .findViewById(R.id.txt_height);

                final EditText age = (EditText) alertDialog
                        .findViewById(R.id.txt_age);

                final EditText bp = (EditText) alertDialog
                        .findViewById(R.id.txt_BP);



                final TextView Quick = (TextView) alertDialog
                        .findViewById(R.id.textView);

                final TextView bp1 = (TextView) alertDialog
                        .findViewById(R.id.bp);

                final TextView sugar1 = (TextView) alertDialog
                        .findViewById(R.id.sugar);

                final TextView height1 = (TextView) alertDialog
                        .findViewById(R.id.height);

                final TextView weight1 = (TextView) alertDialog
                        .findViewById(R.id.weight);

                final TextView age1 = (TextView) alertDialog
                        .findViewById(R.id.age);



                final Button bt = (Button) alertDialog
                        .findViewById(R.id.button2);

                okbutton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Toast.makeText(
                                MainActivity.this,
                                "Sugar:" + sugar.getText().toString()
                                        +"Height:" + height.getText().toString()
                                        +"Weight:" + weight.getText().toString()
                                        +"Age:" + age.getText().toString()
                                ,
                                Toast.LENGTH_LONG).show();
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