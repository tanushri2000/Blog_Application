package com.tanu.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="POSTS_TBL")
@Setter
@Getter
public class Posts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	private String title;
	private String description;
	@Lob
	private String content;
	@Column(name="created_on", updatable=false)
	@CreationTimestamp
	private LocalDate createdOn;
	@UpdateTimestamp
	private LocalDate updatedOn;
	
	
	@OneToMany(mappedBy="post",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JsonManagedReference
	private List<Comments> comments;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	private String deleteStatus;
	


}
