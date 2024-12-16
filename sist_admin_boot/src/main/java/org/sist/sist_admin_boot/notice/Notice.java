package org.sist.sist_admin_boot.notice;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String title;
	
	@Column(length = 50)
	private String writer;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createDate;
	
	@Column(columnDefinition = "integer default 0")
	private Integer viewCount;
	
	private String email;
	
	private String filePath;
	
	private Boolean fix;
	
	

}
