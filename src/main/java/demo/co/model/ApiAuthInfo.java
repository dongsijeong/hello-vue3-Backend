package demo.co.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import springfox.documentation.service.ApiInfo;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "auth")
public class ApiAuthInfo {
    @XmlElement(name = "restapi")
    private List<ApiInfo> apiList;
}
