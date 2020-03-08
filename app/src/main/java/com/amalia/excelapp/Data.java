package com.amalia.excelapp;

import com.amalia.excelapp.model.Address;
import com.amalia.excelapp.model.Education;
import com.amalia.excelapp.model.Participant;
import com.amalia.excelapp.model.Position;
import com.amalia.excelapp.model.WorkPlace;

import java.util.ArrayList;

public  class Data {

    public static Participant loadData() {
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
       return  mParticipant;


    }
}
