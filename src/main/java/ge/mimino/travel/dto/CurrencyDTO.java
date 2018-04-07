package ge.mimino.travel.dto;

import ge.mimino.travel.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDTO {

    private Integer id;
    private String name;


    public static CurrencyDTO parse(Currency record) {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        return dto;
    }


    public static List<CurrencyDTO> parseToList(List<Currency> records) {
        ArrayList<CurrencyDTO> list = new ArrayList<CurrencyDTO>();
        for (Currency record : records) {
            list.add(CurrencyDTO.parse(record));
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
