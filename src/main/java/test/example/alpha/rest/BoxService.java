package test.example.alpha.rest;

import org.springframework.stereotype.Service;
import test.example.alpha.db.box.BoxEntity;
import test.example.alpha.db.box.BoxInterface;
import test.example.alpha.db.item.ItemEntity;
import test.example.alpha.db.item.ItemInterface;
import test.example.alpha.error.BadRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoxService {

    final BoxInterface boxRepo;
    final ItemInterface itemRepo;
    List<Long> result;


    public BoxService(BoxInterface boxRepo, ItemInterface itemInterface) {
        this.boxRepo = boxRepo;
        this.itemRepo = itemInterface;
    }

    public List<Long> getItems(BoxRequest boxRequest) {
        result = new ArrayList<>();
        Long id = boxRequest.getId();
        String color = boxRequest.getColor();
        if (id == null) throw new BadRequest("ID must be not null");
        if (color == null) throw new BadRequest("COLOR must be not null");
        getItemsByIdAndColor(id, color);
        return result;
    }


    private void getItemsByIdAndColor(Long id, String color) {
        for (ItemEntity item : itemRepo.getByParentAndColor(id, color)) {
            result.add(item.getId());
        }
        Optional<BoxEntity> optional = boxRepo.findById(id);
        if (optional.isPresent()) {
            BoxEntity parentBox = optional.get();
            for (BoxEntity nextBoxEntity : boxRepo.findAllByContainedIn(parentBox.getId())) {
                getItemsByIdAndColor(nextBoxEntity.getId(), color);
            }
        }
    }
}
