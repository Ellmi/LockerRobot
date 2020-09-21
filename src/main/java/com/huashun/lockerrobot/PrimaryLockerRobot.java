package com.huashun.lockerrobot;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        return managedLockers.stream().filter(locker -> locker.getCapacity() > 0).findFirst().get().store(bag);
    }
}
