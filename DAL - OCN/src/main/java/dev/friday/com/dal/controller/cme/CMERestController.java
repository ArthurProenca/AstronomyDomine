package dev.friday.com.dal.controller.cme;

import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import dev.friday.com.dal.service.cme.CMEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/cme")
@RequiredArgsConstructor
public class CMERestController {

    private final CMEService cmeService;

    @GetMapping("/v1/")
    public ResponseEntity<CMEDTO> find() {
        return ResponseEntity.ok(cmeService.findAll());
    }
}
