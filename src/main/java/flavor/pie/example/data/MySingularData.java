package flavor.pie.example.data;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableSingleData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractSingleData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

public class MySingularData extends AbstractSingleData<TextColor, MySingularData, MySingularData.Immutable> {
    MySingularData(TextColor color) {
        super(color, MyKeys.SINGULAR_COLOR);
    }
    @Override
    protected Value<?> getValueGetter() {
        return Sponge.getRegistry().getValueFactory().createValue(MyKeys.SINGULAR_COLOR, getValue());
    }

    @Override
    public Optional<MySingularData> fill(DataHolder dataHolder, MergeFunction overlap) {
        Optional<MySingularData> data_ = dataHolder.get(MySingularData.class);
        if (data_.isPresent()) {
            MySingularData data = data_.get();
            MySingularData finalData = overlap.merge(this, data);
            setValue(finalData.getValue());
        }
        return Optional.of(this);
    }

    @Override
    public Optional<MySingularData> from(DataContainer container) {
        return Optional.of(this);
    }

    @Override
    public MySingularData copy() {
        return new MySingularData(getValue());
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(getValue());
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    public static class Immutable extends AbstractImmutableSingleData<TextColor, Immutable, MySingularData> {
        Immutable(TextColor color) {
            super(color, MyKeys.SINGULAR_COLOR);
        }

        @Override
        protected ImmutableValue<?> getValueGetter() {
            return Sponge.getRegistry().getValueFactory().createValue(MyKeys.SINGULAR_COLOR, getValue()).asImmutable();
        }

        @Override
        public MySingularData asMutable() {
            return new MySingularData(getValue());
        }

        @Override
        public int getContentVersion() {
            return 1;
        }
    }
    public static class Builder extends AbstractDataBuilder<MySingularData> implements DataManipulatorBuilder<MySingularData, Immutable> {
        Builder() {
            super(MySingularData.class, 1);
        }

        @Override
        public MySingularData create() {
            return new MySingularData(TextColors.WHITE);
        }

        @Override
        public Optional<MySingularData> createFrom(DataHolder dataHolder) {
            return create().fill(dataHolder);
        }

        @Override
        protected Optional<MySingularData> buildContent(DataView container) throws InvalidDataException {
            return Optional.of(create());
        }
    }
}
