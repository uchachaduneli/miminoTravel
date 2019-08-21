package ge.mimino.travel.service;


import ge.mimino.travel.controller.TmpHotelGroup;
import ge.mimino.travel.dao.ParamValuePair;
import ge.mimino.travel.dao.RequestDAO;
import ge.mimino.travel.dto.*;
import ge.mimino.travel.model.Currency;
import ge.mimino.travel.model.*;
import ge.mimino.travel.request.AddRequest;
import ge.mimino.travel.request.ProductRequest;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ucha
 */
@Service
public class RequestService {

    Logger logger = Logger.getLogger(RequestService.class);

    @Autowired
    private RequestDAO requestDAO;

    @Autowired
    private MailService mailService;


    public List<RequestDTO> getRequests(int start, int limit, AddRequest srchRequest) throws Exception {
        return RequestDTO.parseToList(requestDAO.getRequests(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Request save(AddRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Request obj = request.getId() != null ? (Request) requestDAO.find(Request.class, request.getId()) : new Request();

        obj.setContactEmail(request.getContactEmail());
        obj.setCombined(request.getCombined());
        obj.setTourStart(request.getTourStart());
        obj.setStrTourStart(request.getStrTourStart());
        obj.setTourEnd(request.getTourEnd());
        obj.setStrTourEnd(request.getStrTourEnd());
        obj.setDaysCount(request.getDaysCount());
        obj.setNightsCount(request.getNightsCount());
        try {
            obj.setArrivalTime(new Timestamp(sdf.parse(request.getArrivalTime()).getTime()));//request.getArrivalTime()
        } catch (Exception e) {
        }
        try {
            obj.setLeaveTime(new Timestamp(sdf.parse(request.getLeaveTime()).getTime()));//request.getLeaveTime()
        } catch (Exception e) {
        }
        obj.setStrArrivalTime(request.getStrArrivalTime());
        obj.setStrLeaveTime(request.getStrLeaveTime());
        obj.setTourType(request.getTourType());
        obj.setGuideDriver(request.getGuideDriver());
        obj.setHotelCategory(request.getHotelCategory());
        obj.setEntranceFees(request.getEntranceFees());
        obj.setComment(request.getComment());
        obj.setBudget(request.getBudget());
        obj.setTourCode(request.getTourCode());
        obj.setNationality(request.getNationality());
        obj.setEur(request.getEur());
        obj.setUsd(request.getUsd());
//
        if (request.getCurrencyId() != null) {
            obj.setCurrency((Currency) requestDAO.find(Currency.class, request.getCurrencyId()));
        }
        if (request.getArrivalCityId() != null) {
            obj.setArrivalCity((City) requestDAO.find(City.class, request.getArrivalCityId()));
        }
        if (request.getLeaveCityId() != null) {
            obj.setLeaveCity((City) requestDAO.find(City.class, request.getLeaveCityId()));
        }
        if (request.getMealCategoryId() != null) {
            obj.setMealCategory((MealCategory) requestDAO.find(MealCategory.class, request.getMealCategoryId()));
        }
        if (request.getPackageCategoryId() != null) {
            obj.setPackageCategory((PackageCategory) requestDAO.find(PackageCategory.class, request.getPackageCategoryId()));
        }
        if (request.getGuideLanguageId() != null) {
            obj.setGuideLanguage((Language) requestDAO.find(Language.class, request.getGuideLanguageId()));
        }
        if (request.getLanguageGroupId() != null) {
            obj.setLanguageGroup((Language) requestDAO.find(Language.class, request.getLanguageGroupId()));
        }

        obj.setUser((Users) requestDAO.find(Users.class, request.getUserId()));
//


        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Request) requestDAO.update(obj);
        } else {
            obj.setRequestKey(UUID.randomUUID() + "");
            obj.setStage((Stage) requestDAO.find(Stage.class, 2));
            obj = (Request) requestDAO.create(obj);
        }

        requestDAO.removeRequestCountries(obj.getId());
        if (request.getCombinedCountries() != null && !request.getCombinedCountries().isEmpty()) {
            for (CombinedCountry combCntr : request.getCombinedCountries()) {
                requestDAO.create(new RequestCountry(obj.getId(), ((Country) requestDAO.find(Country.class, combCntr.getCountryId())),
                        combCntr.getDaysCount(), combCntr.getNote()));
            }
        }

        requestDAO.removeTouristCounts(obj.getId());
        String tmpCount = "";
        if (request.getTouristCount() != null && !request.getTouristCount().isEmpty()) {
            for (TouristCount tc : request.getTouristCount()) {
                tmpCount += tc.getCount() + ",";
                requestDAO.create(new TouristCount(obj.getId(), tc.getCount(), tc.getPlusCount(), tc.getPlusCountStr()));
            }
            Calendar cal = Calendar.getInstance();
            obj.setTourCode(cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "-" + tmpCount);
            requestDAO.update(obj);
        }

        /* Generate Transports for This Request Tourist Counts*/
        defineTransportForRequest(obj.getId(), true);

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateRequestStage(Integer stageId, Integer requestId, Integer userId) throws Exception {
        Stage stage = (Stage) requestDAO.find(Stage.class, stageId);
        Request obj = (Request) requestDAO.find(Request.class, requestId);
        obj.setStage(stage);
        requestDAO.update(obj);
        Users user = (Users) requestDAO.find(Users.class, userId);
        RequestStageHistory requestStageHistory = new RequestStageHistory(stage, requestId, user);
        requestDAO.create(requestStageHistory);
        new Thread(() -> {
            try {
                mailService.sendNotifUsingGmail(stageId, obj);
            } catch (MessagingException e) {
                logger.error("Can't Send Email Notifications");
            }
        }).start();

    }

    public List<Stage> getStages() throws Exception {
        return requestDAO.getAll(Stage.class);
    }

    public List<RequestStageHistoryDTO> getRequestStages(Integer requestId) throws Exception {

        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));

        List<ParamValuePair> order = new ArrayList<>();
        order.add(new ParamValuePair("id", "ASC"));

        return RequestStageHistoryDTO.parseToList(requestDAO.getAllByParamValue(RequestStageHistory.class, paramValues, order));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveMessage(RequestMessageDTO request) throws Exception {
        RequestMessage msg = new RequestMessage();
        msg.setRequestId(request.getRequestId());
        msg.setMessage(request.getMessage());
        msg.setUser((Users) requestDAO.find(Users.class, request.getUserId()));
        requestDAO.create(msg);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void saveProduct(ProductRequest request) throws Exception {

        if (request.getHotels() != null && !request.getHotels().isEmpty()) {
            requestDAO.removeProductHotels(request.getRequestId(), request.getDay());
            for (TmpHotelGroup obj : request.getHotels()) {
                requestDAO.create(new ProductHotels((Hotel) requestDAO.find(Hotel.class, obj.getHotelId()), request.getRequestId(), request.getDay(), obj.getGroupId()));
            }
        }

        if (request.getNonstandarts() != null && !request.getNonstandarts().isEmpty()) {
            requestDAO.removeProductNonstandarts(request.getRequestId(), request.getDay());
            for (Integer obj : request.getNonstandarts()) {
                requestDAO.create(new ProductNonstandarts((NonstandartService) requestDAO.find(NonstandartService.class, obj), request.getRequestId(), request.getDay()));
            }
        }

        if (request.getPlaces() != null && !request.getPlaces().isEmpty()) {
            requestDAO.removeProductPlaces(request.getRequestId(), request.getDay());
            for (Integer obj : request.getPlaces()) {
                requestDAO.create(new ProductPlaces((Place) requestDAO.find(Place.class, obj), request.getRequestId(), request.getDay()));
            }
        }

        if (request.getRegions() != null && !request.getRegions().isEmpty()) {
            requestDAO.removeProductRegions(request.getRequestId(), request.getDay());
            for (Integer obj : request.getRegions()) {
                requestDAO.create(new ProductRegions((Region) requestDAO.find(Region.class, obj), request.getRequestId(), request.getDay()));
            }
        }

        if (request.getSights() != null && !request.getSights().isEmpty()) {
            requestDAO.removeProductSights(request.getRequestId(), request.getSights(), request.getDay());
            for (GeoObjectDTO obj : request.getSights()) {
                requestDAO.create(new ProductSights((GeoObject) requestDAO.find(GeoObject.class, obj.getId()), request.getRequestId(), request.getDay(), obj.getPhotoOrVisit() == null ? 1 : obj.getPhotoOrVisit()));
            }
        }

        if (request.getRestaurants() != null && !request.getRestaurants().isEmpty()) {
            requestDAO.removeProductRestaurants(request.getRequestId(), request.getRestaurants(), request.getDay());
            for (ProductRestaurantsDTO obj : request.getRestaurants()) {
                requestDAO.create(new ProductRestaurants((Restaurant) requestDAO.find(Restaurant.class, obj.getRestaurantId()), request.getRequestId(), request.getDay(), obj.getMealCategories(), obj.getPackages()));
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Request obj = (Request) requestDAO.find(Request.class, id);
        if (obj != null) {
            requestDAO.delete(obj);
        }
    }

    public List<RequestCountryDTO> getRequestCountries(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", caseId));
        return RequestCountryDTO.parseToList(requestDAO.getAllByParamValue(RequestCountry.class, paramValues, null));
    }


    public List<TouristCountDTO> getTouristCounts(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", caseId));
        return TouristCountDTO.parseToList(requestDAO.getAllByParamValue(TouristCount.class, paramValues, null));
    }

    public List<RequestDetailsDTO> getRequestDetails(int caseId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", caseId));
        return RequestDetailsDTO.parseToList(requestDAO.getAllByParamValue(RequestDetails.class, paramValues, null));
    }

    public List<RequestMessageDTO> getRequestMessages(int id) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", id));
        return RequestMessageDTO.parseToList(requestDAO.getAllByParamValue(RequestMessage.class, paramValues, null));
    }

    public List<DetailsDTO> getDetails() {
        return DetailsDTO.parseToList(requestDAO.getAll(Details.class));
    }

    public List<TouristCountDTO> getTouristsCount(int requestId) {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));
        return TouristCountDTO.parseToList(requestDAO.getAllByParamValue(TouristCount.class, paramValues, null));
    }

    public Request getRequestByKey(String key) throws IndexOutOfBoundsException {
        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestKey", key));
        return (Request) requestDAO.getAllByParamValue(Request.class, paramValues, null).get(0);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updateTransportDays(Integer requestId, String checkedDays) {
//    String days = "";
//    for (String s : checkedDays) {
//      days += s + ",";
//    }
        requestDAO.updateTransportDays(requestId, checkedDays);
    }

    //    @Transactional(rollbackFor = Throwable.class)
    public void defineTransportForRequest(Integer requestId, boolean deleteOld) {

        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));

        List<Integer> touristCounts = new ArrayList<>();

        for (TouristCount tc : ((List<TouristCount>) requestDAO.getAllByParamValue(TouristCount.class, paramValues, null))) {
            touristCounts.add(tc.getCount());
        }

        if (deleteOld) {
            for (ProductTransports obj : (List<ProductTransports>) requestDAO.getAllByParamValue(ProductTransports.class, paramValues, null)) {
                requestDAO.delete(obj);
            }
        }

        List<ProductTransports> transportsForSave = new ArrayList<>();

        Request request = (Request) requestDAO.find(Request.class, requestId);
        for (Integer count : touristCounts) {
            if (count > 0) {
                if (request.getNationality() != null && RequestDTO.NAT_FOR_TRANSPORT.contains(request.getNationality())) {
                    if (count < 3) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.SEDAN), requestId, 1, count));
                    } else if (count > 2 && count < 5) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.VIANO), requestId, 1, count));
                    } else if (count > 4 && count < 13) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.SPRINTER), requestId, 1, count));
                    } else if (count > 12 && count < 49) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.BUS), requestId, 1, count));
                    } else {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.BUS), requestId, (int) count / 48, count));
                    }
                } else {
                    if (count < 3) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.SEDAN), requestId, 1, count));
                    } else if (count > 2 && count < 6) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.VIANO), requestId, 1, count));
                    } else if (count > 5 && count < 15) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.SPRINTER), requestId, 1, count));
                    } else if (count > 14 && count < 49) {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.BUS), requestId, 1, count));
                    } else {
                        transportsForSave.add(new ProductTransports((Transport) requestDAO.find(Transport.class, TransportDTO.BUS), requestId, (int) count / 48, count));
                    }
                }
            }
        }

        for (ProductTransports tr : transportsForSave) {
            requestDAO.create(tr);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public ProductRequest getProductDetailsById(Integer requestId, Integer day) {

        ProductRequest res = new ProductRequest();
        res.setHotels(new ArrayList<TmpHotelGroup>());
        res.setNonstandarts(new ArrayList<>());
        res.setPlaces(new ArrayList<>());
        res.setRegions(new ArrayList<>());
        res.setSights(new ArrayList<>());
        res.setTransports(new ArrayList<>());
        res.setTransportDays(new ArrayList<>());
        res.setRestaurants(new ArrayList<>());

        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));
        paramValues.add(new ParamValuePair("day", day));

        for (ProductHotels obj : (List<ProductHotels>) requestDAO.getAllByParamValue(ProductHotels.class, paramValues, null)) {
            res.getHotels().add(new TmpHotelGroup(obj.getHotel(), obj.getGroupId()));
        }

        for (ProductNonstandarts obj : (List<ProductNonstandarts>) requestDAO.getAllByParamValue(ProductNonstandarts.class, paramValues, null)) {
            res.getNonstandarts().add(obj.getNonstandartService().getId());
        }

        for (ProductPlaces obj : (List<ProductPlaces>) requestDAO.getAllByParamValue(ProductPlaces.class, paramValues, null)) {
            res.getPlaces().add(obj.getPlace().getId());
        }

        for (ProductSights obj : (List<ProductSights>) requestDAO.getAllByParamValue(ProductSights.class, paramValues, null)) {
            GeoObjectDTO objectDTO = GeoObjectDTO.parse(obj.getSight());
            obj.setPhotoOrVisit(obj.getPhotoOrVisit());
            res.getSights().add(objectDTO);
        }

        for (ProductRegions obj : (List<ProductRegions>) requestDAO.getAllByParamValue(ProductRegions.class, paramValues, null)) {
            res.getRegions().add(obj.getRegion().getId());
        }

        paramValues.clear();
        paramValues.add(new ParamValuePair("requestId", requestId));
        List<ProductTransports> transports = (List<ProductTransports>) requestDAO.getAllByParamValue(ProductTransports.class, paramValues, null);
        if (transports.isEmpty()) {
            defineTransportForRequest(requestId, false);
            transports = (List<ProductTransports>) requestDAO.getAllByParamValue(ProductTransports.class, paramValues, null);
        }
        res.setTransports(transports);
        if (!transports.isEmpty() && transports.get(0).getDays() != null) {
            List<String> tmp = Arrays.asList(transports.get(0).getDays().split(","));
//      tmp.removeAll(Arrays.asList("", null));
            res.setTransportDays(tmp);
        }

        res.setGuides(ProductGuidesDTO.parseToList((List<ProductGuides>) requestDAO.getAllByParamValue(ProductGuides.class, paramValues, null)));
        if (!res.getGuides().isEmpty() && res.getGuides().get(0).getDays() != null) {
            List<String> tmp = Arrays.asList(res.getGuides().get(0).getDays().split(","));
            res.setGuideDays(tmp);
        }

        paramValues.add(new ParamValuePair("day", day));
        res.setRestaurants(ProductRestaurantsDTO.parseToList((List<ProductRestaurants>) requestDAO.getAllByParamValue(ProductRestaurants.class, paramValues, null)));

        return res;
    }

    public ProductRequest getProductDetailsForFinancialById(Integer requestId, Integer day) {

        ProductRequest res = new ProductRequest();
        res.setHotels(new ArrayList<TmpHotelGroup>());
        res.setNonstandartsList(new ArrayList<>());
        res.setPlacesList(new ArrayList<>());
        res.setRegionsList(new ArrayList<>());
        res.setTransports(new ArrayList<>());
        res.setTransportDays(new ArrayList<>());
        res.setRestaurants(new ArrayList<>());
        res.setSightsList(new ArrayList<>());
        res.setGuides(new ArrayList<>());
        res.setGuideDays(new ArrayList<>());

        List<ParamValuePair> paramValues = new ArrayList<>();
        paramValues.add(new ParamValuePair("requestId", requestId));
        paramValues.add(new ParamValuePair("day", day));

        for (ProductHotels obj : (List<ProductHotels>) requestDAO.getAllByParamValue(ProductHotels.class, paramValues, null)) {
            res.getHotels().add(new TmpHotelGroup(obj.getHotel(), obj.getGroupId(), obj.getPrice()));
        }

        res.getNonstandartsList().addAll((List<ProductNonstandarts>) requestDAO.getAllByParamValue(ProductNonstandarts.class, paramValues, null));

        res.getPlacesList().addAll((List<ProductPlaces>) requestDAO.getAllByParamValue(ProductPlaces.class, paramValues, null));

        res.getSightsList().addAll((List<ProductSights>) requestDAO.getAllByParamValue(ProductSights.class, paramValues, null));

        res.getRegionsList().addAll((List<ProductRegions>) requestDAO.getAllByParamValue(ProductRegions.class, paramValues, null));

        res.setRestaurants(ProductRestaurantsDTO.parseToList((List<ProductRestaurants>) requestDAO.getAllByParamValue(ProductRestaurants.class, paramValues, null)));

        paramValues.clear();
        paramValues.add(new ParamValuePair("requestId", requestId));

        res.setGuides(ProductGuidesDTO.parseToList((List<ProductGuides>) requestDAO.getAllByParamValue(ProductGuides.class, paramValues, null)));
        if (!res.getGuides().isEmpty()) {
            List<String> tmp = Arrays.asList(res.getGuides().get(0).getDays().split(","));
            res.setGuideDays(tmp);
        }

        res.getTransports().addAll((List<ProductTransports>) requestDAO.getAllByParamValue(ProductTransports.class, paramValues, null));
        if (!res.getTransports().isEmpty() && res.getTransports().get(0).getDays() != null) {
            List<String> tmp = Arrays.asList(res.getTransports().get(0).getDays().split(","));
            res.setTransportDays(tmp);
        }
        return res;
    }

    public Map<String, String> getCurrency() {
        Document doc;
        Map<String, String> res = new HashMap<>();
        try {
            doc = Jsoup.connect("http://www.nbg.ge/rss.php").get();
            Element description = doc.getElementsByTag("description").get(1);
            Elements rows = Jsoup.parse(description.text()).getElementsByTag("tr");
            Element tr = rows.get((rows.size() - 3));
            res.put("USD", tr.getElementsByTag("td").get(2).text());
            tr = rows.get(13);
            res.put("EUR", tr.getElementsByTag("td").get(2).text());
        } catch (IOException e) {
            logger.fatal(e.getMessage());
        }
        return res;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(getCurrency().get("USD") + "    " + getCurrency().get("EUR"));
//    }

}