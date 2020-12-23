package test.example.alpha.db;

import org.springframework.stereotype.Component;
import test.example.alpha.db.box.BoxEntity;
import test.example.alpha.db.box.BoxInterface;
import test.example.alpha.db.item.ItemEntity;
import test.example.alpha.db.item.ItemInterface;
import test.example.alpha.loader.pojo.Box;
import test.example.alpha.loader.pojo.Item;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaveToBD {
    final BoxInterface boxRepo;
    final ItemInterface itemRepo;

    public SaveToBD(BoxInterface boxInterface, ItemInterface itemInterface) {
        this.boxRepo = boxInterface;
        this.itemRepo = itemInterface;
    }

    public void saveBoxes(List<Box> boxes) {
        List<BoxEntity> boxesEntity = new ArrayList<>();
        for (Box box: boxes) {
            BoxEntity boxEntity = new BoxEntity(box.getId(), box.getContained_in());
            boxesEntity.add(boxEntity);
        }
        boxRepo.saveAll(boxesEntity);
    }

    public void saveItems(List<Item> items) {
        List<ItemEntity> itemsEntity = new ArrayList<>();
        for (Item item: items) {
            ItemEntity itemEntity = new ItemEntity(item.getId(),
                    item.getContained_in(), item.getColor());
            itemsEntity.add(itemEntity);
        }
        itemRepo.saveAll(itemsEntity);
    }
}
