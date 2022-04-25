package com.example.rit_library;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrentTime {
    private Date date = new Date();
    RIT_LibraryDatabase database;
    LocalTime start = LocalTime.parse( "08:00:00" ) ;
    LocalTime end = LocalTime.parse( "16:00:00" ) ;
    public CurrentTime(){
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }
    public boolean testTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        LocalTime time = LocalTime.parse(currentTime);
        if(time.isAfter(start) && time.isBefore(end)){
            return true;
        }
        else{
            return false;
        }
    }

    public Date getDate() {
        return date = new Date();
    }
}
