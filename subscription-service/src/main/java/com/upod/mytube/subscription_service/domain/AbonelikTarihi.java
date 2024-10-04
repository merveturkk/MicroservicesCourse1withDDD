package com.upod.mytube.subscription_service.domain;

// Abonelik Tarihi Değer Nesnesi

import java.time.Instant;

public class AbonelikTarihi {
    private final Instant deger;

    public AbonelikTarihi(Instant deger) {
        if (deger == null) {
            throw new IllegalArgumentException("Abonelik tarihi boş olamaz");
        }
        this.deger = deger;
    }

    public Instant getDeger() {
        return deger;
    }
}

