package com.coughy.maybe.resource;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.service.AddInService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.coughy.maybe.util.PageUtility.setPageRequest;

@RestController
@RequestMapping("/api/add-in")
public class AddInResource {

    private final AddInService addInService;

    public AddInResource(AddInService addInService) {
        this.addInService = addInService;
    }

    @PostMapping("")
    public ResponseEntity<AddIn> saveAddIn(@RequestBody AddIn addIn) {
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity<>(addInService.saveAddIn(addIn), headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<AddIn>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(addInService.getAllByVisibleTrue(), headers, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<AddIn>> getAddIns(@RequestHeader Map<String, String> headers) {
        Pageable pageable = setPageRequest(headers);
        return new ResponseEntity<>(addInService.getAllForAdmin(pageable), HttpStatus.OK);
    }

    @GetMapping("/toggle/{id}")
    public ResponseEntity<AddIn> toggle(@PathVariable String id) {
        try {
            return new ResponseEntity<>(addInService.toggle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
