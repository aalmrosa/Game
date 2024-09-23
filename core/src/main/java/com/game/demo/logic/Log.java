/* 23/09/2024 - Gust
 * Log class for application. Still needs upgrades such as auto-deletion excluding the last few files.
 * Last updated: 23/09/2024 - Gust
 */

package com.game.demo.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Formatter;

public class Log {
    private static final Logger logger = Logger.getLogger("com.core.demo.logic.Log");

    /**
     * Defines structure format for data streamed to .log files
     */
    public static void build(){
        FileHandler fh;
        SimpleDateFormat sdf = new SimpleDateFormat("M-d_HH-mm-ss");

        try {
            fh = new FileHandler("C:/Users/rosaa/IdeaProjects/Demo/assets/log/log_" +
                sdf.format(Calendar.getInstance().getTime()) + ".log");

            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                    Calendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(record.getMillis());
                    return logTime.format(cal.getTime())
                        + " || "
                        + String.format("%-7S : ", record.getLevel())
                        + record.getMessage() + "\n";
                }
            });
            logger.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace(); //TODO: needs better handling
        }
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void warning(String message){
        logger.warning(message);
    }

    public static void error(String message){
        logger.severe(message);
    }
}
