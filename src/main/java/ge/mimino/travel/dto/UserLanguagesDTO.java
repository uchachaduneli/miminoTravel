package ge.mimino.travel.dto;

import ge.mimino.travel.model.UserLanguages;

import java.util.ArrayList;
import java.util.List;

public class UserLanguagesDTO {

    private Integer id;
    private Integer userId;
    private LanguageDTO language;


    public static UserLanguagesDTO parse(UserLanguages record) {
        UserLanguagesDTO dto = new UserLanguagesDTO();
        dto.setId(record.getId());
        dto.setUserId(record.getUserId());
        dto.setLanguage(LanguageDTO.parse(record.getLanguage()));
        return dto;
    }


    public static List<UserLanguagesDTO> parseToList(List<UserLanguages> records) {
        ArrayList<UserLanguagesDTO> list = new ArrayList<UserLanguagesDTO>();
        for (UserLanguages record : records) {
            list.add(UserLanguagesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }
}
