package ge.mimino.travel.dto;

import ge.mimino.travel.model.Stage;

import java.util.ArrayList;
import java.util.List;

public class StageDTO {

    private Integer id;
    private String name;


    public static StageDTO parse(Stage record) {
        StageDTO dto = new StageDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<StageDTO> parseToList(List<Stage> records) {
        ArrayList<StageDTO> list = new ArrayList<StageDTO>();
        for (Stage record : records) {
            list.add(StageDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
