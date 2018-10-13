package com.xykj.omadmin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: CourseClassifyVoAdmin
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/13下午7:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseClassifyVoAdmin {

    private Integer id;
    private Integer dbId;
    private String classifyName;
    private Integer parentId;
    private Integer sequence;

}
