package info.nightscout.androidaps.plugins.DanaRKorean.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import info.nightscout.androidaps.Config;
import info.nightscout.androidaps.plugins.DanaR.comm.MessageBase;
import info.nightscout.androidaps.plugins.DanaRKorean.DanaRKoreanPlugin;

public class MsgSettingPumpTime extends MessageBase {
    private static Logger log = LoggerFactory.getLogger(MsgSettingPumpTime.class);

    public MsgSettingPumpTime() {
        SetCommand(0x320A);
    }

    public void handleMessage(byte[] bytes) {
        Date time =
                new Date(
                        100 + intFromBuff(bytes, 5, 1),
                        intFromBuff(bytes, 4, 1) - 1,
                        intFromBuff(bytes, 3, 1),
                        intFromBuff(bytes, 2, 1),
                        intFromBuff(bytes, 1, 1),
                        intFromBuff(bytes, 0, 1)
                );

        if (Config.logDanaMessageDetail)
            log.debug("Pump time: " + time);

        DanaRKoreanPlugin.getDanaRPump().pumpTime = time;
    }
}
