package pro.gravit.launcher.serialize.stream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pro.gravit.launcher.LauncherAPI;
import pro.gravit.launcher.serialize.HInput;
import pro.gravit.launcher.serialize.HOutput;
import pro.gravit.launcher.serialize.stream.EnumSerializer.Itf;
import pro.gravit.utils.helper.VerifyHelper;

public final class EnumSerializer<E extends Enum<?> & Itf> {
    @FunctionalInterface
    public interface Itf {
        @LauncherAPI
        int getNumber();
    }

    @LauncherAPI
    public static void write(HOutput output, Itf itf) throws IOException {
        output.writeVarInt(itf.getNumber());
    }

    private final Map<Integer, E> map = new HashMap<>(16);

    @LauncherAPI
    public EnumSerializer(Class<E> clazz) {
        for (E e : clazz.getEnumConstants())
            VerifyHelper.putIfAbsent(map, e.getNumber(), e, "Duplicate number for enum constant " + e.name());
    }

    @LauncherAPI
    public E read(HInput input) throws IOException {
        int n = input.readVarInt();
        return VerifyHelper.getMapValue(map, n, "Unknown enum number: " + n);
    }
}
