package com.in.dev.cbts.service;

import com.in.dev.cbts.controller.HomeController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CodeJobSchedulerTest {

    @Test
    void triggerCodeSync_callsWelcome() {
        HomeController hc = mock(HomeController.class);
        CodeJobScheduler s = new CodeJobScheduler(hc);
        s.triggerCodeSync();
        verify(hc).welcome();
    }

    @Test
    void triggerCentralDbPush_runs() {
        HomeController hc = mock(HomeController.class);
        CodeJobScheduler s = new CodeJobScheduler(hc);
        s.triggerCentralDbPush();
        // doesn't throw
    }
}
