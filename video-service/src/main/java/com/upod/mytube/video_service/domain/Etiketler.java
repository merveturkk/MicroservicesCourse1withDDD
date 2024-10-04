package com.upod.mytube.video_service.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// Etiketler Değer Nesnesi
public class Etiketler {
    private final Set<String> etiketler;

    public Etiketler(Set<String> etiketler) {
        if (etiketler == null) {
            throw new IllegalArgumentException("Etiketler boş olamaz");
        }
        this.etiketler = new HashSet<>(etiketler);
    }

    public Set<String> getEtiketler() {
        return Collections.unmodifiableSet(etiketler);
    }
}
