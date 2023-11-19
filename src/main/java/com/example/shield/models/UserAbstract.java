package com.example.shield.models;

import java.util.List;

abstract class UserAbstract implements User {
    public String name;
    public List<Mission> missions;
    public String checkMission(){
        if (missions.isEmpty()){
            return "NO MISSIONS FOUND";
        }
        else{
            String str = "MISSION NAME\tSTATUS\tAVENGERS\n";
            for (Mission mission : missions){
                str = str.concat(mission.displayMissionStatus()+"\n");
            }
            return str;
        }

    }
    public String missionDetail(String name){
        for (Mission mission : missions){
            if (mission.getName().equals(name)){
                return mission.getDetails();
            }
        }
        return null;
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
