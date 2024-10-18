package test.api.gps.services;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

@Service
public class CepService {
    public CepResponse buscarCep(String cep) {
        String logradouro = "";
        String tipoLogradouro = "";
        String resultado = null;
        CepResponse response = new CepResponse();

        try {
            // URL do serviço de CEP
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            // Parser XML
            SAXReader xml = new SAXReader();
            Document documento = xml.read(conn.getInputStream());
            Element root = documento.getRootElement();

            // Iteração sobre os elementos do XML
            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();
                switch (element.getQualifiedName()) {
                    case "cidade" -> response.setCidade(element.getText());
                    case "bairro" -> response.setBairro(element.getText());
                    case "uf" -> response.setUf(element.getText());
                    case "tipo_logradouro" -> tipoLogradouro = element.getText();
                    case "logradouro" -> logradouro = element.getText();
                    case "resultado" -> {
                        resultado = element.getText();
                        response.setResultado(resultado);
                    }
                }
            }

            // Combina tipo de logradouro e logradouro
            response.setLogradouro(tipoLogradouro + " " + logradouro);

            // Verifica o resultado da busca
            if (!"1".equals(resultado)) {
                throw new Exception("CEP não encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar o CEP: " + e.getMessage());
        }

        return response;
    }
}
