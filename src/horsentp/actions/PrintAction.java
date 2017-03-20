/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.actions;

import horsentp.ActionMap;
import horsentp.CombatMap;
import horsentp.GameAction;
import horsentp.GameMap;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class PrintAction extends GameAction {
    
    private String message;
    
    public PrintAction(String message) {
        super("Print");
        this.message= message;
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        System.out.println(message);
    }
    
}
