package org.zstack.header.host;

import org.zstack.header.message.NeedReplyMessage;

/**
 * Created by luchukun on 8/9/16.
 */
public class OnlineChangeVmCpuMemoryMsg extends NeedReplyMessage implements HostMessage {
    private String vmInstanceUuid;
    private String hostUuid;
    private int cpuNum;
    private long memorySize;

    public void setVmInstanceUuid(String vmInstanceUuid) {
        this.vmInstanceUuid = vmInstanceUuid;
    }

    public String getVmInstanceUuid() {
        return vmInstanceUuid;
    }

    public void setHostUuid(String hostUuid) {
        this.hostUuid = hostUuid;
    }

    @Override
    public String getHostUuid() {
        return hostUuid;
    }


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
