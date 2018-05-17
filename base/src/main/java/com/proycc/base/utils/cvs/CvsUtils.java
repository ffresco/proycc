/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.utils.cvs;

import com.proycc.base.domain.FileTextRegistry;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.dto.TextFileDTO;
import com.proycc.base.service.OperacionService;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author fafre
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CvsUtils {

    //The one asked by BCRA
    private static final CsvPreference OPCAM_DELIMITED = new CsvPreference.Builder('"', ';', "\r\n").build();

    /**
     * Sets up the processors used for the examples. There are 10 CSV columns,
     * so 10 processors are defined. All values are converted to Strings before
     * writing (there's no need to convert them), and null values will be
     * written as empty columnns (no need to convert them to "").
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors;
        processors = new CellProcessor[]{
            new NotNull(), // customerNo (must be unique)
            new NotNull(), // firstName
            new NotNull(), // lastName
            new NotNull(), // mailingAddress
        };

        return processors;
    }

    /**
     * retorna true si lo pudo generar, false si sale por excepcion
     */
    @Transactional(readOnly = true)
    public boolean writeWithCsvBeanWriter(OperacionService service, FileTextRegistry fileReg) {
        boolean generado = false;

        //Implemento un try catch con recursos para no tener que cerrar los archivos
        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter("txt/" + fileReg.getFileName()),
                OPCAM_DELIMITED);
                Stream<Operacion> todoStream = service.getStreamOpOpCam(fileReg);
                ) {

            // the header elements are used to map the bean values to each column (names must match) so name are equals to the properties names
            final String[] header = new String[]{"nombre", "documento", "dni", "monto"};
            final CellProcessor[] processors = getProcessors();

            //-->write the header lo saco porque por ahora el opcam va sin cabecera
            //beanWriter.writeHeader(header);
            
            //--Aca hago la lectura y escritura con el Stream abierto en el try
    	    todoStream.forEach(todo -> {			
                TextFileDTO rowFile = convertOpToFileTextDTO(todo);
                try {
                    beanWriter.write(rowFile, header, processors);
                } catch (IOException ex) {
                    Logger.getLogger(CvsUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
        	});
           //--Fin de la letura       
            
           /* Esto es el ejemplo como venia 
            // create the customer beans
            List<TextFileDTO> aux = new ArrayList();
            aux.add(new TextFileDTO("pepe", "333333", "dni", 11.f));
            aux.add(new TextFileDTO("hhhh", "343434", "CUIT", 44.8f));
            for (final TextFileDTO customer : aux) {
                    beanWriter.write(customer, header, processors);
                }
            // Fin del ejemplo de prueba como venia */    
                generado = true;

            } catch (IOException ex) {
                generado = false;
                fileReg.setObservaciones(ex.getMessage());
                System.out.println("Se cancelo la generacion del archivo " + ex);
            }
            return generado;
        }

    private static TextFileDTO convertOpToFileTextDTO(Operacion todo) {
      TextFileDTO fileDTO = new TextFileDTO(todo.getCliente().getNombre(), todo.getCliente().getDocumento()+"", "dni", 0);
      return fileDTO;
    }
    }
