package com.github.leo_scream.java_se_course.unit_07.task_01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransferService {
    @XmlElement(name = "transfer")
    private final Set<Transfer> transfers;

    public TransferService() {
        transfers = new HashSet<>();
    }

    public TransferService fromXmlFile(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Transfer.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (TransferService) unmarshaller.unmarshal(xmlFile);
    }

    public void toXmlFile(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Transfer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(this, xmlFile);
    }
}
