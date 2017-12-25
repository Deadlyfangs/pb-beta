package com.octopus.pb.controller;


import com.octopus.pb.enums.GroupType;
import com.octopus.pb.enums.PhotoType;
import com.octopus.pb.model.*;
import com.octopus.pb.repository.EventRepository;
import com.octopus.pb.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    EventRepository eventRepository;


    @GetMapping("/test")
    private void test() {

        Rank rank = new Rank();
        rank.setName("First");

        Rank rank2 = new Rank();
        rank2.setName("TeamRank");

        Player player = new Player();
        player.setName("Player1");

        Team team = new Team();
        team.setName("lolz");
        team.setRank(rank2);

        player.setTeam(team);
        player.setRank(rank);

        Photo playerPhoto = new Photo();
        playerPhoto.setPhotoType(PhotoType.PLAYER);
        playerPhoto.setPath("Some player photo path");

        player.getPhotoSet().add(playerPhoto);

        playerRepository.save(player);

        //test1
    }

    @GetMapping("/group")
    private void testGroup() {
        Player player1 = new Player("One");
        Player player2 = new Player("Two");
        Player player3 = new Player("Three");
        Player player4 = new Player("Four");
        Player player5 = new Player("Five");

        Team team1 = new Team("FirstTeam");
        Team team2 = new Team("SecondTeam");

        Event event1 = new Event("FirstEvent");

        Group group1 = new Group(GroupType.RED, event1);
        Group group2 = new Group(GroupType.BLUE, event1);

        Field field1 = new Field("FirstField");

        team1.getPlayerSet().add(player1);
        team1.getPlayerSet().add(player2);
        team2.getPlayerSet().add(player3);
        team2.getPlayerSet().add(player4);

        event1.getGroupSet().put(group1.getGroupType(), group1);
        event1.getGroupSet().put(group2.getGroupType(), group2);

        event1.setField(field1);

        eventRepository.save(event1);
    }

}
