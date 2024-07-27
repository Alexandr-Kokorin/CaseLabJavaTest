package caselab.java.manager.service.jpa;

import caselab.java.manager.controller.dto.FileRequest;
import caselab.java.manager.controller.dto.FileResponse;
import caselab.java.manager.controller.dto.IdResponse;
import caselab.java.manager.controller.dto.ListFilesResponse;
import caselab.java.manager.domain.entity.FileEntity;
import caselab.java.manager.domain.jpa.JpaFileRepository;
import caselab.java.manager.service.FileService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JpaFileService implements FileService {

    private JpaFileRepository fileRepository;

    @Override
    public IdResponse add(FileRequest fileRequest) {
        FileEntity file = new FileEntity(fileRequest.file(), fileRequest.title(),
            fileRequest.creation_date(), fileRequest.description());
        fileRepository.saveAndFlush(file);
        return new IdResponse(file.getId());
    }

    @Override
    public FileResponse get(long id) {
        var entity = fileRepository.findById(id);
        if (entity.isEmpty()) {
            return null;
        }
        FileEntity file = entity.get();
        return new FileResponse(file.getFile(), file.getTitle(), file.getCreationDate(), file.getDescription());
    }

    @Override
    public ListFilesResponse getList() {
        var entityList = fileRepository.findAllWithSort();
        List<FileResponse> list = new ArrayList<>();
        for (FileEntity file : entityList) {
            list.add(new FileResponse(file.getFile(), file.getTitle(), file.getCreationDate(), file.getDescription()));
        }
        return new ListFilesResponse(list.size(), list);
    }
}
