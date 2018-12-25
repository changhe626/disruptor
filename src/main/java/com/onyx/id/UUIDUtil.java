package com.onyx.id;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * 生成有序的UUID
 */
public class UUIDUtil {

    public static void main(String[] args) {
        System.out.println(generatorUUID());
        System.out.println(generatorUUID());


    }

    public static String generatorUUID(){
        TimeBasedGenerator generator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        return generator.generate().toString();
    }

}
