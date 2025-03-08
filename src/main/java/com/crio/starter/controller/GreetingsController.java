package com.crio.starter.controller;

import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memes")
@Validated
public class GreetingsController {

    @Autowired
    private GreetingsService memeService;

    @PostMapping("/")
    public ResponseEntity<?> createMeme(@Valid @RequestBody ResponseDto memeDto) {
        try {
            ResponseDto createdMeme = memeService.saveMeme(memeDto);
            return new ResponseEntity<>(createdMeme, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseDto>> getAllMemes() {
        return new ResponseEntity<>(memeService.getAllMemes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemeById(@PathVariable String id) {
        ResponseDto meme = memeService.getMemeById(id);
        if (meme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meme, HttpStatus.OK);
        
    }
}



// @RestController
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
// public class GreetingsController {

//   private final GreetingsService greetingsService;

//   @GetMapping("/say-hello")
//   public ResponseDto sayHello(@RequestParam("messageId") String messageId) {
//     return greetingsService.getMessage(messageId);
//   }

  
//   @PostMapping("/post-meme")
//   public ResponseDto postMeme(@RequestBody String memeContent) {
//     String memeId = greetingsService.postMeme(memeContent);
//     return new ResponseDto("Meme posted with ID: " + memeId);
//   }

//   @GetMapping("/meme/{memeId}")
//   public ResponseDto getMeme(@PathVariable("memeId") String memeId) {
//     return greetingsService.getMeme(memeId);
//   }

//   @GetMapping("/latest-memes")
//   public ResponseDto getLatestMemes() {
//     return greetingsService.getLatestMemes();
//   }
// }
