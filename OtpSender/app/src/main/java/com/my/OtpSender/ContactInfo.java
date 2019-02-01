package com.my.OtpSender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.app.PendingIntent.getActivity;

public class ContactInfo extends AppCompatActivity {
    TextView tv1,tv2;
    Button btn1;
    String phone_number;
    String fullName;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        final Intent intent=getIntent();
        pos = intent.getIntExtra("position",0);
       //ContactsFragment contactsFragment=new ContactsFragment();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONObject jsonObject=new JSONObject(new JsonHandler(ContactInfo.this).loadJSONFromAsset("contacts.json"));
                    JSONArray m_jArry = jsonObject.getJSONArray("contact_info");
                    JSONObject jo_inside = m_jArry.getJSONObject(pos);
                    fullName=jo_inside.getString("firstname")+" "+jo_inside.getString("middlename")+" "+jo_inside.getString("lastname");
                    phone_number=jo_inside.getString("phone");
                    tv1.setText(fullName);
                    tv2.setText(phone_number);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }


    public void onBtnClk(View view) {

                Intent intent1 = new Intent(this,sendMsgActivity.class);
                intent1.putExtra("phone_number",phone_number);
                intent1.putExtra("name",fullName);
                startActivity(intent1);
            }


}

