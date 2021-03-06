package com.octopus.pb.repository;

import com.octopus.pb.entity.Rank;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RankRepository extends CrudRepository<Rank, Integer> {

    @Override
    Rank save(Rank rank);

    @Override
    void delete(Rank rank);

    @Override
    Rank findOne(Integer id);

    @Override
    List<Rank> findAll();

    @Modifying
    @Query("UPDATE Rank rank SET rank.name = :name WHERE rank.id = :id")
    int setName(@Param("name") String name, @Param("id") Integer id);

}
