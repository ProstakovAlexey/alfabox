package test.example.alpha.loader.pojo;

import lombok.Data;
import test.example.alpha.loader.LoaderInterface;

@Data
public class Box {
    private long id;
    private Long contained_in;
}

