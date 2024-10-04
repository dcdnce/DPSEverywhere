package dpsmod;

import necesse.engine.modLoader.annotations.ModEntry;
import dpsmod.patches.*;
import necesse.engine.util.DPSTracker;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;

@ModEntry
public class DPSMod {

    public static DPSTracker playerDPSTracker = new DPSTracker();

    public void init() {
        System.out.println("Hello world from DPSmod!");

//        PacketRegistry.registerPacket(ExamplePacket.class);
    }

    public static boolean shouldHandleMob(Mob mob, Attacker attacker) {
        return mob.isHostile && attacker.getAttackOwner().isPlayer;
    }

}
