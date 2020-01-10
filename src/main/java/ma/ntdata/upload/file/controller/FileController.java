package ma.ntdata.upload.file.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.ntdata.upload.file.model.File;
import ma.ntdata.upload.file.repository.FileRepository;

@RestController
@CrossOrigin("http://localhost:4200")
public class FileController {

	@Autowired
	private FileRepository fileRepo;
	@PostMapping("/upload/file")
	public String uploadFile(@RequestParam("filetoupload") MultipartFile file) {
		File fileToUpload;
		
		try {
			fileToUpload = new File(System.currentTimeMillis()+file.getOriginalFilename(),file.getOriginalFilename(),file.getContentType(),file.getBytes());
			fileRepo.save(fileToUpload);
			return "Your File uploaded successfully -> file = "+file.getOriginalFilename()+"  id = "+fileToUpload.getId();
		} catch (IOException e) {
			
			return "FAIL!! ";
		}
	}
	
	@GetMapping("download/file/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String id){
		Optional<File> fileOpt = fileRepo.findById(id);
		
		if(fileOpt.isPresent()) {
			File file = fileOpt.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename\""+file.getName()+"\"")
					.body(file.getFile());
		}
		return ResponseEntity.status(404).body(null);
	}
	
	@GetMapping("download/files")
	public List<File> findAllFiles(){
		return fileRepo.findAll();
	}
}
