/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SelmanKasim
 */
public class GeneralUtil {
    

    public static String readConsole() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(GeneralUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static Integer toInt(String sayi) {
        try {
            return Integer.parseInt(sayi);
        } catch (Exception e) {
            return null;
        }
    }
}

