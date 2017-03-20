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
public class ReadAction extends GameAction {

    public ReadAction() {
        super("READ PLAQUE");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject plaque = gm.getPlayerTile().getObject(GameObjectType.PLAQUE);
        cm.log("IT READS \"" + plaque.getName() + "\"");
        am.refreshActions(gm, cm, vm);
    }
    
}
