package Appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {
    
    public static String readString(String msg) throws IOException {

        System.out.println(msg);

        String line = "";
        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();

        return line;
    }
}
