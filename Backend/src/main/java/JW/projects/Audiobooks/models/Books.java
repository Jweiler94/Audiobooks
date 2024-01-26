package JW.projects.Audiobooks.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(
        name = "books"
)
public class Books extends AbstractIdentifiableModel {
    @ManyToOne
    @JoinColumn(
            name = "owner_id"
    )

    private User owner;
    private String title;
    private String description;
    private String url_text_source;
    private String language;
    private Integer copyright_year;
    private Integer num_sections;
    private String url_zip_file;
    private String url_rss;
    private String url_project;
    private String url_librivox;
    private String url_iarchive;
    private String url_other;
    private Double totaltime;
    private Integer totaltimesecs;
    private String authors;
    private String sections;
    private String genres;
    private String translators;

}
