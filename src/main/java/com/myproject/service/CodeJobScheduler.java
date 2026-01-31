package com.myproject.service;

import com.myproject.controller.HomeController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CodeJobScheduler {


	private final HomeController webController;
	
	public CodeJobScheduler(HomeController webController) {
		this.webController=webController;
		
	}

    /**
     * CODE SYNC API
     * Runs 4 times daily: 12AM, 6AM, 12PM, 6PM
     */
    @Scheduled(cron = "0 06 0,6,12,16,18 * * ?")
    public synchronized void triggerCodeSync() {
        try {
            System.out.println("Code Sync started at: " + LocalDateTime.now());
           // codeSyncService.syncCodes();
            webController.welcome();
            
            System.out.println("Code Sync completed");
        } catch (Exception e) {
            System.err.println("Code Sync failed: " + e.getMessage());
        }
    }

    /**
     * CENTRAL DB PUSH API
     * Runs 6 times daily: 1AM, 5AM, 9AM, 1PM, 5PM, 9PM
     */
    @Scheduled(cron = "0 0 1,5,9,13,17,21 * * ?")
    public synchronized void triggerCentralDbPush() {
        try {
            System.out.println("Central DB Push started at: " + LocalDateTime.now());
           // centralDbPushService.pushCodes();
            System.out.println("Central DB Push completed");
        } catch (Exception e) {
            System.err.println("Central DB Push failed: " + e.getMessage());
        }
    }
}
