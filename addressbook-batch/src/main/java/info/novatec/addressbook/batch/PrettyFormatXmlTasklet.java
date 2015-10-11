package info.novatec.addressbook.batch;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

/**
 * Batch tasklet to pretty format xml.
 */
public class PrettyFormatXmlTasklet implements Tasklet {
	private Resource resource;
	
	@Override
	public RepeatStatus execute(final StepContribution contribution,
			final ChunkContext chunkContext) throws Exception {
		
		String xml = IOUtils.toString(resource.getInputStream(), "UTF-8");
		String formattedXml = format(xml);
		BufferedOutputStream outputStream = new BufferedOutputStream(
				new FileOutputStream(resource.getFile()));
		IOUtils.write(formattedXml, outputStream, "UTF-8");
		outputStream.close();
		
		contribution.setExitStatus(ExitStatus.COMPLETED);
		return RepeatStatus.FINISHED;
	}
	
	private String format(final String xml) {

		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(src).getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml
					.startsWith("<?xml"));

			final DOMImplementationRegistry registry = DOMImplementationRegistry
					.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry
					.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print",
					Boolean.TRUE); // Set this to true if the output needs to be
									// beautified.
			writer.getDomConfig().setParameter("xml-declaration",
					keepDeclaration); // Set this to true if the declaration is
										// needed to be outputted.

			return writer.writeToString(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets the {@link Resource} to for xml file to pretty format xml.
	 * @param resource to set
	 */
	public void setResource(final Resource resource) {
		this.resource = resource;
	}
}
