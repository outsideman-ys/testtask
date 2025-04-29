package ru.smirnov.testtask.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.smirnov.testtask.util.QuickSelect;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@Service
public class MinNumberService {

    public int findNthMin(String filePath, int n) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }

        if (numbers.size() < n) throw new IllegalArgumentException("Not enough elements");

        int[] arr = numbers.stream().mapToInt(i -> i).toArray();
        return QuickSelect.findNthSmallest(arr, n);
    }
}