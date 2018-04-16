package ge.mimino.travel.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Case {
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
    private City arrivalCity;
    private City leaveCity;
    private String tourType;
    private String guideDriver;
    private String hotelCategory;
    private MealCategory mealCategory;
    private String entranceFees;
    private Currency currency;
    private String comment;
    private Double budget;
    private PackageCategory packageCategory;
    private Timestamp createDate;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contact_email", nullable = false, length = 50)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Basic
    @Column(name = "combined", nullable = false)
    public Integer getCombined() {
        return combined;
    }

    public void setCombined(Integer combined) {
        this.combined = combined;
    }

    @Basic
    @Column(name = "tour_start", nullable = true)
    public Date getTourStart() {
        return tourStart;
    }

    public void setTourStart(Date tourStart) {
        this.tourStart = tourStart;
    }

    @Basic
    @Column(name = "tour_end", nullable = true)
    public Date getTourEnd() {
        return tourEnd;
    }

    public void setTourEnd(Date tourEnd) {
        this.tourEnd = tourEnd;
    }

    @Basic
    @Column(name = "days_count", nullable = false)
    public Integer getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(Integer daysCount) {
        this.daysCount = daysCount;
    }

    @Basic
    @Column(name = "nights_count", nullable = false)
    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    @Basic
    @Column(name = "tourists_count", nullable = false)
    public Integer getTouristsCount() {
        return touristsCount;
    }

    public void setTouristsCount(Integer touristsCount) {
        this.touristsCount = touristsCount;
    }

    @Basic
    @Column(name = "tourists_count_note", nullable = true, length = 250)
    public String getTouristsCountNote() {
        return touristsCountNote;
    }

    public void setTouristsCountNote(String touristsCountNote) {
        this.touristsCountNote = touristsCountNote;
    }

    @Basic
    @Column(name = "arrival_time", nullable = false)
    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "leave_time", nullable = false)
    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
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
    @Column(name = "tour_type", nullable = true, length = -1)
    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    @Basic
    @Column(name = "guide_driver", nullable = true, length = -1)
    public String getGuideDriver() {
        return guideDriver;
    }

    public void setGuideDriver(String guideDriver) {
        this.guideDriver = guideDriver;
    }

    @Basic
    @Column(name = "hotel_category", nullable = true, length = -1)
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
    @Column(name = "entrance_fees", nullable = true, length = -1)
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
    @Column(name = "comment", nullable = false, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "budget", nullable = false, precision = 0)
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
    @Column(name = "create_date", updatable = false, insertable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}