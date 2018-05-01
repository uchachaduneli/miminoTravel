package ge.mimino.travel.request;

import java.sql.Date;
import java.util.List;

public class AddCaseRequest {
    private Integer id;
    private String contactEmail;
    private Integer combined;
    private Date tourStart;
    private Date tourEnd;
    private Integer daysCount;
    private Integer nightsCount;
    private Integer touristsCount;
    private String touristsCountNote;
    private Date arrivalTime;
    private Date leaveTime;
    private Integer arrivalCityId;
    private Integer leaveCityId;
    private String tourType;
    private Integer guideDriver;
    private Integer guideLanguageId;
    private String hotelCategory;
    private Integer mealCategoryId;
    private String entranceFees;
    private Integer currencyId;
    private String comment;
    private Double budget;
    private Integer packageCategoryId;
    private Integer userId;
    private List<Integer> otherDetails;

    public Integer getGuideLanguageId() {
        return guideLanguageId;
    }

    public void setGuideLanguageId(Integer guideLanguageId) {
        this.guideLanguageId = guideLanguageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getCombined() {
        return combined;
    }

    public void setCombined(Integer combined) {
        this.combined = combined;
    }

    public Date getTourStart() {
        return tourStart;
    }

    public void setTourStart(Date tourStart) {
        this.tourStart = tourStart;
    }

    public Date getTourEnd() {
        return tourEnd;
    }

    public void setTourEnd(Date tourEnd) {
        this.tourEnd = tourEnd;
    }

    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    public Integer getTouristsCount() {
        return touristsCount;
    }

    public void setTouristsCount(Integer touristsCount) {
        this.touristsCount = touristsCount;
    }

    public String getTouristsCountNote() {
        return touristsCountNote;
    }

    public void setTouristsCountNote(String touristsCountNote) {
        this.touristsCountNote = touristsCountNote;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(Integer arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public Integer getLeaveCityId() {
        return leaveCityId;
    }

    public void setLeaveCityId(Integer leaveCityId) {
        this.leaveCityId = leaveCityId;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public Integer getGuideDriver() {
        return guideDriver;
    }

    public void setGuideDriver(Integer guideDriver) {
        this.guideDriver = guideDriver;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    public Integer getMealCategoryId() {
        return mealCategoryId;
    }

    public void setMealCategoryId(Integer mealCategoryId) {
        this.mealCategoryId = mealCategoryId;
    }

    public String getEntranceFees() {
        return entranceFees;
    }

    public void setEntranceFees(String entranceFees) {
        this.entranceFees = entranceFees;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getPackageCategoryId() {
        return packageCategoryId;
    }

    public void setPackageCategoryId(Integer packageCategoryId) {
        this.packageCategoryId = packageCategoryId;
    }

    public List<Integer> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(List<Integer> otherDetails) {
        this.otherDetails = otherDetails;
    }
}
