package com.game.demo.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Formatter;

public class Log {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private FileHandler fh;

    public Log() {
        SimpleDateFormat sdf = new SimpleDateFormat("M-d_HH-mm-ss");
        try {
            fh = new FileHandler("C:/Users/rosaa/IdeaProjects/Demo/assets/log/demo_log_" +
                sdf.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                Calendar cal = new GregorianCalendar();
                cal.setTimeInMillis(record.getMillis());
                return String.format("%-7S : ", record.getLevel())
                    + logTime.format(cal.getTime())
                    + " || "
                    + record.getMessage() + "\n";
            }
        });
        logger.addHandler(fh);
    }

    public void info(String message){
        logger.info(message);
    }

    public void warning(String message){
        logger.warning(message);
    }

    public void error(String message){
        logger.severe(message);
    }
}
