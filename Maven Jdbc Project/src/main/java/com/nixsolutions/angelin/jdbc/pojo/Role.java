package com.nixsolutions.angelin.jdbc.pojo;

public class Role {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Role))
            return false;
        Role role = (Role) obj;
        if (!name.equals(role.name))
            return false;
        return id == role.id;
    }

    @Override
    public String toString() {
        return "Role - " + name + " (id - " + id + ")";
    }
}
