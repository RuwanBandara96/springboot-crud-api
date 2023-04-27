package com.springboot.camel.postgresql.route;
import com.springboot.camel.postgresql.entity.Event;
import com.springboot.camel.postgresql.service.EventService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EventRoute extends RouteBuilder {


    private final Environment env;

    public EventRoute(Environment env) {
        this.env = env;
    }

    public void configure() throws Exception {

        restConfiguration()
                .contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Spring Boot Camel Postgres Rest API.")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(env.getProperty("server.port", "8080"))
                .bindingMode(RestBindingMode.json);

        rest("/data")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .get("/{eventId}").route()
                .to("{{route.findById}}")
                .endRest()
                .get("/").route()
                .to("{{route.findAllData}}")
                .endRest()
                .post("/").route()
                .marshal().json()
                .unmarshal(getJacksonDataFormat(Event.class))
                .to("{{route.saveData}}")
                .endRest()
                .put("/{eventId}").route()
                .marshal().json()
                .unmarshal(getJacksonDataFormat(Event.class))
                .to("{{route.updateData}}")
                .endRest()
                .delete("/{eventId}").route()
                .to("{{route.removeData}}")
                .end();

        from("{{route.findById}}")
                .log("Received header : ${header.eventId}")
                .bean(EventService.class, "findEventById(${header.eventId})");


        from("{{route.findAllData}}")
                .bean(EventService.class, "findAllEvents");

        from("{{route.saveData}}")
                .log("Received Body ${body}")
                .bean(EventService.class, "addEvent(${body})");


        from("{{route.removeData}}")
                .log("Received header : ${header.eventId}")
                .bean(EventService.class, "removeEvent(${header.eventId})");
        from("{{route.updateData}}")
                // .log("Received header : ${header.eventId}")
                .bean(EventService.class, "updateEvent(${header.eventId},${body})");

    }

    private JacksonDataFormat getJacksonDataFormat(Class<?> unmarshalType) {
        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(unmarshalType);
        return format;
    }
}
