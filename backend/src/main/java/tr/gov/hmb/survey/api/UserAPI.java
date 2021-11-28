package tr.gov.hmb.survey.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.gov.hmb.survey.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    /**
     * Test edilmesi kolay olmasi icin frontend'e tum kullanicilarin token bilgisini gosteren endpoint'tir.
     * Bu method canliya alim esnasinda kapatilmalidir.
     *
     * @return tum kullanicilarin token bilgisi.
     */
    @GetMapping(value = "/getTokens")
    public ResponseEntity<List<String>> getTokens() {
        return ResponseEntity.ok(userService.getTokens());
    }

}
