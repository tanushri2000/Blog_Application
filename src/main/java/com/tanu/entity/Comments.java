package com.tanu.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="COMMENTS_TBL")
@Setter
@Getter
public class Comments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	private String name;
	private String email;
	@Lob
	private String comment;
	@CreationTimestamp
	private LocalDate createdOn;
	private String commentStatus;
	@ManyToOne
	@JoinColumn(name="post_id")
	 @JsonBackReference
	private Posts post;
	
}
