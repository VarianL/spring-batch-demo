package com.gxy.demo.world.lintener;

import org.springframework.batch.core.SkipListener;

public class CustomSkipListener implements SkipListener {
    @Override
    public void onSkipInRead(Throwable throwable) {
        System.out.println("----------------------------------");
        System.out.println("on skip read");
        throwable.printStackTrace();
        System.out.println("----------------------------------");
    }

    @Override
    public void onSkipInWrite(Object o, Throwable throwable) {
        System.out.println("----------------------------------");
        System.out.println("on skip writer");
        throwable.printStackTrace();
        System.out.println("----------------------------------");
    }

    @Override
    public void onSkipInProcess(Object o, Throwable throwable) {
        System.out.println("----------------------------------");
        System.out.println("on skip process");
        throwable.printStackTrace();
        System.out.println("----------------------------------");
    }
}
