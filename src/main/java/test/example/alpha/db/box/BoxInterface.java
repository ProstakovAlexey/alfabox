package test.example.alpha.db.box;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoxInterface extends CrudRepository<BoxEntity, Long> {
    @ReadOnlyProperty
    @Query(
            value = "SELECT b FROM BoxEntity b  WHERE b.contained_in=:containedIn"
    )
    List<BoxEntity> findAllByContainedIn(Long containedIn);
}
