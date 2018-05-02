package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Request {
    private Integer id;
    private String contactEmail;
    private Integer combined;
    private Date tourStart;
    private Date tourEnd;
    private Integer daysCount;
    private Integer nightsCount;
    private Integer touristsCount;
    private String touristsCountNote;
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
    @Column(name = "tourists_count")
    public Integer getTouristsCount() {
        return touristsCount;
    }

    public void setTouristsCount(Integer touristsCount) {
        this.touristsCount = touristsCount;
    }

    @Basic
    @Column(name = "tourists_count_note")
    public String getTouristsCountNote() {
        return touristsCountNote;
    }

    public void setTouristsCountNote(String touristsCountNote) {
        this.touristsCountNote = touristsCountNote;
    }

    @Basic
    @Column(name = "arrival_time", insertable = false, updatable = false)
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "leave_time", insertable = false, updatable = false)
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

    @Basic
    @Column(name = "create_date", insertable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
