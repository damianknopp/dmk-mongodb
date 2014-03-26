package dmk.mongodb.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.annotation.Id;

/**
 * @author dmknopp
 */
public class SysEvent {

	@Id
	private String id;
	private String eventType;
	private String sysEvent;
	private boolean deleted;

	// empty constructor required by marshaller?
	public SysEvent() {
		super();
	}

	public SysEvent(String eventType, String sysEvent) {
		super();
		this.eventType = eventType;
		this.sysEvent = sysEvent;
	}

	public String getSysEvent() {
		return sysEvent;
	}

	public SysEvent setSysEvent(String sysEvent) {
		this.sysEvent = sysEvent;
		return this;
	}

	public String getEventType() {
		return eventType;
	}

	public SysEvent setEventType(String eventType) {
		this.eventType = eventType;
		return this;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(sysEvent).append(eventType)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public String toJson() {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(out, this);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getCause());
		}
		final byte[] data = out.toByteArray();
		return new String(data);
	}

}