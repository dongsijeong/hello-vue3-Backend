package demo.mo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AccesInfo {

    private boolean allAccesCtl;

    private boolean noCheckCtl;

    private List<String> controlidList = new ArrayList<>();
}
