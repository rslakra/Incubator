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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class TikaMainTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TikaMainTest.class);
    
    @Test
    public void whenUsingDetector_thenDocumentTypeIsReturned() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("tika.txt");
        String mediaType = TikaMain.detectDocTypeUsingFacade(stream);
        LOGGER.debug("mediaType={}", mediaType);
        
        Assert.assertEquals("application/pdf", mediaType);
        
        stream.close();
    }
    
}