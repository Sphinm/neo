package com.example.neo.entity.params;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class ILogin {
    @NotNull
    private String mobile;

    @NotNull
    private String password;
}
