package org.gurikin;

public class Parent {
    public Child child;

    public Parent(Child child) {
        this.child = child;
    }

    public Child getChild() {
        return child;
    }
}
