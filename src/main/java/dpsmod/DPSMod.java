package dpsmod;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.modLoader.annotations.ModEntry;
import dpsmod.patches.*;
import necesse.engine.util.DPSTracker;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameFont.GameFontHandler;
import necesse.gfx.gameFont.LoadGameFont;

import java.awt.*;

@ModEntry
public class DPSMod {

    public static DPSTracker playerDPSTracker = new DPSTracker();
    public static GameFontHandler font = null;
    public static final FontOptions smallFontOptions = (new FontOptions(12)).color(Color.WHITE);

    public void init() {
        System.out.println("Hello world from DPSmod!");

        font = new GameFontHandler();
        font.regularFonts.add(new LoadGameFont(), true);
    }

    public static boolean shouldHandleMob(Mob mob, Attacker attacker) {
        return mob.isHostile && attacker.getAttackOwner().isPlayer;
    }

}
