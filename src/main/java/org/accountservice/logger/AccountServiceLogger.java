package org.accountservice.logger;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Account service logger
 */
@Data
@Component
public class AccountServiceLogger {

    private final AtomicInteger getCount;
    private final AtomicInteger addCount;
    private final Map<LocalDateTime, AtomicInteger> getRPM;
    private final Map<LocalDateTime, AtomicInteger> addRPM;

    public AccountServiceLogger() {
        this.addCount = new AtomicInteger(0);
        this.getCount = new AtomicInteger(0);
        this.addRPM = new ConcurrentHashMap<>();
        this.getRPM = new ConcurrentHashMap<>();
    }

    /**
     * Counts requests and requests per minute of addAmount()
     */
    public void addCountIncrease() {
        addCount.addAndGet(1);
        var sec = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        if (addRPM.containsKey(sec)) {
            addRPM.get(sec).addAndGet(1);
        } else {
            addRPM.put(sec, new AtomicInteger(1));
        }
    }

    /**
     * Counts requests and requests per minute of getAmount()
     */
    public void getCountIncrease() {
        getCount.addAndGet(1);
        var sec = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        if (getRPM.containsKey(sec)) {
            getRPM.get(sec).addAndGet(1);
        } else {
            getRPM.put(sec, new AtomicInteger(1));
        }
    }

    /**
     * Clears log data and log file
     */
    public void clearLog() {
        addCount.set(0);
        getCount.set(0);
        addRPM.clear();
        getRPM.clear();
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/log/log.txt");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes log message into file
     */
    public void getLog() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/log/log.txt");
            fileWriter.write(logMessage());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Forms log message
     *
     * @return log message
     */
    private String logMessage() {
        StringBuilder message = new StringBuilder()
                .append("Add requests:").append(addCount).append("\n")
                .append("Get requests:").append(getCount).append("\n")
                .append("All requests:").append(getCount.intValue() + addCount.intValue()).append("\n")
                .append("\n").append("Add RPM:");
        for (var entry : addRPM.entrySet()) {
            message.append(entry.getKey()).append(entry.getValue().intValue()).append("\n");
        }
        message.append("\n").append("Get RPM:");
        for (var entry : getRPM.entrySet()) {
            message.append(entry.getKey()).append(entry.getValue().intValue()).append("\n");
        }
        return message.toString();
    }

}
