package com.xykj.ombase.utils.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: ErrorInfo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/9/24下午1:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorInfo {

    public static final Integer OK = 0;
    public static final Integer ERROR = -1;

    private Integer code;
    private String msg;
    private String url;

}
