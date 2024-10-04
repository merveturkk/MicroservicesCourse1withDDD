package com.upod.mytube.userservice.domain;

import java.util.Collections;
import java.util.Set;

public class Rol {

    private final Long id;
    private final String isim;
    private final Set<Izin> izinler;

    public Rol(Long id, String isim, Set<Izin> izinler) {
        this.id = id;
        this.isim = isim;
        this.izinler = izinler;
    }

    public String getIsim() {
        return isim;
    }

    public Set<Izin> getIzinler() {
        return Collections.unmodifiableSet(izinler);
    }

    public Long getId() {
        return id;
    }

}
