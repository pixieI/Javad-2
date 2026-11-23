Answers
Q1. Why did we have to override hashCode() and equals()?

By default, Object compares identity (memory address), not content. Every time the program writes new Seat('B', 5), it creates a new object, so Java considers it a different key from any earlier Seat('B', 5).

HashMap uses both methods when it looks up a key:

hashCode() decides which bucket to look in.
equals() checks whether the key in that bucket is the same key.

Overriding both makes two Seat objects with the same row and number count as the same key, so get, containsKey, remove and putIfAbsent all work correctly.

Q2. Delete hashCode() but keep equals(). What happens and why?

Result: the program behaves wrongly again, exactly like Step 2: null for C3, false for A2, Youssef is booked on B5, and the map holds 7 seats instead of 4.

Why: the default hashCode() is based on object identity, so two equal seats usually get different hash codes and land in different buckets. HashMap never even calls equals() on them, because it is looking in the wrong bucket.

Rule: if a.equals(b) is true, then a.hashCode() == b.hashCode() must also be true.

Q3. Why are row and number final? What could go wrong if the number of a booked seat could be changed after it was put in the map?

final makes the Seat immutable. Its hash code is calculated from row and number, and HashMap stores the key in a bucket based on the hash code at the time of insertion.

If the number changed after insertion:

The hash code would change, but the key would stay in the old bucket.
get() and containsKey() would look in the new bucket and fail to find it.
The booking would be effectively lost inside the map: it still exists and counts in size(), but it can no longer be found or removed.

Using immutable keys avoids this whole class of bugs.

Q4. Change equals(Object o) to equals(Seat o). What happens? Then also remove @Override. What happens now?

With @Override: compile error.

error: method does not override or implement a method from a supertype

Object.equals takes an Object, so equals(Seat) is not an override. @Override catches this mistake at compile time.

Without @Override: it compiles, but the program is wrong again (null for C3, 7 seats booked, and so on).

equals(Seat) is an overload, not an override. It is a different method that sits next to the inherited equals(Object). HashMap calls equals(Object), which is still the default identity comparison, so seats are never considered equal. There is no warning, which is why @Override is important.

BONUS. Replace the whole Seat class with record Seat(char row, int number) {}. What happens and why?

Result: the program works correctly. The output has the same values (4 seats booked, the same chart). The only difference is how seats print inside the map:

{Seat[row=A, number=1]=Ali, Seat[row=C, number=3]=Mona, ...}

instead of {A1=Ali, ...}.

Why: a record automatically generates:

equals() that compares all fields,
hashCode() computed from all fields,
toString() in the form Seat[row=A, number=1],
private final fields, so records are immutable by design.
