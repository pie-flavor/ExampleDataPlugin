package flavor.pie.example.data;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import java.util.UUID;

public class MyImmutableStandardData extends AbstractImmutableData<MyImmutableStandardData, MyStandardData> {
    private String name;
    private int amount;
    private UUID id;

    public MyImmutableStandardData(String name, int amount, UUID id) {
        this.name = name;
        this.amount = amount;
        this.id = id;
        registerGetters();
    }

    @Override
    protected void registerGetters() {
        registerFieldGetter(MyKeys.STANDARD_ID, () -> this.id);
        registerFieldGetter(MyKeys.STANDARD_NAME, () -> this.name);
        registerFieldGetter(MyKeys.STANDARD_AMOUNT, () -> this.amount);
        registerKeyValue(MyKeys.STANDARD_ID, this::id);
        registerKeyValue(MyKeys.STANDARD_NAME, this::name);
        registerKeyValue(MyKeys.STANDARD_AMOUNT, this::amount);
    }

    public ImmutableValue<Integer> amount() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_AMOUNT, amount).asImmutable();
    }

    public ImmutableValue<String> name() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_NAME, name).asImmutable();
    }

    public ImmutableValue<UUID> id() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_ID, id).asImmutable();
    }

    @Override
    public MyStandardData asMutable() {
        return new MyStandardData(name, amount, id);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer().set(MyKeys.STANDARD_AMOUNT.getQuery(), this.amount)
                .set(MyKeys.STANDARD_NAME.getQuery(), this.amount)
                .set(MyKeys.STANDARD_ID.getQuery(), this.id);
    }
}
