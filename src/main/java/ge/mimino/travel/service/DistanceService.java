package ge.mimino.travel.service;


import ge.mimino.travel.dao.DistancesDAO;
import ge.mimino.travel.dto.DistancesDTO;
import ge.mimino.travel.model.Distances;
import ge.mimino.travel.model.Place;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author ucha
 */
@Service
public class DistanceService {
    Logger logger = Logger.getLogger(DistanceService.class);

  @Autowired
  private DistancesDAO distanceDAO;


  public List<DistancesDTO> getDistances(int start, int limit, DistancesDTO srchRequest) {
    return DistancesDTO.parseToList(distanceDAO.getDistances(start, limit, srchRequest));
  }


  public Map<String, Double> getDistancesByPlace(List<Integer> srchRequest) {
    return distanceDAO.getDistancesByPlace(srchRequest);
  }

  @Transactional(rollbackFor = Throwable.class)
  public Distances save(DistancesDTO request) throws Exception {

    Distances obj = request.getId() != null ? ((Distances) distanceDAO.find(Distances.class, request.getId())) : new Distances();
    obj.setDistance(request.getDistance());
    obj.setFromPlace((Place) distanceDAO.find(Place.class, request.getFromPlaceId()));
    obj.setToPlace((Place) distanceDAO.find(Place.class, request.getToPlaceId()));

    if (request.getId() != null) {
      obj.setId(request.getId());
      obj = (Distances) distanceDAO.update(obj);
    } else {
      obj = (Distances) distanceDAO.create(obj);
    }

    return obj;
  }

  @Transactional(rollbackFor = Throwable.class)
  public void delete(int id) {
    Distances obj = (Distances) distanceDAO.find(Distances.class, id);
    if (obj != null) {
      distanceDAO.delete(obj);
    }
  }
}