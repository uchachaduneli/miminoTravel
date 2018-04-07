package ge.mimino.travel.dto;

import ge.mimino.travel.model.PackageCategory;

import java.util.ArrayList;
import java.util.List;

public class PackageCategoryDTO {

    private Integer id;
    private String name;


    public static PackageCategoryDTO parse(PackageCategory record) {
        PackageCategoryDTO dto = new PackageCategoryDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<PackageCategoryDTO> parseToList(List<PackageCategory> records) {
        ArrayList<PackageCategoryDTO> list = new ArrayList<PackageCategoryDTO>();
        for (PackageCategory record : records) {
            list.add(PackageCategoryDTO.parse(record));
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
