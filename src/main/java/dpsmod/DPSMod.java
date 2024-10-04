package dpsmod;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.modLoader.annotations.ModEntry;
import dpsmod.patches.*;
import necesse.engine.util.DPSTracker;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.MainGameFormManager;

@ModEntry
public class DPSMod {

    public static DPSTracker playerDPSTracker = new DPSTracker();
    public static DPSSidebarForm dpsSidebarForm= new DPSSidebarForm();
    public static MainGameFormManager mainGameFormManager = null;

    public void init() {
        System.out.println("Hello world from DPSmod!");
    }

    public static boolean shouldHandleMob(Mob mob, Attacker attacker) {
        return mob.isHostile && attacker.getAttackOwner().isPlayer;
    }

}
