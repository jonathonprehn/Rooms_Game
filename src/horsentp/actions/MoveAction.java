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
import horsentp.GameObject;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class MoveAction extends GameAction {

    private int goalX;
    private int goalY;
    
    public MoveAction(String label, int goalX, int goalY) {
        super(label);
        this.goalX = goalX;
        this.goalY = goalY;
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        if (goalX >=0 && goalX < gm.getWidth() && goalY >= 0 && goalY < gm.getHeight()
                && gm.getMap()[goalX][goalY] != null) {
            GameObject player = gm.player;
            gm.getMap()[gm.playerLocX][gm.playerLocY].removeObject(player);
            gm.getMap()[goalX][goalY].addObject(player);
            gm.playerLocX = goalX;
            gm.playerLocY = goalY;
            cm.log("MOVED");
            am.refreshActions(gm, cm, vm);
        }
    }
    
}
