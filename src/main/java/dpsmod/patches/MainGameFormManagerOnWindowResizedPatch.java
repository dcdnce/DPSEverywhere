package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.window.GameWindow;
import necesse.engine.window.WindowManager;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = MainGameFormManager.class, name = "onWindowResized", arguments = {GameWindow.class})
public class MainGameFormManagerOnWindowResizedPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This MainGameFormManager mainGameFormManager) {
        if (DPSMod.form != null)
            DPSMod.form.setPosition(WindowManager.getWindow().getHudWidth() - DPSMod.form.getWidth() - 10, mainGameFormManager.minimap.getHeight() + 20);
    }
}
