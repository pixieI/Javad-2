bookings.put(new Seat('A', 1), "Ali");
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");
        System.out.println("Bookings:           " + bookings);

        String c3Owner = bookings.get(new Seat('C', 3));
        System.out.println("Who booked C3:      " + c3Owner);

        System.out.println("Is A2 taken:        " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken:        " + bookings.containsKey(new Seat('E', 6)));

        String b5Existing = bookings.putIfAbsent(new Seat('B', 5), "Youssef");
        if (b5Existing == null) {
            System.out.println("Youssef -> B5:      booked");
        } else {
            System.out.println("Youssef -> B5:      refused, taken by " + b5Existing);
        }

        bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), "Ali");
        System.out.println("After Ali moved:    " + bookings);

        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));
        Seat e1 = new Seat('E', 1);
        if (broken.contains(e1)) {
            System.out.println("Hana -> E1:         refused, seat is broken");
        } else {
            bookings.put(e1, "Hana");
            System.out.println("Hana -> E1:         booked");
        }

        System.out.println("Seats booked:       " + bookings.size());