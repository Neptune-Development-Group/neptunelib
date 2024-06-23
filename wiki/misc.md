# Miscellaneous 
Just random small things that I've added that are nice, but not worth having their own dedicated page or much documentation on them in general

## Delta Time Manager
There's a full javadoc setup for DeltaTimeManager, but here's a simplistic view of it's functions
```java
/**
     * Creates a delta time that'll be run after a certain amount of time
     * @param time_from_now Time from now, based on the provided Time Unit
     * @param time_unit The time unit to use for time_from_now
     * @param runnable The runnable to run
     */
    public static void queueTask(long time_from_now, TimeUnit time_unit, Runnable runnable){...}

    /**
     * Creates a delta time that'll be run after a certain amount of time.
     *
     * @param  id            the identifier for the delta time
     * @param  time_from_now the time from now, based on the provided TimeUnit
     * @param  time_unit     the time unit to use for time_from_now
     */
    public static void createDelta(Identifier id, long time_from_now, TimeUnit time_unit) {...}

    /**
     * Retrieves the delta time associated with the given identifier.
     *
     * @param  id  the identifier for which to retrieve the delta time
     * @return     the delta time in milliseconds
     */
    public static long getDelta(Identifier id) {...}

    /**
     * Removes the delta time associated with the given identifier from the DELTA map.
     *
     * @param  id  the identifier of the delta time to be removed
     */
    public static void removeDelta(Identifier id) {...}

    /**
     * Clears the DELTA HashMap, removing all key-value pairs.
     */
    public static void clear() {...}

    /**
     * Checks if the given Identifier is present in the DELTA map.
     *
     * @param  id  the Identifier to check for in the DELTA map
     * @return     true if the Identifier is present in the DELTA map, false otherwise
     */
    public static boolean hasDelta(Identifier id) {...}

    /**
     * Checks if the delta time for the given identifier has expired.
     *
     * @param  id  the identifier for which to check the delta time
     * @return     true if the delta time has expired, false otherwise
     */
    public static boolean isDeltaOver(Identifier id) {...}
    /**
     * Updates the DELTA HashMap by removing entries where the current time is greater than the value.
     *
     * @return       No return value.
     */
    public static void update() {...}
```