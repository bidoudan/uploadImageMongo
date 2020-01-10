package ma.ntdata.upload.file.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import ma.ntdata.upload.file.model.File;

public interface FileRepository extends MongoRepository<File, String>{

	
}
