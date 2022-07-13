package com.example.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }
    public User get(Integer id) throws UserNotFound {
        Optional<User> ra = repo.findById(id);
        if(ra.isPresent()){
            return ra.get();
        }
        throw new UserNotFound("not found id "+id);
    }
    public void delete(Integer id)throws UserNotFound{
        Long count =repo.countById(id);
        if(count==null || count==0){
            throw new UserNotFound("");
        }
                repo.deleteById(id);
    }
}
