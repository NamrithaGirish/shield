package com.example.shield.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mission {
    private final int missionID;
    protected String name;
    protected String details;
    protected String status;
    protected List<Avenger> assignedAvenger;
    public Mission(String name, String details ){
        this.missionID = new Random().nextInt();
        this.name = name;
        this.details = details;
        status = "ASSIGNED";
        assignedAvenger = new ArrayList<>();
    }

    public int getMissionID() {
        return missionID;
    }
    public String displayMissionStatus(){
        String str = name+"\t"+status+"\t";
        for (Avenger avenger : assignedAvenger){
            str = str.concat(avenger.getName()+" ");
        }
        return str;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return "MISSION NAME : "+this.getName()+"\nDETAILS : "+this.details+"\nSTATUS : "+this.status+"\nAVENGERS : "+this.assignedAvenger;
    }
    public void updateMission(String status){
        this.status = status;
        if (status.equals("COMPLETED")){
            for (Avenger avenger : assignedAvenger){
                avenger.decrementAssignedCount();
                avenger.sendNotification("MISSION "+this.name+" COMPLETED");
            }
        }
    }
    public String getAssignedAvengers(){
        String avengerList = "";
        for (Avenger avenger : assignedAvenger){
            avengerList=avengerList.concat(avenger.getName()+" ");
        }
        return avengerList;
    }
}
