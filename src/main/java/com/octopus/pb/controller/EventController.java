package com.octopus.pb.controller;

import com.octopus.pb.enums.GroupType;
import com.octopus.pb.model.Event;
import com.octopus.pb.model.Field;
import com.octopus.pb.model.Group;
import com.octopus.pb.model.Player;
import com.octopus.pb.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;


    @GetMapping("/test/{id}")
    public @ResponseBody List<?> test(@PathVariable("id") int id) {

        Field field1 = Field.builder()
                .name("Field1")
                .fieldInfo("Info about field1")
                .address("City, Street, Building")
                .capacity(100)
                .build();

        Group redGroup = new Group(GroupType.RED);
        Group blueGroup = new Group(GroupType.BLUE);

        Event event1 = Event.builder()
                .name("Event1")
                .eventInfo("Info about event1")
                .beginDate(LocalDateTime.of(2018, Month.MAY, 20, 10, 0))
                .endDate(LocalDateTime.of(2018, Month.MAY, 20, 18, 0))
                .build();
        event1.addField(field1);
        event1.addGroup(redGroup);
        event1.addGroup(blueGroup);

        List<Object> responseList = new ArrayList<>();
        responseList.add("You entered ID: " + id);
        responseList.add(redGroup);
        responseList.add(blueGroup);

        return responseList;
    }

    @PostMapping("/create")
    public @ResponseBody String createEvent() {

        String response = "Created event: ??";

        return response;
    }

    @GetMapping("/get/{id}")
    public @ResponseBody Event getEvent(@PathVariable("id") int id) {

        Event eventResponse = eventService.getEvent(id);

        return eventResponse;
    }

    @GetMapping("/getAll")
    public @ResponseBody List<Event> getEventList() {

        List<Event> eventList = new ArrayList<>();

        return eventList;
    }

}
