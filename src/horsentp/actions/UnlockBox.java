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
import horsentp.GameObjectType;
import horsentp.MagicBoxGameObject;
import horsentp.PortalGameObject;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class UnlockBox extends GameAction {

    public UnlockBox() {
        super("UNLOCK BOX");
    }
    
    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        MagicBoxGameObject box = (MagicBoxGameObject)gm.getPlayerTile().getObject(GameObjectType.MAGIC_BOX);
        vm.addKeys(-1);
        gm.getPlayerTile().removeObject(box);
        gm.getPlayerTile().addObject(new PortalGameObject("PORTAL",
                GameObjectType.PORTAL, "images/portal.png", box.getPortalLoc(),
                box.getPortalX(), box.getPortalY()));
        cm.log("OPENED PORTAL");
        am.refreshActions(gm, cm, vm);
    }
    
}
