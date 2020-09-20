package com.huashun.lockerrobot;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_return_correct_locker_type_ticket_when_store_bag_given_different_size_bag_and_locker(SizeType sizeType) {
        Locker locker = new Locker(sizeType, 1);

        Ticket ticket = locker.store(new Bag(sizeType));

        assertEquals(sizeType, ticket.getLockerSizeType());
    }

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_throw_LockerIsFullException_when_store_bag_given_no_free_capacity_locker(SizeType sizeType) {
        Locker locker = new Locker(sizeType, 1);
        locker.store(new Bag(sizeType));

        assertThrows(LockerIsFullException.class, () -> locker.store(new Bag(sizeType)));

    }


}
