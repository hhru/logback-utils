package ru.hh.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * User: SerP
 * Date: 03.03.13
 * Time: 18:32
 */
public class ListAppenderTest {
  org.slf4j.Logger classLogger = LoggerFactory.getLogger(ListAppenderTest.class);


  private ListAppender listAppender;

  @Before
  public void setUp() throws Exception {
    listAppender = new ListAppender();
    listAppender.start();

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
    rootLogger.addAppender(listAppender);
  }

  @After
  public void tearDown() throws Exception {
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
    rootLogger.detachAppender(listAppender);

    listAppender.stop();
  }

  @Test
  public void testName() throws Exception {

    classLogger.info("something important");

    String actualLogLine = listAppender.getLogLineBySubstring("something");
    assertEquals("something important", actualLogLine);
  }
}
