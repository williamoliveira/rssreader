package shared.repository.jpa;

import java.util.Date;

public interface Timestamped {
    public Date getCreatedAt();
    public void setCreatedAt(Date createdAt);
    public Date getUpdatedAt();
    public void setUpdatedAt(Date updatedAt);
}
