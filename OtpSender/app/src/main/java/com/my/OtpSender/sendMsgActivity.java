package com.my.OtpSender;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;



public class sendMsgActivity extends AppCompatActivity {

    int otp;
    String message,phone_number, name;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);



        Random rand = new Random();
        otp = rand.nextInt(900000) + 100000;
        message = "Hi Your OTP is: " + otp;

        TextView tv1 = findViewById(R.id.textView3);
        Button sendBtn = findViewById(R.id.button2);
        tv1.setText(message);
        Bundle bundle = getIntent().getExtras();
        phone_number = bundle.getString("phone_number");
        name = bundle.getString("name");

        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Thread ID is.........."+Thread.currentThread().getId());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TextLocalApiHandler textLocalApiHandler=new TextLocalApiHandler();
                        textLocalApiHandler.sendthisOtp(getApplicationContext(), message, phone_number);
                        System.out.println("Thread ID is.........."+Thread.currentThread().getId());

                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        saveMsgHistory(name,currentTime(),Integer.toString(otp));
                        System.out.println("Thread ID is.........."+Thread.currentThread().getId());

                    }
                }).start();





            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }






    public String currentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void saveMsgHistory(String name,String dateTime,String otp)
    {
        Msg m=new Msg();
        m.setName(name);
        m.setDateTime(dateTime);
        m.setOtp(otp);
        MyDbHandler myDbHandler = new MyDbHandler(getApplicationContext());
        Temp.setMyDbHandler(myDbHandler);
        myDbHandler.insertHistory(m);


    }
}





