package JW.projects.Audiobooks.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractIdentifiableModel {
        @Id
        @GeneratedValue(
                strategy = GenerationType.IDENTITY
        )
        private int id;

        public AbstractIdentifiableModel() {
        }

    public void setId(int id) {
            this.id = id;
        }

    public int hashCode() {
            return Objects.hash(new Object[]{this.id});
        }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AbstractIdentifiableModel that = (AbstractIdentifiableModel) o;
            return this.id == that.id;
        } else {
             return false;
        }
    }


}
