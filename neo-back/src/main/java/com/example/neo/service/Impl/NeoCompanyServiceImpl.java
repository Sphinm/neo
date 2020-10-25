package com.example.neo.service.Impl;


import com.example.neo.model.ICreateUser;
import com.example.neo.service.NeoCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NeoCompanyServiceImpl implements NeoCompanyService {

    @Override
    public List<ICreateUser> fetchCompanyInfo() {
        return null;
    }

    @Override
    public void updateCompanyInfo(ICreateUser user) {

    }

    @Override
    public void deleteCompanyInfo(int id) {

    }
}
