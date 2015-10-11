package info.novatec.addressbook.jsr352;

import info.novatec.addressbook.entity.Person;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample JSR 352 item writer.
 */
public class JSR352ItemWriter implements ItemWriter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JSR352ItemWriter.class);
	
	private JSR352Repository jsr352Repository; 

	@Override
	public void open(final Serializable checkpoint) throws Exception {
		LOGGER.info("open({})", checkpoint);
	}

	@Override
	public void close() throws Exception {
		LOGGER.info("close()");
	}

	@Override
	public void writeItems(final List<Object> items) throws Exception {
		LOGGER.info("writeItems({})", items.size());
		for (Object item : items) {
			this.jsr352Repository.save((Person) item);
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		LOGGER.info("checkpointInfo()");
		return null;
	}

	/**
	 * Sets the {@link JSR352Repository}.
	 * @param jsr352Repository to set
	 */
	public void setJsr352Repository(final JSR352Repository jsr352Repository) {
		this.jsr352Repository = jsr352Repository;
	}
}
