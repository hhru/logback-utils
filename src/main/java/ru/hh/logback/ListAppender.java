package ru.hh.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class ListAppender extends ch.qos.logback.core.read.ListAppender<ILoggingEvent> {

  public String getLogLineBySubstring(String substring) {
    String actualLogString = null;

    for (ILoggingEvent event : list) {
      final String m = event.getFormattedMessage();
      if (m.contains(substring)) {
        actualLogString = m;
        break;
      }
    }
    return actualLogString;
  }

}

