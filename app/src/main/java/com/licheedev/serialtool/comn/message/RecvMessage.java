package com.licheedev.serialtool.comn.message;

import com.licheedev.serialtool.util.TimeUtil;

/**
 * 收到的日志
 */

public class RecvMessage implements IMessage {
    
    private String command;
    private String message;

    public RecvMessage(String command) {
        this.command = command;
        this.message = command;//TimeUtil.currentTime() + " Received ：\n" + command;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isToSend() {
        return false;
    }
}
