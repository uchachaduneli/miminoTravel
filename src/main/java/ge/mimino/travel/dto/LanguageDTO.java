package ge.mimino.travel.dto;

import ge.mimino.travel.model.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageDTO {

    private Integer id;
    private String name;


    public static LanguageDTO parse(Language record) {
        LanguageDTO dto = new LanguageDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<LanguageDTO> parseToList(List<Language> records) {
        ArrayList<LanguageDTO> list = new ArrayList<LanguageDTO>();
        for (Language record : records) {
            list.add(LanguageDTO.parse(record));
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
