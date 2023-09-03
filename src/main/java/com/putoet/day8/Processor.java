package com.putoet.day8;

interface Processor extends Runnable{
    int getAccumulator();
    void setAccumulator(int value);
    int getIP();
    void setIP(int value);
    Processor enableTerminateOnRepeat();
    boolean terminatedOnRepeat();
}
