package com.famacias.turno.publico.demo.utils;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.famacias.turno.publico.demo.domain.Comunas;

public final class MapperUtil {

    private static final String EXP_REG = "<option value='0' selected>Elija Comuna</option>";

    private MapperUtil() {
        // Default
    }

    public static Map<String, Comunas> parseRsToComunas(String rsBody) {
        Map<String, Comunas> comunas = new HashMap<>();
        rsBody = rsBody.substring(1, rsBody.length() - 1);
        rsBody = rsBody.replaceAll(EXP_REG, "");
        Document doc = Jsoup.parse(rsBody);
        org.jsoup.select.Elements comuna = doc.select("option");
        comuna.forEach(c -> {
            Comunas elemnt = new Comunas();
            elemnt.setId(c.val());
            elemnt.setNombre(c.text());
            comunas.put(c.text(), elemnt);
        });
        return comunas;
    }

}
