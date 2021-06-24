package com.pelion.pt.example;

import com.pelion.kafka.connect.DeviceRequestData;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/requests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestsResource {

  private static final Logger LOGGER =
      Logger.getLogger("RequestsResource");

  @Inject
  @Channel("requests")
  Emitter<DeviceRequestData> emitter;


  @POST
  public Response enqueueDeviceRequest(DeviceRequestData dd) {
    LOGGER.infof("Sending device management request %s to Kafka",
        dd.toString()
    );
    emitter.send(dd);
    return Response.accepted().build();
  }
}
