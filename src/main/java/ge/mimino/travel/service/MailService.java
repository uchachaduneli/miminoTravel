package ge.mimino.travel.service;


import ge.mimino.travel.dao.MailDAO;
import ge.mimino.travel.dto.EmailDTO;
import ge.mimino.travel.model.Email;
import ge.mimino.travel.model.Users;
import ge.mimino.travel.request.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class MailService {

    @Autowired
    private MailDAO mailDAO;


    public List<EmailDTO> getEmails(int start, int limit, MailRequest srchRequest) throws ParseException {
        return EmailDTO.parseToList(mailDAO.getEMails(start, limit, srchRequest));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Email save(MailRequest request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Email obj = new Email();
        obj.setFrom(request.getFrom());
        obj.setTo(request.getTo());
        obj.setSubject(request.getSubject());
        obj.setContent(request.getContent());
        obj.setAttachments(request.getAttachments());
        obj.setSendDate(new Timestamp(sdf.parse(request.getSendDate()).getTime()));
        obj.setReceiveDate(new Timestamp(sdf.parse(request.getReceiveDate()).getTime()));
        obj.setUser((Users) mailDAO.find(Users.class, request.getUserId()));
//
        if (request.getId() != null) {
            obj.setId(request.getId());
            obj = (Email) mailDAO.update(obj);
        } else {
            obj = (Email) mailDAO.create(obj);
        }

        return obj;
    }
}