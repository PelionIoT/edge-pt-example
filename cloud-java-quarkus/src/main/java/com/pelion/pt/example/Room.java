package com.pelion.pt.example;

import org.jboss.logging.Logger;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Room {

  static final Logger LOGGER =
      Logger.getLogger("Room");

  static final String THERMOSTAT = "thermostat";
  static final String BLOWER = "blower";

  final String name;

  Thermostat thermostat = new Thermostat();
  Blower blower = new Blower();

  static final Timer TIMER = new Timer();
  TimerTask failureTimerTask;

  long heatcoolChangeTimestamp;
  long pointChangeTimestamp;

  // called after 'blower' sensor change
  void processBlowerChange() {
    if (blower.heat || blower.cool) {
      if (failureTimerTask == null) {
        heatcoolChangeTimestamp = System.currentTimeMillis();   // mark current time
        failureTimerTask = createFailureTimerTask();
        TIMER.schedule(failureTimerTask, 20 * 1000L /*20 secs*/);
        LOGGER.infof("['%s'] blower state change (%s), started failure timer..", name, blower);
      }
    }
  }

  // called after 'thermostat' sensor change
  void processThermostatChange() {
    if (failureTimerTask != null) {
      failureTimerTask.cancel();
      LOGGER.infof("['%s'] ambient temperature change (temp=%s), stopping failure timer..", name, thermostat);
      failureTimerTask = null; // reset
      long diff = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - heatcoolChangeTimestamp);
      if (diff >= 10 && diff < 20) {
        LOGGER.infof("['%s'] ambient change occurred after '%d secs', sending 'WARNING' message..", name, diff);
        // TODO send warning
      }
    }
  }

  void processTicks() {
    if (thermostat.ticks == 1)
      pointChangeTimestamp = System.currentTimeMillis();

    // calculate timestamp since last change
    long diff = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - pointChangeTimestamp);
    if (diff <= 20 && thermostat.ticks == 4) {
      LOGGER.infof("['%s'] set point changed more than 4 times in %d seconds, sending 'OPTIMIZATION' message..", name, diff);
      thermostat.ticks = 0; // reset
    } else if (diff > 20) {
      LOGGER.infof("['%s'] set point ticks [%d] reset", name, thermostat.ticks);
      thermostat.ticks = 0; // reset
    }
  }

  private TimerTask createFailureTimerTask() {
    return new TimerTask() {
      @Override
      public void run() {
        LOGGER.infof("['%s'] timeout occurred and no ambient change occurred, sending 'ERROR' message..", name);
        failureTimerTask = null; // reset

        // TODO send error
      }
    };
  }

  public Room(String name) {
    this.name = name;
  }
}

abstract class Sensor {
  String name;
  String ep;
  String path;

  long timestamp;
}

class Thermostat extends Sensor {
  double temp;
  double point;
  int ticks;

  @Override
  public String toString() {
    return "temp=" + temp + ",point=" + point;
  }
}

class Blower extends Sensor {
  long fan;
  boolean heat;
  boolean cool;

  @Override
  public String toString() {
    return "fan=" + fan + ",heat=" + heat + ",cool=" + cool;
  }
}
