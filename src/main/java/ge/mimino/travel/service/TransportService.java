package ge.mimino.travel.service;


import ge.mimino.travel.dao.TransportDAO;
import ge.mimino.travel.dto.FuelDTO;
import ge.mimino.travel.dto.TransportDTO;
import ge.mimino.travel.model.Fuel;
import ge.mimino.travel.model.Transport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class TransportService {
    Logger logger = Logger.getLogger(TransportService.class);

    @Autowired
    private TransportDAO transportDAO;


    public List<TransportDTO> getTransports(int start, int limit, TransportDTO srchRequest) {
        return TransportDTO.parseToList(transportDAO.getTransports(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Transport save(TransportDTO request) throws Exception {

        Transport obj = request.getId() != null ? ((Transport) transportDAO.find(Transport.class, request.getId())) : new Transport();
        obj.setFuel((Fuel) transportDAO.find(Fuel.class, request.getFuelId()));
        obj.setNameEn(request.getNameEn());
        obj.setNameGe(request.getNameGe());
        obj.setNameFr(request.getNameFr());
        obj.setNameIt(request.getNameIt());
        obj.setNameSp(request.getNameSp());
        obj.setNamePo(request.getNamePo());
        obj.setNameRu(request.getNameRu());
        obj.setDescriptionEn(request.getDescriptionEn());
        obj.setDescriptionGe(request.getDescriptionGe());
        obj.setDescriptionFr(request.getDescriptionFr());
        obj.setDescriptionIt(request.getDescriptionIt());
        obj.setDescriptionSp(request.getDescriptionSp());
        obj.setDescriptionPo(request.getDescriptionPo());
        obj.setDescriptionRu(request.getDescriptionRu());
        obj.setFuelConsumption(request.getFuelConsumption());
        obj.setPrice(request.getPrice());
        obj.setSeatsCount(request.getSeatsCount());

        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Transport) transportDAO.update(obj);
        } else {
            obj = (Transport) transportDAO.create(obj);
        }

        return obj;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        Transport obj = (Transport) transportDAO.find(Transport.class, id);
        if (obj != null) {
            transportDAO.delete(obj);
        }
    }


    public List<FuelDTO> getFuels() {
        return FuelDTO.parseToList(transportDAO.getAll(Fuel.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Fuel saveFuel(FuelDTO request) throws Exception {

        Fuel obj = request.getId() != null ? ((Fuel) transportDAO.find(Fuel.class, request.getId())) : new Fuel();

        if (obj != null) {
            obj.setPrice(request.getPrice());
            obj = (Fuel) transportDAO.update(obj);
        }
        return obj;
    }
}