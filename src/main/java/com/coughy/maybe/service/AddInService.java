package com.coughy.maybe.service;

import com.coughy.maybe.entity.AddIn;
import com.coughy.maybe.repository.AddInRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddInService {

    private final AddInRepository addInRepository;

    public AddInService(AddInRepository addInRepository) {
        this.addInRepository = addInRepository;
    }

    public AddIn saveAddIn(AddIn addIn) throws DataIntegrityViolationException {
        if (addIn.getId() == null) {
            addIn.setVisible(false);
        }
        return addInRepository.save(addIn);
    }

    public List<AddIn> getAllByVisibleTrue() {
        return addInRepository.getAllByVisibleTrue();
    }

    public Page<AddIn> getAllForAdmin(Pageable pageable) {
        return addInRepository.findAll(pageable);
    }

    @Transactional
    public AddIn toggle(String id) {
        AddIn addIn = addInRepository.findById(id).get();
        addIn.setVisible(!addIn.getVisible());
        return addInRepository.save(addIn);
    }
}
