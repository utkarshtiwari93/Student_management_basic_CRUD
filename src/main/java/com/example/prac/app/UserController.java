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

    private userService userService = new userService();


    public UserController(userService userService) {
        this.userService = userService;
    }

    //GET the user
    @GetMapping
    public List<User> fetchUser(){
        return userService.getAllUsers();
    }

//Add the user
    @PostMapping
    public ResponseEntity<User>  createUser (@RequestBody User user ){
       User createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUser);
    }
//Update the user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
       User updated = userService.updateUser(user);
       if (updated==null){
          // throw new IllegalArgumentException("user with id :" +user.getId()+"not allowed");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.ok(updated);

    }
//Delete the user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id ){
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the id ");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("delet sucess");
    }



    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public  ResponseEntity<User> getUserOrder(@PathVariable(value = "userId" , required = false) int id , @PathVariable int orderId){
        System.out.println("Order id : " + orderId);
        User user= userService.getUserById(id);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<User>> searchUser(@RequestParam(required = false , defaultValue = "utkarsh") String name ){
//        System.out.println(name);
//        return ResponseEntity.ok(new ArrayList<>(userDb.values()));
//    }


}
