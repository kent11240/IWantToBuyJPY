package com.buyjpy.test;

import com.buyjpy.util.FormatUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCallClass {
    public static void main(String[] args) {
        try {
            Method m = FormatUtil.class.getDeclaredMethod("formatDateTime", Date.class);
            System.out.println(m.invoke(FormatUtil.class, new Date()));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TestCallClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
