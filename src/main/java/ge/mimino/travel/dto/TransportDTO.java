package ge.mimino.travel.dto;

import ge.mimino.travel.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class TransportDTO {

  public static Integer SEDAN = 1;
  public static Integer VIANO = 5;
  public static Integer SPRINTER = 3;
  public static Integer BUS = 4;
  private Integer id;
  private Integer seatsCount;
  private FuelDTO fuel;
  private Integer fuelId;
  private Double fuelConsumption;
  private Double price;
  private String nameEn;
  private String nameGe;
  private String nameFr;
  private String nameIt;
  private String nameSp;
  private String namePo;
  private String nameRu;
  private String descriptionEn;
  private String descriptionGe;
  private String descriptionFr;
  private String descriptionIt;
  private String descriptionSp;
  private String descriptionPo;
  private String descriptionRu;

  public static TransportDTO parse(Transport record) {
    TransportDTO dto = new TransportDTO();
    dto.setId(record.getId());
    dto.setNameEn(record.getNameEn());
    dto.setNameGe(record.getNameGe());
    dto.setNameFr(record.getNameFr());
    dto.setNameIt(record.getNameIt());
    dto.setNameSp(record.getNameSp());
    dto.setNamePo(record.getNamePo());
    dto.setNameRu(record.getNameRu());
    dto.setDescriptionEn(record.getDescriptionEn());
    dto.setDescriptionGe(record.getDescriptionGe());
    dto.setDescriptionFr(record.getDescriptionFr());
    dto.setDescriptionIt(record.getDescriptionIt());
    dto.setDescriptionSp(record.getDescriptionSp());
    dto.setDescriptionPo(record.getDescriptionPo());
    dto.setDescriptionRu(record.getDescriptionRu());
    dto.setSeatsCount(record.getSeatsCount());
    dto.setFuel(FuelDTO.parse(record.getFuel()));
    dto.setFuelId(record.getFuel().getId());
    dto.setFuelConsumption(record.getFuelConsumption());
    dto.setPrice(record.getPrice());
    return dto;
  }


  public static List<TransportDTO> parseToList(List<Transport> records) {
    ArrayList<TransportDTO> list = new ArrayList<TransportDTO>();
    for (Transport record : records) {
      list.add(TransportDTO.parse(record));
    }
    return list;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSeatsCount() {
    return seatsCount;
  }

  public void setSeatsCount(Integer seatsCount) {
    this.seatsCount = seatsCount;
  }

  public FuelDTO getFuel() {
    return fuel;
  }

  public void setFuel(FuelDTO fuel) {
    this.fuel = fuel;
  }

  public Integer getFuelId() {
    return fuelId;
  }

  public void setFuelId(Integer fuelId) {
    this.fuelId = fuelId;
  }

  public Double getFuelConsumption() {
    return fuelConsumption;
  }

  public void setFuelConsumption(Double fuelConsumption) {
    this.fuelConsumption = fuelConsumption;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public String getNameGe() {
    return nameGe;
  }

  public void setNameGe(String nameGe) {
    this.nameGe = nameGe;
  }

  public String getNameFr() {
    return nameFr;
  }

  public void setNameFr(String nameFr) {
    this.nameFr = nameFr;
  }

  public String getNameIt() {
    return nameIt;
  }

  public void setNameIt(String nameIt) {
    this.nameIt = nameIt;
  }

  public String getNameSp() {
    return nameSp;
  }

  public void setNameSp(String nameSp) {
    this.nameSp = nameSp;
  }

  public String getNamePo() {
    return namePo;
  }

  public void setNamePo(String namePo) {
    this.namePo = namePo;
  }

  public String getNameRu() {
    return nameRu;
  }

  public void setNameRu(String nameRu) {
    this.nameRu = nameRu;
  }

  public String getDescriptionEn() {
    return descriptionEn;
  }

  public void setDescriptionEn(String descriptionEn) {
    this.descriptionEn = descriptionEn;
  }

  public String getDescriptionGe() {
    return descriptionGe;
  }

  public void setDescriptionGe(String descriptionGe) {
    this.descriptionGe = descriptionGe;
  }

  public String getDescriptionFr() {
    return descriptionFr;
  }

  public void setDescriptionFr(String descriptionFr) {
    this.descriptionFr = descriptionFr;
  }

  public String getDescriptionIt() {
    return descriptionIt;
  }

  public void setDescriptionIt(String descriptionIt) {
    this.descriptionIt = descriptionIt;
  }

  public String getDescriptionSp() {
    return descriptionSp;
  }

  public void setDescriptionSp(String descriptionSp) {
    this.descriptionSp = descriptionSp;
  }

  public String getDescriptionPo() {
    return descriptionPo;
  }

  public void setDescriptionPo(String descriptionPo) {
    this.descriptionPo = descriptionPo;
  }

  public String getDescriptionRu() {
    return descriptionRu;
  }

  public void setDescriptionRu(String descriptionRu) {
    this.descriptionRu = descriptionRu;
  }
}
