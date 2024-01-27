package JW.projects.Audiobooks.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDTO {

    private @NotNull(message = "Owner ID cannot be null") int ownerId;
    private @NotNull String title;
    private String description;
    private String url_text_source;
    private String language;
    private Integer copyright_year;
    private Integer num_sections;
    private @NotNull String url_zip_file;
    private String url_rss;
    private String url_project;
    private String url_librivox;
    private String url_iarchive;
    private String url_other;
    private Double totaltime;
    private Integer totaltimesecs;
    private @NotNull String authors;
    private String sections;
    private @NotNull String genres;
    private String translators;

}
