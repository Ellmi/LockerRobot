package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import com.huashun.lockerrobot.exception.TicketTypeNotMatch;

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

    public int getCapacity() {
        return capacity;
    }

    public Ticket store(Bag bag) {
        if (capacity == 0) throw new LockerIsFullException();
        capacity--;
        Ticket ticket = new Ticket(sizeType);
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag fetchBagBy(Ticket ticket) {
        if (!ticket.getLockerSizeType().equals(sizeType)) throw new TicketTypeNotMatch();
        Bag bag = ticketBagMap.remove(ticket);
        if (bag == null) throw new InvalidTicketException();
        return bag;
    }
}
