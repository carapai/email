package io.getplatforms;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Base64;


@Path("/send")
public class ExampleResource {

  @Inject
  Mailer mailer;


  @POST
  public JsonObject add(JsonObject person) {
    String b64 = person.getString("pdf");
    byte[] decoder = Base64.getDecoder().decode(b64);
    mailer.send(
      Mail.withText("olupotcharles@gmail.com", "A simple email from quarkus", "This is my body.")
        .addAttachment("result.pdf", decoder, "application/pdf"));
    return person;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    mailer.send(Mail.withText("stephocay@gmail.com", "A simple email from quarkus", "This is my body."));
    return "hello";
  }
}
