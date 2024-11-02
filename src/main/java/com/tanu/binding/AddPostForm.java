package com.tanu.binding;



import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddPostForm {
	
	private String title;
	private String description;
	@Lob
    private String content;
	private String deleteStatus;

}