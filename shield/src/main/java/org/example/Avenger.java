package org.example;

import java.util.ArrayList;
import java.util.List;

public class Avenger extends UserAbstract{
    private String name;
    private boolean availability;
    private String orgName;
    private String ability;
    private int assignedCount;
    private int completedCount;
    private String communicationMode;
    public List<Mission> workingMissions;
    public Avenger(String name, String orgName, String ability, String communicationMode){
        this.name = name;
        this.orgName = orgName;
        this.ability = ability;
        this.communicationMode = communicationMode;
        assignedCount = 0;
        completedCount = 0;
        availability = true;
        workingMissions = new ArrayList<>();
    }

    void details(){
        System.out.println("ORG NAME : "+orgName+"\nABILITIES : "+ability);

    }
    void missionDetails(){
        System.out.println("MISSION ASSIGNED : "+assignedCount+"\nMISSION COMPLETED: "+completedCount);

    }
    public String getCommunicationMode(){
        return this.communicationMode;
    }
    public String getName() {
        return name;
    }

    public void incrementAssignedCount(){
        assignedCount++;
        if (assignedCount == 2){
            availability=false;
        }
    }
    public void decrementAssignedCount(){
        assignedCount--;
        completedCount++;
        if (assignedCount < 2){
            availability=true;
        }
    }
    public boolean isAvailability() {
        return availability;
    }
    public void sendNotification(String msg){
        System.out.println("SENT \""+msg+"\" NOTIFICATION TO "+this.getCommunicationMode());
    }
    public void missionStatusDetail(){
        System.out.print(name+"\t"+this.isAvailability()+"\t");
        for (Mission mission : workingMissions){
            System.out.print(mission.getName()+ " ");
        }

    }
    @Override
    public void optionDisplay() {

    }


}
