package com.example.neo.controller;

import com.example.neo.model.IChangeMobile;
import com.example.neo.service.SignUpService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PreAuthorize("hasAnyAuthority('sign_up')")
    @GetMapping("/sign/un-sign-up")
    public ResponseBean fetchUnSignUpList() {
        return signUpService.fetchUnSignUpList();
    }

    @PreAuthorize("hasAnyAuthority('sign_up')")
    @GetMapping("/sign/sign-up")
    public ResponseBean fetchSignUpList() {
        return signUpService.fetchSignUpList();
    }

    @PreAuthorize("hasAnyAuthority('sign_up')")
    @GetMapping("/sign/search-by-idCard")
    public ResponseBean searchByIdCard(@RequestParam("id") String id) {
        return signUpService.searchByIdCard(id);
    }

    @PreAuthorize("hasAnyAuthority('sign_up')")
    @PostMapping("/sign/change-mobile")
    public ResponseBean changeMobile(@RequestBody IChangeMobile mobile) {
        return signUpService.changeMobile(mobile);
    }
}
