package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;
import web.model.UserDto;
import web.model.UserDtoOutput;
import web.repository.UserRepository;
import web.utils.HttpResponse;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(UserDto userDto){
        return userRepository.save(new User(userDto.username, userDto.password));
    }

    public User getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    public UserDtoOutput getUserByUsernameAndPassword(UserDto userDto) throws HttpResponse.UserNotFound {
        User user = userRepository.getUserByUsernameAndPassword(userDto.username, userDto.password);
        if (user == null) throw new HttpResponse.UserNotFound();
        return new UserDtoOutput(user.getUsername());
    }
}
