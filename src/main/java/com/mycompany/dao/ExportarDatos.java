/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.domainclasses.Publicacion;
import com.mycompany.domainclasses.Usuario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ExportarDatos {

    public static void generarReporteExcelPublicaciones(List<Publicacion> publicaciones) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
            String filePath = Paths.get(folderPath, "PublicacionesConReacciones.xlsx").toString();

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Publicaciones");

                // Encabezados de las columnas
                String[] encabezados = {"ID Publicación", "ID Usuario", "Título", "Descripción", "Fecha de Creación", "Total de Reacciones"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < encabezados.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(encabezados[i]);
                }

                // Agregar los datos de las publicaciones
                int rowIdx = 1;
                for (Publicacion publicacion : publicaciones) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(publicacion.getIdPublicacion());
                    row.createCell(1).setCellValue(publicacion.getIdUsuario());
                    row.createCell(2).setCellValue(publicacion.getTitulo());
                    row.createCell(3).setCellValue(publicacion.getDescripcion());
                    row.createCell(4).setCellValue(publicacion.getFecha_creacion().toString());
                    row.createCell(5).setCellValue(publicacion.getReacciones().size());
                }

                // Escribir el archivo Excel
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(null, "El reporte Excel se ha generado correctamente en: " + filePath);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó un directorio válido.");
        }
    }

    public static void generarReportePDFUsuarios(List<Usuario> usuarios, HashMap<Integer, Integer> publicacionesPorUsuario) {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = folderChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + "/UsuariosConPublicaciones.pdf";

            try {
                // Crear el documento PDF
                Document pdfDoc = new Document(PageSize.A4);
                PdfWriter.getInstance(pdfDoc, new FileOutputStream(filePath));

                pdfDoc.open();

                // Agregar un título al documento
                pdfDoc.add(new Paragraph("Reporte de Usuarios con Publicaciones"));
                pdfDoc.add(new Paragraph("Fecha: " + java.time.LocalDate.now().toString()));
                pdfDoc.add(new Paragraph("\n\n"));

                // Agregar datos de los usuarios al documento
                for (Usuario usuario : usuarios) {
                    int totalPublicaciones = publicacionesPorUsuario.getOrDefault(usuario.getIdUsuario(), 0);

                    pdfDoc.add(new Paragraph("Nombre: " + usuario.getNombre()));
                    pdfDoc.add(new Paragraph("Correo: " + usuario.getCorreo()));
                    pdfDoc.add(new Paragraph("Rol: " + usuario.getRol()));
                    pdfDoc.add(new Paragraph("Bloqueado: " + (usuario.isBloqueado() ? "Sí" : "No")));
                    pdfDoc.add(new Paragraph("Total de Publicaciones: " + totalPublicaciones));
                    pdfDoc.add(new Paragraph("\n"));
                }

                pdfDoc.close();
                JOptionPane.showMessageDialog(null, "El reporte PDF se ha generado correctamente en: " + filePath);

            } catch (IOException | DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó un directorio válido.");
        }
    }
}