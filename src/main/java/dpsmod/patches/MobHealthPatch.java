package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = Mob.class, name = "setHealthHidden", arguments = {int.class, float.class, float.class, Attacker.class, boolean.class})
public class MobHealthPatch {

    @Advice.OnMethodEnter()
    static void onEnter(@Advice.This Mob mob, @Advice.Argument(3) Attacker attacker, @Advice.Local("beforeHealth") int beforeHealth, @Advice.Local("localAttacker") Attacker localAttacker) {
        beforeHealth = mob.getHealth();
        localAttacker = attacker;
    }

    @Advice.OnMethodExit
    static void onExit(@Advice.This Mob mob, @Advice.Argument(3) Attacker attacker, @Advice.Local("beforeHealth") int beforeHealth) {
        if (mob == null || attacker == null)
            return ;

        if (mob.isClient() && mob.getLevel() != null) {
            if (DPSMod.shouldHandleMob(mob, attacker) && mob.shouldSendSpawnPacket()) {
                int damage = beforeHealth - mob.getHealth();
                if (damage > 0) {
                    DPSMod.playerDPSTracker.addHit(mob.getWorldEntity().getTime(), damage);
                }
            }
        }
    }

}
