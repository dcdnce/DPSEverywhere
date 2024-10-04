package dpsmod;

import necesse.engine.localization.message.GameMessage;
import necesse.gfx.fairType.FairType;
import necesse.gfx.fairType.TypeParsers;
import necesse.gfx.fairType.parsers.TypeParser;
import necesse.gfx.forms.components.FormFairTypeLabel;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.forms.presets.sidebar.SidebarForm;
import necesse.gfx.gameFont.FontOptions;

public class DPSSidebarForm extends SidebarForm {
    private final FormFairTypeLabel objectiveText;
    private GameMessage objective;

    public DPSSidebarForm() {
        super("dpssidebar", 240, 10);
        this.objective = null;
        this.addComponent(new FormLocalLabel("ui", "tutoriallabel", new FontOptions(20), -1, 10, 8));
        FontOptions options = new FontOptions(16);
        this.objectiveText = (FormFairTypeLabel)this.addComponent((new FormFairTypeLabel("", 10, 40)).setFontOptions(options).setMaxWidth(this.getWidth() - 20).setTextAlign(FairType.TextAlign.LEFT).setParsers(new TypeParser[]{TypeParsers.GAME_COLOR, TypeParsers.InputIcon(options), TypeParsers.ItemIcon(16)}));
        this.objectiveText.onUpdated((e) -> {
            this.updateHeight();
        });
        this.setContent(this.objective);
    }

    public void setContent(GameMessage objective) {
        if (this.objective == null || !this.objective.equals(objective)) {
            this.objective = objective;
            this.objectiveText.setText(objective);

            this.updateHeight();
        }
    }

    public void updateHeight() {
        int height = 60;
        height += this.objectiveText.getBoundingBox().height;

        this.setHeight(height);
    }
}