package demo.mo.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ApiDetailInfo {

    private boolean allAccess;

    private Map<String, AccesInfo> accessMap = new HashMap<>();

}
