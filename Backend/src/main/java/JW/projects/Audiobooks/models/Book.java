package JW.projects.Audiobooks.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(
        name = "books"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends AbstractIdentifiableModel {
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


    @Override
    public String toString() {
        return "Books{" +
                "owner=" + owner +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url_text_source='" + url_text_source + '\'' +
                ", language='" + language + '\'' +
                ", copyright_year=" + copyright_year +
                ", num_sections=" + num_sections +
                ", url_zip_file='" + url_zip_file + '\'' +
                ", url_rss='" + url_rss + '\'' +
                ", url_project='" + url_project + '\'' +
                ", url_librivox='" + url_librivox + '\'' +
                ", url_iarchive='" + url_iarchive + '\'' +
                ", url_other='" + url_other + '\'' +
                ", totaltime=" + totaltime +
                ", totaltimesecs=" + totaltimesecs +
                ", authors='" + authors + '\'' +
                ", sections='" + sections + '\'' +
                ", genres='" + genres + '\'' +
                ", translators='" + translators + '\'' +
                '}';
    }
}
