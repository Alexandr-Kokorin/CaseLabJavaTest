package caselab.java.manager;

import caselab.java.manager.controller.dto.FileRequest;
import caselab.java.manager.controller.dto.IdResponse;
import caselab.java.manager.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.Base64;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
public class FileServiceTest extends IntegrationTest {

    @Autowired
    private FileService fileService;

    @Test
    @Transactional
    @Rollback
    void addTest() {
        fileService.add(new FileRequest(
            Base64.getEncoder().encodeToString("test".getBytes()),
            "title",
            OffsetDateTime.now(),
            "desc"
        ));

        var list = fileService.getList();

        assertThat(list.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    void getTest() {
        IdResponse id = fileService.add(new FileRequest(
            Base64.getEncoder().encodeToString("test".getBytes()),
            "title",
            OffsetDateTime.now(),
            "desc"
        ));

        var file = fileService.get(id.id());

        assertThat(file.title()).isEqualTo("title");
    }

    @Test
    @Transactional
    @Rollback
    void getNullTest() {
        IdResponse id = fileService.add(new FileRequest(
            Base64.getEncoder().encodeToString("test".getBytes()),
            "title",
            OffsetDateTime.now(),
            "desc"
        ));

        var file = fileService.get(id.id() + 1);

        assertThat(file).isEqualTo(null);
    }

    @Test
    @Transactional
    @Rollback
    void getListTest() {
        fileService.add(new FileRequest(
            Base64.getEncoder().encodeToString("test1".getBytes()),
            "title1",
            OffsetDateTime.now(),
            "desc1"
        ));
        fileService.add(new FileRequest(
            Base64.getEncoder().encodeToString("test2".getBytes()),
            "title2",
            OffsetDateTime.now(),
            "desc2"
        ));

        var list = fileService.getList();

        assertThat(list.files().get(0).title()).isEqualTo("title1");
    }

    @Test
    @Transactional
    @Rollback
    void getEmptyListTest() {
        var list = fileService.getList();

        assertThat(list.count()).isEqualTo(0);
    }
}
