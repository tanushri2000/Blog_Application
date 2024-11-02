package com.tanu.binding;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCommentForm {
	 private Long postId;
	private String name;
	private String email;

	private String comment;
	
	

}
