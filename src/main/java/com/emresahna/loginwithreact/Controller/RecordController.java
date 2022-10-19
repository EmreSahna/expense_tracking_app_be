package com.emresahna.loginwithreact.Controller;

import com.emresahna.loginwithreact.Dto.RecordRequest;
import com.emresahna.loginwithreact.Dto.RecordResponse;
import com.emresahna.loginwithreact.Entity.Record;
import com.emresahna.loginwithreact.Exception.RecordNotFoundException;
import com.emresahna.loginwithreact.Service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public ResponseEntity<List<RecordResponse>> getRecords(){
        List<RecordResponse> returnedRecords= recordService.getAll();
        return new ResponseEntity<>(returnedRecords, HttpStatus.OK);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<RecordResponse> getRecord(@PathVariable Long recordId) throws RecordNotFoundException {
        RecordResponse returnedRecord = recordService.getRecordById(recordId);
        return new ResponseEntity<>(returnedRecord,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Record> createCategory(@RequestBody RecordRequest newRecord){
        Record createdRecord = recordService.create(newRecord);
        return new ResponseEntity<>(createdRecord,HttpStatus.CREATED);
    }
}
