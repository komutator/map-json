package com.example;

public class SouthPanel extends AbstractPanel{

    public SouthPanel(){
        setBorderName("Log");
        LogPanel log = new LogPanel();
        log.addLog("Check DB\n");
        log.addLog("Check DB1\n");
        log.addLog("Check DB2\n");
        log.addLog("Check DB3\n");
        log.addLog("Check DB4\n");
        add(log);
    }

}
