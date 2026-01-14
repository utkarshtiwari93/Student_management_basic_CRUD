package com.example.prac.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Integer , User> userDb = new HashMap<>();

    //GET the user
    @GetMapping
    public List<User> fetchUser(){

        return new ArrayList<>(userDb.values());
    }

//Add the user
    @PostMapping
    public ResponseEntity<User>  createUser (@RequestBody User user ){
        userDb.putIfAbsent(user.getId() , user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user);
    }
//Update the user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        if (userDb.containsKey(user.getId())){
        userDb.put(user.getId(), user) ;
        return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
//Delete the user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id ){
        if (!userDb.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the id ");
        }
        userDb.remove(id);

        return ResponseEntity.status(HttpStatus.OK).body("delet sucess");
    }

    //Get one student
    @GetMapping("/{id}")
    public ResponseEntity<User> getStudent(@PathVariable int id) {

        if (!userDb.containsKey(id)) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userDb.get(id));
        }
        userDb.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }


}
