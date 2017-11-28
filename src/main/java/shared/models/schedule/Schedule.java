package shared.models.schedule;

import shared.repository.jpa.Timestamped;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule implements Timestamped {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "time")
    private String time;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Schedule() {
    }

    public Schedule(long id, String time, Date createdAt, Date updatedAt) {
        this.id = id;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Schedule(String time) {
        this.time = time;
    }

    public int getHour() {
        return Integer.parseInt(time.split(":")[0]);
    }

    public int getMinutes() {
        return Integer.parseInt(time.split(":")[1]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
