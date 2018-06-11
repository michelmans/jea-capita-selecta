package nl.anitro.bakapp.service;

import nl.anitro.bakapp.domain.BakappException;
import nl.anitro.bakapp.domain.Product;
import nl.anitro.bakapp.domain.User;
import nl.anitro.bakapp.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

        User user1 = new User("user1", "examplestreet 1 temptown");
        User user2 = new User("user2", "tempstreet 1 exampletown");

        try {
            registerUser(user1);
            registerUser(user2);
        } catch (BakappException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserByName(String username) throws BakappException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if(!user.isPresent()) throw new BakappException("Username is not present");
        return user.get();
    }

    public User registerUser(User user) throws BakappException {
        try{
            return this.userRepository.save(user);
        } catch(Exception e) {
            throw new BakappException("Could not register username");
        }
    }

}
