package core.managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;

import core.ADB;
import core.MyLogger;

public class DriverManager {
	private static String nodeJS = System.getenv("APPIUM_HOME")+"/node.exe";
    private static String appiumJS = System.getenv("APPIUM_HOME")+"/node_modules/appium/bin/appium.js";
    private static DriverService service;
    private static String deviceID;

    private static HashMap<String, URL> hosts;
    private static String unlockPackage = "io.appium.unlock";

    private static DesiredCapabilities getCaps(String deviceID){
        MyLogger.log.info("Creating driver caps for device: "+deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", deviceID);
            caps.setCapability("platformName", "Android");
            caps.setCapability("app", "C:/Appium/node_modules/appium/build/unlock_apk/unlock_apk-debug.apk");
        return caps;
    }

    private static URL host(String deviceID) throws MalformedURLException {
        if(hosts == null){
            hosts = new HashMap<String, URL>();
            hosts.put("ZX1G324LHF", new URL("http://127.0.0.1:4723/wd/hub"));
        }return hosts.get(deviceID);
    }

    private static ArrayList<String> getAvailableDevices(){
        MyLogger.log.info("Checking for available devices");
        ArrayList<String> avaiableDevices = new ArrayList<String>();
        ArrayList connectedDevices = ADB.getConnectedDevices();
        for(Object connectedDevice: connectedDevices){
            String device = connectedDevice.toString();
            ArrayList apps = new ADB(device).getInstalledPackages();
            if(!apps.contains(unlockPackage)){
               // if(useDevice(deviceID)) avaiableDevices.add(device);
                //else MyLogger.log.info("Device: "+deviceID+" is being used by another JVM");
            }
            else MyLogger.log.info("Device: "+device+" has "+unlockPackage+" installed, assuming it is under testing");
        }
        if(avaiableDevices.size() == 0) throw new RuntimeException("Not a single device is available for testing at this time");
        return avaiableDevices;
    }


}
