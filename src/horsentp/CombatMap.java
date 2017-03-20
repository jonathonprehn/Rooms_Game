/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class CombatMap {
    
    private ArrayList<String> log = new ArrayList<>();
    private static final int LOG_ITEMS = 6;
    
    public void log(String logItem) {
        log.add(logItem);
        if (log.size() > LOG_ITEMS) {
            log.remove(0);
        }
    }
    
    public int getLogItems() {
        return log.size();
    }
    
    public String getLogItem(int index) {
        return log.get(index);
    }
}
