package com.xykj.omservice.user.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user_course_collect", schema = "omdb", catalog = "")
public class TUserCourseCollectPo {
    /**
     * 自增id
     */
    @Id@Column(name = "id", nullable = false)
    private int id;

    /**
     * 用户id
     */
    @Basic@Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * 课程id
     *
     */
    @Basic@Column(name = "course_id", nullable = false)
    private int courseId;

    /**
     * 收藏课程的时间
     */
    @Basic@Column(name = "collect_time", nullable = false)
    private Timestamp collectTime;

}
