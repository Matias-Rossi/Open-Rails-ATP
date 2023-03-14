package com.mr.or_atp.or_atp.realtime_data_fetcher;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mr.or_atp.or_atp.models.sim_data.CabControls;
import com.mr.or_atp.or_atp.models.sim_data.SimData;
import com.mr.or_atp.or_atp.models.sim_data.TrackMonitor;
import com.mr.or_atp.or_atp.models.sim_data.TrainDisplay;
import com.mr.or_atp.or_atp.models.sim_data.json_models.CabControl;
import com.mr.or_atp.or_atp.models.sim_data.json_models.TrackMonitorRow;
import com.mr.or_atp.or_atp.models.sim_data.json_models.TrainDisplayRow;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Component
public class DataFetcher {

    private Integer serverPort = 2150; //TODO set in application.properties

    Gson gson = new Gson();

    private static DataFetcher instance = null;

    public static DataFetcher getInstance() {
        if (instance == null) {
            instance = new DataFetcher();
        }
        return instance;
    }

    // @Autowired
    // private DataFetcher(@Value("${or.server.port}") Integer serverPort) {
    //     this.serverPort = serverPort;
    // }

    @PostConstruct
    @Scheduled(fixedDelay = 1000)
    public SimData fetchData() {

        System.out.println(" ============== Fetching data ============== ");

        TrackMonitor trackMonitor = fetchTrackMonitorData();
        TrainDisplay trainDisplay = fetchTrainDisplayData();
        CabControls cabControls = fetchCabControlsData();

        //System.out.println("VEL: " + trainDisplay.getSpeed() + " // BC: " + trainDisplay.getBrakeCylinderPressure() );

        // trackMonitor.getSignals().forEach(signal -> {
        //     System.out.println("Signal: " + signal.getAspect() + " // Distance: " + signal.getDistance());
        // });
        
        trackMonitor.getSpeedLimitPosts().forEach(speedLimitPost -> {
            System.out.println("SpeedLimitPost: " + speedLimitPost.getSpeedLimit() + " // Distance: " + speedLimitPost.getDistance());
        });

        return new SimData(trackMonitor, trainDisplay, cabControls);
    }

    private TrackMonitor fetchTrackMonitorData() {
        URL url;
        try {
            url = new URL("http://localhost:" + Integer.toString(serverPort) + "/API/TRACKMONITORDISPLAY");
            String response = HttpReqUtils.getJsonFromURL(url);
            return new TrackMonitor(gson.fromJson(response, TrackMonitorRow[].class));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TrainDisplay fetchTrainDisplayData() {
        URL url;
        try {
            url = new URL("http://localhost:" + Integer.toString(serverPort) + "/API/TRAINDRIVINGDISPLAY?normalText=true");
            String response = HttpReqUtils.getJsonFromURL(url);
            return new TrainDisplay(gson.fromJson(response, TrainDisplayRow[].class));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CabControls fetchCabControlsData() {
        URL url;
        try {
            url = new URL("http://localhost:" + Integer.toString(serverPort) + "/API/CABCONTROLS");
            String response = HttpReqUtils.getJsonFromURL(url);
            return new CabControls(gson.fromJson(response, CabControl[].class));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
