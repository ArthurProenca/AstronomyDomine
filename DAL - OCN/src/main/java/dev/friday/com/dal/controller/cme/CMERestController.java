package dev.friday.com.dal.controller.cme;

import dev.friday.com.dal.controller.BaseRestController;
import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import dev.friday.com.dal.service.cme.CMEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CMERestController extends BaseRestController {

    private final CMEService cmeService;

    @GetMapping("/v1/cme")
    public ResponseEntity<List<CMEDTO>> find() {
        return ResponseEntity.ok(cmeService.findAll());
    }
}
