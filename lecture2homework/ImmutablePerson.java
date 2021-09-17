package lecture2homework;

/* What is immutable, Implement an immutable class (e.g. myDateTime)
*
* Immutable class in java means that once an object is created, we cannot change its content.
* In Java, all the wrapper classes (like Integer, Boolean, Byte, Short) and String class is immutable.
*
* An immutable class is good for caching purposes because you don’t have to worry about the value changes.
* Another benefit of immutable class is that it is inherently thread-safe,
* so you don’t have to worry about thread safety in case of multi-threaded environment.
*
* */

public final class ImmutablePerson {
    private final String name;
    private final String address;
    public ImmutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[ Person: name = " + name + ", address = " + address + " ]";
    }
}

