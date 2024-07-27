package caselab.java.manager.service;

import caselab.java.manager.controller.dto.FileRequest;
import caselab.java.manager.controller.dto.FileResponse;
import caselab.java.manager.controller.dto.IdResponse;
import caselab.java.manager.controller.dto.ListFilesResponse;

public interface FileService {

    IdResponse add(FileRequest fileRequest);

    FileResponse get(long id);

    ListFilesResponse getList();
}
