package ma.ntdata.upload.file.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document(collection = "files")
@AllArgsConstructor
public class File {

	@Id
	
	private String id;
	
	private String name;
	
	private String type;
	
	
	private byte[] file;


	
	
	
}
