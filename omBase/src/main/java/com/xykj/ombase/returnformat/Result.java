package com.xykj.ombase.returnformat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer code;
    private String msg;
    private Object data;

}
