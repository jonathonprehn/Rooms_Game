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
public class CollectCrystal extends GameAction {

    public CollectCrystal() {
        super("COLLECT CRYSTAL");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject crystal = gm.getPlayerTile().getObject(GameObjectType.CRYSTAL);
        vm.addCrystals(1);
        gm.getPlayerTile().removeObject(crystal);
        cm.log("COLLECTED CRYSTAL");
        am.refreshActions(gm, cm, vm);
    }
    
}
