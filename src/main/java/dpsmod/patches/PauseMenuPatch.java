package dpsmod.patches;

import dpsmod.DPSMod;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.forms.presets.PauseMenuForm;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = PauseMenuForm.class, name = "setHidden", arguments = {boolean.class})
public class PauseMenuPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This PauseMenuForm pauseMenuForm, @Advice.Argument(0) boolean hidden) {
        if (DPSMod.form != null)
            DPSMod.form.setHidden(!hidden);
    }
}
