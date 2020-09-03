package com.buyjpy.test;

import com.buyjpy.gui.IWantToBuyJPYUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class TestCTCB {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#0.0000");
        try {
            byte[] postData = "listenerExp=spotExchangeRateController.ajaxGetRateData&sellCode=TWD&buyCode=JPY".getBytes("UTF-8");
            URL url = new URL("https://www.ctbcbank.com/CTCBPortalWeb/pages/exchangeRate/exchangeRate.jsf");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData);
            }
            
            StringBuilder result = new StringBuilder();

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
            
            System.out.println(result);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
