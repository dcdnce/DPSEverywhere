package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.gfx.forms.MainGameFormManager;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = MainGameFormManager.class, name = "setup", arguments = {})
public class MainGameFormManagerSetupPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This MainGameFormManager manager) {
        DPSMod.mainGameFormManager = manager;
        manager.addSidebar(DPSMod.dpsSidebarForm);
    }
}