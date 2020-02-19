package com.amalia.excelapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Participant> participants = new ArrayList<>();
    private final String TAG = "main act";
    String[] headerTopCol = {"No", "NAMA", "NIK", "TANGGAL \n LAHIR", "TEMPAT \n LAHIR", "JK", "NIP", "JABATAN", "GOL", "TINGKAT\nPEND\nTERAKHIR",
            "FAKULTAS/JURUSAN\nPEND\nTERAKHIR", "TEMPAT\nKERJA", "ALAMAT TEMPAT KERJA", "",
            "", "ALAMAT RUMAH", "", "", "", "EMAIL", "NO.\nHP", "KODE\nPOS", "PARAF"};

    String[] headerBottomCol = {"ALAMAT", "KEC", "KAB", "ALAMAT", "KEC", "KAB", "PROV"};
    XSSFRow headerRow;
    Cell cell;
    int idxHeaderCell;

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
                snackBar(view, "Clicked");
                createExcelFile();

            }
        });
    }

    private void importFromExcel() {

    }

    private void createExcelFile() {

        //creating workbook and a sheet

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet participantsSheet = workbook.createSheet("peserta diklat");


        //**HEADER**//
        //DO JUST ONCE WHEN FILE .XLSX CREATED
        //create style font for header
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);


        //row 0
        idxHeaderCell = 0;
        headerRow = participantsSheet.createRow(0);
        //create cell header, set value & style
        for (int i = 0; i < headerTopCol.length; i++) {
            cell = headerRow.createCell(i);
            cell.setCellValue(headerTopCol[i]);
            //merging cell (header)
            if (i == 12) {
                participantsSheet.addMergedRegion(new CellRangeAddress(0, 0, i, 14));
                i = 14;
            } else if (i == 15) {
                participantsSheet.addMergedRegion(new CellRangeAddress(0, 0, i, 18));
                i = 18;
            } else {
                participantsSheet.addMergedRegion(new CellRangeAddress(0, 1, i,i));

            }
            cell.setCellStyle(style);
//            idxHeaderCell++;
        }
        idxHeaderCell = 0;
        //row 1
        idxHeaderCell = 12;
        headerRow = participantsSheet.createRow(1);
        for (int i = 0; i < headerBottomCol.length; i++) {
            cell = headerRow.createCell(idxHeaderCell);
            cell.setCellValue(headerBottomCol[i]);
            cell.setCellStyle(style);
            idxHeaderCell++;
        }

        //**INSERTING VALUE **//
        //iterate data
        int rowNum = 2;
        int numTable = 1;
        for (Participant participant : participants) {
            Row row = participantsSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(numTable++);
            row.createCell(1).setCellValue(participant.getName());
            row.createCell(2).setCellValue(participant.getNikId());
            row.createCell(3).setCellValue(participant.getBirthDate());
            row.createCell(4).setCellValue(participant.getBirthPlace());
            row.createCell(5).setCellValue(participant.getGender());
            row.createCell(6).setCellValue(participant.getNipId());

            row.createCell(7).setCellValue(participant.getPosition().getName());
            row.createCell(8).setCellValue(participant.getPosition().getEchelon());

            row.createCell(9).setCellValue(participant.getEducation().getEduLevel());
            row.createCell(10).setCellValue(participant.getEducation().getMajor());

            row.createCell(11).setCellValue(participant.getWorkPlace().getName());
            row.createCell(12).setCellValue(participant.getWorkPlace().getAddress().getStreet());
            row.createCell(13).setCellValue(participant.getWorkPlace().getAddress().getKec());
            row.createCell(14).setCellValue(participant.getWorkPlace().getAddress().getKab());

            row.createCell(15).setCellValue(participant.getPersonalAddress().getStreet());
            row.createCell(16).setCellValue(participant.getPersonalAddress().getKec());
            row.createCell(17).setCellValue(participant.getPersonalAddress().getKab());
            row.createCell(18).setCellValue(participant.getPersonalAddress().getProv());

            row.createCell(19).setCellValue(participant.getEmail());
            row.createCell(20).setCellValue(participant.getNumPhone());
            row.createCell(21).setCellValue(participant.getWorkPlace().getPostalCode());
        }

        //creating file excel
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "daftar peserta diklat.xlsx");//Save into dir Documents
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
        mParticipant.setPersonalAddress(new Address("jln. bunga setangkai no. 58 kel pasar lubuk jambi","Kuantan Mudik","kuantan singingi",""));
        mParticipant.setEmail("irwansyahmasni70@gmail.com");
        mParticipant.setNumPhone("0853 6570 8289");
        mParticipant.setPosition(new Position("Kepala Puskesmas", "III/C"));
        mParticipant.setEducation(new Education("S1", "Kesehatan Masyarakat"));
        mParticipant.setWorkPlace(new WorkPlace(
                                                "UPTD Kesehatan Puskesmas Lubuk Jambi",
                                                new Address("JlN. lingkar banjar padang _ kasang Banjar Padang","Kuantan Mudik","Kuantan Singingi","Riau"),
                                                29564)
                                );


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

    private void snackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
