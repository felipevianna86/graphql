package com.example.tutorial.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ForumController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @SchemaMapping(typeName = "Query", value = "findById")
    //@QueryMapping
    public Post findById(@Argument String id){
        return this.postService.findById(id);
    }

    @MutationMapping
    public Collection<Post> createPost(@Argument String content){
        return this.postService.createPost(content);
    }

    @MutationMapping
    public Collection<Comment> createComment(@Argument String content, @Argument String postId){
        return this.commentService.createComment(content, postId);
    }

    @SchemaMapping(typeName = "Post", value = "comments")
    public Collection<Comment> comments(Post post){
        return this.commentService.findByPost(post.id());
    }
}
