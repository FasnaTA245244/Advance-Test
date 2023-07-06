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

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
    	if(user.isEmpty()) {

            return ResponseEntity.noContent().build();
    	}
    	if(user.get().getSubscribed() == false)
    	{
    		throw new UserNotSubscribedException("");
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
    	if(user.isEmpty()) {
    		 return ResponseEntity.noContent().build();
    	}
         user.get().setSubscribed(true);
         return ResponseEntity.ok().body(userRepository.save(user.get()));

    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
    	User updated = null;
    	final var us= userRepository.findById(user.getId());
    	if(us.isPresent())
    	{
    		updated= us.get();
    		updated.setId(user.getId());
    		updated.setName(user.getName());
    		updated.setSubscribed(user.getSubscribed());
    		return ResponseEntity.ok().body(userRepository.save(updated));
    	}

            return ResponseEntity.notFound().build();

    }

    @PutMapping("/issue-book")
    public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue){
    	Issue iss =null;
    	final var is= issueRepository.findById(issue.getId());
    	if(is.isPresent()) {
    		iss = is.get();
    		iss.setId(issue.getId());
    		iss.setFine(issue.getFine());
    		iss.setIssueDate(issue.getIssueDate());
    		iss.setPeriod(issue.getPeriod());
    		iss.setReturnDate(issue.getReturnDate());
    		return ResponseEntity.ok().body(issueRepository.save(iss));
    	}

            return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/usr/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
    	final var use = userRepository.findById(id);
    	if(use.isEmpty())
    	{
    		return ResponseEntity.notFound().build();
    		
    	}
    	else {
    	userRepository.deleteById(id);
    	}
    	return ResponseEntity.ok().body("Deleted");
     

    }

    @DeleteMapping("/issue/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable long id) {

    	final var issuer = issueRepository.findById(id);
    	if(issuer.isEmpty())
    	{
    		return ResponseEntity.notFound().build();
    		
    	}
    	else {
    	issueRepository.deleteById(id);
    	}
    	return ResponseEntity.ok().body("Deleted");
    }
}
