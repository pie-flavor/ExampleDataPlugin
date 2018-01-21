package flavor.pie.example.data;

import com.google.inject.Inject;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.data.Has;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

@Plugin(id = "exampledataplugin", authors = "pie_flavor")
public class ExampleDataPlugin {
    @Inject
    PluginContainer container;
    @Listener
    public void preInit(GamePreInitializationEvent e) {
        MyKeys.dummy();
        DataRegistration.builder()
                .dataName("My Standard Data")
                .manipulatorId("standard_data") // prefix is added for you and you can't add it yourself
                .dataClass(MyStandardData.class)
                .immutableClass(MyImmutableStandardData.class)
                .builder(new MyStandardDataBuilder())
                .buildAndRegister(container);
        DataRegistration.builder()
                .dataName("My Singular Data")
                .manipulatorId("singular_data")
                .dataClass(MySingularData.class)
                .immutableClass(MySingularData.Immutable.class)
                .builder(new MySingularData.Builder())
                .buildAndRegister(container);
        DataRegistration.builder()
                .dataName("My Bool Data")
                .manipulatorId("bool_data")
                .dataClass(MyBoolData.class)
                .dataImplementation(MyBoolDataImpl.class)
                .immutableClass(MyBoolData.Immutable.class)
                .immutableImplementation(MyBoolDataImpl.Immutable.class)
                .builder(new MyBoolDataImpl.Builder())
                .buildAndRegister(container);
        Sponge.getDataManager().registerContentUpdater(MyBoolDataImpl.class, new MyBoolDataImpl.BoolEnabled1To2Updater());
    }
    @Listener
    public void init(GameInitializationEvent e) {
        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .arguments(GenericArguments.catalogedElement(Text.of("color"), TextColor.class))
                .executor(this::setcolor)
                .build(), "setcolor");
    }

    // keys don't work until you add the manipulator
    @Listener
    public void onJoin(ClientConnectionEvent.Join e,
                       @Getter("getTargetEntity") @Has(value = MySingularData.class, inverse = true) Player player) {
        player.offer(player.getOrCreate(MySingularData.class).get());
    }

    private CommandResult setcolor(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            ((Player) src).offer(MyKeys.SINGULAR_COLOR, args.<TextColor>getOne("color").get());
            return CommandResult.success();
        }
        throw new CommandException(Text.of(TextColors.RED, "Must be a player!"));
    }
}
