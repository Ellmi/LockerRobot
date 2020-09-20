package com.huashun.lockerrobot;

import org.junit.jupiter.api.Test;

import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockerTest {

    @Test
    public void should_return_s_ticket_when_store_bag_given_small_bag_and_s_locker() {
        Locker locker = new Locker(S, 1);

        Ticket ticket = locker.store(new Bag(S));

        assertEquals(S, ticket.getLockerSizeType());
    }


}
