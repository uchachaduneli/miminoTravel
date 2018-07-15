package ge.mimino.travel.dto;

import ge.mimino.travel.model.Transport;

import java.util.ArrayList;
import java.util.List;

public class TransportDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer seatsCount;
    private FuelDTO fuel;
    private Integer fuelId;
    private Double fuelConsumption;
    private Double price;


    public static TransportDTO parse(Transport record) {
        TransportDTO dto = new TransportDTO();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setDescription(record.getDescription());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
