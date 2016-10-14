package flavor.pie.example.data;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;
import java.util.UUID;

public class MyStandardDataBuilder extends AbstractDataBuilder<MyStandardData> implements DataManipulatorBuilder<MyStandardData, MyImmutableStandardData> {
    MyStandardDataBuilder() {
        super(MyStandardData.class, 1);
    }

    @Override
    public MyStandardData create() {
        return new MyStandardData("", 1, UUID.randomUUID());
    }

    @Override
    public Optional<MyStandardData> createFrom(DataHolder dataHolder) {
        return create().fill(dataHolder);
    }

    @Override
    protected Optional<MyStandardData> buildContent(DataView container) throws InvalidDataException {
        return create().from(container);
    }
}
