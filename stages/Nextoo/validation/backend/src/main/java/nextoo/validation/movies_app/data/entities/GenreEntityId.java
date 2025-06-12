package nextoo.validation.movies_app.data.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenreEntityId implements Serializable {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private ProfileEntity pid;

    private Integer gid;

    public ProfileEntity getPid() {
        return pid;
    }

    public void setPid(ProfileEntity pid) {
        this.pid = pid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GenreEntityId entity = (GenreEntityId) o;
        return Objects.equals(this.gid, entity.gid) &&
                Objects.equals(this.pid, entity.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, pid);
    }

}