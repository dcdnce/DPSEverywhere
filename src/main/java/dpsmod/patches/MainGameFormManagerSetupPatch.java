package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.window.GameWindow;
import necesse.engine.window.WindowManager;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import net.bytebuddy.asm.Advice;

import java.awt.*;

@ModMethodPatch(target = MainGameFormManager.class, name = "setup", arguments = {})
public class MainGameFormManagerSetupPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This MainGameFormManager mainGameFormManager) {
        DPSMod.mainGameFormManager = mainGameFormManager;
        DPSMod.form = (Form) mainGameFormManager.addComponent(new Form("dpsmod", 100, 25));
        DPSMod.form.setPosition(WindowManager.getWindow().getHudWidth() - DPSMod.form.getWidth() - 10, mainGameFormManager.minimap.getHeight() + 10);
        DPSMod.formLabel = new FormLocalLabel(DPSMod.staticMessage, DPSMod.smallFontOptions, 0, 50, 0);
        DPSMod.form.addComponent(DPSMod.formLabel);
    }
}
