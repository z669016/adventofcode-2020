package com.putoet.day8;

public interface Processor extends Runnable{
    int getAccumulator();
    Processor setAccumulator(int value);
    int getIP();
    Processor setIP(int value);
    Processor enableVerbose();
    Processor disableVerbose();
    Processor enableTerminateOnRepeat();
    Processor disableTerminateOnRepeat();
    boolean terminatedOnRepeat();
}
