package com.pinho.workshopmongo.config;

import com.pinho.workshopmongo.domain.Post;
import com.pinho.workshopmongo.domain.User;
import com.pinho.workshopmongo.dto.AuthorDTO;
import com.pinho.workshopmongo.repository.PostRepository;
import com.pinho.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));


        userRepository.deleteAll();
        postRepository.deleteAll();

        User user0 = new User(null, "Maria Brown", "maria@gmail.com");
        User user1 = new User(null, "Alex Green", "alex@gmail.com");
        User user2 = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(user0, user1, user2));

        Post post1 = new Post(null, sdf.parse("21/03/2020"), "Title example 1", "Body example 1", new AuthorDTO(user0));
        Post post2 = new Post(null, sdf.parse("22/03/2020"), "Title example 2", "Body example 2", new AuthorDTO(user0));

        postRepository.saveAll(Arrays.asList(post1, post2));

        user0.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(user0);
    }

}
