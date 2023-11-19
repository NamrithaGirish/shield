package com.example.shield.controller;

import com.example.shield.models.Avenger;
import com.example.shield.models.Captain;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CaptainController {
    private final Captain captain;
    @Autowired
    public CaptainController(Captain captain) {
        this.captain = captain;
    }

    @GetMapping("/1")
    public String checkMission() {
        return captain.checkMission();
    }
    public static class MissionRequest {
        private List<String> avengerNames;
        private String missionName;
        private String missionDetails;
        public MissionRequest(@JsonProperty("avengerNames") List<String> avengerNames,@JsonProperty("missionName") String missionName,@JsonProperty("missionDetails") String missionDetails){
            this.avengerNames = avengerNames;
            this.missionDetails = missionDetails;
            this.missionName = missionName;
        }
        public List<String> getAvengerNames() {
            return avengerNames;
        }
        public String getMissionDetails() {
            return missionDetails;
        }

        public String getMissionName() {
            return missionName;
        }

    }
    @GetMapping("")
    public String optionDisplay(){
        return captain.optionDisplay();
    }
    @PostMapping("/2")
    public String assignMission(@RequestBody MissionRequest missionRequest) {
        List<String> avengerNames = missionRequest.getAvengerNames();
        String missionName = missionRequest.getMissionName();
        String missionDetails = missionRequest.getMissionDetails();

        captain.assignMission(avengerNames, missionName, missionDetails);

        return "Mission assigned successfully to " + avengerNames;
    }

    @PostMapping("/3")
    public String missionDetail(@RequestBody String name) {
        return captain.missionDetail(name);
    }
    @PostMapping("/4")
    public String avengerDetails(@RequestBody String name) {
        return captain.avengerDetails(name);
    }
    @PostMapping("/5")
    public String modifyMission(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String status = (String) requestMap.get("status");
        return captain.modifyMission(name,status);

    }
    @GetMapping("/6")
    public List<Avenger> allAvengerDetails() {
        return captain.allAvengerDetails();
    }

    @PostMapping("/7")
    public String assignAvengerToMission(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String missionName = (String) requestMap.get("missionName");
        return captain.assignAvengerToMission(name,missionName);
    }

}
