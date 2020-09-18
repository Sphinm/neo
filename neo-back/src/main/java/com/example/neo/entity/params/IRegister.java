package com.example.neo.entity.params;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class IRegister {
    @NotNull
    public String mobile;

    @NotNull
    public String password;
}
