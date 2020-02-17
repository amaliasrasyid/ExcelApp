package com.amalia.excelapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> participantNames ;
    private ArrayList<Integer> numbIdParticipants;
    private ArrayList<Participant> participants = new ArrayList<>();
    private final String TAG = "main act";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //load data
        loadData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"clicked");
                createExcelFile();

            }
        });
    }

    private void createExcelFile() {

        //creating workbook and a sheet
        Workbook workbook = new HSSFWorkbook();
        Sheet participantsSheet = workbook.createSheet("peserta diklat");

//        //adjust column with to the content
//        participantsSheet.autoSizeColumn(2);

        //create column and row
        Row headerRow = participantsSheet.createRow(0);
        // declare a cell object reference
        Cell cell = null;


        cell = headerRow.createCell(0);
        cell.setCellValue("No.");

        cell = headerRow.createCell(1);
        cell.setCellValue("Nama peserta");

        cell = headerRow.createCell(2);
        cell.setCellValue("NIK");


        //iterate data
        int rowNum = 1;
        for(Participant participant :participants ){
            Row row = participantsSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum);
            row.createCell(1).setCellValue(participant.getName());
            row.createCell(2).setCellValue(participant.getId());

        }

        //creating file excel
        File file =  new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"daftar peserta diklat.xls");//Save into dir Documents
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            Log.d(TAG,"file created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG," file not created");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void loadData(){
        participantNames = new ArrayList<>();
        participantNames.add("Amalia Salmi Rasyid");
        participantNames.add("A.salmi rasyid");
        participantNames.add("A.rasyid");
        participantNames.add("salmi");

        numbIdParticipants = new ArrayList<>();
        numbIdParticipants.add(12345);
        numbIdParticipants.add(54321);
        numbIdParticipants.add(21435);
        numbIdParticipants.add(54123);

        //insert into list Participant
        for(int i = 0 ; i<participantNames.size();i++){
            participants.add(new Participant(participantNames.get(i),String.valueOf(numbIdParticipants.get(i))));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
