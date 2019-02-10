package ge.mimino.travel.service;


import ge.mimino.travel.dao.GuideDAO;
import ge.mimino.travel.dto.GuideDTO;
import ge.mimino.travel.model.Guide;
import ge.mimino.travel.model.GuidePrices;
import ge.mimino.travel.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class GuideService {

    @Autowired
    private GuideDAO guideDAO;


    public List<Guide> getGuides(int start, int limit, Guide srchRequest) {
        return guideDAO.getGuides(start, limit, srchRequest);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Guide save(GuideDTO request) throws Exception {

        Guide obj = request.getId() != null ? ((Guide) guideDAO.find(Guide.class, request.getId())) : new Guide();
        obj.setName(request.getName());
        obj.setTrackingPrice(request.getTrackingPrice());
        obj.setType(request.getType());
        obj.setLanguage((Language) guideDAO.find(Language.class, request.getLanguageId()));

        if (request.getId() != null) {
            guideDAO.removeGuidePrices(obj.getId());
            for (GuidePrices pg : request.getPrices()) {
                pg.setGuide(obj);
                pg.setId(null);
                guideDAO.create(pg);
            }
            obj = (Guide) guideDAO.update(obj);
        } else {
            obj = (Guide) guideDAO.create(obj);
            for (GuidePrices pg : request.getPrices()) {
                pg.setGuide(obj);
                pg.setId(null);
                guideDAO.create(pg);
            }
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Guide obj = (Guide) guideDAO.find(Guide.class, id);
        if (obj != null) {
            guideDAO.delete(obj);
        }
    }

}