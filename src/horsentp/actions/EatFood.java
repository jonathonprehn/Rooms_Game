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
import horsentp.GameObjectType;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class EatFood extends GameAction {
    
    public EatFood() {
        super("EAT FOOD");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject food = gm.getPlayerTile().getObject(GameObjectType.FOOD);
        vm.addHealth(1);
        gm.getPlayerTile().removeObject(food);
        cm.log("ATE " + food.getName());
        am.refreshActions(gm, cm, vm);
    }
    
    
}
