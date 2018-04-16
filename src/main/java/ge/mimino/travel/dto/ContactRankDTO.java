package ge.mimino.travel.dto;

import ge.mimino.travel.model.ContactRank;

import java.util.ArrayList;
import java.util.List;

public class ContactRankDTO {

    private Integer id;
    private String name;


    public static ContactRankDTO parse(ContactRank record) {
        ContactRankDTO dto = new ContactRankDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<ContactRankDTO> parseToList(List<ContactRank> records) {
        ArrayList<ContactRankDTO> list = new ArrayList<ContactRankDTO>();
        for (ContactRank record : records) {
            list.add(ContactRankDTO.parse(record));
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
