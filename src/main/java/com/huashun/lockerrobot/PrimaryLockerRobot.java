package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import com.huashun.lockerrobot.exception.TicketTypeNotMatch;

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
        if (ticket.getLockerSizeType() != SizeType.M) {
            throw new TicketTypeNotMatch();
        }
        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.contains(ticket)).findAny();
        if (goalLocker.isPresent()) {
            return goalLocker.get().fetchBagBy(ticket);
        }
        throw new InvalidTicketException();
    }
}
