package ge.mimino.travel.dto;

import ge.mimino.travel.model.PlaceMealCategory;

import java.util.ArrayList;
import java.util.List;

public class PlaceMealCategoryDTO {

    private Integer id;
    private MealCategoryDTO mealCategory;
    private Integer mealCategoryId;
    private PlaceDTO place;
    private Integer placeId;
    private RequestDTO request;
    private Integer requestId;


    public static PlaceMealCategoryDTO parse(PlaceMealCategory record) {
        PlaceMealCategoryDTO dto = new PlaceMealCategoryDTO();
        dto.setId(record.getId());
        dto.setMealCategory(MealCategoryDTO.parse(record.getMealCategory()));
        dto.setMealCategoryId(record.getMealCategory().getId());
        dto.setPlace(PlaceDTO.parse(record.getPlace()));
        dto.setPlaceId(record.getPlace().getId());
        dto.setRequest(RequestDTO.parse(record.getRequest()));
        dto.setRequestId(record.getRequest().getId());
        return dto;
    }


    public static List<PlaceMealCategoryDTO> parseToList(List<PlaceMealCategory> records) {
        ArrayList<PlaceMealCategoryDTO> list = new ArrayList<PlaceMealCategoryDTO>();
        for (PlaceMealCategory record : records) {
            list.add(PlaceMealCategoryDTO.parse(record));
        }
        return list;
    }

    public Integer getMealCategoryId() {
        return mealCategoryId;
    }

    public void setMealCategoryId(Integer mealCategoryId) {
        this.mealCategoryId = mealCategoryId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MealCategoryDTO getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategoryDTO mealCategory) {
        this.mealCategory = mealCategory;
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }
}
