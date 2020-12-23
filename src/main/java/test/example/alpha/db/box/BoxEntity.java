package test.example.alpha.db.box;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "box")
public class BoxEntity {
    @Id
    private long id;
    private Long contained_in;

    public BoxEntity() { }

    public BoxEntity(long id, Long contained_in) {
        this.id = id;
        this.contained_in = contained_in;
    }

    public long getId() {
        return id;
    }
}

