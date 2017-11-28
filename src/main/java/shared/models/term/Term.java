package shared.models.term;

import shared.repository.jpa.Timestamped;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "term")
public class Term implements Timestamped {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "term")
    private String term;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Term() {
    }

    public Term(long id, String term, Date createdAt, Date updatedAt) {
        this.id = id;
        this.term = term;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Term(String term) {
        this.term = term;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
