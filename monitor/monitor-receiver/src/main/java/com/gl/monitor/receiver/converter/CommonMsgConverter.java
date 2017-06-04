package com.gl.monitor.receiver.converter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.gl.monitor.common.object.CommonMsg;

public class CommonMsgConverter implements HttpMessageConverter<CommonMsg> {
	private static final Logger log = LoggerFactory.getLogger(CommonMsgConverter.class);

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		if(!CommonMsg.class.isAssignableFrom(clazz)){
			return false;
		}
		Field bodyField;
		try {
			bodyField = clazz.getDeclaredField("body");
			if(bodyField == null){
				return false;
			}else{
				return true;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			log.error("", e);
			return false;
		}
		
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		
		return canRead(clazz, mediaType);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return null;
	}

	@Override
	public CommonMsg read(Class<? extends CommonMsg> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(CommonMsg t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

}
