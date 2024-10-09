package dpsmod;

import necesse.engine.localization.message.StaticMessage;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.util.DPSTracker;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.gameFont.FontOptions;

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
        playerDPSTracker.DPS_TRACKING_TIME = 1000;
    }

    public static boolean shouldHandleMob(Attacker attacker) {
        Mob attackerMob = attacker.getAttackOwner();

        if (attackerMob == null || !attackerMob.isPlayer)
            return false;

        PlayerMob playerAttacker = (PlayerMob) attacker.getAttackOwner();
        PlayerMob clientPlayer = attackerMob.getClient().getPlayer();

        return playerAttacker.getUniqueID() == clientPlayer.getUniqueID();
    }

}
