package com.octopus.pb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.octopus.pb.enums.FieldType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"eventSet", "photoSet"})
@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(length = 10485760)
    private String info;

    @Enumerated(EnumType.STRING)
    private FieldType type;

    private String size;
    private int capacity;
    private String address;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rating_id", foreignKey = @ForeignKey(name = "fields_to_ratings"))
    private Rating rating;

    @JsonIgnore
    @OneToMany(mappedBy = "field")
    private final Set<Event> eventSet = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "fieldSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Photo> photoSet = new HashSet<>();

    public Field(String name) {
        this.name = name;
    }

    public void addRating(Rating rating) {
        setRating(rating);
        rating.setField(this);
    }

    public void removeRating(Rating rating) {
        setRating(null);
        rating.setField(null);
    }

    public void addPhoto(Photo photo) {
        photoSet.add(photo);
        photo.getFieldSet().add(this);
    }

    public void removePhoto(Photo photo) {
        photo.getFieldSet().remove(this);
        photoSet.remove(photo);
    }

}
