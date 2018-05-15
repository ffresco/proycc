/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * MIKUS
 */
package com.proycc.base.controller;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.dto.OperacionReportDTO;
import com.proycc.base.domain.dto.TextFileDTO;
import com.proycc.base.utils.cvs.CvsUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleReportExportConfiguration;
import net.sf.jasperreports.export.SimpleTextExporterConfiguration;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.WriterExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @rodrti hjhh
 */
@Controller
public class AuditoriaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriaController.class);

    @RequestMapping("/auditoria")
    public ModelAndView getMainPage() {
        return new ModelAndView("auditoria");
    }

    @RequestMapping("/auditoria/create")
    public ModelAndView getCreatePage() {
        return new ModelAndView("auditoria_create");
    }

    @RequestMapping(value = "/getFile")
    void getFile(HttpServletResponse response) throws IOException {

        System.out.println("Headr " + response);
        System.out.println("Print header " + response.getHeader("X-Frame-Options"));
        System.out.println(response.getHeaderNames());
        String fileName = "opcam.txt";
        String path = "txt/" + fileName;

        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        response.setContentType("application/txt");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");

        FileCopyUtils.copy(inputStream, response.getOutputStream());

    }

    @RequestMapping(path = "/opcamtxt", method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response)  {
        LOGGER.info("--Le pegue al print --Generando TXT-");
        /*
        InputStream reporte = getClass().getResourceAsStream("/opcam.jasper");

        //meto en memoria la operacion
        List<TextFileDTO> aux = new ArrayList<>();
        
        LOGGER.debug("La operacion recuperada es ");
        aux.add(new TextFileDTO("pepe", "333333", "dni", 11.f));
        aux.add(new TextFileDTO("hhhh","343434", "CUIT", 44.8f));

        try {
            //Pido el reporte ya compilado (si tuviera uno y quiero recuperarlo
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reporte);

            // fills compiled report with parameters and a connection
            System.out.println("Empezando a llenar el reporte");

            //O puedo usar una coleccion en memoria
            JRBeanCollectionDataSource dsMemo = new JRBeanCollectionDataSource(aux);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dsMemo);

            System.out.println("el jasper print " + jasperPrint);
     
            // exports report to pdf      
            System.out.println("empezandoa a exportar");
            System.out.println("este es el expoerter ");
            
            //desde aca
            JRCsvExporter exporter = new JRCsvExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            System.out.println("Tratando de abrir el output");

            exporter.setExporterOutput(new SimpleWriterExporterOutput("txt/opcam2.txt"));
            System.out.println("");
            System.out.println("Pude abrir el output");
            
            SimpleCsvExporterConfiguration exportConfig
                    = new SimpleCsvExporterConfiguration();
            SimpleCsvReportConfiguration reportConfig = new SimpleCsvReportConfiguration();
          

            
            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);
            System.out.println("por generar el export");
            exporter.exportReport();
            */
        try{  
        CvsUtils.writeWithCsvBeanWriter();

        } catch (Exception e) {
            System.out.println("salio por el catch hubo exceptcion " + e);
        }
        // compiles jrxml
    }

    }
