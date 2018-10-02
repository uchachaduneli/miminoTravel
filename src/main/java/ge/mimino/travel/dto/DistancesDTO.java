package ge.mimino.travel.dto;

import ge.mimino.travel.model.Distances;

import java.util.ArrayList;
import java.util.List;

public class DistancesDTO {

    private Integer id;
    private Integer fromPlaceId;
    private Integer toPlaceId;
    private PlaceDTO fromPlace;
    private PlaceDTO toPlace;
    private Double distance;


    public static DistancesDTO parse(Distances record) {
        DistancesDTO dto = new DistancesDTO();
        dto.setId(record.getId());
        dto.setDistance(record.getDistance());
        dto.setFromPlace(PlaceDTO.parse(record.getFromPlace()));
        dto.setToPlace(PlaceDTO.parse(record.getToPlace()));
        dto.setFromPlaceId(record.getFromPlace().getId());
        dto.setToPlaceId(record.getToPlace().getId());
        return dto;
    }


    public static List<DistancesDTO> parseToList(List<Distances> records) {
        ArrayList<DistancesDTO> list = new ArrayList<DistancesDTO>();
        for (Distances record : records) {
            list.add(DistancesDTO.parse(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromPlaceId() {
        return fromPlaceId;
    }

    public void setFromPlaceId(Integer fromPlaceId) {
        this.fromPlaceId = fromPlaceId;
    }

    public Integer getToPlaceId() {
        return toPlaceId;
    }

    public void setToPlaceId(Integer toPlaceId) {
        this.toPlaceId = toPlaceId;
    }

    public PlaceDTO getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(PlaceDTO fromPlace) {
        this.fromPlace = fromPlace;
    }

    public PlaceDTO getToPlace() {
        return toPlace;
    }

    public void setToPlace(PlaceDTO toPlace) {
        this.toPlace = toPlace;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
