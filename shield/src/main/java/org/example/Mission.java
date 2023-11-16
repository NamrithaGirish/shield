package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mission {
    private final int missionID;
    protected String name;
    protected String details;
    protected String status;
    protected List<Avenger> assignedAvenger;
    public Mission(String name,String details ){
        this.missionID = new Random().nextInt();
        this.name = name;
        this.details = details;
        status = "ASSIGNED";
        assignedAvenger = new ArrayList<>();
    }

    public int getMissionID() {
        return missionID;
    }
    public void displayMissionStatus(){
        System.out.print(name+"\t"+status+"\t");
        for (Avenger avenger : assignedAvenger){
            System.out.print(avenger.getName()+" ");
        }
    }

    public String getName() {
        return name;
    }

    public void getDetails() {
        System.out.println("MISSION NAME : "+this.getName()+"\nDETAILS : "+this.details+"\nSTATUS : "+this.status+"\nAVENGERS : "+this.assignedAvenger);
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
