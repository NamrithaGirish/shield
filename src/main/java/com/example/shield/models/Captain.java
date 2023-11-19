package com.example.shield.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class Captain extends UserAbstract {

    public List<Avenger> avengers;
    Scanner s;
    public Captain(){
        this.name = "Captain Fury";
        s = new Scanner(System.in);
        avengers = new ArrayList<>();
        missions = new ArrayList<>();
        String temp = "ABCDEF";
        for (int i=0;i<6;i++){
            initialiseAvengers(temp.substring(i,i+1));
        }
    }
    @Override
    public String optionDisplay() {

        return "CHOICE MENU\n1.CHECK MISSION\n2.ASSIGN MISSION\n3.CHECK MISSION DETAILS\n4.CHECK AVENGER DETAILS\n5.UPDATE MISSION\n6.LIST AVENGERS\n7.ASSIGN AVENGER TO MISSION";


    }
    public void initialiseAvengers(String name){

        /*System.out.println("ENTER AVENGER : ");
        String name = s.nextLine();
        System.out.println("ENTER AVENGER'S ORG NAME : ");
        String orgName = s.nextLine();
        System.out.println("ENTER AVENGER ABILITIES : ");
        String abilities = s.nextLine();
        System.out.println("ENTER AVENGER COMMUNICATION MODE : ");
        String cmode = s.nextLine();*/
        Avenger avenger = new Avenger(name,name,name,name);
        avengers.add(avenger);
    }

    public void assignMission(List<String> avengerNames, String missionName, String missionDetails){
        /*System.out.println("AVENGERS TO BE ASSIGNED : ");
        String names = s.nextLine();
        StringTokenizer st = new StringTokenizer(names,",");*/
        int avengerCount=0;
        Mission mission;

        for (String avengerName : avengerNames) {
            Avenger avenger = getAvenger(avengerName);
            System.out.println("Found"+avengerName);

            if ((!avenger.isAvailability())){
                System.out.println(avenger.getName()+" IS ALREADY WORKING ON 2 PROJECTS");

            }
            else {

                if(avengerCount==0){
                    mission = new Mission(missionName,missionDetails);
                    missions.add(mission);
                }

                mission = getMission(missionName);
                mission.assignedAvenger.add(avenger);
                avenger.workingMissions.add(mission);
                avenger.incrementAssignedCount();
                avenger.sendNotification("MISSION ASSIGNED");
                avengerCount++;
            }

        }



    }
    public Avenger getAvenger(String name){
        for (Avenger avenger : avengers){
            if (avenger.getName().equals(name)) {
                return avenger;
            }
        }
        return null;
    }

    public String avengerDetails(String avengerName){
        for (Avenger avenger : avengers){
            if (avenger.getName().equals(avengerName)) {
                return avenger.details().concat(avenger.missionDetails());
            }
        }
        return null;
    }
    public String modifyMission(String name, String status){
        for (Mission mission : missions){
            if (mission.getName().equals(name)){
                mission.updateMission(status);
                return "STATUS UPDATED";
            }
        }
        return null;
    }
    public List<Avenger> allAvengerDetails(){
        return avengers;
        /*for (Avenger avenger : avengers){
            avenger.missionStatusDetail();
        }*/
    }
    public Mission getMission(String missionName){
        for (Mission mission : missions){
            if (mission.getName().equals(missionName)) {
                return mission;
            }
        }
        return null;
    }
    public String assignAvengerToMission(String name, String missionName){
        //System.out.println("AVENGER TO BE ASSIGNED : ");
        //String name = s.nextLine();
        if (getAvenger(name).isAvailability()){
            //System.out.print("MISSION NAME : ");
            //String missionName = s.nextLine();
            Mission mission = getMission(missionName);
            if (mission.assignedAvenger.size()==2){
                return "ALREADY HAS "+mission.assignedAvenger.get(0).getName()+" " + mission.assignedAvenger.get(1).getName()+" WORKING ON THE MISSION";
            }
            mission.assignedAvenger.add(getAvenger(name));
            getAvenger(name).workingMissions.add(mission);
            getAvenger(name).incrementAssignedCount();
            return getAvenger(name).sendNotification("MISSION ASSIGNED");
        }
        else{
            return "ALREADY HAS 2 MISSIONS";
        }
    }


}
