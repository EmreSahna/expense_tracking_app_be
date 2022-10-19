package com.emresahna.loginwithreact.Service;

import com.emresahna.loginwithreact.Dto.RecordRequest;
import com.emresahna.loginwithreact.Dto.RecordResponse;
import com.emresahna.loginwithreact.Entity.Record;
import com.emresahna.loginwithreact.Exception.RecordNotFoundException;
import com.emresahna.loginwithreact.Repository.CategoryRepository;
import com.emresahna.loginwithreact.Repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final CategoryRepository categoryRepository;

    public RecordService(RecordRepository recordRepository, CategoryRepository categoryRepository) {
        this.recordRepository = recordRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<RecordResponse> getAll() {
        return recordRepository.findAll().stream().map(record -> {
            RecordResponse response = new RecordResponse();
            response.setId(record.getId());
            response.setTitle(record.getTitle());
            response.setAmount(record.getAmount());
            response.setCreatedAt(record.getCreatedAt());
            response.setUpdatedAt(record.getUpdatedAt());
            response.setCategory(record.getCategory());
            return response;
        }).collect(Collectors.toList());
    }

    public RecordResponse getRecordById(Long recordId) throws RecordNotFoundException {
        return recordRepository.findById(recordId).map(record -> {
            RecordResponse response = new RecordResponse();
            response.setId(record.getId());
            response.setTitle(record.getTitle());
            response.setAmount(record.getAmount());
            response.setCreatedAt(record.getCreatedAt());
            response.setUpdatedAt(record.getUpdatedAt());
            response.setCategory(record.getCategory());
            return response;
        }).orElseThrow(() -> new RecordNotFoundException("Record not found."));
    }

    public Record create(RecordRequest newRecord) {
        Record createdRecord = new Record();
        createdRecord.setTitle(newRecord.getTitle());
        createdRecord.setAmount(newRecord.getAmount());
        createdRecord.setCategory(categoryRepository.findById(newRecord.getCategory_id()).orElse(null));
        return recordRepository.save(createdRecord);
    }
}
