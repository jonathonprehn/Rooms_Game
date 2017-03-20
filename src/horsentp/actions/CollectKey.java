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
public class CollectKey extends GameAction {
    
    public CollectKey() {
        super("COLLECT KEY");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject key = gm.getPlayerTile().getObject(GameObjectType.KEY);
        gm.getPlayerTile().removeObject(key);
        vm.addKeys(1);
        cm.log("COLLECTED " + key.getName());
        am.refreshActions(gm, cm, vm);
    }
    
    
}
