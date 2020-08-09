package io.gateplatforms;

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
public class GateMail {

  @Inject
  Mailer mailer;


  @POST
  public JsonObject add(JsonObject mail) {
    String b64 = mail.getString("attachment");
    String recipient = mail.getString("recipient");
    String subject = mail.getString("subject");
    String body = mail.getString("body");
    String fileName = mail.getString("fileName", "results");
    byte[] decoder = Base64.getDecoder().decode(b64);
    mailer.send(
      Mail.withText(recipient, subject, body)
        .addAttachment(fileName + ".pdf", decoder, "application/pdf"));
    return mail;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    mailer.send(Mail.withText("stephocay@gmail.com", "A simple email from quarkus", "This is my body."));
    return "hello";
  }
}
