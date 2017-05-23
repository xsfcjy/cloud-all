/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.sfxie.component.ui.tags.report.netty.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sfxie.component.ui.tags.report.netty.server.ReportWebSocketServer;
import com.sfxie.utils.StringUtils;
import com.sfxie.utils.jacson.codehaus.JsonUtil;

/**
 * Echoes uppercase content of text frames.
 */
public class ReportWebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private static final Logger logger = LoggerFactory.getLogger(ReportWebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // ping and pong frames already handled
    	ReportWebSocketServer.getChannelGroup().add(ctx.channel());
    	logger.info(ctx.channel().localAddress()+"连接成功");
        /*if (frame instanceof TextWebSocketFrame) {
        	ReportWebSocketEntity reportWebSocketEntity = new ReportWebSocketEntity();
            String reportName = ((TextWebSocketFrame) frame).text();
            if(StringUtils.isNotEmpty(reportName)){
            	String reportJrxml = getJrxmlString(reportName);
            	reportWebSocketEntity.setReportName(reportName);
            	reportWebSocketEntity.setReportJrxml(reportJrxml);
            	ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJSON(reportWebSocketEntity)));
            }
        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }*/
    }
    
    private String getJrxmlString(String reportName) throws IOException{
    	InputStream i = this.getClass().getResource(reportName).openStream();
		String dd = inputStream2String(i);
		return dd;
    }
    
    private String inputStream2String(InputStream is) throws IOException{
 	   BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
 	   StringBuffer buffer = new StringBuffer();
 	   String line = "";
 	   while ((line = in.readLine()) != null){
 	     buffer.append(line);
 	   }
 	   return buffer.toString();
 	}
    
//    public static void main(String[] args) throws IOException {
//    	ReportWebSocketFrameHandler hand= new ReportWebSocketFrameHandler();
//    	System.out.println(hand.getClass().getResource(""));
//    	URL  u = hand.getClass().getResource("certificates.jrxml");
//    	InputStream i = hand.getClass().getResource("certificates.jrxml").openStream();
//		String dd = hand.inputStream2String(i);
//	}
}
