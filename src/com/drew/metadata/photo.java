package com.drew.metadata;

import com.drew.lang.StringUtil;

import javax.print.attribute.DateTimeSyntax;
import java.io.File;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class photo {
    final int photo_id;
    String photo_filepath;
    File image;
    boolean outside;
    long nodeID;
    double Longitude;
    double Latitude;
    LocalDate theDate;
    LocalTime theTime;
    Node theNode;

    photo(int id, String filepath, Node node){
        photo_id = id;
        photo_filepath = filepath + "\\" + (id) + ".jpg"; //create filepath to each individual photo
        image = new File(photo_filepath);
        outside = (photo_id % 20 == 0) ? true:false;
        setNodeInfo(node);
        theDate = LocalDate.of(2020,6,17);
        theTime = LocalTime.of((int)(Math.random()*24), (int)(Math.random()*60),(int)(Math.random()*60));
        theNode = node;

    }

    public void setNodeInfo(Node n){

        Pattern p = Pattern.compile("\'([^\']*)\'");
        Matcher m = p.matcher(n.Nodetxt);

        int counter = 0;
        while (m.find()) {

            if(counter == 0){
                nodeID = Long.parseLong(m.group(1));
            }
            if(counter == 3){
                Latitude = Double.parseDouble(m.group(1));
            }
            if(counter == 4){
                Longitude = Double.parseDouble(m.group(1));
            }
            counter++;

        }


    }

    public String toString(){
        return "photo_id: " + photo_id + " NodeID: " + nodeID + " outside: " + outside + " Longitude: " + Longitude + " Latitude: " + Latitude + " Date: " + theDate + " Time: " + theTime;
    }

}
