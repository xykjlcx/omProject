package com.xykj.omservice.user.po;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author ocean
 * @Title: TIdeaBackPo
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/7上午11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_idea_back", schema = "omdb", catalog = "")
public class TIdeaBackPo {
    @Id@Column(name = "id")@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic@Column(name = "user_id")
    private int userId;
    @Basic@Column(name = "content")
    private String content;
    @Basic@Column(name = "contact")
    private String contact;
    @Basic@Column(name = "address")
    private String address;
    @Basic@Column(name = "create_time")
    private Timestamp createTime;

}
