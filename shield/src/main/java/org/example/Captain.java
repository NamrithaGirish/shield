package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Captain extends UserAbstract{

    public List<Avenger> avengers;
    Scanner s;
    public Captain(String name){
        this.name = "Captain Fury";
        s = new Scanner(System.in);
        avengers = new ArrayList<>();
        missions = new ArrayList<>();
        for (int i=0;i<6;i++){
            initialiseAvengers();
        }
    }
    @Override
    public void optionDisplay() {

        System.out.println("CHOICE MENU\n1.CHECK MISSION\n2.ASSIGN MISSION\n3.CHECK MISSION DETAILS\n4.CHECK AVENGER DETAILS\n5.UPDATE MISSION\n6.LIST AVENGERS\n7.ASSIGN AVENGER TO MISSION");
        int choice;
        do {
            System.out.println("ENTER CHOICE : ");

            choice = Integer.parseInt(s.nextLine());
            switch (choice) {
                case 1:
                    super.checkMission();
                    break;
                case 2:
                    assignMission();
                    break;
                case 3:
                    System.out.println("ENTER MISSION NAME : ");
                    String name = s.nextLine();
                    missionDetail(name);
                    break;
                case 4:
                    avengerDetails();
                    break;
                case 5:
                    modifyMission();
                    break;
                case 6:
                    allAvengerDetails();
                    break;
                case 7:
                    assignAvengerToMission();
                    break;
                default:
                    System.out.println("ENTER VALID INPUT SYMBOL");
            }
        }while(choice!=8);

    }
    public void initialiseAvengers(){
        System.out.println("ENTER AVENGER : ");
        String name = s.nextLine();
        System.out.println("ENTER AVENGER'S ORG NAME : ");
        String orgName = s.nextLine();
        System.out.println("ENTER AVENGER ABILITIES : ");
        String abilities = s.nextLine();
        System.out.println("ENTER AVENGER COMMUNICATION MODE : ");
        String cmode = s.nextLine();
        Avenger avenger = new Avenger(name, orgName,abilities,cmode);
        avengers.add(avenger);
    }

    public void assignMission(){
        System.out.println("AVENGERS TO BE ASSIGNED : ");
        String names = s.nextLine();
        StringTokenizer st = new StringTokenizer(names,",");
        int avengerCount=0;
        Mission mission;
        String missionName = "";
        while (st.hasMoreTokens()){
            Avenger avenger = getAvenger(st.nextToken());
            if ((!avenger.isAvailability())){
                System.out.println(avenger.getName()+" IS ALREADY WORKING ON 2 PROJECTS");

            }
            else {

                if(avengerCount==0){
                    System.out.print("MISSION NAME : ");
                    missionName = s.nextLine();
                    System.out.print("MISSION DETAILS : ");
                    String missionDetails = s.nextLine();
                    mission = new Mission(missionName,missionDetails);
                    missions.add(mission);
                }

                mission = getMission(missionName);
                mission.assignedAvenger.add(avenger);
                avenger.workingMissions.add(mission.getMissionID());
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

    public void avengerDetails(){
        System.out.println("ENTER AVENGER NAME : ");
        String avengerName = s.nextLine();
        for (Avenger avenger : avengers){
            if (avenger.getName().equals(avengerName)) {
                avenger.details();
                avenger.missionDetails();
            }
        }
    }
    public void modifyMission(){
        System.out.println("ENTER MISSION NAME : ");
        String name = s.nextLine();
        for (Mission mission : missions){
            if (mission.getName().equals(name)){
                System.out.println("ENTER NEW STATUS : ");
                String status = s.nextLine();
                mission.updateMission(status);
            }
        }
    }
    public void allAvengerDetails(){
        for (Avenger avenger : avengers){
            avenger.missionStatusDetail();        }
    }
    public Mission getMission(String missionName){
        for (Mission mission : missions){
            if (mission.getName().equals(missionName)) {
                return mission;
            }
        }
        return null;
    }
    public void assignAvengerToMission(){
        System.out.println("AVENGER TO BE ASSIGNED : ");
        String name = s.nextLine();
        if (getAvenger(name).isAvailability()){
            System.out.print("MISSION NAME : ");
            String missionName = s.nextLine();
            Mission mission = getMission(missionName);
            if (mission.assignedAvenger.size()==2){
                System.out.println("ALREADY HAS "+mission.assignedAvenger.get(0).getName()+" " + mission.assignedAvenger.get(1).getName()+" WORKING ON THE MISSION");
            }
            mission.assignedAvenger.add(getAvenger(name));
            getAvenger(name).workingMissions.add(mission.getMissionID());
            getAvenger(name).incrementAssignedCount();
            getAvenger(name).sendNotification("MISSION ASSIGNED");
        }
        else{
            System.out.println("ALREADY HAS 2 MISSIONS");
        }
    }


}
