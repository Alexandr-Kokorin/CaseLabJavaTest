package caselab.java.manager.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String file;
    private String title;
    private OffsetDateTime creationDate;
    private String description;

    public FileEntity(String file, String title, OffsetDateTime creationDate, String description) {
        this.file = file;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }
}
