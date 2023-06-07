package dev.friday.com.dal.utils.scrapping;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Scrapper {
    public static Document getDocumentFromUri(final String uri) {
        try {
            return Jsoup.connect(uri).get();
        } catch (IOException e) {
            log.info("Error while getting document from uri: {}, message: {}", uri, e.getMessage());
            return null;
        }
    }

    public static List<Element> getElementFromDocument(final Document document, final String cssQuery) {
        if(document == null) {
            log.info("Document is null");
            return new ArrayList<>();
        }
        log.info("Getting element from document with cssQuery: {}", cssQuery);
        return document.select(cssQuery);
    }

}
