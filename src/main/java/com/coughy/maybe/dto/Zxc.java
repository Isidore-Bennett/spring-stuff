package com.coughy.maybe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zxc {
    private String string1;
    private String string2;
    private String string3;
    private String string4;

    public Zxc setString1(String string) {
        this.string1 = string.split("\\.")[2];
        return this;
    }
}
