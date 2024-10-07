package dpsmod;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.localization.message.StaticMessage;
import necesse.engine.modLoader.annotations.ModEntry;
import dpsmod.patches.*;
import necesse.engine.util.DPSTracker;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameFont.GameFontHandler;
import necesse.gfx.gameFont.LoadGameFont;

import java.awt.*;

@ModEntry
public class DPSMod {

    public static DPSTracker playerDPSTracker = new DPSTracker();
    public static final FontOptions fontOptions = (new FontOptions(12)).color(Color.WHITE).forcePixelFont();
    public static Form form = null;
    public static FormLocalLabel formLabel = null;
    public static final StaticMessage staticMessage = new StaticMessage("");
    public static MainGameFormManager mainGameFormManager = null;

    public void init() {
        System.out.println("Hello world from DPSmod!");
        playerDPSTracker.DPS_TRACKING_TIME = 1001;
    }

    public static boolean shouldHandleMob(Mob mob, Attacker attacker) {
        return attacker.getAttackOwner() != null && attacker.getAttackOwner().isPlayer;
    }

}
