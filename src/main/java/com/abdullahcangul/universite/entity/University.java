package com.abdullahcangul.universite.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class University extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "api_id",unique = true)
    private int apiId;

    @Column(name = "name",length = 50,unique = true)
    private String name;

    @Column(name = "city",length = 50)
    private String city;

    @Column(name = "web_page",length = 50)
    private String web_page;

    @Column(name = "type",length = 50)
    private String type;

    @Column(name = "founded_at",length = 50)
    private String founded_at;

    @OneToMany(mappedBy = "university",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Student> students;
}
