package flavor.pie.example.data;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;
import java.util.UUID;

// Example of complex data
// Example of multi-class layout
public class MyStandardData extends AbstractData<MyStandardData, MyImmutableStandardData> {
    private String name;
    private int amount;
    private UUID id;

    MyStandardData(String name, int amount, UUID id) {
        this.name = name;
        this.amount = amount;
        this.id = id;
        // you must call this!
        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(MyKeys.STANDARD_ID, () -> this.id);
        registerFieldGetter(MyKeys.STANDARD_AMOUNT, () -> this.amount);
        registerFieldGetter(MyKeys.STANDARD_NAME, () -> this.name);
        registerFieldSetter(MyKeys.STANDARD_ID, id -> this.id = id);
        registerFieldSetter(MyKeys.STANDARD_AMOUNT, amount -> this.amount = amount);
        registerFieldSetter(MyKeys.STANDARD_NAME, name -> this.name = name);
        registerKeyValue(MyKeys.STANDARD_ID, this::id);
        registerKeyValue(MyKeys.STANDARD_AMOUNT, this::amount);
        registerKeyValue(MyKeys.STANDARD_NAME, this::name);
    }

    public Value<String> name() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_NAME, name);
    }

    public Value<Integer> amount() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_AMOUNT, amount);
    }

    public Value<UUID> id() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.STANDARD_ID, id);
    }

    @Override
    public Optional<MyStandardData> fill(DataHolder dataHolder, MergeFunction overlap) {
        Optional<MyStandardData> otherData_ = dataHolder.get(MyStandardData.class);
        if (otherData_.isPresent()) {
            MyStandardData otherData = otherData_.get();
            MyStandardData finalData = overlap.merge(this, otherData);
            this.name = finalData.name;
            this.id = finalData.id;
            this.amount = finalData.amount;
        }
        return Optional.of(this);
    }

    // the double method isn't strictly necessary but makes implementing the builder easier
    @Override
    public Optional<MyStandardData> from(DataContainer container) {
        return from((DataView) container);
    }

    public Optional<MyStandardData> from(DataView view) {
        if (view.contains(MyKeys.STANDARD_AMOUNT.getQuery()) && view.contains(MyKeys.STANDARD_ID.getQuery()) && view.contains(MyKeys.STANDARD_NAME.getQuery())) {
            this.id = view.getObject(MyKeys.STANDARD_ID.getQuery(), UUID.class).get();
            this.amount = view.getInt(MyKeys.STANDARD_AMOUNT.getQuery()).get();
            this.name = view.getString(MyKeys.STANDARD_NAME.getQuery()).get();
            return Optional.of(this);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public MyStandardData copy() {
        return new MyStandardData(this.name, this.amount, this.id);
    }

    @Override
    public MyImmutableStandardData asImmutable() {
        return new MyImmutableStandardData(this.name, this.amount, this.id);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    // IMPORTANT this is what causes your data to be written to NBT
    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(MyKeys.STANDARD_AMOUNT.getQuery(), this.amount)
                .set(MyKeys.STANDARD_NAME.getQuery(), this.amount)
                .set(MyKeys.STANDARD_ID.getQuery(), this.id);
    }
}
