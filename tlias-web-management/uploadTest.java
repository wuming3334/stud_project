package com.itwu;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class uploadTest {
    @Test
    public void TestUpload(){
        String newFileNameTemp = UUID.randomUUID().toString();
        System.out.println(newFileNameTemp);
    }
}
