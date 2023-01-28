package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.service.IMetadata;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@UtilityClass
public class MetadataHolder {
    private static final Map<UUID, List<ThreadLocal<IMetadata<Object>>>> localValues = new HashMap<>();

    public static void setData(UUID id, IMetadata<Object> metadata) {
        var values = localValues.get(id);
        values.forEach(value -> value.set(metadata));
    }

    public static List<ThreadLocal<IMetadata<Object>>> getData(UUID id) {
        return localValues.get(id);
    }
}
