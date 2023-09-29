package io.nirahtech.librairies.pdf;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public record Table(Map<String, Collection<Object>> array) {

    public final Collection<String> headers() {
        return Collections.unmodifiableCollection(this.array.keySet());
    }

    // public final Collection<String> raw(final int index) {
    //     return Collections.unmodifiableCollection(this.array.values());
    // }
    
}
