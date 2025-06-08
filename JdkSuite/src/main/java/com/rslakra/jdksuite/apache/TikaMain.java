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

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import com.devamatre.core.CoreUtility;

public class TikaMain {

	public static void main(String args[]) throws Exception {

		FileInputStream is = null;
		try {
			String filePath = CoreUtility.pathString(CoreUtility.getUserDir(), "lib/dLogger-v1.0.0.jar");
			File f = new File(filePath);
			is = new FileInputStream(f);

			ContentHandler contenthandler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
			Parser parser = new AutoDetectParser();
			// OOXMLParser parser = new OOXMLParser();
			parser.parse(is, contenthandler, metadata, null);
			// parser.parse(is, contenthandler, metadata);
			System.out.println("Mime: " + metadata.get(Metadata.CONTENT_TYPE));
			System.out.println("Title: " + metadata.get(Metadata.TITLE));
			System.out.println("Author: " + metadata.get(Metadata.AUTHOR));
			System.out.println("content: " + contenthandler.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
	}
}

