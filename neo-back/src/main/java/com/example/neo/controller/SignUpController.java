package com.example.neo.controller;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangeMobile;
import com.example.neo.service.SignUpService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // 发放逻辑也这里写吧，懒得创建 controller
    @PreAuthorize("hasAnyAuthority('provide')")
    @PostMapping("/provide/upload-provide-list")
    public ResponseBean uploadProvideList(@RequestParam(value = "provideExcel") MultipartFile file, String taskName) {
        try {
            if (file.isEmpty() || file.getSize() == 0){
                return ResponseBean.fail(ResponseCodeEnum.FILE_NOT_NULL);
            }
            String fileName = file.getOriginalFilename();
            if (fileName != null && !fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                // 文件格式不正确
                return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR_TYPE);
            }
            return signUpService.uploadProvideList(file, taskName);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.FILE_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('provide')")
    @GetMapping("/provide/list")
    public ResponseBean fetchProvideList() {
        return signUpService.fetchProvideList();
    }

    @PreAuthorize("hasAnyAuthority('provide')")
    @GetMapping("/provide/detail")
    public ResponseBean fetchProvideDetail() {
        return signUpService.fetchProvideDetail();
    }

    @PreAuthorize("hasAnyAuthority('provide')")
    @DeleteMapping("/provide/delete/{id}")
    public ResponseBean fetchProvideDetail(@PathVariable int id) {
        return signUpService.deleteProvideById(id);
    }
}
