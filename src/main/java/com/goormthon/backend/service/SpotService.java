package com.goormthon.backend.service;

import com.goormthon.backend.dto.GenerateStoryRequest;
import com.goormthon.backend.dto.GenerateStoryResponse;
import com.goormthon.backend.entity.Spot;
import com.goormthon.backend.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Transactional
    public GenerateStoryResponse generateStory(GenerateStoryRequest req) {
        String prompt = createPrompt(req);
        String aiStory = callGeminiApi(prompt);

        Spot saved = spotRepository.save(
                Spot.builder()
                        .ownerName(req.getOwnerName())
                        .spotName(req.getSpotName())
                        .address(req.getAddress())
                        .thumbnailUrl(req.getThumbnailUrl())
                        .text1(req.getText1())
                        .imageUrl1(req.getImageUrl1())
                        .text2(req.getText2())
                        .imageUrl2(req.getImageUrl2())
                        .text3(req.getText3())
                        .imageUrl3(req.getImageUrl3())
                        .generatedStory(aiStory)
                        .build()
        );

        return new GenerateStoryResponse(aiStory);
    }

    private String createPrompt(GenerateStoryRequest req) {
        return String.format(
                """
                질문 1 %s
                질문 2 %s
                질문 3 %s
    
                각각의 답변 값을 인스타 카드 뉴스 느낌으로 어투를 수정해주고
                위 내용을 바탕으로 각 답변에서 키워드 1개씩 뽑아줘
    
                해당 내용을 전부 다 json 형태로 return 해줘
    
                질문 1 내용의 key 값은 1,
                질문 2 내용의 key 값은 2,
                질문 3 내용의 key 값은 3,
                키워드의 key 값은 keyword로 하고 value는 string 배열로 해줘
                문어체로 써주고, 이모지와 마크다운은 넣지 말아줘
                인스타의 감성적인 것처럼 서술해주면 좋겠고, 귀엽진 않았으면 좋겠어
    
                예시 return 값은 다음과 같아
                {
                   1: "저는 단골손님이 신메뉴 먹을때 좋았어요",
                   2: "서울에서 피로를 느꼈어요",
                   3: "힐링하고 가세요",
                   keyword: ["신메뉴", "피로", "힐링"]
                }
                """,
                req.getText1(),
                req.getText2(),
                req.getText3()
        );
    }

    private String callGeminiApi(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            GeminiRequest requestBody = new GeminiRequest(
                    List.of(new Content(List.of(new Part(prompt))))
            );

            HttpEntity<GeminiRequest> entity = new HttpEntity<>(requestBody, headers);

            String url = apiUrl + "?key=" + apiKey;

            GeminiResponse response = restTemplate.postForObject(
                    url,
                    entity,
                    GeminiResponse.class
            );

            if (response != null && !response.candidates().isEmpty()) {
                return response.candidates().get(0).content().parts().get(0).text();
            }
            return "AI 응답 없음";

        } catch (HttpClientErrorException e) {
            log.error("Gemini API 호출 실패 (HTTP 상태 코드): {}", e.getStatusCode());
            log.error("응답 본문: {}", e.getResponseBodyAsString());
            return "API 호출 실패: " + e.getStatusCode();
        } catch (Exception e) {
            log.error("Gemini API 알 수 없는 오류", e);
            return "서버 오류 발생: " + e.getMessage();
        }
    }
}

record GeminiRequest(List<Content> contents) {}
record Content(List<Part> parts) {}
record Part(String text) {}
record GeminiResponse(List<Candidate> candidates) {}
record Candidate(Content content) {}