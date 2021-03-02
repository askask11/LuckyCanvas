
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jianqing
 */
public class CmdExec {

public static Scanner s = null;


public static void main(String[] args) throws InterruptedException, IOException {
    s = new Scanner(System.in);
    
    while(true){
    System.out.print("$ ");
    String cmd = s.nextLine();
    final Process p = Runtime.getRuntime().exec(cmd);

    new Thread(new Runnable() {
        public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null; 

            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();

    p.waitFor();
     }
}
 }
