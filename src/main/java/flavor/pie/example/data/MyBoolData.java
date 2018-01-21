package flavor.pie.example.data;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;

public interface MyBoolData extends DataManipulator<MyBoolData, MyBoolData.Immutable> {

    Value<Boolean> enabled();

    interface Immutable extends ImmutableDataManipulator<Immutable, MyBoolData> {
        ImmutableValue<Boolean> enabled();
    }

    interface Builder extends DataManipulatorBuilder<MyBoolData, MyBoolData.Immutable> {

    }
}
