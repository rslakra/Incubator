/******************************************************************************
 * Copyright (C) Devamatre Inc 2015
 *
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code or the snippet
 * is not permitted without prior express written consent of Devamatre.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 *
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 *
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.jdksuite.apache;

import com.rslakra.appsuite.core.IOUtils;
import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class TikaMain {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TikaMain.class);
    
    public static String detectDocTypeUsingFacade(InputStream stream) throws IOException {
        Tika tika = new Tika();
        String mediaType = tika.detect(stream);
        return mediaType;
    }
    
    /**
     * @param stream
     * @return
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    public static String extractContentUsingParser(InputStream stream) throws IOException, TikaException, SAXException {
        
        Parser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        
        parser.parse(stream, handler, metadata, context);
        return handler.toString();
    }
    
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            String filePath = IOUtils.pathString(IOUtils.getUserDir(), "JdkSuite/src/main/resources/logback.xml");
            LOGGER.debug("filePath={}", filePath);
            inputStream = IOUtils.newFileInputStream(filePath);
            LOGGER.debug("inputStream={}", inputStream);
            
            Parser parser = new AutoDetectParser();
            ContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();
            
            //  Detects the type of document read from an InputStream:
            Detector detector = new DefaultDetector();
            MediaType mediaType = detector.detect(inputStream, metadata);
            LOGGER.debug("metadata={}", metadata);
            LOGGER.debug("{}={}", Metadata.CONTENT_TYPE, metadata.get(Metadata.CONTENT_TYPE));
            LOGGER.debug("mediaType={}", mediaType);
            
            // Parsing the string
            parser.parse(inputStream, handler, metadata, context);
            LOGGER.debug("handler={}", handler);
            
        } catch (Exception ex) {
            LOGGER.error("Error={}", ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            IOUtils.closeSilently(inputStream);
        }
    }
}

