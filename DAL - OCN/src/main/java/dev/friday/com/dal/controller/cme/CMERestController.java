package dev.friday.com.dal.controller.cme;

import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import dev.friday.com.dal.service.cme.CMEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CMERestController extends BaseController {

    private final CMEService cmeService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody CMEDTO cmeDto) {
        cmeService.save(cmeDto.toEntity());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(cmeService.findAll());
    }
}
