package flavor.pie.example.data;

import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.text.format.TextColor;

import java.util.UUID;

public class MyKeys {
    private MyKeys() {}
    public static Key<Value<UUID>> STANDARD_ID = KeyFactory.makeSingleKey(TypeToken.of(UUID.class),
            new TypeToken<Value<UUID>>(){},
            DataQuery.of('.', "standard.id"),
            "example:standard_id",
            "Standard ID"
    );
    public static Key<Value<String>> STANDARD_NAME = KeyFactory.makeSingleKey(TypeToken.of(String.class),
            new TypeToken<Value<String>>(){},
            DataQuery.of('.', "standard.name"),
            "example:standard_name",
            "Standard Name"
    );
    public static Key<Value<Integer>> STANDARD_AMOUNT = KeyFactory.makeSingleKey(TypeToken.of(Integer.class),
            new TypeToken<Value<Integer>>(){},
            DataQuery.of('.', "standard.amount"),
            "example:standard_amount",
            "Standard Amount"
    );
    public static Key<Value<TextColor>> SINGULAR_COLOR = KeyFactory.makeSingleKey(TypeToken.of(TextColor.class),
            new TypeToken<Value<TextColor>>(){},
            DataQuery.of('.', "singular.color"),
            "example:singular_color",
            "Singular Color"
    );
    public static Key<Value<Boolean>> BOOL_ENABLED = KeyFactory.makeSingleKey(TypeToken.of(Boolean.class),
            new TypeToken<Value<Boolean>>(){},
            DataQuery.of('.', "bool.enabled"),
            "example:bool_enabled",
            "Bool Enabled"
    );
}
