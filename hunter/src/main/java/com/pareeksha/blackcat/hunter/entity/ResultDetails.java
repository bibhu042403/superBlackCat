package com.pareeksha.blackcat.hunter.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name= "result_details")
public class ResultDetails {

    @Id
    @GenericGenerator(name ="generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    int id;

    @Column(name = "exam_id")
    String examId;

    @Column(name = "exam_name")
    String examName;

    @Column(name = "declared_date")
    Date declaredDate;

    @Column(name = "result_url")
    String resultUrl;

    @Column(name= "date_created")
    Date dateCreated = new Date();

    @Column(name = "date_modified")
    Date dateModified = new Date();
}
