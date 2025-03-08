package com.example.RelatorioPDF.controller;

import com.example.RelatorioPDF.model.Ocorrencia;
import com.example.RelatorioPDF.resources.GerarPdf;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ControllerOcorrencia {

    @GetMapping
    public String getHome(){
        return "home";
    }


    @PostMapping
    public ResponseEntity<byte[]> getRelatorio(HttpServletRequest request, @RequestParam("descricaoFinal") String descricaoFinal, @RequestParam("arquivo") MultipartFile[] imagens,
                                               @RequestParam("nomeCliente") String nomeCliente, @RequestParam("solicitante") String Solicitante,
                                               @RequestParam("tipoContato") String tipoContato, @RequestParam("kmInicial") String kmInicial,
                                               @RequestParam("kmFinal") String kmFinal, @RequestParam("horaInicial")String horaInicial,
                                               @RequestParam("horaFinal") String horaFinal, @RequestParam("dataHoraSolicitacao") String horaSolicitacao ){
        List<Image> lista ;
        if(imagens.length >0){
            for (int i = 0; i < imagens.length; i++) {
                if(imagens[i].getContentType() != null){
                    System.out.println("tipo de imagem: "+imagens[i].getContentType());
                    System.out.println("resp de imagem: "+!Objects.equals(imagens[i].getContentType(), "image/jpeg")+!Objects.equals(imagens[i].getContentType(),"image/png")
                            +!Objects.equals(imagens[i].getContentType(),"image/GIF"));
                    if(!Objects.equals(imagens[i].getContentType(), "image/jpeg") && !Objects.equals(imagens[i].getContentType(),"image/png") &&
                            !Objects.equals(imagens[i].getContentType(),"image/GIF") ){
                        ResponseEntity<byte[]> tResponseEntity = new ResponseEntity<>(("<h2 >Aceito somento arquivos de Imagens !<h2>").getBytes(), new HttpHeaders(), HttpStatus.OK);
                        return tResponseEntity;
                    }
                }
            }
        }
        lista = convertMultipartfileToImage(imagens);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime horaOcorrencia = LocalDateTime.parse(horaSolicitacao, formatter);
        LocalDateTime finalHora = LocalDateTime.parse(horaFinal,formatter);
        LocalDateTime inicialHora = LocalDateTime.parse(horaInicial,formatter);
        Ocorrencia ocorrencia = new Ocorrencia(nomeCliente,Solicitante,tipoContato,horaOcorrencia,descricaoFinal,inicialHora,finalHora,Long.valueOf(kmInicial),Long.valueOf(kmFinal));


        return convertPDFtoResponse(lista,ocorrencia);
    }


    public ResponseEntity<byte[]> convertPDFtoResponse(List<Image> lista,Ocorrencia ocorrencia){
        try {

            byte[] contents = new GerarPdf().getPDF(ocorrencia, lista).toByteArray();
            HttpHeaders header =new HttpHeaders();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String filename="";
            header.setContentType(MediaType.parseMediaType("application/pdf"));
            header.setContentDispositionFormData(filename,filename);
            header.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(contents,header, HttpStatus.OK);
        } catch (DocumentException e ) {
            throw new RuntimeException(e);
        }

    }

    public List<Image> convertMultipartfileToImage(MultipartFile[] files){
        List<Image> lista = new ArrayList<>();
        for(int i=0;i< files.length;i++){
            if(!files[i].isEmpty()){
                try {
                    Image img = Image.getInstance(files[i].getBytes());
                    lista.add(img);
                } catch (BadElementException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return lista;
    }

}
