package demo.mo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "restapi")
@Data
public class ApiInfo {

    @XmlAttribute(name = "path")
    private String path;

    @XmlAttribute(name = "screenid")
    private String  screenid;

    @XmlAttribute(name = "controlid")
    private String  controlid;

}
