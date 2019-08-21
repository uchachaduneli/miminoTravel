package ge.mimino.travel.dto;

import ge.mimino.travel.model.TouristCount;

import java.util.ArrayList;
import java.util.List;

public class TouristCountDTO {

  private Integer id;
  private Integer requestId;
  private Integer count;
    private Integer plusCount;
    private String plusCountStr;


  public static TouristCountDTO parse(TouristCount record) {
    TouristCountDTO dto = new TouristCountDTO();
    dto.setId(record.getId());
    dto.setCount(record.getCount());
    dto.setRequestId(record.getRequestId());
    dto.setPlusCount(record.getPlusCount());
      dto.setPlusCountStr(record.getPlusCountStr());
    return dto;
  }


  public static List<TouristCountDTO> parseToList(List<TouristCount> records) {
    ArrayList<TouristCountDTO> list = new ArrayList<TouristCountDTO>();
    for (TouristCount record : records) {
      list.add(TouristCountDTO.parse(record));
    }
    return list;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

    public Integer getPlusCount() {
    return plusCount;
  }

    public void setPlusCount(Integer plusCount) {
    this.plusCount = plusCount;
  }

    public String getPlusCountStr() {
        return plusCountStr;
    }

    public void setPlusCountStr(String plusCountStr) {
        this.plusCountStr = plusCountStr;
    }
}
