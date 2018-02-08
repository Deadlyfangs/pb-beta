package com.octopus.pb.repository;


import com.octopus.pb.enums.*;
import com.octopus.pb.model.*;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Slf4j
public class AllRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Test
    public void testContextLoader() {
        assertTrue(8 == 8);
    }

    @Test
    public void testPlayerSave() {

        List<Player> playerList1 = new ArrayList<>();
        playerList1.add(new Player("One"));
        playerList1.add(new Player("Two"));
        playerList1.add(new Player("Three"));

        List<Player> playerList2 = new ArrayList<>();
        playerList2.add(new Player("Four"));
        playerList2.add(new Player("Five"));

        List<Player> savedList1 = playerRepository.save(playerList1);
        List<Player> savedList2 = playerRepository.save(playerList2);

        assertTrue("SavedList1 size is not 3", savedList1.size() == 3);
        assertTrue("SavedList2 size is not 2", savedList2.size() == 2);
    }

    @Test
    public void testPlayerGet() {

        Player player1 = playerRepository.findOne(1);
        Player player2 = playerRepository.findOne(2);
        Player player3 = playerRepository.findOne(3);
        Player player88 = playerRepository.findOne(88);

        assertEquals("Player1 name does not match", player1.getName(), "inserted_player_1");
        assertEquals("Player2 name does not match", player2.getName(), "inserted_player_2");
        assertEquals("Player3 name does not match", player3.getName(), "inserted_player_3");
        assertEquals("Player88 name does not match", player88.getName(), "inserted_player_88");
    }

    @Test
    public void testTeamSave() {

        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        Team savedTeam1 = teamRepository.save(team1);
        Team savedTeam2 = teamRepository.save(team2);

        assertEquals("SavedTeam1 name does not match", savedTeam1.getName(), "Team1");
        assertEquals("SavedTeam2 name does not match", savedTeam2.getName(), "Team2");
    }

    @Test
    public void testEventSave() {

        Event event1 = new Event("FirstEvent");

        Group group1 = new Group(GroupType.RED);
        Group group2 = new Group(GroupType.BLUE);

        List<Group> groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);

        event1.addGroupList(groupList);

        eventRepository.save(event1);
    }

    @Test
    public void testGroupSave() {

        Group group1 = new Group(GroupType.BLUE);
        Group group2 = new Group(GroupType.RED);
        Group group3 = new Group(GroupType.YELLOW);

        Group savedGroup1 = groupRepository.save(group1);
        Group savedGroup2 = groupRepository.save(group2);
        Group savedGroup3 = groupRepository.save(group3);

        assertEquals("savedGroup1 name does not match", savedGroup1.getGroupType().toString(), "BLUE");
        assertEquals("savedGroup1 name does not match", savedGroup2.getGroupType().toString(), "RED");
        assertEquals("savedGroup1 name does not match", savedGroup3.getGroupType().toString(), "YELLOW");
    }

    @Test
    public void testCascadeSave() {
        log.info("testCascadeSave is invoked");

        Rank playerRank1 = new Rank("Knight", RankType.PLAYER);
        Rank playerRank2 = new Rank("Marshal", RankType.PLAYER);

        Rank teamRank1 = new Rank("Bronze", RankType.TEAM);
        Rank teamRank2 = new Rank("Silver", RankType.TEAM);
        Rank teamRank3 = new Rank("Gold", RankType.TEAM);

        Rating fieldRating1 = new Rating();
        Rating fieldRating2 = new Rating();

        Field field1 = new Field("Sport");
        Field field2 = new Field("Forest");
        field1.addRating(fieldRating1);
        field2.addRating(fieldRating2);

        Player player1 = new Player("Captain Geek++");
        Player player2 = new Player("Newbie");
        player1.setRank(playerRank1);
        player2.setRank(playerRank2);

        Team team1 = new Team("Brazilian Trucker Cleave");
        Team team2 = new Team("The Void");
        Team team3 = new Team("MVP");
        team1.setRank(teamRank1);
        team2.setRank(teamRank2);
        team3.setRank(teamRank3);
        team1.addPlayer(player1);
        team2.addPlayer(player2);

        User user1 = new User("someUser1", "somePassword1", "someemail1@email.com");
        User user2 = new User("someUser2", "somePassword2", "someemail2@email.com");
        user1.addPlayer(player1);
        user2.addPlayer(player2);

        Group group1 = new Group(GroupType.RED);
        Group group2 = new Group(GroupType.BLUE);
        group1.addPlayer(player1);
        group2.addPlayer(player2);

        Event event1 = new Event("BPM");
        event1.addField(field1);
        event1.addGroup(group1);
        event1.addGroup(group2);

        eventRepository.save(event1);
    }

}
