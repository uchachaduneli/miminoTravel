package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "request")
public class Request {
    private Integer id;
    private String contactEmail;
    private Integer combined;
    private Date tourStart;
    private Date tourEnd;
    private Integer daysCount;
    private Integer nightsCount;
    private Timestamp arrivalTime;
    private Timestamp leaveTime;
    private City arrivalCity;
    private City leaveCity;
    private String tourType;
    private Integer guideDriver;
    private Language guideLanguage;
    private String hotelCategory;
    private MealCategory mealCategory;
    private String entranceFees;
    private Currency currency;
    private String comment;
    private Double budget;
    private PackageCategory packageCategory;
    private Timestamp createDate;
    private Users user;
    private String requestKey;
    private String strTourStart;
    private String strTourEnd;
    private String strLeaveTime;
    private String tourCode;
    private String nationality;
    private String strArrivalTime;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contact_email")
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Basic
    @Column(name = "combined")
    public Integer getCombined() {
        return combined;
    }

    public void setCombined(Integer combined) {
        this.combined = combined;
    }

    @Basic
    @Column(name = "tour_start")
    public Date getTourStart() {
        return tourStart;
    }

    public void setTourStart(Date tourStart) {
        this.tourStart = tourStart;
    }

    @Basic
    @Column(name = "tour_end")
    public Date getTourEnd() {
        return tourEnd;
    }

    public void setTourEnd(Date tourEnd) {
        this.tourEnd = tourEnd;
    }

    @Basic
    @Column(name = "days_count")
    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    @Basic
    @Column(name = "nights_count")
    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    @Basic
    @Column(name = "arrival_time")
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "leave_time")
    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    @JoinColumn(name = "arrival_city_id")
    @OneToOne
    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    @JoinColumn(name = "leave_city_id")
    @OneToOne
    public City getLeaveCity() {
        return leaveCity;
    }

    public void setLeaveCity(City leaveCity) {
        this.leaveCity = leaveCity;
    }

    @Basic
    @Column(name = "tour_type")
    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    @Basic
    @Column(name = "guide_driver")
    public Integer getGuideDriver() {
        return guideDriver;
    }

    public void setGuideDriver(Integer guideDriver) {
        this.guideDriver = guideDriver;
    }

    @JoinColumn(name = "guide_language_id")
    @OneToOne
    public Language getGuideLanguage() {
        return guideLanguage;
    }

    public void setGuideLanguage(Language guideLanguage) {
        this.guideLanguage = guideLanguage;
    }

    @Basic
    @Column(name = "hotel_category")
    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    @JoinColumn(name = "meal_category_id")
    @OneToOne
    public MealCategory getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(MealCategory mealCategory) {
        this.mealCategory = mealCategory;
    }

    @Basic
    @Column(name = "entrance_fees")
    public String getEntranceFees() {
        return entranceFees;
    }

    public void setEntranceFees(String entranceFees) {
        this.entranceFees = entranceFees;
    }

    @JoinColumn(name = "currency_id")
    @OneToOne
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "budget")
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @JoinColumn(name = "package_category_id")
    @OneToOne
    public PackageCategory getPackageCategory() {
        return packageCategory;
    }

    public void setPackageCategory(PackageCategory packageCategory) {
        this.packageCategory = packageCategory;
    }

    @JoinColumn(name = "user_id")
    @OneToOne
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Basic
    @Column(name = "create_date", insertable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "request_key", updatable = false)
    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    @Basic
    @Column(name = "str_tour_start")
    public String getStrTourStart() {
        return strTourStart;
    }

    public void setStrTourStart(String strTourStart) {
        this.strTourStart = strTourStart;
    }

    @Basic
    @Column(name = "str_tour_end")
    public String getStrTourEnd() {
        return strTourEnd;
    }

    public void setStrTourEnd(String strTourEnd) {
        this.strTourEnd = strTourEnd;
    }

    @Basic
    @Column(name = "str_leave_time")
    public String getStrLeaveTime() {
        return strLeaveTime;
    }

    public void setStrLeaveTime(String strLeaveTime) {
        this.strLeaveTime = strLeaveTime;
    }

    @Basic
    @Column(name = "tour_code")
    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    @Basic
    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "str_arrival_time")
    public String getStrArrivalTime() {
        return strArrivalTime;
    }

    public void setStrArrivalTime(String strArrivalTime) {
        this.strArrivalTime = strArrivalTime;
    }

}
