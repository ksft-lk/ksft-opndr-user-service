package com.kochasoft.opendoor.userservice.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SingleCollector {
    private SingleCollector() {
    }

    public static <T> Collector<T,?,T> collector(){
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if(list.size()>1){
                    throw new IllegalStateException();
                }
                return list.isEmpty() ? null:list.get(0);
            });
    }
}
