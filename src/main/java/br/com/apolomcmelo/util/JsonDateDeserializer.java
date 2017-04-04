package br.com.apolomcmelo.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Classe utilizada para deserializar java.util.Date
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String date = jsonparser.getText();
		try {
			return format.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}