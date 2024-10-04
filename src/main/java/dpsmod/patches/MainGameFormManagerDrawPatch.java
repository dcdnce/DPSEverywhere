package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.MainGameFormManager;
import net.bytebuddy.asm.Advice;


@ModMethodPatch(target = MainGameFormManager.class, name = "draw", arguments = {TickManager.class, PlayerMob.class})
public class MainGameFormManagerDrawPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This MainGameFormManager manager, @Advice.Argument(0) TickManager tickManager, @Advice.Argument(1) PlayerMob perspective) {
        DPSMod.dpsSidebarForm.draw(tickManager, perspective, null);
    }
}
