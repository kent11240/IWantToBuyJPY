package com.buyjpy.test;

import com.buyjpy.gui.IWantToBuyJPYUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestEsun {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        try {
            URL url = new URL("https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Referer", "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/foreign-exchange-rates");

            conn.setDoOutput(true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sdf.format(new Date());
            String data = "{day:'" + nowDate.split(" ")[0] + "',time:'" + nowDate.split(" ")[1] + "'}";
//            String data = "{day:'2020-12-31',time:'23:59:59'}";
            System.out.println(data);
            byte[] outputInBytes = data.getBytes("UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(outputInBytes);
            }

            StringBuilder result = new StringBuilder();

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }

            String json = result.toString().replaceAll("\\\\", "");
            json = json.replace("{\"d\":\"", "");
            json = json.substring(0, json.length() - 2);
            System.out.println(json);
            JSONObject jo = new JSONObject(json);
            JSONArray ja = (JSONArray) jo.get("Rates");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject joRate = (JSONObject) ja.get(i);
                if (joRate.get("Key").equals("JPY")) {
                    System.out.println(df.format(joRate.get("SBoardRate")));
                }
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
