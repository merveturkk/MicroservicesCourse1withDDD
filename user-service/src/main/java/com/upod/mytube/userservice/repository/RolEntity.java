package com.upod.mytube.userservice.repository;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.upod.mytube.userservice.domain.Izin;
import com.upod.mytube.userservice.domain.Rol;

@Table(name = "ROLLER")
public class RolEntity {

    @Id
    private Long id;
    private String isim;
    private Set<Izin> izinler;

    public RolEntity(String isim, Set<Izin> izinler, Long id) {
        this.isim = isim;
        this.izinler = izinler;
        this.id = id;
    }

    public static RolEntity fromDomain(Rol rol) {
        RolEntity entity = new RolEntity(
                rol.getIsim(),
                rol.getIzinler(),
                rol.getId()
        );
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setRolId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Set<Izin> getIzinler() {
        return izinler;
    }

    public void setIzinler(Set<Izin> izinler) {
        this.izinler = izinler;
    }

}
