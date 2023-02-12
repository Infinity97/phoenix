package com.pheonix.core.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.utils.enums.FileType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.validation.constraints.*;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadFileRequest {

	@NotBlank(message = "File Information cannot be empty")
	private String fileData;//BASE 64 String for file upload.
	@NotBlank(message = "File Name cannot be empty")
	@Pattern(regexp = "^[\\w\\s.-]+\\.[\\w]{1,4}$")
	private String fileName;//name of the file with extension.
	private FileType fileType;
	private String contextId;

}
