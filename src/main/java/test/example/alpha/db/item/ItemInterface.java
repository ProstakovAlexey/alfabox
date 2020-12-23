package test.example.alpha.db.item;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInterface extends CrudRepository<ItemEntity, Long> {

    @ReadOnlyProperty
    @Query(
            value = "SELECT i FROM ItemEntity i WHERE i.contained_in=:parent and i.color=:color"
    )
    List<ItemEntity> getByParentAndColor(Long parent, String color);
}
