package flavor.pie.example.data;

import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;

@Plugin(id = "exampledataplugin", authors = "pie_flavor")
public class ExampleDataPlugin {
    @Inject
    Game game;
    @Listener
    public void preInit(GamePreInitializationEvent e) {
        game.getDataManager().register(MyStandardData.class, MyImmutableStandardData.class, new MyStandardDataBuilder());
        game.getDataManager().register(MySingularData.class, MySingularData.Immutable.class, new MySingularData.Builder());
        game.getDataManager().register(MyBoolData.class, MyBoolData.Immutable.class, new MyBoolData.Builder());
        game.getDataManager().registerContentUpdater(MyBoolData.class, new MyBoolData.BoolEnabled1To2Updater());
    }
    @Listener
    public void init(GameInitializationEvent e) {
        game.getCommandManager().register(this, CommandSpec.builder()
                .arguments(GenericArguments.catalogedElement(Text.of("color"), TextColor.class))
                .executor((src, args) -> {
                    if (src instanceof DataHolder) {
                        ((DataHolder) src).getOrCreate(MyStandardData.class).get().set(MyKeys.SINGULAR_COLOR, args.<TextColor>getOne("color").get());
                    }
                    return CommandResult.success();
                })
                .build(), "setcolor");
    }
}
