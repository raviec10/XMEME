package com.crio.starter.service;

import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.crio.starter.data.GreetingsEntity;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;

@Service
public class GreetingsService {

    @Autowired
    private GreetingsRepository memeRepository;

    public ResponseDto saveMeme(ResponseDto memeDto) {
        if (memeRepository.existsByNameAndUrlAndCaption(memeDto.getName(), memeDto.getUrl(), memeDto.getCaption())) {
            throw new RuntimeException("Duplicate meme");
        }
        GreetingsEntity meme = new GreetingsEntity();
        meme.setName(memeDto.getName());
        meme.setUrl(memeDto.getUrl());
        meme.setCaption(memeDto.getCaption());
        GreetingsEntity savedMeme = memeRepository.save(meme);
        memeDto.setId(savedMeme.getId());
        return memeDto;
    }

    public List<ResponseDto> getAllMemes() {
        return memeRepository.findTop100ByOrderByIdDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseDto getMemeById(String id) {
        Optional<GreetingsEntity> meme = memeRepository.findById(id);
        return meme.map(this::convertToDto).orElse(null);
    }

    private ResponseDto convertToDto(GreetingsEntity meme) {
        ResponseDto memeDto = new ResponseDto();
        memeDto.setId(meme.getId());
        memeDto.setName(meme.getName());
        memeDto.setUrl(meme.getUrl());
        memeDto.setCaption(meme.getCaption());
        return memeDto;
    }
}

// @Service
// @RequiredArgsConstructor
// public class GreetingsService {

//   @Autowired
//   private final GreetingsRepository greetingsRepository;

//   // public ResponseDto getMessage(String id) {
//   //   return new ResponseDto(greetingsRepository.findByExtId(id).getMessage());
//   // }

//   public ResponseDto getMessage(String extId) {
//     GreetingsEntity entity = greetingsRepository.findByExtId(extId);
//     if (entity != null) {
//       return new ResponseDto(entity.getMessage());
//     } else {
//       return new ResponseDto("Message not found");
//     }
//   }

//   // New Method to Post a Meme
//   public String postMeme(String memeContent) {
//     GreetingsEntity entity = new GreetingsEntity();
//     entity.setExtId(String.valueOf(System.currentTimeMillis()));
//     entity.setMessage(memeContent);
//     GreetingsEntity savedEntity = greetingsRepository.save(entity);
//     return savedEntity.getExtId();
//   }

//   public ResponseDto getMeme(String memeId) {
//     GreetingsEntity entity = greetingsRepository.findByExtId(memeId);
//     if (entity != null) {
//       return new ResponseDto(entity.getMessage());
//     } else {
//       return new ResponseDto("Meme not found");
//     }
//   }

//   public ResponseDto getLatestMemes() {
//     List<GreetingsEntity> latestMemes = greetingsRepository.findAll().stream()
//             .sorted((m1, m2) -> m2.getExtId().compareTo(m1.getExtId()))
//             .limit(100)
//             .collect(Collectors.toList());

//     StringBuilder response = new StringBuilder();
//     for (GreetingsEntity meme : latestMemes) {
//         response.append(meme.getMessage()).append("\n");
//     }
//     return new ResponseDto(response.toString());
//   }
// }
