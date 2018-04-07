package ge.mimino.travel.dto;

import ge.mimino.travel.model.MealCategory;

import java.util.ArrayList;
import java.util.List;

public class MealCategoryDTO {

    private Integer id;
    private String name;


    public static MealCategoryDTO parse(MealCategory record) {
        MealCategoryDTO dto = new MealCategoryDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<MealCategoryDTO> parseToList(List<MealCategory> records) {
        ArrayList<MealCategoryDTO> list = new ArrayList<MealCategoryDTO>();
        for (MealCategory record : records) {
            list.add(MealCategoryDTO.parse(record));
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
