package org.sist.sist_admin_boot.notice;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

// DTO == create 할때 입력하는 값

@Setter
@Getter
public class NoticeForm {

	@NotEmpty(message = "제목은 필수입니다.")
	@Column(length = 100)
	private String title;
	
	@NotEmpty(message = "작성자 입력은 필수입니다.")
	@Column(length = 50)
	private String writer;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	
	private LocalDateTime createDate;
	
	
	@Email
	@NotEmpty(message = "이메일 입력은 필수입니다.")
	private String email;
	
	//private MultipartFile uploadFile;
	
	//@NotNull
    //@Column(name = "fix", nullable = false)
    //@ColumnDefault("false")
	private Boolean fix; // 이건 어떻게 줘야할까?
	
}
