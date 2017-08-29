/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pavan.apche.camel.scattergather;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ScatterGatherTest extends ContextTestSupport {

	@Test
    public void testScatterAndGather() throws Exception {
        MockEndpoint result = getMockEndpoint("mock:result");
        result.expectedMessageCount(1);
        // START SNIPPET: e1
        result.expectedBodiesReceived(1); // expect the lowest quote
        // END SNIPPET: e1

        // START SNIPPET: e2
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("listOfVendors", "bean:vendor1, bean:vendor2, bean:vendor3");
        headers.put("quoteRequestId", "quoteRequest-1");
        template.sendBodyAndHeaders("direct:start", "<quote_request item=\"beer\"/>", headers);
        // END SNIPPET: e2
        
        result.assertIsSatisfied();
    }
    
    protected CamelContext createCamelContext() throws Exception {
        return createSpringCamelContext(this, "org/apache/camel/spring/processor/scattergather/scatter-gather.xml");
//        CamelContext context = super.createCamelContext();
//        context.addRoutes(new RouteBuilder() {
//            public void configure() {
//                from("direct:start").to("file://Workset_MyExamples/ApacheCamel/src/test/resources/NewFile.xml");
//            }
//        });
//        context.start();
//        return context;
    }

	private CamelContext createSpringCamelContext(
			ScatterGatherTest scatterGatherTest, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}