package com.deviza.lab_1_dam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG =
            "Sdcard";
    private TextView tv;
    String FileN="INF_Aplic.txt";
    Context MyContext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv = (TextView)findViewById(R.id.textView2);
        tv.setText("Test");
        WriteIntern();
        ReadIntern();
        checkExternalMedia();
        writeToSDFile();
        readSDFile();
    }

    private void WriteIntern()
    {
        String File_Name= "Proba.txt"; //gives file name
        String MyInfo="Success Internal Write info!"; //define data
        File dir = new File(this.getFilesDir(),
                "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            File mf = new File(dir, File_Name);
            FileWriter writer = new
                    FileWriter(mf);
            writer.append(MyInfo);
            writer.flush();
            writer.close();
            tv.append("\nSucces: " + " WRITE_Internal_STORAGE,Success Internal Write info! ");
            writer.close();
        }
        catch(Exception e){
            tv.append("\nProblems: " +" WRITE_Internal_STORAGE ");
        }
    }
    private void ReadIntern() { String File_Name= "Proba.txt";
//gives file name
        String MyInfo="Success Internal Write info!"; //define data
        File dir = new File(this.getFilesDir(),
                "mydir");
        if(!dir.exists()){ dir.mkdir(); }
        try {
            File mf = new File(dir, File_Name);
            BufferedReader br = new
                    BufferedReader(new FileReader(mf));
            tv.append(br.readLine());
            tv.append("\nSucces: " + "read_Internal_STORAGE,Success Internal read info! ");
            br.close();
        }
        catch(Exception e){
            tv.append("\nProblems: " +" Read_Internal_STORAGE ");
            Log.i(TAG, "Problems: " + " Read_Internal_STORAGE ");
        }
    }

    private void checkExternalMedia(){
        boolean mExternalStorageAvailable =
                false;
        boolean mExternalStorageWriteable =
                false;
        String state =
                Environment.getExternalStorageState();
        if
        (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if
        (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        tv.append("\n\nExternal Media: readable = "
                +mExternalStorageAvailable+"; writable = "+mExternalStorageWriteable);
    }

    private void writeToSDFile(){
        File root = android.os.Environment.getExternalStorageDirectory();
        tv.append("\nExternal file system root:"+root);
        File dir = new File
                (root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, FileN);
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.write("\nPrId, PrN, Price, Quant, Date");
            pw.write("\n1, PrN1, 2, 5, 24/02/18");
            pw.write("\n2, PrN2, 4, 6, 24/02/18");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "File not found. Did you" + " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.append("\n\nFile written to:\n"+file);
    }

    public void readSDFile()
    { // read from SD card file data in the text box
        String aDataRow = "";
        String aBuffer = "";
        String [] ss;
        try {
            @SuppressLint("SdCardPath") File myFile = new
                    File("/mnt/sdcard/download/"+FileN);
            FileInputStream fIn = new FileInputStream(myFile);

            BufferedReader myReader = new BufferedReader( new InputStreamReader(fIn));
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            tv.append("\n"+aBuffer);
            myReader.close();
            Toast.makeText(getBaseContext(), "Done reading SD 'IA181.txt'"+aBuffer, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.d("Asdad", e.getMessage());
        }
    }
}


