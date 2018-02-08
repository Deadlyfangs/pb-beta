package com.octopus.pb.model;


import com.octopus.pb.enums.GroupType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"groupMap", "photoSet"})
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_id", foreignKey = @ForeignKey(name = "events_to_fields"))
    private Field field;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @MapKey(name="groupType")
    @MapKeyEnumerated(EnumType.STRING)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Map<GroupType, Group> groupMap = new HashMap<>();

    @ManyToMany(mappedBy = "eventSet")
    private Set<Photo> photoSet;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, LocalDateTime beginDate, LocalDateTime endDate, boolean isActive) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public void addGroup(Group group) {
        groupMap.put(group.getGroupType(), group);
        group.setEvent(this);
    }

    public void addGroupList(List<Group> groupList) {
        groupList.forEach(g -> {
            groupMap.put(g.getGroupType(), g);
            g.setEvent(this);
        });
    }

    public void removeGroup(Group group) {
        group.setEvent(null);
        groupMap.remove(group.getGroupType());
    }

    public void addField(Field field) {
        setField(field);
        field.getEventSet().add(this);
    }

    public void removeField(Field field) {
        setField(null);
        field.getEventSet().remove(this);
    }

}
