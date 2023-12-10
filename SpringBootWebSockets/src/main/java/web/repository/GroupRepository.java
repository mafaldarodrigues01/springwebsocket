package web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    /*@Query(value = "select * \n" +
            "from GROUPP G \n" +
            "left join ( \n" +
            "select GU.gid as id \n" +
            "from group_user GU \n" +
            "inner join USERR U \n" +
            "on U.username = :username and GU.uid = U.uid ) as R \n" +
            "on R.id = G.gid", nativeQuery = true)
    List<Group> getAllGroupsFromAnUser(@Param("username") String username);*/

}