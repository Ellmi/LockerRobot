package com.huashun.lockerrobot;

public interface Storable {
    Ticket store(Bag bag);

    Bag fetchBagBy(Ticket ticket);
}
