package com.huashun.lockerrobot;

import com.huashun.lockerrobot.exception.InvalidTicketException;
import com.huashun.lockerrobot.exception.LockerIsFullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static com.huashun.lockerrobot.SizeType.L;
import static com.huashun.lockerrobot.SizeType.M;
import static com.huashun.lockerrobot.SizeType.S;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LockerRobotManagerTest {

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_return_correct_locker_type_ticket_when_store_different_size_bag_given_robot_manage_1_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot_which_all_free(SizeType sizeType) {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();

        Ticket ticket = lockerRobotManager.store(new Bag(sizeType));

        assertEquals(sizeType, ticket.getLockerSizeType());
    }

    private LockerRobotManager buildLockerRobotManager() {
        return new LockerRobotManager(List.of(new Locker(S, 1), new PrimaryLockerRobot(List.of(new Locker(M, 1))), new SuperLockerRobot(List.of(new Locker(L, 1)))));
    }

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_throw_LockerIsFullException_when_store_different_size_bag_given_robot_manage_1_s_locker_and_1_primaryLockerRobot_and_1_superLockerRobot_which_all_full(SizeType sizeType) {

        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        lockerRobotManager.store(new Bag(sizeType));

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.store(new Bag(sizeType)));
    }

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_return_correct_bag_when_fetch_bag_given_valid_different_locker_size_ticket(SizeType sizeType) {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        Bag storedBag = new Bag(sizeType);
        Ticket ticket = lockerRobotManager.store(storedBag);

        Bag fetchedBag = lockerRobotManager.fetchBagBy(ticket);

        assertSame(storedBag, fetchedBag);
    }

    @ParameterizedTest
    @EnumSource(SizeType.class)
    public void should_throw_InvalidTicketException_when_fetch_bag_given_invalid_different_locker_size_ticket(SizeType sizeType) {
        LockerRobotManager lockerRobotManager = buildLockerRobotManager();
        Bag storedBag = new Bag(sizeType);
        lockerRobotManager.store(storedBag);

        assertThrows(InvalidTicketException.class, () -> lockerRobotManager.fetchBagBy(new Ticket(sizeType)));
    }

}
