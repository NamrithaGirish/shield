package org.example;

import java.util.List;

abstract class UserAbstract implements User{
    public String name;
    public List<Mission> missions;
    public void checkMission(){
        if (missions.isEmpty()){
            System.out.println("NO MISSIONS FOUND");
        }
        else{
            System.out.println("MISSION NAME\tSTATUS\tAVENGERS");
            for (Mission mission : missions){
                mission.displayMissionStatus();
            }
        }
    }
    public void missionDetail(String name){
        for (Mission mission : missions){
            if (mission.getName().equals(name)){
                mission.getDetails();
            }
        }
    }
    public String missionDetail(int id){
        for (Mission mission : missions){
            if (mission.getMissionID()==id){
                return mission.getName();
            }
        }
        return null;
    }
}
