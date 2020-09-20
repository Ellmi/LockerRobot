package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import com.huashun.lockerrobot.exception.TicketTypeNotMatch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.huashun.lockerrobot.SizeType.M;
import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

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

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_return_correct_bag_when_fetch_bag_given_different_locker_type_ticket_and_locker(SizeType sizeType) {
        Locker locker = new Locker(sizeType, 1);
        Bag storedBag = new Bag(sizeType);
        Ticket ticket = locker.store(storedBag);

        Bag fetchedBag = locker.fetchBagBy(ticket);

        assertSame(storedBag, fetchedBag);
    }

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_throw_InvalidTicketException_when_fetch_bag_given_invalid_ticket(SizeType sizeType) {
        Locker locker = new Locker(sizeType, 1);
        locker.store(new Bag(sizeType));

        assertThrows(InvalidTicketException.class, () -> locker.fetchBagBy(new Ticket(sizeType)));

    }

    @ParameterizedTest
    @EnumSource(value = SizeType.class, names = {"M", "L"})
    public void should_throw_TicketTypeNotMatch_when_s_locker_fetch_bag_given_not_s_locker_type_ticket(SizeType sizeType) {
        Locker locker = new Locker(S, 1);
        locker.store(new Bag(S));

        assertThrows(TicketTypeNotMatch.class, () -> locker.fetchBagBy(new Ticket(sizeType)));

    }

    @ParameterizedTest
    @EnumSource(value = SizeType.class, names = {"S", "L"})
    public void should_throw_TicketTypeNotMatch_when_m_locker_fetch_bag_given_not_m_locker_type_ticket(SizeType sizeType) {
        Locker locker = new Locker(M, 1);
        locker.store(new Bag(M));

        assertThrows(TicketTypeNotMatch.class, () -> locker.fetchBagBy(new Ticket(sizeType)));

    }


}
