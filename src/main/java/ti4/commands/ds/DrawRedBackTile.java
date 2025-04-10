package ti4.commands.ds;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.helpers.DiscordantStarsHelper;

class DrawRedBackTile extends GameStateSubcommand {

    public DrawRedBackTile() {
        super(Constants.DRAW_RED_BACK_TILE, "Draw a random red back tile (for Dane's mystery tweet)", true, true);
        addOptions(new OptionData(OptionType.INTEGER, Constants.COUNT, "How many to draw? Default: 1"));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int count = event.getOption(Constants.COUNT, 1, OptionMapping::getAsInt);
        DiscordantStarsHelper.drawRedBackTiles(event, getGame(), getPlayer(), count);
    }
}
