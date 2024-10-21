package com.ankit.spring_boot_rest.controller;

import com.ankit.spring_boot_rest.model.JobPost;
import com.ankit.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController    //use @RestController annotation instead of @ResponseBody annotation
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {
    @Autowired
    private JobService service;
    @GetMapping("/")
        public String home(){
            return "This is Home page of JobPosts";
        }
    @GetMapping(path="jobPosts", produces = {"application/json"})
//    @ResponseBody     //for use returning a JSON data
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }
//    @GetMapping(path="jobPost/{postId}", produces = {"application/xml"})   //convert data into JSON format
    @GetMapping(path="jobPost/{postId}", produces = {"application/json"})   //convert data into XML format
    public JobPost getJob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }

//    @PostMapping(path="jobPost", consumes = {"application/xml"})    //use consume to accept only XML data
    @PostMapping("jobPost")    //use consume to accept only XML data
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }
    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }
    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        service.deleteJob(postId);
        return "Deleted";
    }
    @GetMapping("load")
    public String loadData(){
        service.load();
        return "Success";
    }

}
