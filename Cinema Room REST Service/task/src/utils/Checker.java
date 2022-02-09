package utils;

import cinema.Seat;
import cinema.Ticket;

public class Checker {
    public static void check(Seat seat) {
        if (seat == null) {
            throw new NullPointerException("The number of a row or a column is out of bounds!");
        } else if (seat.isPurchased()) {
            throw new IllegalArgumentException("The ticket has been already purchased!");
        }
    }

    public static void check(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Wrong token!");
        }
    }

    public static void checkPassword(String password) throws IllegalAccessException {
        if (password == null || !password.equals("super_secret")) {
            throw new IllegalAccessException("The password is wrong!");
        }
    }
}
