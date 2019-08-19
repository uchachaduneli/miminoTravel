package ge.mimino.travel.request;

import ge.mimino.travel.dto.CombinedCountry;
import ge.mimino.travel.model.TouristCount;

import java.sql.Date;
import java.util.List;

public class AddRequest {
    private Integer id;
    private String contactEmail;
    private Integer combined;
    private Date tourStart;
    private Date tourEnd;
    private Date tourStartTo;
    private Date tourEndTo;
    private Integer daysCount;
    private Integer nightsCount;
    private String arrivalTime;
    private String leaveTime;
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
    private Integer userTypeId;
    private List<Integer> otherDetails;
    private List<CombinedCountry> combinedCountries;
    private List<TouristCount> touristCount;
    private String strTourStart;
    private String strTourEnd;
    private String strArrivalTime;
    private String strLeaveTime;
    private String tourCode;
    private String nationality;
    private Double eur;
    private Double usd;

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
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

    public Date getTourStartTo() {
        return tourStartTo;
    }

    public void setTourStartTo(Date tourStartTo) {
        this.tourStartTo = tourStartTo;
    }

    public Date getTourEndTo() {
        return tourEndTo;
    }

    public void setTourEndTo(Date tourEndTo) {
        this.tourEndTo = tourEndTo;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
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

    public Integer getGuideLanguageId() {
        return guideLanguageId;
    }

    public void setGuideLanguageId(Integer guideLanguageId) {
        this.guideLanguageId = guideLanguageId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(List<Integer> otherDetails) {
        this.otherDetails = otherDetails;
    }

    public List<CombinedCountry> getCombinedCountries() {
        return combinedCountries;
    }

    public void setCombinedCountries(List<CombinedCountry> combinedCountries) {
        this.combinedCountries = combinedCountries;
    }

    public List<TouristCount> getTouristCount() {
        return touristCount;
    }

    public void setTouristCount(List<TouristCount> touristCount) {
        this.touristCount = touristCount;
    }

    public String getStrTourStart() {
        return strTourStart;
    }

    public void setStrTourStart(String strTourStart) {
        this.strTourStart = strTourStart;
    }

    public String getStrTourEnd() {
        return strTourEnd;
    }

    public void setStrTourEnd(String strTourEnd) {
        this.strTourEnd = strTourEnd;
    }

    public String getStrArrivalTime() {
        return strArrivalTime;
    }

    public void setStrArrivalTime(String strArrivalTime) {
        this.strArrivalTime = strArrivalTime;
    }

    public String getStrLeaveTime() {
        return strLeaveTime;
    }

    public void setStrLeaveTime(String strLeaveTime) {
        this.strLeaveTime = strLeaveTime;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
