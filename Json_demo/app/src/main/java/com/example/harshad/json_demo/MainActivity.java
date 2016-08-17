package com.example.harshad.json_demo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2, ed3;
    Button btn;
    ProgressDialog dialog;
    private static final String TAG = "MainActivity";
    int cnt = 0;
    String response;
    private static String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.email);
        ed3 = (EditText) findViewById(R.id.pass);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        URL = "http://10.0.2.2/Json_demo/insert.php?"
                + "name="
                + ed1.getText().toString().replaceAll(" ", "%20")
                + "&email="
                + ed2.getText().toString().replaceAll(" ", "%20")
                + "&password="
                + ed3.getText().toString().replaceAll(" ", "%20");
        new Register().execute();

    }


    public class Register extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONParser jsonParser = new JSONParser();
            response = jsonParser.makeServiceCall(URL);
            Log.e(TAG, "doInBackground" + response);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if (!response.isEmpty()) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String URLMessge = jsonObject.getString("msg").toString();

                    if (URLMessge.contentEquals("inserted successfully")) {

                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        //getting gcm id
                    } else {
                        Toast.makeText(MainActivity.this, "" + URLMessge, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Error connection to server", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


