package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import com.huashun.lockerrobot.exception.TicketTypeNotMatch;

import java.util.HashMap;

public class Locker implements Storable{
    private SizeType sizeType;
    private int capacity;
    private HashMap<Ticket, Bag> ticketBagMap;

    public Locker(SizeType sizeType, int capacity) {
        this.sizeType = sizeType;
        this.capacity = capacity;
        this.ticketBagMap = new HashMap<>();
    }

    public boolean canStoreBag() {
        return capacity > ticketBagMap.size();
    }

    public double getCapacityRate() {
        return (capacity - ticketBagMap.size()) / (double) capacity;
    }

    public Ticket store(Bag bag) {
        if (!canStoreBag()) throw new LockerIsFullException();
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

    public boolean contains(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }
}
