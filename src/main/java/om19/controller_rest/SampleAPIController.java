package om19.controller_rest;

import lombok.extern.slf4j.Slf4j;
import om19.api.exception.SampleException;
import om19.api.request.SampleRequestDto;
import om19.api.response.SampleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@Slf4j
public class SampleAPIController {

    @GetMapping("/sample/{message}")
    public ResponseEntity<SampleResponseDto> getSample(@NotNull @PathVariable("message") String message,
                                                       @NotNull @RequestParam("addMessage") String addMessage) throws Exception {
        return ResponseEntity.ok(new SampleResponseDto(message + addMessage, "OK"));
    }

    @PostMapping("/sample")
    public ResponseEntity<SampleResponseDto> postSample(@RequestBody @Validated
                                                        SampleRequestDto sampleRequestDto) {
        return ResponseEntity.ok(new SampleResponseDto(sampleRequestDto.getMessage(), "OK"));
    }

    @GetMapping("/sample/error")
    public ResponseEntity<SampleResponseDto> getSample() throws Exception {
        throw new SampleException("Sample Error");
    }
}
