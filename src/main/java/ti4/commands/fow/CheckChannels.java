package ti4.commands.fow;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.helpers.FoWHelper;
import ti4.map.Player;
import ti4.message.MessageHelper;

class CheckChannels extends GameStateSubcommand {

    public CheckChannels() {
        super(Constants.CHECK_CHANNELS, "Ping each channel that is set up", true, false);
    }

    public void execute(SlashCommandInteractionEvent event) {
        if (FoWHelper.isPrivateGame(event)) {
            MessageHelper.replyToMessage(event, "This command is not available in fog of war private channels.");
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("**__Currently set channels:__**\n>>> ");
        boolean first = true;
        for (Player player : getGame().getPlayers().values()) {
            if (!first) output.append("\n");
            first = false;

            output.append(player.getUserName()).append(" - ");
            MessageChannel channel = player.getPrivateChannel();
            if (channel == null) {
                output.append("No private channel");
            } else {
                output.append(channel.getAsMention());
                if (!((TextChannel)channel).getMembers().contains(player.getMember())) {
                    output.append(" - No access");
                }
            }
            output.append(" - ");

            Role roleForCommunity = player.getRoleForCommunity();
            if (roleForCommunity == null) {
                output.append("No community role");
            } else {
                output.append(roleForCommunity.getAsMention());
            }
        }

        MessageHelper.replyToMessage(event, output.toString());
    }
}
