package com.nixsolutions.laba8.task2;

import java.io.Serializable;

import interfaces.task8.CyclicItem;

public class CyclicItemImpl implements CyclicItem, Serializable {
    private static final long serialVersionUID = -5000313665724170249L;
    private transient Object temp;
    private Object value;

    private CyclicItem nextItem = this;

    @Override
    public Object getTemp() {
        return temp;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public CyclicItem nextItem() {
        return nextItem;
    }

    @Override
    public void setNextItem(CyclicItem nextCyclic) {
        if (nextCyclic == null)
            nextItem = this;
        nextItem = nextCyclic;
    }

    @Override
    public void setTemp(Object temp) {
        this.temp = temp;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CyclicItemImpl other = (CyclicItemImpl) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CyclicItemImpl [temp=" + temp + ", value=" + value + "]";
    }

}
