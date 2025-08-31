package org.example.service;

import org.example.dao.ValueDao;
import org.example.model.Value;

public class ValueService {
    private final ValueDao valueDao;

    public ValueService(ValueDao valueDao) {
        this.valueDao = valueDao;
    }

    public Value update(Value value){
        return valueDao.update(value);
    }
}
