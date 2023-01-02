package com.coughy.maybe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZxcDTO {
    private String string1;
    private String string4;

    public ZxcDTO(Zxc zxc) {
        this.string1 = zxc.getString1();
        this.string4 = zxc.getString4();
    }

    public void setString(String string) {
        this.string1 = string + "asdasd";
    }
}
