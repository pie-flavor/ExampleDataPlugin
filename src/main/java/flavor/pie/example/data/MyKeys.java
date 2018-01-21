package flavor.pie.example.data;

import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.util.TypeTokens;

import java.util.UUID;

public class MyKeys {
    private MyKeys() {}
    static void dummy() {} // invoke static constructor
    public static final Key<Value<UUID>> STANDARD_ID;
    public static final Key<Value<String>> STANDARD_NAME;
    public static final Key<Value<Integer>> STANDARD_AMOUNT;
    public static final Key<Value<TextColor>> SINGULAR_COLOR;
    public static final Key<Value<Boolean>> BOOL_ENABLED;

    static {
        STANDARD_ID = Key.builder()
                .type(TypeTokens.UUID_VALUE_TOKEN)
                .id("exampledataplugin:standard_id")
                .name("Standard ID")
                .query(DataQuery.of('.', "standard.name"))
                .build();
        STANDARD_NAME = Key.builder()
                .type(TypeTokens.STRING_VALUE_TOKEN)
                .id("exampledataplugin:standard_name")
                .name("Standard Name")
                .query(DataQuery.of('.', "standard.name"))
                .build();
        STANDARD_AMOUNT = Key.builder()
                .type(TypeTokens.INTEGER_VALUE_TOKEN)
                .id("exampledataplugin:standard_amount")
                .name("Standard Amount")
                .query(DataQuery.of('.', "standard.amount"))
                .build();
        SINGULAR_COLOR = Key.builder()
                .type(new TypeToken<Value<TextColor>>(){})
                .id("exampledataplugin:singular.color")
                .name("Singular Color")
                .query(DataQuery.of('.', "singular.color"))
                .build();
        BOOL_ENABLED = Key.builder()
                .type(TypeTokens.BOOLEAN_VALUE_TOKEN)
                .id("exampledataplugin:bool_enabled")
                .name("Bool Enabled")
                .query(DataQuery.of('.', "bool.enabled"))
                .build();
    }
}
