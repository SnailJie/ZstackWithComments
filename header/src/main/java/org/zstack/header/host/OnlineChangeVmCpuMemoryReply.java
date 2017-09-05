package org.zstack.header.host;

import org.zstack.header.message.MessageReply;

/**
 * Created by luchukun on 8/9/16.
 */
public class OnlineChangeVmCpuMemoryReply extends MessageReply {
    private int cpuNum;
    private long memorySize;

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(long memorySize) {
        this.memorySize = memorySize;
    }
}

