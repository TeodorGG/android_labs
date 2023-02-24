package com.deviza.lab_1_dam.ALB3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileOper {
    String tablesN;
    int numberOfTables;

    public FileOper() {
    }

    public FileOper(String tablesN) {
        this.tablesN = tablesN;
    }

    public FileOper(int n, String tablesN) {
        this.tablesN = tablesN;
        this.numberOfTables = n;
    }

    public String readTable(String tablesN) {
        String aDataRow = "";
        String aBuffer = "";
        final String rez;
        {
            try {
                //File myFile = new File("/storage/extSdCard/"+tablesN+".txt");
                File myFile = new File("/mnt/sdcard/" + tablesN + ".txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(
                        new InputStreamReader(fIn));
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer += aDataRow + "\n";
                }
                myReader.close();
            } catch (Exception e) {
                Log.d("ERROR", "Error on reading the file" + tablesN);
            }
        }
        return aBuffer;
    }
}
