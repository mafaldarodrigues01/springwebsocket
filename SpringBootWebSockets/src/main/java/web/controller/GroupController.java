package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.GroupDto;
import web.model.GroupDtoOutput;
import web.service.GroupService;
import web.utils.ControllerHandler;

import java.util.List;

@RequestMapping("/groups")
@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping
    public synchronized void addGroup(@RequestBody GroupDto groupDto){
        ControllerHandler.handleException(()->{
            return "OK";
           // return groupService.addGroup(groupDto);
        }
        , HttpStatus.CREATED);
    }

    @GetMapping("{username}/userWeb")
    public synchronized ResponseEntity<List<GroupDtoOutput>> getAllGroupsOfUser(@PathVariable String username){
        return ControllerHandler.handleException(()->groupService.getAllGroupsOfUser(username), HttpStatus.OK);
    }

}