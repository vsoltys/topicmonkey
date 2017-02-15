package com.busybox.topicmonkey.domain.utils;

import static java.util.UUID.randomUUID;

public class UniqueIdGenerator {

    public static String uniqueId() {
        return randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

}
