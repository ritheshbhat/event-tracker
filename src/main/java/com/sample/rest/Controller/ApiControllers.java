package com.sample.rest.Controller;

import com.sample.rest.Models.Status;
import com.sample.rest.Models.User;
import com.sample.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value="/")
    public String getPage(){
        return  "Welcome";

    }
    @GetMapping(value="/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public Optional<User> getUserById(@PathVariable long id){
        //User getUserById=userRepo.findById(id).get();
        return  userRepo.findById(id);

    }
    @PostMapping(value="/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser=userRepo.findById(id).get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setZipcode(user.getZipcode());
        updatedUser.setUtaId(user.getUtaId());
        userRepo.save(updatedUser);
        return "Updated....";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser=userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with id: "+id;
    }

    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Status registerUser(@Valid @RequestBody User newUser){
        List<User> users = userRepo.findAll();
//        System.out.println("New user: " + newUser.toString());
        for (User user : users) {
//            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
//                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userRepo.save(newUser);
        return Status.SUCCESS;
    }
    @PutMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepo.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepo.save(user);
                return Status.SUCCESS ;
            }
        }
        return Status.FAILURE;
    }
    @PutMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepo.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepo.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
}
