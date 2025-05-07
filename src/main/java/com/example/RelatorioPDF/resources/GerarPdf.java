package com.example.RelatorioPDF.resources;


import java.awt.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.RelatorioPDF.model.Ocorrencia;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GerarPdf {

    ArrayList<com.lowagie.text.Image> listaImagens = new ArrayList<>();


    Font fontTitleTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);

    Font fontTitlePage = FontFactory.getFont(FontFactory.TIMES, 25, 0);
    Font fontePadrao = FontFactory.getFont("C:\\Windows\\Fonts\\Calibri.ttf", 11);

    Font fontColumnTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 11);
    Document documento = new Document();

    public ByteArrayOutputStream getPDF(Ocorrencia ocorrencia, List<Image> listaImg) throws DocumentException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(documento, out);
        writer.setFullCompression();
        writer.setLinearPageMode();

        documento.setPageSize(PageSize.A4);
        documento.setMargins(16, 16, 16, 10);
        HeaderFooter header = new HeaderFooter(new Phrase("www.grupogoldensat.com.br\nRua Haiti,129 - Parque das Nações - Santo André\nCEP:09280-390"), true);
        header.setBorder(Rectangle.NO_BORDER);
        header.setAlignment(Element.ALIGN_CENTER);
        documento.setFooter(header);
        documento.open();
        documento.add(texto("\n\n\nRelatório de Atendimento", fontTitlePage, true));
        documento.add(new Paragraph(" "));
        fontePadrao.setColor(Color.gray);
        //documento.add(texto("Protocolo: " + ocorrencia.getProtocolo() + "\n\n\n", fontePadrao, true));
        fontePadrao.setColor(Color.black);
        PdfPTable tabela = new PdfPTable(4);
        PdfPTable tabelaAtendimento = new PdfPTable(4);
        float[] colunas = {4, 6, 5, 7};
        tabela.setWidths(colunas);
        tabelaAtendimento.setWidths(colunas);
        tabela.setWidthPercentage(90);
        tabelaAtendimento.setWidthPercentage(70);
        String nConsta = "Não Consta";
        String cavalo = nConsta;
        String modelo= nConsta;
        String carreta = nConsta;
        String rastreador = nConsta;
        String rastreadorUltimaposicao = nConsta;
        String isca = nConsta;
        String iscaUltimaPosicao = nConsta;

       /* if(ocorrencia.getVeiculo() != null){
            Veiculo veiculo = ocorrencia.getVeiculo();

            cavalo = veiculo.getCavalo().isEmpty() ? nConsta : veiculo.getCavalo();

            modelo = contruirModelo(veiculo,nConsta);

            if(!veiculo.getCarreta().isEmpty()){
                carreta = veiculo.getCarreta();
            }
            if(veiculo.getIdRastreador() != 0){
               rastreador = "Golden Sat";
            }
            if(!veiculo.getEnderecoUltimaPosicao().isEmpty()){
                rastreadorUltimaposicao = veiculo.getEnderecoUltimaPosicao();
            }
        }*/


       /* if (!ocorrencia.getIscas().isEmpty()) {
            isca = ocorrencia.getIscas().get(0).getIdIsca().toString();
            iscaUltimaPosicao = ocorrencia.getIscas().get(0).getEnderecoUltimaComunicacao();
        }*/
        createCell(tabela,"Cliente", fontColumnTitle, false);
        createCell(tabela,ocorrencia.getCliente(), fontePadrao, false);
        createCell(tabela,"Ocorrencia", fontColumnTitle, false);
        createCell(tabela,"Pronta Resposta", fontePadrao, false);

        createCell(tabela,"Solicitante", fontColumnTitle, true);
        createCell(tabela,ocorrencia.getSolicitante(), fontePadrao, true);
        createCell(tabela,"Tipo de Contato", fontColumnTitle, true);
        createCell(tabela,ocorrencia.getTipoContato(), fontePadrao, true);

        createCell(tabela,"Data/Hora", fontColumnTitle, false);
        createCell(tabela,date.format(ocorrencia.getHoraOcorrencia()), fontePadrao, false);
        createCell(tabela,"Operador", fontColumnTitle, false);
        createCell(tabela,"Rafael", fontePadrao, false);

       // List<String> listaServico = ocorrencia.getServicoEfetuado().stream().map(n -> n.getServico()).toList();
      //  listaServico = listaServico.stream().distinct().toList();

        createCell(tabela,"Tipo", fontColumnTitle, true);
        createCell(tabela,"Preservação", fontePadrao, true);
        createCell(tabela,"Qtd de Agente", fontColumnTitle, true);
        createCell(tabela,"1", fontePadrao, true);

        createTitle(tabela, "Dados do Veiculo", false);
        createCell(tabela,"Placa:", fontColumnTitle, true);
        createCell(tabela,nConsta, fontePadrao, true);
        createCell(tabela,"Modelo/Cor/Ano", fontColumnTitle, true);
        createCell(tabela,nConsta, fontePadrao, true);

        createCell(tabela,"Carreta:", fontColumnTitle, false);
        createCell(tabela,nConsta, fontePadrao, false);
        createCell(tabela,nConsta, fontColumnTitle, false);
        createCell(tabela,nConsta, fontePadrao, false);

        createCell(tabela,"Rastreador:", fontColumnTitle, true);
        createCell(tabela,nConsta, fontePadrao, true);
        createCell(tabela,"Última posição", fontColumnTitle, true);
        createCell(tabela,nConsta, fontePadrao, true);

        createCell(tabela,"Isca:", fontColumnTitle, false);
        createCell(tabela,nConsta, fontePadrao, false);
        createCell(tabela,"Última posição", fontColumnTitle, false);
        createCell(tabela,nConsta, fontePadrao, false);

        createTitle(tabela, "Detalhes da Ocorrência / Detalhes Adicionais", true);

        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(ocorrencia.getDescrição(), fontePadrao)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(Color.gray);
        cell.setPaddingLeft(5);
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        cell.setBorder(Rectangle.BOX);
        cell.setColspan(4);
        tabela.addCell(cell);
        tabela.setSpacingAfter(20);



            createTitle(tabelaAtendimento, "Detalhes do Atendimento", true);

            createCell(tabelaAtendimento,"Serviço:", fontColumnTitle, false);
            createCell(tabelaAtendimento,"Inicial", fontColumnTitle, false);
            createCell(tabelaAtendimento,"Final", fontColumnTitle, false);
            createCell(tabelaAtendimento,"Quantidade", fontColumnTitle, false);



                    createCell(tabelaAtendimento,"KM Rodado:", fontColumnTitle, false);
                    createCell(tabelaAtendimento, String.valueOf(ocorrencia.getKmInicial()), fontePadrao, false);
                    createCell(tabelaAtendimento,String.valueOf(ocorrencia.getKmFinal()), fontePadrao, false);
                    double kmTotal = ocorrencia.getKmFinal() - ocorrencia.getKmInicial();
                    createCell(tabelaAtendimento,String.valueOf(kmTotal), fontePadrao, false);

                    createCell(tabelaAtendimento,"Hora Trabalhada:", fontColumnTitle, false);
                    createCell(tabelaAtendimento,date.format(ocorrencia.getHoraInicial()), fontePadrao, false);
                    createCell(tabelaAtendimento,date.format(ocorrencia.getHoraFinal()), fontePadrao, false);
                    Duration totalTime = Duration.between(ocorrencia.getHoraInicial(), ocorrencia.getHoraFinal());
                    createCell(tabelaAtendimento,getHours(totalTime), fontePadrao, false);






        documento.add(tabela);

        documento.add(tabelaAtendimento);

        documento.newPage();

        for (Image image : listaImg) {
                image.scaleAbsolute((documento.getPageSize().width() / 2)-15,(documento.getPageSize().height() / 4)+30);
                listaImagens.add(image);
        }
        addImgPdf();
        documento.close();
        return out;
    }

    public void addImgPdf() throws DocumentException{
        int countImg=0;
        int incrementadorImg=1;
        int qdtImgColuna=2;
        int margem=10;
        int totalImg = listaImagens.size();
        float posicaoY =0;
        float posicaoX =0;
        float widthImg= (documento.getPageSize().width()-(margem*3))/2;
        float heightImg=(documento.getPageSize().height()-100)/3;
        boolean valorWhile=true;
        while(valorWhile){
            for(int i=countImg;i<totalImg;i++){
                if(!listaImagens.isEmpty()){
                    if(i==countImg || i%4==0){
                        documento.newPage();
                        incrementadorImg=1;
                        qdtImgColuna=2;
                    }
                    widthImg= (documento.getPageSize().width()- (margem*3))/2;
                    heightImg= (documento.getPageSize().height()-60 - (margem*2))/2;
                    if(totalImg%4==1 && i==totalImg-1){
                        widthImg=documento.getPageSize().width()- 20;
                        heightImg=documento.getPageSize().height()-70;
                    }
                    if(i%qdtImgColuna ==0){
                        posicaoX=margem;
                        posicaoY= documento.getPageSize().height() - ((heightImg+margem)*incrementadorImg);
                    }else{
                        posicaoX=  widthImg+margem+margem;
                        posicaoY= documento.getPageSize().height() - ((heightImg+margem)*incrementadorImg);
                        incrementadorImg++;
                    }
                    listaImagens.get(i).scaleAbsolute(widthImg,heightImg);
                    listaImagens.get(i).setAbsolutePosition(posicaoX, posicaoY);
                    documento.add(listaImagens.get(i));
                    valorWhile=false;
                }
            }
            totalImg=listaImagens.size();
            countImg=0;
            incrementadorImg=1;
        }
    }

    public Paragraph texto(String texto, Font fonte, boolean resp) {
        Paragraph str = new Paragraph();
        str.add(new Chunk(texto, fonte));

        if (resp) {
            str.setAlignment(Element.ALIGN_CENTER);
        }
        return str;
    }

    public void createCell(PdfPTable tabela,String str, Font fonte, boolean color) {
        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(str, fonte)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(Color.gray);
        cell.setPaddingLeft(5);
        if (color) cell.setBackgroundColor(Color.decode("#f2f2f2"));
        cell.setBorder(Rectangle.BOX);
        tabela.addCell(cell);
    }

    public void createTitle(PdfPTable table, String str, boolean value) {
        PdfPCell cell = new PdfPCell(new Phrase(new Chunk(str, fontTitleTable)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(Color.gray);
        cell.setPaddingLeft(5);
        if (value) cell.setBackgroundColor(Color.decode("#f2f2f2"));
        cell.setBorder(Rectangle.BOX);

        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    public String getHours(Duration totalTime) {
        int hora = (int) totalTime.toMinutes() / 60;
        int minuto = (int) totalTime.toMinutes() % 60;
        if (hora > 23) {
            if (minuto < 10) {
                return hora + ":0" + minuto;
            }
            return hora + ":" + minuto;
        }
        return LocalTime.of(hora, minuto).toString();
    }

   /* public String contruirModelo(Veiculo veiculo, String valorPadrao){
        List<String> partesModelo = new ArrayList<>();

        if(!veiculo.getModelo().isEmpty()){
            partesModelo.add(veiculo.getModelo());
        }
        if(!veiculo.getCor().isEmpty()){
            partesModelo.add((veiculo.getCor()));
        }
        if(veiculo.getAno() != 0){
           partesModelo.add(String.valueOf(veiculo.getAno()));
        }
        return partesModelo.isEmpty() ? valorPadrao :String.join("/",partesModelo);
    }*/

}
