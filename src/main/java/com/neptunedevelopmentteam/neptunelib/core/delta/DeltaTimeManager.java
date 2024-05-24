package com.neptunedevelopmentteam.neptunelib.core.delta;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeltaTimeManager {
    private static final HashMap<Identifier, Long> DELTA = new HashMap<>(); // Keep everything in ms

    /**
     * Creates a delta time that'll be run after a certain amount of time
     * @param time_from_now Time from now, based on the provided Time Unit
     * @param time_unit The time unit to use for time_from_now
     * @param runnable The runnable to run
     */
    public static void queueTask(long time_from_now, TimeUnit time_unit, Runnable runnable) {
        try {
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(runnable, time_from_now, time_unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a delta time that'll be run after a certain amount of time.
     *
     * @param  id            the identifier for the delta time
     * @param  time_from_now the time from now, based on the provided TimeUnit
     * @param  time_unit     the time unit to use for time_from_now
     */
    public static void createDelta(Identifier id, long time_from_now, TimeUnit time_unit) {
        DELTA.put(id, System.currentTimeMillis() + time_unit.toMillis(time_from_now));
    }

    /**
     * Retrieves the delta time associated with the given identifier.
     *
     * @param  id  the identifier for which to retrieve the delta time
     * @return     the delta time in milliseconds
     */
    public static long getDelta(Identifier id) {
        return DELTA.get(id); // ms
    }

    /**
     * Removes the delta time associated with the given identifier from the DELTA map.
     *
     * @param  id  the identifier of the delta time to be removed
     */
    public static void removeDelta(Identifier id) {
        DELTA.remove(id);
    }

    /**
     * Clears the DELTA HashMap, removing all key-value pairs.
     */
    public static void clear() {
        DELTA.clear();
    }

    /**
     * Checks if the given Identifier is present in the DELTA map.
     *
     * @param  id  the Identifier to check for in the DELTA map
     * @return     true if the Identifier is present in the DELTA map, false otherwise
     */
    public static boolean hasDelta(Identifier id) {
        return DELTA.containsKey(id);
    }

    /**
     * Checks if the delta time for the given identifier has expired.
     *
     * @param  id  the identifier for which to check the delta time
     * @return     true if the delta time has expired, false otherwise
     */
    public static boolean isDeltaOver(Identifier id) {
        if (!hasDelta(id)) {
            return false;
        }
        if (System.currentTimeMillis() < DELTA.get(id)) {
            return false;
        }
        removeDelta(id);
        return true;
    }

    /**
     * Updates the DELTA HashMap by removing entries where the current time is greater than the value.
     *
     * @return       No return value.
     */
    public static void update() {
        DELTA.forEach((key, value) -> {
            if (System.currentTimeMillis() > value) {
                DELTA.remove(key);
            }
        });
    }
}
