package com.buyjpy.test;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TestBot {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://rate.bot.com.tw/xrt/quote/day/JPY");
            Document html = Jsoup.parse(url, 5000);
            Elements data = html.select(".table tbody tr:last-child td");
            System.out.println(data);
            System.out.println(data.get(4).text());
            System.out.println(data.get(5).text());
        } catch (Exception ex) {
            Logger.getLogger(TestBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
