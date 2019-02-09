package ge.mimino.travel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.mimino.travel.misc.JsonDateSerializeSupport;
import ge.mimino.travel.misc.JsonDateTimeSerializeSupport;
import ge.mimino.travel.model.Request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestDTO {

    private Integer id;
    private String contactEmail;
    private Integer combined;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date tourStart;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date tourEnd;
    private Integer daysCount;
    private Integer nightsCount;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Date arrivalTime;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Date leaveTime;
    private CityDTO arrivalCity;
    private Integer arrivalCityId;
    private CityDTO leaveCity;
    private Integer leaveCityId;
    private String tourType;
    private Integer guideDriver;
    private LanguageDTO guideLanguage;
    private Integer guideLanguageId;
    private String hotelCategory;
    private MealCategoryDTO mealCategory;
    private Integer mealCategoryId;
    private String entranceFees;
    private CurrencyDTO currency;
    private Integer currencyId;
    private String comment;
    private Double budget;
    private PackageCategoryDTO packageCategory;
    private Integer packageCategoryId;
    private UsersDTO user;
    private Integer userId;
    @JsonSerialize(using = JsonDateTimeSerializeSupport.class)
    private Timestamp createDate;
    private String requestKey;
    private String strTourStart;
    private String strTourEnd;
    private String strArrivalTime;
    private String strLeaveTime;
    private String tourCode;
    private String nationality;
    private Double eur;
    private Double usd;

    public static String NAT_FOR_TRANSPORT = "German/Italian/Spain/France";


    public static RequestDTO parse(Request record) {
        RequestDTO dto = new RequestDTO();
        dto.setId(record.getId());
        dto.setContactEmail(record.getContactEmail());
        dto.setCombined(record.getCombined());
        dto.setTourStart(record.getTourStart());
        dto.setTourEnd(record.getTourEnd());
        dto.setDaysCount(record.getDaysCount());
        dto.setNightsCount(record.getNightsCount());
        dto.setArrivalTime(record.getArrivalTime());
        dto.setLeaveTime(record.getLeaveTime());
        dto.setArrivalCity(CityDTO.parse(record.getArrivalCity()));
        dto.setArrivalCityId(record.getArrivalCity().getId());
        dto.setLeaveCity(CityDTO.parse(record.getLeaveCity()));
        dto.setLeaveCityId(record.getLeaveCity().getId());
        dto.setTourType(record.getTourType());
        dto.setGuideDriver(record.getGuideDriver());
        dto.setHotelCategory(record.getHotelCategory());
        dto.setMealCategory(MealCategoryDTO.parse(record.getMealCategory()));
        dto.setMealCategoryId(record.getMealCategory().getId());
        dto.setEntranceFees(record.getEntranceFees());
        dto.setCurrency(CurrencyDTO.parse(record.getCurrency()));
        dto.setCurrencyId(record.getCurrency().getId());
        dto.setComment(record.getComment());
        dto.setBudget(record.getBudget());
        dto.setPackageCategory(PackageCategoryDTO.parse(record.getPackageCategory()));
        dto.setPackageCategoryId(record.getPackageCategory().getId());
        dto.setUser(UsersDTO.parse(record.getUser()));
        dto.setUserId(record.getUser().getUserId());
        dto.setGuideLanguage(LanguageDTO.parse(record.getGuideLanguage()));
        dto.setGuideLanguageId(record.getGuideLanguage().getId());
        dto.setCreateDate(record.getCreateDate());
        dto.setRequestKey(record.getRequestKey());
        dto.setTourCode(record.getTourCode());
        dto.setNationality(record.getNationality());
        dto.setStrArrivalTime(record.getStrArrivalTime());
        dto.setStrLeaveTime(record.getStrLeaveTime());
        dto.setStrTourStart(record.getStrTourStart());
        dto.setStrTourEnd(record.getStrTourEnd());
        dto.setEur(record.getEur());
        dto.setUsd(record.getUsd());
        return dto;
    }


    public static List<RequestDTO> parseToList(List<Request> records) {
        ArrayList<RequestDTO> list = new ArrayList<RequestDTO>();
        for (Request record : records) {
            list.add(RequestDTO.parse(record));
        }
        return list;
    }

    public static String getNatForTransport() {
        return NAT_FOR_TRANSPORT;
    }

    public static void setNatForTransport(String natForTransport) {
        NAT_FOR_TRANSPORT = natForTransport;
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

    public CityDTO getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(CityDTO arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Integer getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(Integer arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public CityDTO getLeaveCity() {
        return leaveCity;
    }

    public void setLeaveCity(CityDTO leaveCity) {
        this.leaveCity = leaveCity;
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

    public LanguageDTO getGuideLanguage() {
        return guideLanguage;
    }

    public void setGuideLanguage(LanguageDTO guideLanguage) {
        this.guideLanguage = guideLanguage;
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

    public MealCategoryDTO getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategoryDTO mealCategory) {
        this.mealCategory = mealCategory;
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

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
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

    public PackageCategoryDTO getPackageCategory() {
        return packageCategory;
    }

    public void setPackageCategory(PackageCategoryDTO packageCategory) {
        this.packageCategory = packageCategory;
    }

    public Integer getPackageCategoryId() {
        return packageCategoryId;
    }

    public void setPackageCategoryId(Integer packageCategoryId) {
        this.packageCategoryId = packageCategoryId;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
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
