package com.pelion.pt.example;

import com.pelion.kafka.connect.AsyncIDResponseData;
import com.pelion.kafka.connect.EndpointData;
import com.pelion.kafka.connect.NotificationData;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EdgeNotificationsConsumer {

  private final List<Room> rooms = new ArrayList<>();

  private static final Logger LOGGER =
      Logger.getLogger("EdgeNotificationsConsumer");


  @Incoming("response-data")
  public void responses(AsyncIDResponseData rd) {
    LOGGER.infof("Received Async-Response: %s)",
        rd.toString());
  }

  @Incoming("registration-data")
  public void responses(EndpointData ed) {
    LOGGER.infof("Received Registration: %s)",
        ed.toString());
  }

  @Incoming("notification-data")
  public void notifications(NotificationData nd) {
//    LOGGER.infof("Received Notification: %s)",
//        nd.toString());

    // extract room
    String[] info = nd.getOriginalEp().split("-");
    String roomName = info[0];
    String sensorName = info[1];

    Optional<Room> room = rooms.stream().filter(o -> o.name.equals(roomName)).findFirst();
    Room r;

    if (room.isPresent()) {
      r = room.get();
    } else {
      // create new room
      r = new Room(roomName);
      rooms.add(r);
      LOGGER.infof("created room ['%s'] ", r.name);
    }

    // extract resource path
    String path = nd.getPath();

    // update internal model
    if (sensorName.equals(Room.BLOWER)) {
      if (path.equals("/3312/0/5851")) { // fan
        r.blower.fan = nd.getPayload().getL();
      } else if (path.equals("/3312/1/5850")) {  // heat
        r.blower.heat = nd.getPayload().getB();
      } else if (path.equals("/3312/2/5850")) {
        r.blower.cool = nd.getPayload().getB(); // cool
      }
      r.processBlowerChange();

    } else if (sensorName.equals(Room.THERMOSTAT)) {
      if (path.equals("/3303/0/5700")) { // temp
        r.thermostat.temp = nd.getPayload().getD();
      } else if (path.equals("/3308/0/5900")) {
        r.thermostat.point = nd.getPayload().getD();
        r.thermostat.ticks++;
        r.processTicks();
      }
      r.processThermostatChange();
    }
  }
}