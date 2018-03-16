package com.octopus.pb.dto;

import com.octopus.pb.entity.Group;
import com.octopus.pb.enums.GroupType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class EventDto {

    private int id;
    private String name;
    private String info;
    private String shortInfo;
    private String gameRules;
    private int capacity;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private boolean isActive;
    private FieldDto field;

    private Map<GroupType, GroupDto> groupMap;
//    private Set<Photo> photoSet;

}
