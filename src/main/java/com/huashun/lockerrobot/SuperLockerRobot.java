package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import com.huashun.lockerrobot.exception.TicketTypeNotMatch;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot {
    private List<Locker> managedLockers;

    public SuperLockerRobot(List<Locker> managedLockers) {
        this.managedLockers = managedLockers;
    }

    public Ticket store(Bag bag) {
        Optional<Locker> goalLocker = managedLockers.stream().max(Comparator.comparing(locker -> locker.getCapacityRate()));
        if (goalLocker.isPresent() && goalLocker.get().getCapacityRate() != 0) return goalLocker.get().store(bag);
        throw new LockerIsFullException();
    }

    public Bag fetchBagBy(Ticket ticket) {
        if (ticket.getLockerSizeType() != SizeType.L) {
            throw new TicketTypeNotMatch();
        }
        Optional<Locker> goalLocker = managedLockers.stream().filter(locker -> locker.contains(ticket)).findAny();
        if (goalLocker.isPresent()) {
            return goalLocker.get().fetchBagBy(ticket);
        }
        throw new InvalidTicketException();
    }
}
