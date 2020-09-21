package com.huashun.lockerrobot;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        return managedLockers.get(0).store(bag);
    }
}
