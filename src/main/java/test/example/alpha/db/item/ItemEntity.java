package test.example.alpha.db.item;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private long id;
    private Long contained_in;
    private String color;

    public ItemEntity() {}

    public ItemEntity(long id, Long contained_in, String color) {
        this.id = id;
        this.contained_in = contained_in;
        this.color = color;
    }

    public long getId() {
        return id;
    }
}
