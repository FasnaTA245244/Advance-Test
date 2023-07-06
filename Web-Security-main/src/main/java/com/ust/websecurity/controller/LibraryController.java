package com.ust.websecurity.controller;

import com.ust.websecurity.entity.Issue;
import com.ust.websecurity.entity.User;
import com.ust.websecurity.exception.UserNotSubscribedException;
import com.ust.websecurity.repository.IssueRepository;
import com.ust.websecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LibraryController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;

    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issuebook(@RequestBody Issue issue){
        final var user = userRepository.findById(issue.getUser().getId());
        if(user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        if(user.get().getSubscribed()==false){
            throw new UserNotSubscribedException("fghj");
        }
        return ResponseEntity.ok().body(issueRepository.save(issue));
    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubcription(@PathVariable Long id){
        final var user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        user.get().setSubscribed(true);
        return ResponseEntity.ok().body(userRepository.save(user.get()));
    }

}
