package net.core.logdemo;

import lombok.RequiredArgsConstructor;
import net.core.common.MyLogger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService
{
    private final MyLogger myLogger;

    public void logic(String id)
    {
        myLogger.log("servicd id = " + id);
    }
}
