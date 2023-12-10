package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.GroupDtoOutput;
import web.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    /*@Autowired
    UserRepository userRepository;
    */

    public List<GroupDtoOutput> getAllGroupsOfUser(String username){
        List<GroupDtoOutput> groups = new ArrayList<>();
        /*List<Group> db = groupRepository.getAllGroupsFromAnUser(username);
        for (int i = 0; i< db.size(); ++i){
            groups.add(new GroupDtoOutput(db.get(i).getGroupName(),db.get(i).getGid()));
        }*/
                return groups ;

    }

    /*public Group addGroup(GroupDto groupDto){
        List<UserWeb> users = new ArrayList<>();
        for (int i = 0; i<groupDto.users.size(); ++i){
            users.add(userRepository.getUserByUsername(groupDto.users.get(i)));
        }
        return groupRepository.save(new Group(groupDto.groupName,users));
    }*/

}
