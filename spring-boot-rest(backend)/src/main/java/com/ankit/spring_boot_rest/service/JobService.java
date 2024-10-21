package com.ankit.spring_boot_rest.service;

import com.ankit.spring_boot_rest.model.JobPost;
import com.ankit.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepo repo;
    public void addJob(JobPost jobPost) {
        repo.save(jobPost);
    }
    public List<JobPost> getAllJobs(){
        return repo.findAll();
    }
    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost());
    }
    public void updateJob(JobPost jobPost) {
        repo.save(jobPost);
    }
    public void deleteJob(int postId) {
        repo.deleteById(postId);
    }

    public void load() {
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(
                new JobPost(1, "Java Developer", "Must have good experience in core java", 2, Arrays.asList("Java", "Spring Boot")),
                new JobPost(2, "Frontend Developer", "Experience in building process", 3, Arrays.asList("HTML", "CSS", "JavaScript")),
                new JobPost(3, "Data Scientist", "Strong background in machine learning", 1, Arrays.asList("Python", "TensorFlow")),
                new JobPost(4, "Network Engineer", "Design and implement in computer network", 2, Arrays.asList("Cisco", "Networking")),
                new JobPost(5, "Mobile App Developer", "Experience in mobile app development", 3, Arrays.asList("Kotlin", "Flutter"))
        ));
        repo.saveAll(jobs);
    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}
