package xyz.hydrion.care.domain;

import java.util.List;

public class User {
    private String id;
    private String name;
    List<ElderDev> associatedDev;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElderDev> getAssociatedDev() {
        return associatedDev;
    }

    public void setAssociatedDev(List<ElderDev> associatedDev) {
        this.associatedDev = associatedDev;
    }
}
