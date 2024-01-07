package com.neptunedevelopmentteam.neptunelib.memory;

import net.creativious.SharedMemoryWrapper;

import java.util.HashMap;

public class SharedMemoryManager {
    private static final HashMap<String, SharedMemoryWrapper> sharedMemoryWrappers = new HashMap<>();

    public static SharedMemoryWrapper addSharedMemory(String name, int size) {
        if (sharedMemoryWrappers.containsKey(name)) {
            return sharedMemoryWrappers.get(name);
        }
        var shm = SharedMemoryWrapper.open(name, size); // WE NEVER CREATE A SHARED MEMORY, LET THE RUST SIDE CREATE IT
        sharedMemoryWrappers.put(name, shm);
        return shm;
    }

    public static SharedMemoryWrapper getSharedMemory(String name) {
        if (!sharedMemoryWrappers.containsKey(name)) {
            return null;
        }
        return sharedMemoryWrappers.get(name);
    }
}
