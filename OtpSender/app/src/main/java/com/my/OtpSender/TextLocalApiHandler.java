package com.my.OtpSender;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TextLocalApiHandler {
    public void sendthisOtp(Context context, String myMessage, String phone_number)
    {
        try {
            // Construct data
            String apiKey = "apikey=" + "khjd4C30D9g-Zi1xeUhqlrEEKYxRqgp62RukyZExn1";
            String message = "&message=" + myMessage;
            String sender = "&sender=" + "ritik";
            String numbers = "&numbers=" + phone_number;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

        } catch (Exception e) {
            Toast.makeText(context, "Unable to send OTP", Toast.LENGTH_LONG).show();
        }

    }
}
