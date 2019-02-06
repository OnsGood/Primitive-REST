package Hello.controllers;

import Hello.Exceptions.UserNotFoundException;
import Hello.User;
import Hello.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id){
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User newUser){
         return userRepo.save(newUser);
    }

    @PutMapping("/users/{id}")
    public User reaplaceUser(@RequestBody User newUser,@PathVariable long id){
       return userRepo.findById(id)
                .map(User -> {
            User.setName(newUser.getName());
            User.setEmail(newUser.getEmail());
            return userRepo.save(User);
                })
                .orElseGet(()-> {
            newUser.setId(id);
            return userRepo.save(newUser);
                });

    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable long id){
         userRepo.deleteById(id);
    }

}
