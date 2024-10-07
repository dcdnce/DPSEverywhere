package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.localization.Localization;
import necesse.engine.localization.message.GameMessage;
import necesse.engine.localization.message.StaticMessage;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.util.GameUtils;
import necesse.engine.window.GameWindow;
import necesse.engine.window.WindowManager;
import necesse.entity.mobs.Mob;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameFont.GameFontHandler;
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

            String text = "dps: " + GameUtils.formatNumber((double) dps);

//            FloatTextFade floatText = new FloatTextFade(mob.getX() - 16, mob.getY() - 16, text, (new FontOptions(16)).outline().color(Color.ORANGE));
//            mob.getLevel().hudManager.addElement(floatText);

            DPSMod.staticMessage.setMessage(text);
            DPSMod.formLabel.setText(DPSMod.staticMessage);
        }
    }
}
