package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.localization.Localization;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.util.GameRandom;
import necesse.engine.util.GameUtils;
import necesse.entity.mobs.Mob;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameTooltips.GameTooltipManager;
import necesse.gfx.gameTooltips.StringTooltips;
import necesse.gfx.gameTooltips.TooltipLocation;
import necesse.level.maps.hudManager.floatText.FloatTextFade;
import net.bytebuddy.asm.Advice;

import java.awt.*;

@ModMethodPatch(target = Mob.class, name = "clientTick", arguments = {})
public class ClientTickPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This Mob mob) {
        if (!mob.isPlayer)
            return ;

        long currentTime = mob.getWorldEntity().getTime();
        DPSMod.playerDPSTracker.tick(currentTime); // handle hits list

        if (mob.getLevel().tickManager().isFirstGameTickInSecond() && DPSMod.playerDPSTracker.isLastHitBeforeReset(currentTime)) {
            int dps = DPSMod.playerDPSTracker.getDPS(currentTime);

            System.out.println("ClientTickPatch :: player dps : " + dps);

            String text = Localization.translate("misc", "dpscount", "dps", GameUtils.formatNumber((double) dps));
            FloatTextFade floatText = new FloatTextFade(mob.getX() + (int) (GameRandom.globalRandom.nextGaussian() * 6.0), mob.getY() - 32, text, (new FontOptions(16)).outline().color(Color.ORANGE));
            mob.getLevel().hudManager.addElement(floatText);
        }
    }
}
