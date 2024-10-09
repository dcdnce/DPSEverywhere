package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.*;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = Mob.class, name = "isHit", arguments = {MobWasHitEvent.class, Attacker.class})
public class MobIsHitPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This Mob mob, @Advice.Argument(0) MobWasHitEvent event, @Advice.Argument(1) Attacker attacker) {
        if (mob == null || attacker == null)
            return ;

        if (mob.isClient() && mob.getLevel() != null) {
            if (DPSMod.shouldHandleMob(attacker) && mob.shouldSendSpawnPacket() && event.damage > 0) {
                DPSMod.playerDPSTracker.addHit(mob.getWorldEntity().getTime(), event.damage);
            }
        }
    }
}


