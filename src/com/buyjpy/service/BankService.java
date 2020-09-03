package com.buyjpy.service;

import com.buyjpy.gui.IWantToBuyJPYUI;
import com.buyjpy.util.FormatUtil;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class BankService {

    private static final String SINOPAC_URL = "https://mma.sinopac.com/ws/share/rate/ws_exchange.ashx?exchangeType=REMIT&Cross=genREMITResult&1480910457051&callback=genREMITResult&_=1480910456630";
    private static final String FUBON_URL = "https://www.fubon.com/Fubon_Portal/banking/Personal/deposit/exchange_calculate/currency.jsp?currency1=TWD&currency2=%s";
    private static final String ESUN_URL = "https://www.esunbank.com.tw/bank/Layouts/esunbank/Deposit/DpService.aspx/GetForeignExchageRate";
    private static final String CTBC_URL = "https://www.ctbcbank.com/CTCBPortalWeb/pages/exchangeRate/exchangeRate.jsf";
    private static final String BOT_URL = "http://rate.bot.com.tw/xrt/quote/day/%s";

    /**
     * 永豐銀行
     * @param rateTable
     */
    public void doSinopac(HashMap<String, String> rateTable) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(SINOPAC_URL).openConnection();
            conn.setRequestMethod("GET");
            
		    TrustManager[] trustAllCerts = new TrustManager[]{
			        new X509TrustManager() {
			            public X509Certificate[] getAcceptedIssuers() {
			            	return new X509Certificate[0];
			            }
			            public void checkClientTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			            public void checkServerTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			        }
			    };
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, trustAllCerts, new SecureRandom());
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        
	        conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
	        conn.setSSLSocketFactory(ssf);
            
            StringBuilder result = new StringBuilder();

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }

            JSONObject body = new JSONObject(result.toString().replace("genREMITResult([", "").replace("]);", ""));
            JSONArray rates = (JSONArray) body.get("SubInfo");
            String remitSellJPY = "";
            String remitBuyJPY = "";
            for (int i = 0; i < rates.length(); i++) {
                JSONObject rate = (JSONObject) rates.get(i);
                if (rate.get("DataValue4").equals(rateTable.get("nowCcd"))) {
                    remitBuyJPY = ((String) rate.get("DataValue2")).substring(0, 6);
                    remitSellJPY = ((String) rate.get("DataValue3")).substring(0, 6);
                    break;
                }
            }

            rateTable.put("previousSellRate", rateTable.get("nowSellRate"));
            rateTable.put("nowSellRate", remitSellJPY);
            rateTable.put("previousBuyRate", rateTable.get("nowBuyRate"));
            rateTable.put("nowBuyRate", remitBuyJPY);
        } catch (IOException | JSONException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 富邦銀行
     * @param rateTable
     */
    public void doFubon(HashMap<String, String> rateTable) {
        try {
        	HttpsURLConnection conn = (HttpsURLConnection) new URL(String.format(FUBON_URL, rateTable.get("nowCcd"))).openConnection();
            conn.setRequestMethod("GET");
            
		    TrustManager[] trustAllCerts = new TrustManager[]{
			        new X509TrustManager() {
			            public X509Certificate[] getAcceptedIssuers() {
			            	return new X509Certificate[0];
			            }
			            public void checkClientTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			            public void checkServerTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			        }
			    };
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, trustAllCerts, new SecureRandom());
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        
	        conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
	        conn.setSSLSocketFactory(ssf);
        	
	        StringBuilder result = new StringBuilder();

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }
	        
            SAXReader reader = new SAXReader();
            org.dom4j.Document doc = reader.read(new ByteArrayInputStream(result.toString().getBytes("UTF-8")));
            String remitSellJPY = doc.getRootElement().elementTextTrim("buy");
            String remitBuyJPY = doc.getRootElement().elementTextTrim("sell");
            rateTable.put("previousSellRate", rateTable.get("nowSellRate"));
            rateTable.put("nowSellRate", remitSellJPY);
            rateTable.put("previousBuyRate", rateTable.get("nowBuyRate"));
            rateTable.put("nowBuyRate", remitBuyJPY);
        } catch (DocumentException | IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 玉山銀行
     * @param rateTable
     */
    public void doEsun(HashMap<String, String> rateTable) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(ESUN_URL).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Referer", "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/foreign-exchange-rates");

            conn.setDoOutput(true);
            String nowDate = FormatUtil.formatDateTime(new Date());
            String data = "{day:\"" + nowDate.split(" ")[0] + "\",time:\"" + nowDate.split(" ")[1] + "\"}";
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
            JSONObject jo = new JSONObject(json);
            JSONArray ja = (JSONArray) jo.get("Rates");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject joRate = (JSONObject) ja.get(i);
                if (joRate.get("Key").equals(rateTable.get("nowCcd"))) {
                    rateTable.put("previousSellRate", rateTable.get("nowSellRate"));
                    rateTable.put("nowSellRate", FormatUtil.formatNumber(joRate.getDouble("SBoardRate")));
                    rateTable.put("previousBuyRate", rateTable.get("nowBuyRate"));
                    rateTable.put("nowBuyRate", FormatUtil.formatNumber(joRate.getDouble("BBoardRate")));
                    break;
                }
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 中國信託
     * @param rateTable
     */
    public void doCtbc(HashMap<String, String> rateTable) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(CTBC_URL).openConnection();
            conn.setRequestMethod("POST");
            
            TrustManager[] trustAllCerts = new TrustManager[]{
			        new X509TrustManager() {
			            public X509Certificate[] getAcceptedIssuers() {
			            	return new X509Certificate[0];
			            }
			            public void checkClientTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			            public void checkServerTrusted(
			                X509Certificate[] certs, String authType) {
			            }
			        }
			    };
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	        sslContext.init(null, trustAllCerts, new SecureRandom());
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        
	        conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
	        conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);

            String postData = "listenerExp=spotExchangeRateController.ajaxGetRateData&sellCode=TWD&buyCode=" + rateTable.get("nowCcd");
            byte[] postByte = postData.getBytes("UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postByte);
            }

            StringBuilder result = new StringBuilder();

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }

            JSONObject jo = new JSONObject(result.toString());
            rateTable.put("previousSellRate", rateTable.get("nowSellRate"));
            rateTable.put("nowSellRate", FormatUtil.formatNumber(Double.parseDouble(jo.getString("saleRate2"))));
            rateTable.put("previousBuyRate", rateTable.get("nowBuyRate"));
            rateTable.put("nowBuyRate", FormatUtil.formatNumber(Double.parseDouble(jo.getString("buyRate2"))));
        } catch (IOException | JSONException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 臺灣銀行
     * @param rateTable
     */
    public void doBot(HashMap<String, String> rateTable) {
        try {
            org.jsoup.nodes.Document html = Jsoup.parse(new URL(String.format(BOT_URL, rateTable.get("nowCcd"))), IWantToBuyJPYUI.time);
            Elements data = html.select(".table tbody tr:last-child td");
            rateTable.put("previousSellRate", rateTable.get("nowSellRate"));
            rateTable.put("nowSellRate", FormatUtil.formatNumber(Double.parseDouble(data.get(5).text())));
            rateTable.put("previousBuyRate", rateTable.get("nowBuyRate"));
            rateTable.put("nowBuyRate", FormatUtil.formatNumber(Double.parseDouble(data.get(4).text())));
        } catch (IOException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
