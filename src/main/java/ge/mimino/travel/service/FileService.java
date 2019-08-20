package ge.mimino.travel.service;


//import org.apache.commons.codec.binary.Base64;

import ge.mimino.travel.dao.RequestDAO;
import ge.mimino.travel.dto.GeoObjectDTO;
import ge.mimino.travel.model.Request;
import ge.mimino.travel.request.ProductRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ucha
 */
@Service
public class FileService {

  Logger logger = Logger.getLogger(FileService.class);

  @Autowired
  private RequestDAO requestDAO;

  @Autowired
  private RequestService requestService;


  private String rootDir;


  public byte[] readFile(String identifier) {
    try {
      return fileTOBytesArray(getFilePath(identifier));
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  private byte[] fileTOBytesArray(String fileName) throws FileNotFoundException, IOException {
    if (fileName != null) {
      File f = new File(fileName);
      if (f.exists()) {
        byte[] fileAsBytes = IOUtils.toByteArray(new FileInputStream(new File(fileName)));
        return fileAsBytes;
      }
    }
    return new byte[0];
  }

  private String getFilePath(String identifier) {
    try {
      File f = new File(rootDir + identifier.split("/")[0]);
      if (f.isDirectory()) {
        File[] files = f.listFiles();
        for (File _f : files) {
          String a = FilenameUtils.removeExtension(_f.getName());
          if (a.equals(identifier.split("/")[1])) {
            return _f.getPath();
          }
        }
      }
      return null;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void deleteFile(String identifier) {
    File f = new File(rootDir + "/" + identifier);
    if (f.exists()) {
      try {
        f.delete();
      } catch (Exception ex) {

      }
    }
  }

  public String getImageBase64(String fileName) {
    String imageString = null;
    try {
      File f = new File(rootDir + "/" + fileName);
      FileInputStream fis = new FileInputStream(f);
      byte byteArray[] = new byte[(int) f.length()];
      fis.read(byteArray);
      imageString = Base64.getEncoder().encodeToString(byteArray);
    } catch (Exception ex) {
      logger.fatal(ex.getMessage());
    }
    return imageString;
  }

  public String addFile(MultipartFile file, String directory, String objectId) throws IOException {
    String[] fileParts = file.getOriginalFilename().split("\\.");
    String fileExtention = fileParts.length > 1 ? fileParts[fileParts.length - 1] : "";
//        String fileName = "" + UUID.randomUUID() + (fileExtention.length() > 0 ? ("." + fileExtention) : "");
    String fileName = fileParts[0] + (fileExtention.length() > 0 ? ("." + fileExtention) : "");
    File f = new File(rootDir + directory + "/" + objectId + "_" + fileName);
    try {
      file.transferTo(f);
    } catch (Exception ex) {
      logger.fatal(ex.getMessage());
    }
    return fileName;
  }

  public String getFileFullPath(String name) {
    return rootDir + "/" + name;
  }

  public String getRootDir() {
    return rootDir;
  }

  public void setRootDir(String rootDir) {
    this.rootDir = rootDir;
  }

  Dimension getImageDimension(File imgFile) throws IOException {
    BufferedImage img = ImageIO.read(imgFile);
    return new Dimension(img.getWidth(), img.getHeight());
  }

  BigInteger addListStyle(XWPFDocument doc) {
    try {

      XWPFNumbering numbering = doc.createNumbering();
      // generate numbering style from XML
      InputStream in = new ClassPathResource("poiResources/numbering.xml").getInputStream(); //new FileInputStream("C:\\Users\\ucha\\IdeaProjects\\poi\\src\\main\\resources\\numbering.xml");
      CTAbstractNum abstractNum = CTAbstractNum.Factory.parse(in);
      XWPFAbstractNum abs = new XWPFAbstractNum(abstractNum, numbering);
      // find available id in document
      BigInteger id = BigInteger.valueOf(1);
      boolean found = false;
      while (!found) {
        Object o = numbering.getAbstractNum(id);
        found = (o == null);
        if (!found)
          id = id.add(BigInteger.ONE);
      }
      // assign id
      abs.getAbstractNum().setAbstractNumId(id);
      // add to numbering, should get back same id
      id = numbering.addAbstractNum(abs);
      // add to num list, result is numid
      return doc.getNumbering().addNum(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void generateWord(Integer requestId) {
    try {
      logger.info("Generating Of Word Start");

      Map<Integer, ProductRequest> dataMap = new HashMap<>();

      Request request = (Request) requestDAO.find(Request.class, requestId);

      logger.info("Retrieving Data From DB for Word");

      int[] days = new int[request.getDaysCount()];
      String[] dayText = new String[request.getDaysCount()];
      for (int i = 1; i <= request.getDaysCount(); i++) {
        dayText[i - 1] = "Day " + i;
        days[i - 1] = i;
        dataMap.put(i-1, requestService.getProductDetailsById(request.getId(), i));
      }

      logger.info("Have Got Data From DB, Rows Count By Days Is: " + dataMap.size() + " Starting Doc Generation ");

      //Blank Document
      XWPFDocument document = new XWPFDocument();

      CTBody body = document.getDocument().getBody();
      if (!body.isSetSectPr()) {
        body.addNewSectPr();
      }

      CTSectPr section = body.getSectPr();
      if (!section.isSetPgSz()) {
        section.addNewPgSz();
      }

      CTPageSz pageSize = section.getPgSz();
      pageSize.setOrient(STPageOrientation.LANDSCAPE);
//A4 = 595x842 / multiply 20 since BigInteger represents 1/20 Point
      pageSize.setW(BigInteger.valueOf(16840));
      pageSize.setH(BigInteger.valueOf(11900));

      // მარჯინების დასმა
//    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
//    CTPageMar pageMar = sectPr.addNewPgMar();
//    pageMar.setLeft(BigInteger.valueOf(10L));
//    pageMar.setTop(BigInteger.valueOf(10L));
//    pageMar.setRight(BigInteger.valueOf(10L));
//    pageMar.setBottom(BigInteger.valueOf(10L));

      //Write the Document in file system
//        File resource = new ClassPathResource("poiResources/requestDoc.docx").getFile();

      FileOutputStream out = new FileOutputStream(new File(rootDir + "/requestDoc.docx"));
//      FileOutputStream out = new FileOutputStream(new File("C:\\Users\\ucha.chaduneli\\Desktop\\requestDoc.docx"));

      //create Paragraph
      XWPFParagraph paragraph = document.createParagraph();
      XWPFParagraph headerParagraph = document.createParagraph();
      XWPFRun run;

      File preImage = new ClassPathResource("poiResources/background.png").getFile();//შესავლის img
      File lineImage = new ClassPathResource("poiResources/line.png").getFile();
      File bluLineImage = new ClassPathResource("poiResources/bluline.png").getFile();
      File mailIcon = new ClassPathResource("poiResources/mail.png").getFile();
      File phoneIcon = new ClassPathResource("poiResources/phone.png").getFile();
      File siteLinkIcon = new ClassPathResource("poiResources/siteLink.png").getFile();
      File footerImage = new ClassPathResource("poiResources/footer.png").getFile();//ბოლო img

      // create header-footer
      XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
      if (headerFooterPolicy == null) headerFooterPolicy = document.createHeaderFooterPolicy();

      // create header start
      XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

      headerParagraph = header.createParagraph();
      headerParagraph.setAlignment(ParagraphAlignment.LEFT);
      run = headerParagraph.createRun();
      run.setBold(true);
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setColor("1b256c");
      run.setText("MIMINO TRAVEL GEORGIA");
      headerParagraph = header.createParagraph();
      InputStream in = new FileInputStream(lineImage);
      headerParagraph.createRun().addPicture(in, Document.PICTURE_TYPE_PNG, "line.png",
              Units.toEMU(40), Units.toEMU(5));
      in.close();


      Dimension dim = getImageDimension(preImage);
      double width = dim.getWidth();
      double height = dim.getHeight();

      double scaling = 1.0;
      if (width > 50 * 7) scaling = (50 * 7) / width; //scale width not to be greater than 6 inches
      if (height > 40 * 7) scaling = (40 * 7) / height;
      in = new FileInputStream(preImage);
      paragraph.setAlignment(ParagraphAlignment.CENTER);
      paragraph.createRun().addPicture(in, Document.PICTURE_TYPE_PNG, "background.png",
              Units.toEMU(width * scaling), Units.toEMU(height * scaling));
      in.close();

//    File imgFile = new File("C:\\Users\\ucha\\IdeaProjects\\poi\\src\\main\\resources\\background.png");
//    Dimension dim = getImageDimension(imgFile);
//    double width = dim.getWidth();
//    double height = dim.getHeight();
//
//    double scaling = 1.0;
//    if (width > 82 * 10.3) scaling = (82 * 10.3) / width; //scale width not to be greater than 6 inches
//    InputStream in = new FileInputStream(imgFile);
//    paragraph.setAlignment(ParagraphAlignment.BOTH);
//    paragraph.createRun().addPicture(in, Document.PICTURE_TYPE_PNG, "background.png",
//            Units.toEMU(width * scaling), Units.toEMU(height * scaling));
//    in.close();

      // create footer start
      XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
      XWPFParagraph footeParagraph = footer.createParagraph();
      footeParagraph.setAlignment(ParagraphAlignment.CENTER);
//        footeParagraph.setBorderTop(Borders.THICK);
      in = new FileInputStream(lineImage);
      footeParagraph.createRun().addPicture(in, Document.PICTURE_TYPE_PNG, "line.png",
              Units.toEMU(700), Units.toEMU(5));
      in.close();
      footeParagraph = footer.createParagraph();
      footeParagraph.setAlignment(ParagraphAlignment.CENTER);
      run = footeParagraph.createRun();
      in = new FileInputStream(phoneIcon);
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "mail.png",
              Units.toEMU(10), Units.toEMU(10));
      in.close();
      run.setText("  598 46-56-17         ");
      in = new FileInputStream(mailIcon);
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "mail.png",
              Units.toEMU(12), Units.toEMU(10));
      in.close();
      run.setText("  info@miminotravel.com          ");
      in = new FileInputStream(siteLinkIcon);
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "siteLink.png",
              Units.toEMU(10), Units.toEMU(10));
      in.close();
      run.setText("  www.miminotravel.com  ");

//      *************************************   ყოველი დღის ინფორმაციის ჩაწერა *****************************************

      for (int i = 0; i < days.length; i++) {
        paragraph = document.createParagraph();
        //Day Indexes
        run = paragraph.createRun();
        run.setColor("ff0000");
        run.setFontFamily("Arial");
        run.setBold(true);
        run.setFontSize(17);
        run.setText(dayText[i] + ": ");
        // Sight Labels
        run = paragraph.createRun();
        run.setBold(true);
        run.setFontSize(17);
        run.setColor("1b256c");
        if(dataMap.get(i) != null) {
          for (GeoObjectDTO sight : dataMap.get(i).getSights()) {
            //იმ დღის ტური დასახელება Tbilisi city tour მაგალითად, სავარაუდოდ ინფუთი მოუწევს
            run.setText(sight.getNameEn()+" - ");
          }
          for (GeoObjectDTO sight : dataMap.get(i).getSights()) {
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.setFontSize(14);
            run.setFontFamily("Arial");
            run.setColor("0d0d0d");
            run.setText(sight.getDescriptionEn());
          }
        }
      }

      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.CENTER);
      run = paragraph.createRun();
      run.setFontSize(24);
      run.setFontFamily("Arial");
      run.setText("END OF SERVICE");
      in = new FileInputStream(lineImage);
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.CENTER);
      run = paragraph.createRun();
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "line.png",
              Units.toEMU(40), Units.toEMU(5));
      in.close();

      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.setFontSize(21);
      run.setFontFamily("Arial");
      run.setColor("FF0000");
      run.setBold(true);
      run.setText("Land Cost");
      in = new FileInputStream(bluLineImage);
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "blueline.png",
              Units.toEMU(40), Units.toEMU(5));
      in.close();

      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setBold(true);
      run.setText("TOUR PACKAGE PRICE");
      run = paragraph.createRun();
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setBold(true);
      run.setColor("1b256c");
      run.setText(" PER PERSON ");
      run = paragraph.createRun();
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setBold(true);
      run.setColor("000000");
      run.setText(" BASED ON ");
      run = paragraph.createRun();
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setBold(true);
      run.setColor("1b256c");
      run.setText(" DOUBLE ROOM ");
      run = paragraph.createRun();
      run.setFontSize(10);
      run.setFontFamily("Arial");
      run.setBold(true);
      run.setColor("000000");
      run.setText(" OCCUPANCY IN EURO ");

      XWPFTable table;
      XWPFTableRow tableRow;
      XWPFTableCell cell;

      //************************** Pax Table ******************************************
      table = document.createTable();
      tableRow = table.createRow();

      cell = tableRow.createCell();
      cell.setColor("D9D9D9");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setText("PAX");

      cell = tableRow.createCell();
      cell.setColor("D9D9D9");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setText("Editable ");

      cell = tableRow.createCell();
      cell.setColor("D9D9D9");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setText("Editable ");

      // აქედან დინამიური როუები მოუწევს ია ტაკ დუმაიუ პაქსების ამბავი გაარკვიე

      tableRow = table.createRow();

      cell = tableRow.createCell();
      cell.setColor("FF0000");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();

      cell = tableRow.createCell();
      cell.setColor("FF0000");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setText("30+1");

      cell = tableRow.createCell();
      cell.setColor("FF0000");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setText("");

      table.getCTTbl().getTblPr().unsetTblBorders();
      //******************************* End Of Pax Table ************************************

      //************************** Price Includes Table ******************************************
      table = document.createTable();
      tableRow = table.createRow();
      table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(50000));
//        CTTblWidth wdth = table.getCTTbl().addNewTblPr().addNewTblW();
//        wdth.setType(STTblWidth.DXA);
//        wdth.setW(BigInteger.valueOf(9072));

      //პირველი სვეტი, დინამიური იქნება პარაგრაფები, რა სერვისებსაც მოიცავს პაკეტი იმის მიხედვით

      cell = tableRow.createCell();
      cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(25000));
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Private Airport transfers");
      // ეს პარაგრაფები დინამიური იქნება ამ cell -ში
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Transportation according to the tour  program");

      //მეორე სვეტი სტატიკური ინფოა არ იცვლება
      cell = tableRow.createCell();
      cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(25000));
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Flight tickets");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Insurance");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Tips and other personal expenses");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Alcoholic beverages");
      paragraph = cell.addParagraph();
      run = paragraph.createRun();
      run.setText("Entrance fees not mentioned in the program");

      table.getCTTbl().getTblPr().unsetTblBorders();// ეს ბორდერს ხსნის თეიბლს
      //******************************* End Of Price Includes Table ************************************

      //*****************************************  სასტუმროების ჩამონათვალი
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.setFontSize(21);
      run.setFontFamily("Arial");
      run.setColor("FF0000");
      run.setBold(true);
      run.setText("Hotels");
      in = new FileInputStream(bluLineImage);
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "blueline.png",
              Units.toEMU(40), Units.toEMU(5));
      in.close();

      paragraph = document.createParagraph();
      paragraph.setVerticalAlignment(TextAlignment.CENTER);
      paragraph.setNumID(addListStyle(document));
      run = paragraph.createRun();
      run.setText("Tbilisi - ");
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setBold(true);

      run = paragraph.createRun();
      run.setText("Hotel Laerton 4* ");
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setBold(true);
      run.setColor("1b256c");

      run = paragraph.createRun();
      run.setText("(www.laerton-hotel.ge) or the same category ");
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.addBreak();
      run.addBreak();
      run.addBreak();
      run.addBreak();


      //***************************************  Further information შაბლონური ტექსტია ყველა ენაზე ინახება
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.setFontSize(18);
      run.setFontFamily("Arial");
      run.setColor("FF0000");
      run.setBold(true);
      run.setText("Further information : ");
      in = new FileInputStream(bluLineImage);
      paragraph = document.createParagraph();
      paragraph.setAlignment(ParagraphAlignment.LEFT);
      run = paragraph.createRun();
      run.addPicture(in, Document.PICTURE_TYPE_PNG, "blueline.png",
              Units.toEMU(40), Units.toEMU(5));
      in.close();
      paragraph = document.createParagraph();
      run = paragraph.createRun();
      run.setBold(true);
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setColor("1b256c");
      run.setText("Please note the following useful data: ");

      paragraph = document.createParagraph();
      run = paragraph.createRun();
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setText("-Sun and rain protection: Sunscreen, hat, raincoat and wind jacket, comfortable shoes for some sightseeing points");

      paragraph = document.createParagraph();
      run = paragraph.createRun();
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setText("-The ladies will need the headscarf while entering the Georgian Orthodox Churches");

      paragraph = document.createParagraph();
      run = paragraph.createRun();
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setText("-In some churches, the ladies will need the skirt. However, you can find them at the entrance of the church");

      paragraph = document.createParagraph();
      run = paragraph.createRun();
      run.setFontFamily("Arial");
      run.setFontSize(14);
      run.setText("-The short trousers are not allowed to enter the churches");


      paragraph = document.createParagraph();
      dim = getImageDimension(preImage);
      width = dim.getWidth();
      height = dim.getHeight();

      scaling = 1.0;
      if (width > 72 * 7) scaling = (72 * 7) / width; //scale width not to be greater than 6 inches
      if (height > 62 * 7) scaling = (62 * 7) / height;
      in = new FileInputStream(footerImage);
      paragraph.setAlignment(ParagraphAlignment.CENTER);
      paragraph.createRun().addPicture(in, Document.PICTURE_TYPE_PNG, "footerImg.png",
              Units.toEMU(width * scaling), Units.toEMU(height * scaling));
      in.close();

      document.write(out);

      //Close document
      out.close();
      logger.info("Doc Generated successfully");

//      if (Desktop.isDesktopSupported()) {
//        Desktop.getDesktop().open(new File("C:\\Users\\ucha.chaduneli\\Desktop\\requestDoc.docx"));
//      }
    } catch (Exception e) {
      logger.fatal("During Doc Generation FAILED " + e.getMessage());
      e.printStackTrace();
    }
  }
}
