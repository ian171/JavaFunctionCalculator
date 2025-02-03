import net.chen.Login;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryMonitor {
    private final Timer timer;
    private final Login login;

    public MemoryMonitor(Login login) {
        this.login = login;
        this.timer = new Timer();
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                login.monitor();
            }
        }, 0, 1000); // 每秒执行一次
    }

    public void stop() {
        timer.cancel();
    }
}

