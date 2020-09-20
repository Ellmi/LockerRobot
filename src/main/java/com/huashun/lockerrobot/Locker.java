package com.huashun.lockerrobot;

import java.util.HashMap;

public class Locker {
    private SizeType sizeType;
    private int capacity;
    private HashMap<Ticket, Bag> ticketBagMap;

    public Locker(SizeType sizeType, int capacity) {
        this.sizeType = sizeType;
        this.capacity = capacity;
        this.ticketBagMap = new HashMap<>();
    }

    public Ticket store(Bag bag) {
        if (capacity == 0) throw new LockerIsFullException();
        capacity--;
        Ticket ticket = new Ticket(sizeType);
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag fetchBagBy(Ticket ticket) {
        return ticketBagMap.get(ticket);
    }
}
