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


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Participant> participants = new ArrayList<>();
    private final String TAG = "main act";
    String[] headerColumns = {"No","NAMA","NIK","TANGGAL LAHIR",
            "JK","NIP","JABATAN","GOL","TINGKAT PENDIDIKAN TERAKHIR",
            "FAKULTAS/JURUSAN PEND TERAKHIR","TEMPAT KERJA","EMAIL",
            "NO.HP","KODE POS","PARAF"};

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
                Log.d(TAG, "clicked");
                createExcelFile();

            }
        });
    }

    private void importFromExcel(){

    }

    private void createExcelFile() {

        //creating workbook and a sheet

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet participantsSheet = workbook.createSheet("peserta diklat");


        //create column and row
        HSSFRow headerRow = participantsSheet.createRow(0);

        //create style font for header
        HSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);

        // declare a cell object reference
        Cell cell = null;

        for(int i=0;i<headerColumns.length;i++){
            cell = headerRow.createCell(i);
            cell.setCellValue(headerColumns[i]);
//            cell.setCellStyle(style);
        }



        //iterate data
//        int rowNum = 1;
//        for (Participant participant : participants) {
//            Row row = participantsSheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(rowNum);
//            row.createCell(1).setCellValue(participant.getName());
//            row.createCell(2).setCellValue(participant.get());
//
//        }

        //creating file excel
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "daftar peserta diklat.xls");//Save into dir Documents
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            Log.d(TAG, "file created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, " file not created");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void loadData() {
        Participant mParticipant = new Participant();
        mParticipant.setName("Masni, SST");
        mParticipant.setNikId("1409016412750001");
        mParticipant.setNipId("19751224 200604 2 009");
        mParticipant.setGender("Perempuan");
        mParticipant.setBirthPlace("Seberang Pantai");
        mParticipant.setBirthDate("24 Desember 1975");
        mParticipant.setEmail("irwansyahmasni70@gmail.com");
        mParticipant.setNumPhone("0853 6570 8289");
        mParticipant.setPosition(new Position("Kepala Puskesmas","III/C"));
        mParticipant.setEducation(new Education("S1","Kesehatan Masyarakat"));

        //insert into arraylist participants
        participants.add(mParticipant);


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
