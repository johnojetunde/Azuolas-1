package com.teamseven.service.keywordprocessor;

import com.google.common.base.Strings;
import com.teamapt.fileutils.excel.ExcelUtils;
import com.teamseven.model.Applicant;
import com.teamseven.utils.DateHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ApplicationExcelProcessor {

    private enum ApplicantsFileHeaders {

        FIRSTNAME("FIRSTNAME"),
        LASTNAME("LASTNAME"),
        EMAIl("EMAIl"),
        STACKS("STACKS"),
        YEARS_OF_EXPERIENCE("YEARS_OF_EXPERIENCE"),
        DATE_OF_BIRTH("DATE_OF_BIRTH"),
        PHONE_NUMBER("PHONE_NUMBER");

        private String value;

        public static String[] getHeaderValues() {
            ApplicantsFileHeaders[] values = values();
            String[] result = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                result[i] = values[i].value;
            }
            return result;
        }

        public String getValue() {
            return value;
        }

        ApplicantsFileHeaders(String value) {
            this.value = value;
        }
    }

    public String generateReport(List<Applicant> applicants) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        CellStyle headerStyle = ExcelUtils.getBasicHeaderStyle(workbook);
        Sheet applicantSheet = workbook.createSheet("Digital Explorers Applicants");
        int rowIndex = 0;
        String[] sheetHeaders = ApplicantsFileHeaders.getHeaderValues();


        Cell currentCell;
        Row currentRow = applicantSheet.createRow(rowIndex++);

        for (int i = 0, l = sheetHeaders.length; i < l; i++) {
            currentCell = currentRow.createCell(i);
            currentCell.setCellValue(sheetHeaders[i]);
            currentCell.setCellStyle(headerStyle);
        }

        if (applicants.iterator().hasNext()) {
            int cellIndex;

            for (Applicant profile : applicants) {
                cellIndex = 0;
                currentRow = applicantSheet.createRow(rowIndex);

                //First Name
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(Strings.nullToEmpty(profile.getFirstName()));
                //Lastname
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(Strings.nullToEmpty(profile.getLastName()));
                //Email
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(Strings.nullToEmpty(profile.getEmail()));
                //stack
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(Strings.nullToEmpty(profile.getStack()));
                //years of experience
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(profile.getYearsOfExperience() == null ? "" : String.valueOf(profile.getYearsOfExperience()));
                //date of birth
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(profile.getDateOfBirth() == null ? "" : DateHelper.dateToFriendlyString(profile.getDateOfBirth()));
                //phone number
                currentCell = currentRow.createCell(cellIndex++);
                currentCell.setCellValue(Strings.nullToEmpty(profile.getPhoneNumber()));


                ++rowIndex;


            }
        }
        return ExcelUtils.createReportDataString(workbook);
    }
}