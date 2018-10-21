package com.xykj.omadmin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ocean
 * @Title: CourseSectionVo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/2下午11:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseSectionVo {

    private Integer id;
    private Integer dbId;
    private Integer courseId;
    private Integer parentId;
    private String sectionName;
    private String duration;
    private String videoUrl;

}
