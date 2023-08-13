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
@Table(name= "application_form_details")
public class ApplicationFormDetails {

    @Id
    @GenericGenerator(name ="generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    int id;

    @Column(name = "exam_id")
    String examId;

    @Column(name = "exam_name")
    String examName;

    @Column(name = "exam_board")
    String examBoard;

    @Column(name = "fee_details")
    String feeDetails;

    @Column(name = "age_limits")
    String ageLimits;

    @Column(name = "form_urls")
    String url;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "last_date")
    Date lastDate;

    @Column(name = "qualification")
    String qualification;

    @Column(name = "extra_field")
    String extraField;

    @Column(name = "date_created")
    Date dateCreated;

    @Column(name = "date_modified")
    Date dateModified;
}
