package com.client.author;

import com.author.datamodel.dto.ResponseStructure;
import com.author.datamodel.entity.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "author-application"
        ,url = "http://localhost:8082/api/v1/author")
public interface AuthorClient {

    @GetMapping("/{authorid}")
    public Author getAuthorById(@PathVariable String authorid);

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure> updateAuthor(@RequestBody Author author);
}
