package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.LockerIsFullException;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot {
    private List<Locker> managedLockers;

    public PrimaryLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        Optional<Locker> goalLocker = managedLockers.stream().filter(Locker::canStoreBag).findFirst();
        if (goalLocker.isPresent()) {
            return goalLocker.get().store(bag);
        }
        throw new LockerIsFullException();
    }

    public Bag fetchBagBy(Ticket ticket) {
        return managedLockers.stream().filter(locker -> locker.contains(ticket)).findAny().get().fetchBagBy(ticket);
    }
}
