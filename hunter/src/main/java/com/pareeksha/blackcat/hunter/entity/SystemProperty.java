package com.pareeksha.blackcat.hunter.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name= "result_details")
public class SystemProperty {

    @Id
    String name;

    @Column(name = "value", nullable = false, columnDefinition = "TEXT")
    String value;

    @Column(name = "date_created", nullable = false, columnDefinition = "datetime default now()")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date dateCreated = new Date();

    @Column(name = "date_modified", nullable = false, columnDefinition = "datetime default now()")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date dateModified = new Date();
}
