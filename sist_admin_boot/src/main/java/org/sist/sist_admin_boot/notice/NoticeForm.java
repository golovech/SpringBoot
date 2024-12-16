package org.sist.sist_admin_boot.notice;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeForm {

	@NotEmpty(message = "제목은 필수입니다.")
	private String title;
	
	@NotEmpty(message = "작성자 입력은 필수입니다.")
	private String writer;
	
	private String content;
	
	private Integer viewCount;
	
	
	@Email
	@NotEmpty(message = "이메일 입력은 필수입니다.")
	private String email;
	
	private MultipartFile uploadFile;
	
    @Column(name = "fix", nullable = false)
	@NotNull(message = "fix는 필수 입력 값입니다.")
	private Boolean fix; 
	
}
