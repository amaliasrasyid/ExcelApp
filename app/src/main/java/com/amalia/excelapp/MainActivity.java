package com.amalia.excelapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.amalia.excelapp.model.Address;
import com.amalia.excelapp.model.Education;
import com.amalia.excelapp.model.Participant;
import com.amalia.excelapp.model.Position;
import com.amalia.excelapp.model.WorkPlace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;


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
    private ArrayList<Participant> resultReadParticipantList = new ArrayList<>();
    private ArrayList<String> participantsName = new ArrayList<>();
    private Participant mParticipant;
    private static final int PICKFILE_RESULT_CODE = 1;
    ListView listView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //load data
        Data.loadData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clicked");
                snackBar(view, "Clicked");
//                setPathFile();
                createExcelFile();


            }
        });
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
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
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

        //creating folder
        File path = new File(Environment.getExternalStorageDirectory().getPath()+"/Excel App");
        try{
            if(path.mkdirs()){
                Log.d(TAG,"directory created");
            }else{
                Log.d(TAG,"directory not created");
            }
        }catch (Exception e){
            Log.d(TAG,e.getMessage().toString());
        }



        // Get public external storage folder ( /storage/emulated/0 ).
        File file = new File(path, "daftar peserta diklat.xlsx");//Save into dir Documents
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

    private void readExcelFile(String filepath) throws IOException{
        //todo: UNTUK SEMENTARA MENGGUNAKAN FIX PATH FILE
        //create an object of File class to open xlsx file
        File file = new File(filepath);

        //create FileInputStream to read excel file
        FileInputStream  inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        //**READ PROCESS**//
        Sheet inputSheet = workbook.getSheetAt(0);// 0 = first sheet
        //find number of rows in file excel
        int rowCount = inputSheet.getLastRowNum() - inputSheet.getFirstRowNum();
        Log.d(TAG,"rowount = "+rowCount);

        //iterating each row to read all rows of file excel
        Log.d(TAG,"iterating cell");
        for(int idxRow = 0; idxRow < rowCount+1;idxRow++){
            Row row = inputSheet.getRow(idxRow);

            //loop every cell in a row to storing in an object class Participant
            Participant participant = new Participant();
            participant.setName(getCellValue(row.getCell(1)));
            participant.setNikId(getCellValue(row.getCell(2)));
            participant.setBirthDate(getCellValue(row.getCell(3)));
            participant.setBirthPlace(getCellValue(row.getCell(4)));
            participant.setGender(getCellValue(row.getCell(5)));
            participant.setNipId(getCellValue(row.getCell(6)));
            participant.setPosition(new Position(getCellValue(row.getCell(7)), getCellValue(row.getCell(8))));
            participant.setEducation(new Education(getCellValue(row.getCell(9)), getCellValue(row.getCell(10))));
            participant.setWorkPlace(new WorkPlace(
                    getCellValue(row.getCell(11)),
                    new Address(getCellValue(row.getCell(12)),getCellValue(row.getCell(13)),getCellValue(row.getCell(14)),getCellValue(row.getCell(18))),
                    Integer.parseInt(getCellValue(row.getCell(21))))
            );
            participant.setPersonalAddress(new Address(getCellValue(row.getCell(15)),getCellValue(row.getCell(16)),getCellValue(row.getCell(17)),""));
                participant.setEmail(getCellValue(row.getCell(19)));
                participant.setNumPhone(getCellValue(row.getCell(20)));

//            //store in arraylist
//            resultReadParticipantList.add(participant);
            participantsName.add(participant.getName());
            Log.d(TAG,participant.getName());
        }
        Log.d(TAG,"finish iterating cell");


    }



    private String getCellValue(Cell cell){
        switch (cell.getCellType()){
            case XSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case XSSFCell.CELL_TYPE_NUMERIC:
                //check if it was date type or not
                if(DateUtil.isCellDateFormatted(cell)){
                    return customDateFormat(cell.getDateCellValue());
                }else{
                    return String.valueOf((int)cell.getNumericCellValue());
                }
            default:
                return "unknown data type cell";
        }
    }

    private String customDateFormat(Date dateCellValue) {
        //create date pattern format
        SimpleDateFormat customPattern = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

        return customPattern.format(dateCellValue);
    }

    private void chooseFile() {
        Intent intent = new Intent(this, FilePickerActivity.class);
        intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile(".*\\.xlsx$"));
        intent.putExtra(FilePickerActivity.ARG_CLOSEABLE,true);
        startActivityForResult(intent,PICKFILE_RESULT_CODE);

//        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");//format .xlsx
//        startActivityForResult(Intent.createChooser(intent,"Choose File .xlsx"),PICKFILE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);

            Log.d(TAG,"Result path"+filePath);
            //READ FILE
            //read the imported file
            try {
                progressBar.setVisibility(View.VISIBLE);
                readExcelFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG,e.getCause().toString());
            }
            progressBar.setVisibility(View.INVISIBLE);

            //LISTVIEW
            //adapter list item
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,participantsName);
            //set adapter to listview
            listView.setAdapter(adapter);
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
        if (id == R.id.action_importFileExcel) {
            //get file with picker
            chooseFile();
        }
        return super.onOptionsItemSelected(item);
    }



    private void snackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}