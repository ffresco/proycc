/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.utils.cvs;

import com.proycc.base.domain.dto.TextFileDTO;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
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
public class CvsUtils {

    //The one asked by BCRA
    private static final CsvPreference OPCAM_DELIMITED = new CsvPreference.Builder('"', ';', "\r\n").build();
    /**
     * Sets up the processors used for the examples. There are 10 CSV columns,
     * so 10 processors are defined. All values are converted to Strings before
     * writing (there's no need to convert them), and null values will be
     * written as empty columns (no need to convert them to "").
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
     * An example of writing using CsvBeanWriter.
     */
    public static void writeWithCsvBeanWriter() throws Exception {

        // create the customer beans
        List<TextFileDTO> aux = new ArrayList();
        aux.add(new TextFileDTO("pepe", "333333", "dni", 11.f));
        aux.add(new TextFileDTO("hhhh","343434", "CUIT", 44.8f));
        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter("txt/opcam3.txt"),
                    OPCAM_DELIMITED);

            // the header elements are used to map the bean values to each column (names must match) so name are equals to the properties names
            final String[] header = new String[]{"nombre", "documento", "dni", "monto"};
            final CellProcessor[] processors = getProcessors();

            // write the header
            //beanWriter.writeHeader(header);

            // write the beans
            for (final TextFileDTO customer : aux) {
                beanWriter.write(customer, header, processors);
            }

        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }
}